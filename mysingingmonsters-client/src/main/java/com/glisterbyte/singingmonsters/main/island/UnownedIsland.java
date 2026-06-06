package com.glisterbyte.singingmonsters.main.island;

import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.main.catalog.MultiMonsterSpecies;
import com.glisterbyte.singingmonsters.main.client.Client;
import com.glisterbyte.singingmonsters.main.monster.UnownedMonster;
import com.glisterbyte.singingmonsters.main.monster.eggbox.UnownedEggBoxMonster;
import com.glisterbyte.singingmonsters.main.structure.UnownedStructure;
import com.glisterbyte.singingmonsters.main.structure.StructureCategory;
import com.glisterbyte.singingmonsters.main.structure.bakery.UnownedBakery;
import com.glisterbyte.singingmonsters.main.structure.breeding.UnownedBreedingStructure;
import com.glisterbyte.singingmonsters.main.structure.castle.UnownedCastle;
import com.glisterbyte.singingmonsters.main.structure.mine.UnownedMine;
import com.glisterbyte.singingmonsters.main.structure.nursery.UnownedNursery;
import com.glisterbyte.singingmonsters.main.structure.obstacle.UnownedObstacle;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonster;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;

import java.util.List;

public class UnownedIsland extends AbstractIsland {

    public UnownedIsland(Client client, SfsIsland sfsIsland) {
        super(client, sfsIsland);
    }

    protected UnownedMonster buildMonster(SfsMonster sfsMonster) {
        if (sfsMonster.isEggBoxMonster(getCatalog())) return new UnownedEggBoxMonster(this, sfsMonster);
        else return new UnownedMonster(this, sfsMonster);
    }

    protected UnownedStructure buildStructure(SfsStructure sfsStructure) {
        StructureCategory category = getCatalog().getStructureType(sfsStructure.structure).getStructureCategory();
        return switch (category) {
            case NURSERY -> new UnownedNursery(this, sfsStructure);
            case BREEDING -> new UnownedBreedingStructure(this, sfsStructure);
            case MINE -> new UnownedMine(this, sfsStructure);
            case CASTLE -> new UnownedCastle(this, sfsStructure);
            case BAKERY -> new UnownedBakery(this, sfsStructure);
            case OBSTACLE -> new UnownedObstacle(this, sfsStructure);
            default -> new UnownedStructure(this, sfsStructure);
        };
    }

    public List<UnownedMonster> getMonsters() {
        return super.getMonsters().stream().map(m -> (UnownedMonster)m).toList();
    }

    public UnownedMonster getMonster(long userMonsterId) {
        return (UnownedMonster)super.getMonster(userMonsterId);
    }

    public List<UnownedMonster> getMonstersOfSpecies(MonsterSpecies species) {
        return super.getMonstersOfSpecies(species).stream()
                .map(m -> (UnownedMonster)m).toList();
    }

    public List<UnownedMonster> getMonstersOfSpecies(MultiMonsterSpecies multiSpecies) {
        return super.getMonstersOfSpecies(multiSpecies).stream()
                .map(m -> (UnownedMonster)m).toList();
    }

    public UnownedMonster getMonsterOfSpecies(MonsterSpecies species) {
        return (UnownedMonster)super.getMonsterOfSpecies(species);
    }

    public UnownedMonster getMonsterOfSpecies(MultiMonsterSpecies multiSpecies) {
        return (UnownedMonster)super.getMonsterOfSpecies(multiSpecies);
    }

    public List<UnownedStructure> getStructures() {
        return super.getStructures().stream().map(s -> (UnownedStructure)s).toList();
    }

    public UnownedStructure getStructure(long userStructureId) {
        return (UnownedStructure)super.getStructure(userStructureId);
    }

    public List<UnownedStructure> getStructuresOfCategory(StructureCategory category) {
        return super.getStructuresOfCategory(category).stream()
                .map(s -> (UnownedStructure)s).toList();
    }

    public UnownedStructure getStructureOfCategory(StructureCategory category) {
        return (UnownedStructure)super.getStructureOfCategory(category);
    }

    public List<UnownedBakery> getBakeries() {
        return getStructuresOfClassType(UnownedBakery.class);
    }

    public List<UnownedBreedingStructure> getBreedingStructures() {
        return getStructuresOfClassType(UnownedBreedingStructure.class);
    }

    public UnownedCastle getCastle() {
        return getStructureOfClassType(UnownedCastle.class);
    }

    public UnownedMine getMine() {
        return getStructureOfClassType(UnownedMine.class);
    }

    public List<UnownedNursery> getNurseries() {
        return getStructuresOfClassType(UnownedNursery.class);
    }

    public List<UnownedObstacle> getObstacles() {
        return getStructuresOfClassType(UnownedObstacle.class);
    }

    public List<UnownedStructure> getDecorations() {
        return getStructuresOfCategory(StructureCategory.DECORATION).stream().toList();
    }

    public UnownedBakery getBakeryWithBakingId(long userBakingId) {
        return (UnownedBakery)super.getBakeryWithBakingId(userBakingId);
    }

    public UnownedBreedingStructure getBreedingStructureWithBreedingId(long userBreedingId) {
        return (UnownedBreedingStructure)super.getBreedingStructureWithBreedingId(userBreedingId);
    }

    public UnownedNursery getNurseryWithEggId(long userEggId) {
        return (UnownedNursery)super.getNurseryWithEggId(userEggId);
    }
    
}