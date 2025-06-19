package com.glisterbyte.SingingMonsters;

import com.glisterbyte.Network.SfsClient;
import com.glisterbyte.SingingMonsters.Binds.ClientBound;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsPlayer;
import com.glisterbyte.SingingMonsters.SfsModels.Server.UpdateStructure;

import java.util.List;

public class Player extends ClientBound {

    private final SfsPlayer initialSfsModel;

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

    private Player(SfsClient sfsClient, SfsPlayer sfsPlayer) {
        super(sfsClient);
        initialSfsModel = sfsPlayer;
        uniqueId = sfsPlayer.userId;
        country = sfsPlayer.country;
        displayName = sfsPlayer.displayName;
        coins = sfsPlayer.coinsActual;
        diamonds = sfsPlayer.diamondsActual;
        diamondsSpent = sfsPlayer.diamondsSpent;
        shards = sfsPlayer.etherealCurrencyActual;
        food = sfsPlayer.foodActual;
        keys = sfsPlayer.keysActual;
        relics = sfsPlayer.relicsActual;
        starpower = sfsPlayer.starpowerActual;
        level = sfsPlayer.level;
        xp = sfsPlayer.xp;
        islands = sfsPlayer.islands.stream().map(
                sfsIsland -> Island.buildIsland(this, sfsIsland)
        ).toList();
    }

    public static Player buildPlayer(SfsClient sfsClient, SfsPlayer sfsPlayer) {
        return new Player(sfsClient, sfsPlayer);
    }

    public void update(UpdateStructure.UpdateStructureProperties properties) {
        coins = properties.coinsActual;
        diamonds = properties.diamondsActual;
        food = properties.foodActual;
        shards = properties.etherealCurrencyActual;
        keys = properties.keysActual;
        relics = properties.relicsActual;
        // skipped egg_wildcards_actual
        starpower = properties.starpowerActual;
        xp = properties.xp;
        level = properties.level;
        // skipped all other fields
    }

    public SfsPlayer getInitialSfsModel() {
        return initialSfsModel;
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

    public List<Island> getIslands() {
        return islands;
    }

    public Island getIsland(IslandType type) {
        for (Island island : islands) {
            if (island.getIslandType() == type) {
                return island;
            }
        }
        return null;
    }

}