package com.glisterbyte.SingingMonsters;

import com.glisterbyte.SingingMonsters.SfsModels.SfsPlayer;

import java.util.List;

public class Player {

    private int uniqueId;
    private String country;
    private String displayName;

    private long coins;
    private long diamonds;
    private int diamondsSpent;
    private long shards;
    private long food;
    private long keys;
    private long relics;
    private long starpower;

    private int level;
    private int xp;

    private List<Island> islands;

    public Player(SfsPlayer sfs) {
        uniqueId = sfs.userId;
        country = sfs.country;
        displayName = sfs.displayName;
        coins = sfs.coinsActual;
        diamonds = sfs.diamondsActual;
        diamondsSpent = sfs.diamondsSpent;
        shards = sfs.etherealCurrencyActual;
        food = sfs.foodActual;
        keys = sfs.keysActual;
        relics = sfs.relicsActual;
        starpower = sfs.starpowerActual;
        level = sfs.level;
        xp = sfs.xp;
        islands = sfs.islands.stream().map(Island::new).toList();
    }

    public String getCountryCode() {
        return country;
    }

    public String getName() {
        return displayName;
    }

    public long getCoins() {
        return coins;
    }

    public long getDiamonds() {
        return diamonds;
    }

    public long getGems() {
        return diamonds;
    }

    public long getShards() {
        return shards;
    }

    public long getFood() {
        return food;
    }

    public long getTreats() {
        return food;
    }

    public long getKeys() {
        return keys;
    }

    public long getRelics() {
        return relics;
    }

    public long getStarpower() {
        return starpower;
    }

    public long getLevel() {
        return level;
    }

    public long getTotalXp() {
        return xp;
    }

}