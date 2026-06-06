package com.glisterbyte.singingmonsters.main.catalog;

import com.glisterbyte.singingmonsters.main.island.IslandType;
import com.glisterbyte.singingmonsters.main.island.ReadableIsland;

import java.util.HashMap;
import java.util.Map;

public class MultiMonsterSpecies {

    private final Map<IslandType, MonsterSpecies> speciesMap;

    private static final Map<MonsterSpecies, MultiMonsterSpecies> speciesToMultiMap = new HashMap<>();

    public static MultiMonsterSpecies fromSpecies(MonsterSpecies species) {
        return speciesToMultiMap.get(species);
    }

    public MultiMonsterSpecies(MonsterSpecies defaultSpecies, Map<IslandType, MonsterSpecies> speciesMap) {
        this.speciesMap = new HashMap<>();
        for (var islandType : IslandType.allIslandTypes()) {
            this.speciesMap.put(islandType, defaultSpecies);
        }
        this.speciesMap.putAll(speciesMap);
        speciesToMultiMap.put(defaultSpecies, this);
        for (MonsterSpecies species : speciesMap.values()) {
            speciesToMultiMap.put(species, this);
        }
    }

    public MonsterSpecies getSpecies(IslandType islandType) {
        return speciesMap.getOrDefault(islandType, null);
    }

    public MonsterSpecies getSpecies(ReadableIsland island) {
        return getSpecies(island.getIslandType());
    }

    public MonsterSpecies getSpeciesOnRandomIsland() {
        for (MonsterSpecies species : speciesMap.values()) {
            if (species != null) return species;
        }
        return null;
    }

    public boolean matches(MonsterSpecies species) {
        return speciesMap.values().stream().anyMatch(species::equals);
    }

    @Override
    public String toString() {
        MonsterSpecies species = getSpeciesOnRandomIsland();
        if (species == null) return "MultiMonsterSpecies(null)";
        return "MultiMonsterSpecies(" + species.getName() + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof MultiMonsterSpecies other) {
            return speciesMap.equals(other.speciesMap);
        }
        return false;
    }

}
