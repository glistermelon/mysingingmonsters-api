package com.glisterbyte.SingingMonsters;

import com.glisterbyte.SingingMonsters.SfsModels.SfsIsland;

import java.time.Instant;
import java.util.List;

public class Island {

    private IslandType islandType;
    private long islandUniqueId;
    private Instant dateCreated;

    private int likes;
    private int dislikes;

    private boolean lightTorchFlag;

    private double timeWarp;

    private List<Monster> monsters;
    private List<Structure> structures;

    public Island(SfsIsland sfs) {
        islandType = IslandType.getTypeFromId(sfs.island);
        dateCreated = Instant.ofEpochMilli(sfs.dateCreated);
        islandUniqueId = sfs.userIslandId;
        likes = sfs.likes;
        dislikes = sfs.dislikes;
        lightTorchFlag = sfs.lightTorchFlag;
        timeWarp = sfs.warpSpeed;
        monsters = sfs.monsters.stream().map(sfsMonster -> new Monster(sfsMonster, this)).toList();
        structures = sfs.structures.stream().map(sfsStructure -> new Structure(sfsStructure, this)).toList();
    }

    public IslandType getIslandType() {
        return islandType;
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

}
