package com.glisterbyte.Network;

import com.smartfoxserver.v2.entities.data.SFSObject;
import sfs2x.client.*;
import sfs2x.client.core.*;
import sfs2x.client.requests.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public class GameServer
{
    private static class FakeSmartFox extends SmartFox {
        public FakeSmartFox() {
            super();
            //super.majVersion = 1;
            //super.minVersion = 0;
            //super.subVersion = 4;
            //super.clientDetails = "Java::";
        }
    }

    enum ConnectionStatus {
        CONNECTION_PENDING,
        CONNECTION_SUCCESS,
        CONNECTION_LOGIN_SUCCESS,
        CONNECTION_LOGIN_FAILED,
        CONNECTION_ENDED
    }
    private final SmartFox server;
    private ConnectionStatus connectionStatus = ConnectionStatus.CONNECTION_PENDING;
    SFSObject loginInfo;
    Long bbbId;
    private SFSObject playerData = null;
    final ConcurrentMap<String, ConcurrentLinkedQueue<Object>> eventQueues = new ConcurrentHashMap<>();
    final ConcurrentMap<String, SFSObject> eventResponses = new ConcurrentHashMap<>();
    final ConcurrentMap<String, Object> eventLocks = new ConcurrentHashMap<>();
    final ConcurrentMap<String, Object> waitingForResponses = new ConcurrentHashMap<>();
    Long playerDataLastUpdated = 0L;
    public ConnectionStatus getConnectionStatus() { return connectionStatus; }
    public long getBBBId() { return bbbId; }
    public SFSObject getPlayerData() { return playerData; }
    public GameServer(String serverAddress, SFSObject loginInfo) {
        this.loginInfo = loginInfo;
        server = new FakeSmartFox();
        server.addEventListener(SFSEvent.CONNECTION, this::onConnection);
        server.addEventListener(SFSEvent.CONNECTION_LOST, this::onConnectionLost);
        server.addEventListener(SFSEvent.LOGIN, this::onLogin);
        server.addEventListener(SFSEvent.LOGIN_ERROR, this::onLoginError);
        server.addEventListener(SFSEvent.EXTENSION_RESPONSE, this::onKnownEvent);
        //server.addEventListener(SFSEvent.MMOITEM_VARIABLES_UPDATE, this::onUnknownEvent);
        server.addEventListener(SFSEvent.MODERATOR_MESSAGE, this::onUnknownEvent);
        server.addEventListener(SFSEvent.OBJECT_MESSAGE, this::onUnknownEvent);
        server.addEventListener(SFSEvent.PING_PONG, this::onUnknownEvent);
        server.addEventListener(SFSEvent.PRIVATE_MESSAGE, this::onUnknownEvent);
        //server.addEventListener(SFSEvent.PROXIMITY_LIST_UPDATE, this::onUnknownEvent);
        server.addEventListener(SFSEvent.PUBLIC_MESSAGE, this::onUnknownEvent);
        server.addEventListener(SFSEvent.SPECTATOR_TO_PLAYER, this::onUnknownEvent);
        server.connect(serverAddress, 9933);
    }
    public void sendRequest(String cmd, SFSObject params) {
        server.send(new ExtensionRequest(cmd, params));
    }
    public SFSObject sendRequestAndWait(String cmd, SFSObject params) throws InterruptedException {
        System.out.println("Sending " + cmd);
        eventLocks.computeIfAbsent(cmd, k -> new Object());
        eventQueues.computeIfAbsent(cmd, k -> new ConcurrentLinkedQueue<>());
        SFSObject ret = null;
        Object lock = eventLocks.get(cmd);
        System.out.println("Ready to take lock");
        synchronized (lock) {
            System.out.println("Lock taken");
            ConcurrentLinkedQueue<Object> queue = eventQueues.get(cmd);
            queue.add(Thread.currentThread());
            server.send(new ExtensionRequest(cmd, params));
            while (queue.peek() != Thread.currentThread() || !eventResponses.containsKey(cmd)) lock.wait();
            System.out.println("Response received");
            ret = eventResponses.get(cmd);
            eventResponses.remove(cmd);
            queue.poll();
            return ret;
        }
    }
    public void waitForResponse(String cmd) throws InterruptedException {
        Object lock = new Object();
        waitingForResponses.put(cmd, lock);
        synchronized (lock) {
            lock.wait();
        }
    }
    public void updatePlayerData() throws InterruptedException {
        playerDataLastUpdated = System.currentTimeMillis();
        SFSObject params = new SFSObject();
        params.putLong("last_updated", playerDataLastUpdated);
        playerData = (SFSObject) sendRequestAndWait("gs_player", params).getSFSObject("player_object");
    }
    public void terminateConnection() {
        server.disconnect();
    }
    private void onConnection(BaseEvent evt) {

        boolean success = (boolean)evt.getArguments().get("success");

        if (!success) {
            System.out.println("Connection Failed. Is the server running?");
            return;
        }
        connectionStatus = ConnectionStatus.CONNECTION_SUCCESS;

        System.out.println("Connection success");

        String userGameID = loginInfo.getUtfString("user_game_id");
        loginInfo.removeElement("user_game_id");
        server.send(new LoginRequest(
                userGameID, "", "MySingingMonsters", loginInfo
        ));

    }
    private void onKnownEvent(BaseEvent event) {
        System.out.println("EXTENSION RESPONSE " + event.getArguments().get("cmd"));
        Map<String, Object> data = event.getArguments();
        String cmd = (String)data.get("cmd");
        SFSObject params = (SFSObject)data.get("params");

        if (waitingForResponses.containsKey(cmd)) {
            Object lock = waitingForResponses.get(cmd);
            synchronized (lock) {
                lock.notifyAll();
            }
        }
        if (cmd.equals("gs_initialized")) {
            onGameInitialized(params);
            return;
        }
        if (eventLocks.containsKey(cmd)) {
            synchronized (eventLocks.get(cmd)) {
                eventResponses.put(cmd, params);
                eventLocks.get(cmd).notifyAll();
            }
        }
    }
    private void onGameInitialized(SFSObject eventParams) {
        bbbId = eventParams.getLong("bbb_id");
    }
    private void onUnknownEvent(BaseEvent event) {
        System.out.println("EVENT TRIGGERED");
        System.out.println(event.getType());
    }
    private void onConnectionLost(BaseEvent evt) {
        connectionStatus = ConnectionStatus.CONNECTION_ENDED;
        System.out.println("-- Connection lost --");
    }
    private void onLogin(BaseEvent evt)
    {
        connectionStatus = ConnectionStatus.CONNECTION_LOGIN_SUCCESS;
        System.out.println("Logged in as: " + server.getMySelf().getName());
    }
    private void onLoginError(BaseEvent evt)
    {
        connectionStatus = ConnectionStatus.CONNECTION_LOGIN_FAILED;
        String message = (String) evt.getArguments().get("errorMessage");
        System.out.println("Login failed. Error: " + message);
        short code = (short) evt.getArguments().get("errorCode");
        System.out.println("Error Code: " + code);
    }
}