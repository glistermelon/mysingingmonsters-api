package com.glisterbyte.Network.info;

import com.glisterbyte.Network.ClientException;
import com.glisterbyte.Network.GameServer;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class PlayerData {
    GameServer gameServer;
    private SFSObject playerData;
    private long coins;
    private long diamonds; // + GEMS
    private long food; // + TREATS
    private long shards;
    private long relics;
    private long keys;
    private long starpower;
    public SFSObject get() {
        return playerData;
    }
    public long getCoins() { return coins; }
    public long getDiamonds() { return diamonds; }
    public long getGems() { return getDiamonds(); }
    public long getFood() { return food; }
    public long getTreats() { return getFood(); }
    public long getShards() { return shards; }
    public long getRelics() { return relics; }
    public long getKeys() { return keys; }
    public long getStarpower() { return starpower; }
    public PlayerData(GameServer gameServer) throws ClientException {
        this.gameServer = gameServer;
        update();
    }
    public boolean update() {
        try {
            gameServer.updatePlayerData();
            playerData = gameServer.getPlayerData();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (playerData == null) return false;
        try {
            coins = playerData.getLong("coins_actual");
            diamonds = playerData.getLong("diamonds_actual");
            food = playerData.getLong("food_actual");
            shards = playerData.getLong("ethereal_currency_actual");
            relics = playerData.getLong("relics");
            keys = playerData.getLong("keys");
            starpower = playerData.getLong("starpower_actual");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
