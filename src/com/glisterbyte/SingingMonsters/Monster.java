package com.glisterbyte.SingingMonsters;

import com.glisterbyte.SingingMonsters.SfsModels.SfsMonster;

import java.time.Instant;

public class Monster {

    private Island island;

    private Position position;
    private boolean flip;
    private boolean muted;

    private MonsterHappiness happiness;

    private boolean inHotel;

    private Instant lastCollectTime;
    private Instant lastFeedTime;
    
    public Monster(SfsMonster sfs, Island island) {
        this.island = island;
        position = new Position(sfs.posX, sfs.posY);
        flip = sfs.flip == 1;
        muted = sfs.muted == 1;
        happiness = MonsterHappiness.fromPercentage(sfs.happiness);
        inHotel = sfs.inHotel == 1;
        lastCollectTime = Instant.ofEpochMilli(sfs.lastCollection);
        lastFeedTime = Instant.ofEpochMilli(sfs.lastFeeding);
    }

    public Island getIsland() {
        return island;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isFlipped() {
        return flip;
    }

    public boolean isMuted() {
        return muted;
    }

    public MonsterHappiness getHappiness() {
        return happiness;
    }

    public boolean isInHotel() {
        return inHotel;
    }

    public Instant getLastCollectTime() {
        return lastCollectTime;
    }

    public Instant getLastFeedTime() {
        return lastFeedTime;
    }
}
