package com.glisterbyte.singingmonsters.main.island;

import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.main.catalog.MultiMonsterSpecies;
import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.client.Client;
import com.glisterbyte.singingmonsters.main.client.HasClient;
import com.glisterbyte.singingmonsters.main.common.*;
import com.glisterbyte.singingmonsters.main.monster.AbstractMonster;
import com.glisterbyte.singingmonsters.main.structure.AbstractStructure;
import com.glisterbyte.singingmonsters.main.structure.ReadableStructure;
import com.glisterbyte.singingmonsters.main.structure.StructureCategory;
import com.glisterbyte.singingmonsters.main.structure.bakery.ReadableBakery;
import com.glisterbyte.singingmonsters.main.structure.breeding.ReadableBreedingStructure;
import com.glisterbyte.singingmonsters.main.structure.nursery.ReadableNursery;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonster;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;
import com.smartfoxserver.v2.entities.data.SFSObject;
import org.apache.commons.lang3.Validate;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractIsland implements ReadableIsland, HasClient {

    protected final Client client;

    private SFSObject sfsData;
    private SfsIsland sfsModel;

    protected final IslandType islandType;

    /*
        Contrary to other "user x id" fields, the user island ID is
        seemingly distinct across ALL islands owned by ANY players.
     */
    protected final long userIslandId;

    protected Instant dateCreated;

    protected int likes;
    protected int dislikes;

    // These don't necessarily have to be final, but I haven't implemented anything that changes them
    protected boolean lightTorchFlag;
    protected double timeWarp;

    protected final Map<Long, AbstractMonster> monsters = new HashMap<>();
    protected final Map<Long, AbstractStructure> structures = new HashMap<>();

    public void internalRefresh(SfsIsland sfsIsland, boolean first) {

        if (!first) {
            Validate.isTrue(
                    userIslandId == sfsIsland.user_island_id,
                    "User island id refresh mismatch; expected {}, got {}",
                    userIslandId, sfsIsland.user_island_id
            );
            var islandType = IslandType.fromId(sfsIsland.island);
            Validate.isTrue(
                    this.islandType == islandType,
                    "Island type refresh mismatch; expected {}, got {}",
                    this.islandType, islandType
            );
        }

        sfsData = sfsIsland.sfsObject;
        sfsModel = sfsIsland;

        dateCreated = Instant.ofEpochMilli(sfsIsland.date_created);

        likes = sfsIsland.likes;
        dislikes = sfsIsland.dislikes;
        lightTorchFlag = sfsIsland.light_torch_flag;
        timeWarp = sfsIsland.warp_speed;

        for (var sfsMonster : sfsIsland.monsters) {
            long userMonsterId = sfsMonster.user_monster_id;
            if (monsters.containsKey(userMonsterId)) {
                monsters.get(userMonsterId).internalRefresh(sfsMonster, false);
            }
            else {
                monsters.put(userMonsterId, buildMonster(sfsMonster));
            }
        }

        for (var sfsStructure : sfsIsland.structures) {
            long userStructureId = sfsStructure.user_structure_id;
            if (structures.containsKey(userStructureId)) {
                structures.get(userStructureId).internalRefresh(sfsStructure, false);
            }
            else {
                structures.put(userStructureId, buildStructure(sfsStructure));
            }
        }

    }

    protected AbstractIsland(Client client, SfsIsland sfsIsland) {
        this.client = client;
        userIslandId = sfsIsland.user_island_id;
        islandType = IslandType.fromId(sfsIsland.island);
        internalRefresh(sfsIsland, true);
    }

    protected abstract AbstractMonster buildMonster(SfsMonster sfsMonster);

    protected abstract AbstractStructure buildStructure(SfsStructure sfsStructure);

    public synchronized Client getClient() {
        return client;
    }

    public synchronized SFSObject getSfsData() {
        return sfsData;
    }

    public synchronized SfsIsland getSfsModel() {
        return sfsModel;
    }

    public synchronized IslandType getIslandType() {
        return islandType;
    }

    public synchronized long getUserIslandId() {
        return userIslandId;
    }

    public synchronized Instant getDateCreated() {
        return dateCreated;
    }

    public synchronized int getLikes() {
        return likes;
    }

    public synchronized int getDislikes() {
        return dislikes;
    }

    public synchronized int getScore() {
        return likes - dislikes;
    }

    public synchronized boolean isIslandMarkedForTorchLighting() {
        return lightTorchFlag;
    }

    public synchronized double getTimeWarp() {
        return timeWarp;
    }

    public synchronized List<? extends AbstractMonster> getMonsters() {
        return monsters.values().stream().toList();
    }

    public synchronized AbstractMonster getMonster(long userMonsterId) {
        return monsters.get(userMonsterId);
    }

    public List<? extends AbstractMonster> getMonstersOfSpecies(MonsterSpecies species) {
        return getMonsters().stream()
                .filter(m -> m.getSpecies().equals(species)).toList();
    }

    public List<? extends AbstractMonster> getMonstersOfSpecies(MultiMonsterSpecies multiSpecies) {
        return getMonsters().stream()
                .filter(m -> multiSpecies.matches(m.getSpecies())).toList();
    }

    public AbstractMonster getMonsterOfSpecies(MonsterSpecies species) {
        return getMonstersOfSpecies(species).stream().findFirst().orElse(null);
    }

    public AbstractMonster getMonsterOfSpecies(MultiMonsterSpecies multiSpecies) {
        return getMonstersOfSpecies(multiSpecies).stream().findFirst().orElse(null);
    }

    public synchronized List<? extends AbstractStructure> getStructures() {
        return structures.values().stream().toList();
    }

    public synchronized AbstractStructure getStructure(long userStructureId) {
        return structures.get(userStructureId);
    }

    public <T extends AbstractStructure> List<T> getStructuresOfClassType(Class<T> classType) {
        return getStructures().stream().filter(classType::isInstance)
                .map(classType::cast).toList();
    }

    public <T extends AbstractStructure> T getStructureOfClassType(Class<T> classType) {
        return getStructures().stream().filter(classType::isInstance)
                .map(classType::cast).findFirst().orElse(null);
    }

    public List<? extends ReadableStructure> getStructuresOfCategory(StructureCategory category) {
        return getStructures().stream()
                .filter(
                        s -> s.getStructureType().getStructureCategory().equals(category)
                ).toList();
    }

    public ReadableStructure getStructureOfCategory(StructureCategory category) {
        return getStructures().stream()
                .filter(
                        s -> s.getStructureType().getStructureCategory().equals(category)
                ).findFirst().orElse(null);
    }

    public ReadableBakery getBakeryWithBakingId(long userBakingId) {
        return getBakeries().stream()
                .filter(
                        b -> b.getUserBakingId()
                                .map(id -> id == userBakingId)
                                .orElse(false)
                )
                .findFirst().orElse(null);
    }

    public ReadableBreedingStructure getBreedingStructureWithBreedingId(long userBreedingId) {
        return getBreedingStructures().stream()
                .filter(
                        b -> b.getUserBreedingId()
                                .map(id -> id == userBreedingId)
                                .orElse(false)
                )
                .findFirst().orElse(null);
    }

    public ReadableNursery getNurseryWithEggId(long userEggId) {
        return getNurseries().stream()
                .filter(
                        b -> b.getUserEggId()
                                .map(id -> id == userEggId)
                                .orElse(false)
                )
                .findFirst().orElse(null);
    }

    public synchronized List<Positioned> getObjects() {
        return Stream.concat(getMonsters().stream(), getStructures().stream())
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Positioned> getNearbyObjects(Positioned origin, int radius) {
        return getObjects().stream().filter(m -> origin.distanceTo(m) <= radius).toList();
    }

    public CurrencyType getCollectionCurrencyType() {
        return getIslandType().getCollectionCurrencyType();
    }

    public boolean areTilesOccupied(Position position, Size size) {
        PositionedDummy dummy = new PositionedDummy(null, position, size, false);
        return getObjects().stream().anyMatch(obj -> obj.overlapsWith(dummy));
    }

    @Override
    public synchronized String toString() {
        return StringUtil.format("Island(type={})", islandType);
    }

    @Override
    public synchronized boolean equals(Object object) {
        if (!(object instanceof AbstractIsland other)) return false;
        return userIslandId == other.userIslandId;
    }

}