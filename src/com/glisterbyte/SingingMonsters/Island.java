package com.glisterbyte.SingingMonsters;

import com.glisterbyte.SingingMonsters.Binds.PlayerBound;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsIsland;
import com.glisterbyte.SingingMonsters.Structures.Mine;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Island extends PlayerBound {

    private final SfsIsland initialSfsModel;

    private IslandType islandType;
    private long uniqueId;
    private Instant dateCreated;

    private int likes;
    private int dislikes;

    private boolean lightTorchFlag;

    private double timeWarp;

    private final List<Monster> monsters = new ArrayList<>();
    private final List<Structure> structures = new ArrayList<>();

    private Island(Player player, SfsIsland sfsIsland) {

        super(player);

        initialSfsModel = sfsIsland;
        islandType = IslandType.fromId(sfsIsland.island);
        dateCreated = Instant.ofEpochMilli(sfsIsland.dateCreated);
        uniqueId = sfsIsland.userIslandId;
        likes = sfsIsland.likes;
        dislikes = sfsIsland.dislikes;
        lightTorchFlag = sfsIsland.lightTorchFlag;
        timeWarp = sfsIsland.warpSpeed;

        for (var sfsMonster : sfsIsland.monsters) {
            monsters.add(Monster.buildMonster(this, sfsMonster));
        }

        for (var sfsStructure : sfsIsland.structures) {
            structures.add(Structure.buildStructure(this, sfsStructure));
        }

    }

    public static Island buildIsland(Player player, SfsIsland sfsIsland) {
        return new Island(player, sfsIsland);
    }

    public SfsIsland getInitialSfsModel() {
        return initialSfsModel;
    }

    public IslandType getIslandType() {
        return islandType;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public Instant getStartDate() {
        return dateCreated;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getScore() {
        return likes - dislikes;
    }

    public boolean isIslandMarkedForTorchLighting() {
        return lightTorchFlag;
    }

    public double getTimeWarp() {
        return timeWarp;
    }


    public List<Monster> getMonsters() {
        return monsters;
    }

    public List<Structure> getStructures() {
        return structures;
    }

    public Mine getMine() {
        for (Structure structure : structures) {
            if (structure instanceof Mine) {
                return (Mine)structure;
            }
        }
        return null;
    }

}