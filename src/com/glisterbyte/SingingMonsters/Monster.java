package com.glisterbyte.SingingMonsters;

import com.glisterbyte.SingingMonsters.Binds.IslandBound;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsMonster;

import java.time.Instant;

public class Monster extends IslandBound {

    private final SfsMonster initialSfsModel;

    private final IslandSpecificMonsterSpecies species;

    private String name;
    private Position position;
    private boolean flip;
    private boolean muted;

    private MonsterHappiness happiness;

    private boolean inHotel;

    private Instant lastCollectTime;
    private Instant lastFeedTime;
    
    private Monster(Island island, SfsMonster sfsMonster) {
        super(island);
        initialSfsModel = sfsMonster;
        species = Cache.getSpecificMonsterSpeciesBySpeciesId(sfsMonster.monster);
        name = sfsMonster.name;
        position = new Position(sfsMonster.posX, sfsMonster.posY);
        flip = sfsMonster.flip == 1;
        muted = sfsMonster.muted == 1;
        happiness = MonsterHappiness.fromPercentage(sfsMonster.happiness);
        inHotel = sfsMonster.inHotel == 1;
        lastCollectTime = Instant.ofEpochMilli(sfsMonster.lastCollection);
        lastFeedTime = Instant.ofEpochMilli(sfsMonster.lastFeeding);
    }

    public static Monster buildMonster(Island island, SfsMonster sfsMonster) {
        return new Monster(island, sfsMonster);
    }

    public SfsMonster getInitialSfsModel() {
        return initialSfsModel;
    }

    public IslandSpecificMonsterSpecies getSpecificSpecies() {
        return species;
    }

    public MonsterSpecies getGenericSpecies() {
        return species.getGenericSpecies();
    }

    public String getName() {
        return name;
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
