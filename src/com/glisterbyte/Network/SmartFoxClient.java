package com.glisterbyte.Network;

import com.glisterbyte.Network.SmartFoxClientException.ConnectionFailed;
import com.smartfoxserver.v2.entities.data.SFSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sfs2x.client.*;
import sfs2x.client.core.*;
import sfs2x.client.requests.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Predicate;

class SmartFoxClient {

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

        private final Predicate<ExtendedBaseEvent> predicate;
        private final CompletableFuture<SFSObject> future;

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

        public void complete(SFSObject result) {
            future.complete(result);
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

    private static final Logger logger = LoggerFactory.getLogger(SmartFoxClient.class);

    private final SmartFox server = new SmartFox();

    private final String userGameId;
    private final String serverAddress;
    private final SFSObject loginParams;

    private final Object extensionResponseLock = new Object();
    private final List<ExtensionResponseWaiter> extensionResponseWaiters = new ArrayList<>();
    private final List<ExtensionResponseListener> extensionResponseListeners = new ArrayList<>();

    public SmartFoxClient(String serverAddress, String userGameId, SFSObject loginParams) {

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

        synchronized (extensionResponseLock) {

            for (var listener : extensionResponseListeners) {
                if (event.getCmd().equals(listener.cmd)) {
                    listener.call(event.getParams());
                }
            }

            for (var waiter : extensionResponseWaiters) {
                if (waiter.matches(event)) {
                    match = waiter;
                    break;
                }
            }
            if (match != null) extensionResponseWaiters.remove(match);

        }

        if (match != null) match.complete(event.getParams());
        else logger.warn("Extension response did not match any waiters: {}", event);

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

    public CompletableFuture<SFSObject> waitForEvent(
            Predicate<ExtendedBaseEvent> match
    ) {
        synchronized (extensionResponseLock) {
            var responseWaiter = new ExtensionResponseWaiter(match);
            extensionResponseWaiters.add(responseWaiter);
            return responseWaiter.getFuture();
        }
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