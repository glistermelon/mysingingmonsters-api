package com.glisterbyte.Network;

import com.glisterbyte.Network.SmartFoxClientException.ConnectionFailed;
import com.glisterbyte.SfsMapping.SfsMapper;
import com.glisterbyte.SingingMonsters.SfsModels.Client.SfsExpectResponse;
import com.glisterbyte.SingingMonsters.SfsModels.Client.SfsRequestModel;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsResponseModel;
import com.glisterbyte.SingingMonsters.SfsModels.SfsChunked;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sfs2x.client.*;
import sfs2x.client.core.*;
import sfs2x.client.requests.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SfsClient {

//    private static class FakeSmartFox extends SmartFox {
//        public FakeSmartFox() {
//            super();
//            // TODO: might need to revive this spoofing to avoid getting sussed out
//            // super.majVersion = 1;
//            // super.minVersion = 0;
//            // super.subVersion = 4;
//            // super.clientDetails = "Java::";
//        }
//    }

    public static class ExtensionResponseWaiter {

        protected final Predicate<ExtendedBaseEvent> predicate;
        protected final CompletableFuture<SFSObject> future;

        public ExtensionResponseWaiter(Predicate<ExtendedBaseEvent> predicate) {
            this.predicate = predicate;
            future = new CompletableFuture<>();
        }

        public CompletableFuture<SFSObject> getFuture() {
            return future;
        }

        public boolean matches(ExtendedBaseEvent event) {
            return predicate.test(event);
        }

        public boolean complete(SFSObject result) {
            future.complete(result);
            return true;
        }

    }

    public static class ChunkedExtensionResponseWaiter extends ExtensionResponseWaiter {

        private final List<SFSObject> results = new ArrayList<>();
        private Integer numChunks = null;
        private final String chunkedKey;

        public ChunkedExtensionResponseWaiter(Predicate<ExtendedBaseEvent> predicate, String chunkedKey) {
            super(predicate);
            this.chunkedKey = chunkedKey;
        }

        @Override
        public boolean complete(SFSObject result) {
            results.add(result);
            if (numChunks == null && result.containsKey("numChunks")) {
                numChunks = result.getInt("numChunks");
            }
            if (numChunks == null || results.size() == numChunks) {
                SFSArray resultArray = new SFSArray();
                for (SFSObject storedResult : results) {
                    ISFSArray array = storedResult.getSFSArray(chunkedKey);
                    for (int i = 0; i < array.size(); i++) {
                        resultArray.add(array.get(i));
                    }
                }
                SFSObject resultObj = new SFSObject();
                resultObj.putSFSArray(chunkedKey, resultArray);
                future.complete(resultObj);
                return true;
            }
            else {
                return false;
            }
        }

    }

    public static class ExtensionResponseListener {

        public final String cmd;
        private final Consumer<SFSObject> callback;

        public ExtensionResponseListener(String cmd, Consumer<SFSObject> callback) {
            this.cmd = cmd;
            this.callback = callback;
        }

        public void call(SFSObject obj) {
            callback.accept(obj);
        }

    }

    public static class ExtendedBaseEvent {

        BaseEvent baseEvent;

        public ExtendedBaseEvent(BaseEvent baseEvent) {
            this.baseEvent = baseEvent;
        }

        public BaseEvent getBaseEvent() {
            return baseEvent;
        }

        public String getCmd() {
            return (String)baseEvent.getArguments().get("cmd");
        }

        public SFSObject getParams() {
            return (SFSObject)baseEvent.getArguments().get("params");
        }

        public String toString() {
            return "{ cmd: '" + getCmd() + "' }";
        }

    }

    private static final Logger logger = LoggerFactory.getLogger(SfsClient.class);

    private final SmartFox server = new SmartFox();

    private final String userGameId;
    private final String serverAddress;
    private final SFSObject loginParams;

    private final Object extensionResponseLock = new Object();
    private final List<ExtensionResponseWaiter> extensionResponseWaiters = new ArrayList<>();
    private final List<ExtensionResponseListener> extensionResponseListeners = new ArrayList<>();

    public SfsClient(String serverAddress, String userGameId, SFSObject loginParams) {

        this.userGameId = userGameId;
        this.serverAddress = serverAddress;
        this.loginParams = loginParams;

        server.addEventListener(SFSEvent.CONNECTION, this::onConnection);
        server.addEventListener(SFSEvent.CONNECTION_LOST, this::onConnectionLost);
        server.addEventListener(SFSEvent.LOGIN, this::onLogin);
        server.addEventListener(SFSEvent.LOGIN_ERROR, this::onLoginError);
        server.addEventListener(SFSEvent.EXTENSION_RESPONSE, this::onExtensionResponse);

    }

    public void connect() {
        server.connect(serverAddress, 9933);
    }

    public void disconnect() {
        server.disconnect();
    }

    private void onExtensionResponse(BaseEvent baseEvent) {

        var event = new ExtendedBaseEvent(baseEvent);

        ExtensionResponseWaiter match = null;
        boolean matched = false;

        synchronized (extensionResponseLock) {

            for (var listener : extensionResponseListeners) {
                if (event.getCmd().equals(listener.cmd)) {
                    listener.call(event.getParams());
                }
            }

            for (var waiter : extensionResponseWaiters) {
                if (waiter.matches(event)) {
                    matched = true;
                    if (waiter.complete(event.getParams())) {
                        match = waiter;
                    }
                    break;
                }
            }
            if (match != null) extensionResponseWaiters.remove(match);

        }

        if (!matched) logger.warn("Extension response did not match any waiters: {}", event);

    }

    private void cancelEventWaiter(ExtensionResponseWaiter waiter) {
        synchronized (extensionResponseLock) {
            extensionResponseWaiters.remove(waiter);
            waiter.getFuture().complete(null);
        }
    }

    public ExtensionResponseListener addEventListener(String cmd, Consumer<SFSObject> callback) {
        ExtensionResponseListener listener = new ExtensionResponseListener(cmd, callback);
        extensionResponseListeners.add(listener);
        return listener;
    }

    public void removeEventListener(ExtensionResponseListener listener) {
        extensionResponseListeners.remove(listener);
    }

    public CompletableFuture<SFSObject> requestResponse(
            String cmd, SFSObject params,
            Predicate<ExtendedBaseEvent> matchResponse
    ) {
        synchronized (extensionResponseLock) {
            server.send(new ExtensionRequest(cmd, params));
            return waitForEvent(matchResponse);
        }
    }

    public CompletableFuture<SFSObject> requestResponse(String cmd, SFSObject params) {
        return requestResponse(cmd, params, ev -> ev.getCmd().equals(cmd));
    }

    public CompletableFuture<SFSObject> requestResponse(String cmd) {
        return requestResponse(cmd, new SFSObject());
    }

    public CompletableFuture<List<SfsResponseModel>> requestResponses(SfsRequestModel request) {

        synchronized (extensionResponseLock) {

            server.send(new ExtensionRequest(
                    request.getClass().getAnnotation(SfsCmd.class).value(),
                    SfsMapper.mapToSFSObject(request)
            ));

            final List<ExtensionResponseWaiter> waiters = new ArrayList<>();
            final List<CompletableFuture<SfsResponseModel>> futures = new ArrayList<>();
            for (SfsExpectResponse expectedResponse
                    : request.getClass().getAnnotationsByType(SfsExpectResponse.class)) {
                ExtensionResponseWaiter waiter;
                if (!expectedResponse.value().isAnnotationPresent(SfsChunked.class)) {
                    waiter = waitForEventWithWaiter(ev -> {
                        String cmd = expectedResponse.value().getAnnotation(SfsCmd.class).value();
                        return ev.getCmd().equals(cmd);
                    });
                }
                else {
                    waiter = waitForChunkedEventWithWaiter(
                            expectedResponse.value().getAnnotation(SfsChunked.class).value(),
                            ev -> {
                        String cmd = expectedResponse.value().getAnnotation(SfsCmd.class).value();
                        return ev.getCmd().equals(cmd);
                    });
                }
                waiters.add(waiter);
                final int waiterIndex = waiters.indexOf(waiter);
                futures.add(waiter.getFuture().thenApply(params -> {
                    if (params == null) return null;
                    SfsResponseModel response = SfsMapper.mapSFSObjectToClass(expectedResponse.value(), params);
                    if (response.failed()) {
                        for (int i = waiterIndex + 1; i < waiters.size(); i++) {
                            cancelEventWaiter(waiters.get(i));
                        }
                    }
                    return response;
                }));
            }

            CompletableFuture<Void> allFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            return allFuture.thenApply(_ -> futures.stream().map(CompletableFuture::join).toList());

        }

    }

    private ExtensionResponseWaiter waitForEventWithWaiter(
            Predicate<ExtendedBaseEvent> match
    ) {
        synchronized (extensionResponseLock) {
            var responseWaiter = new ExtensionResponseWaiter(match);
            extensionResponseWaiters.add(responseWaiter);
            return responseWaiter;
        }
    }

    private ExtensionResponseWaiter waitForChunkedEventWithWaiter(
            String chunkedKey,
            Predicate<ExtendedBaseEvent> match
    ) {
        synchronized (extensionResponseLock) {
            var responseWaiter = new ChunkedExtensionResponseWaiter(match, chunkedKey);
            extensionResponseWaiters.add(responseWaiter);
            return responseWaiter;
        }
    }

    public CompletableFuture<SFSObject> waitForEvent(
            Predicate<ExtendedBaseEvent> match
    ) {
        return waitForEventWithWaiter(match).getFuture();
    }

    private void onConnection(BaseEvent event) {

        boolean success = (boolean)event.getArguments().get("success");

        if (!success) {
            logger.error("SmartFoxClient connection failed");
            throw new ConnectionFailed();
        }
        logger.info("SmartFoxClient connection succeeded");

        server.send(new LoginRequest(
                userGameId, "", "MySingingMonsters", loginParams
        ));

    }

    private void onConnectionLost(BaseEvent evt) {
        logger.warn("SmartFoxClient connection lost");
    }

    private void onLogin(BaseEvent evt) {
        logger.info("Logged in as <{}>", server.getMySelf().getName());
    }

    private void onLoginError(BaseEvent evt) {
        logger.error(
                "Login failed: (Code {}) {}",
                evt.getArguments().get("errorMessage"),
                evt.getArguments().get("errorCode")
        );
    }

}