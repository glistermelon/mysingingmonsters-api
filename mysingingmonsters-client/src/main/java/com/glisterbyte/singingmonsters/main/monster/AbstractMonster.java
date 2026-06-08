package com.glisterbyte.singingmonsters.main.monster;

import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.catalog.Entity;
import com.glisterbyte.singingmonsters.main.client.Client;
import com.glisterbyte.singingmonsters.main.client.HasClient;
import com.glisterbyte.singingmonsters.main.common.CurrencyType;
import com.glisterbyte.singingmonsters.main.common.Position;
import com.glisterbyte.singingmonsters.main.island.AbstractIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonster;
import com.smartfoxserver.v2.entities.data.SFSObject;
import org.apache.commons.lang3.Validate;

import java.time.Instant;

public abstract class AbstractMonster implements ReadableMonster, HasClient {

    private final AbstractIsland island;

    private SFSObject sfsData;

    protected final long userMonsterId;

    protected final MonsterSpecies species;

    protected String name;
    protected Position position;
    protected boolean flipped;
    protected boolean muted;
    protected double volume;

    protected int level;
    protected int timesFed;

    protected MonsterHappiness happiness;

    protected boolean inHotel;

    protected Instant lastCollectTime;
    protected Instant lastFeedTime;

    protected CurrencyType collectionCurrencyType;

    protected boolean activated;

    public void internalRefresh(SfsMonster sfsMonster, boolean first) {

        if (!first) {

            Validate.isTrue(
                    userMonsterId == sfsMonster.user_monster_id,
                    "User monster id refresh mismatch; expected %s, got %s",
                    userMonsterId, sfsMonster.user_monster_id
            );

            assert this.species != null;
            MonsterSpecies species = getCatalog().getMonsterSpecies(sfsMonster.monster);
            Validate.isTrue(
                    this.species.equals(species),
                    "Monster species refresh mismatch; expected %s, got %s",
                    this.species, species
            );

        }

        sfsData = sfsMonster.sfsObject;

        name = sfsMonster.name;
        position = new Position(sfsMonster.pos_x, sfsMonster.pos_y);
        flipped = sfsMonster.flip != 0;
        muted = sfsMonster.muted != 0;
        volume = sfsMonster.volume;
        level = sfsMonster.level;
        timesFed = sfsMonster.times_fed;
        happiness = MonsterHappiness.fromPercentage(sfsMonster.happiness);
        inHotel = sfsMonster.in_hotel != 0;
        lastCollectTime = Instant.ofEpochMilli(sfsMonster.last_collection);
        lastFeedTime = Instant.ofEpochMilli(sfsMonster.last_feeding);
        collectionCurrencyType = sfsMonster.collection_type != null
                ? CurrencyType.fromString(sfsMonster.collection_type) : null;
        activated = sfsMonster.boxed_eggs == null;
    }

    protected AbstractMonster(AbstractIsland island, SfsMonster sfsMonster) {
        this.island = island;
        userMonsterId = sfsMonster.user_monster_id;
        species = getCatalog().getMonsterSpecies(sfsMonster.monster);
        internalRefresh(sfsMonster, true);
    }

    public synchronized AbstractIsland getIsland() {
        return island;
    }

    public synchronized Client getClient() {
        return island.getClient();
    }

    public synchronized SFSObject getSfsData() {
        return sfsData;
    }

    public synchronized Entity getEntity() {
        return species;
    }

    public synchronized long getUserMonsterId() { return userMonsterId; }

    public synchronized MonsterSpecies getSpecies() {
        return species;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized Position getPosition() {
        return position;
    }

    public synchronized boolean isFlipped() {
        return flipped;
    }

    public synchronized boolean isMuted() {
        return muted;
    }

    public synchronized double getVolume() {
        return volume;
    }

    public synchronized int getLevel() {
        return level;
    }

    public synchronized int getTimesFed() {
        return timesFed;
    }

    public synchronized MonsterHappiness getHappiness() {
        return happiness;
    }

    public synchronized boolean isInHotel() {
        return inHotel;
    }

    public synchronized Instant getLastCollectTime() {
        return lastCollectTime;
    }

    public synchronized Instant getLastFeedTime() {
        return lastFeedTime;
    }

    public synchronized boolean isActivated() {
        return activated;
    }

    public synchronized CurrencyType getCollectionCurrencyType() {
        if (collectionCurrencyType != null) return collectionCurrencyType;
        else return island.getCollectionCurrencyType();
    }

    @Override
    public synchronized String toString() {
        return StringUtil.format("Monster(name='{}', id={}, species={})", getName(), userMonsterId, species);
    }

    @Override
    public synchronized boolean equals(Object object) {
        if (!(object instanceof AbstractMonster other)) return false;
        return getIsland().equals(other.getIsland()) && userMonsterId == other.getUserMonsterId();
    }

}