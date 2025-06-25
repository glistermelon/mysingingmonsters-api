package com.glisterbyte.SingingMonsters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glisterbyte.Configuration.Global;
import com.glisterbyte.Network.SfsClient;
import com.glisterbyte.SfsMapping.SfsMapper;
import com.glisterbyte.SingingMonsters.SfsModels.Client.DbGenesRequest;
import com.glisterbyte.SingingMonsters.SfsModels.Client.DbMonstersRequest;
import com.glisterbyte.SingingMonsters.SfsModels.Client.DbStructuresRequest;
import com.glisterbyte.SingingMonsters.SfsModels.Server.*;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {

    private static Path CACHE_DIRECTORY = Paths.get("msm_cache");

    private static boolean initialized = false;

    private static List<IslandSpecificMonsterSpecies> islandSpecificMonsterSpecies = null;
    private static final String MONSTERS_FILE_NAME = "monsters";

    private static List<Element> elements = null;
    private static final String ELEMENTS_FILE_NAME = "elements";

    private static List<StructureType> structureTypes = null;
    private static final String STRUCTURES_FILE_NAME = "structures";

    private static List<MonsterSpecies> genericMonsterSpecies = null;

    private static void ensureCacheDirExists() {
        try {
            if (!Files.exists(CACHE_DIRECTORY)) Files.createDirectory(CACHE_DIRECTORY);
        }
        catch (IOException ex) {
            throw new RuntimeException("Failed to cache monsters data", ex);
        }
    }

    private static void writeJson(String name, String json) {
        ensureCacheDirExists();
        try {
            Files.writeString(CACHE_DIRECTORY.resolve(name + ".json"), json);
        }
        catch (IOException ex) {
            throw new RuntimeException("Failed to cache '" + name + "'", ex);
        }
    }

    private static String readJson(String name) {
        Path path = CACHE_DIRECTORY.resolve(name + ".json");
        if (!Files.exists(path)) return null;
        try {
            return Files.readString(path);
        }
        catch (IOException ex) {
            throw new RuntimeException("Failed to read '" + name + "''", ex);
        }
    }

    private static void erase(String name) {
        Path path = CACHE_DIRECTORY.resolve(name + ".json");
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            }
            catch (IOException ex) {
                throw new RuntimeException("Failed to delete '" + name + "''", ex);
            }
        }
    }

    private static void save(DbMonstersResponse monstersData) {
        writeJson(MONSTERS_FILE_NAME, SfsMapper.mapToSFSObject(monstersData).toJson());
    }

    private static void save(DbGenesResponse genesData) {
        writeJson(ELEMENTS_FILE_NAME, SfsMapper.mapToSFSObject(genesData).toJson());
    }

    private static void save(DbStructuresResponse structuresData) {
        writeJson(STRUCTURES_FILE_NAME, SfsMapper.mapToSFSObject(structuresData).toJson());
    }

    private static Object load(Class<?> _class, String name) {
        String json = readJson(name);
        if (json == null) return null;
        ISFSObject sfsObject = SFSObject.newFromJsonData(json);
        return SfsMapper.mapSFSObjectToClass(_class, sfsObject);
    }
    
    private static void loadGenericMonsterSpecies() {
        try {
            genericMonsterSpecies = new ArrayList<>();
            JsonNode json = new ObjectMapper().readTree(Global.getResourceAsString("monster_types.json"));
            for (var genericEntry : json.properties()) {
                Map<String, IslandSpecificMonsterSpecies> map = new HashMap<>();
                for (var specificEntry : genericEntry.getValue().properties()) {
                    map.put(
                            specificEntry.getKey(),
                            IslandSpecificMonsterSpecies.fromId(specificEntry.getValue().asInt())
                    );
                }
                MonsterSpecies species = new MonsterSpecies(genericEntry.getKey(), map);
                genericMonsterSpecies.add(species);
            }
        } catch (JsonProcessingException ex) {
            genericMonsterSpecies = null;
            throw new RuntimeException("Misconfigured resources: cannot get monster types", ex);
        }
    }

    private static void preloadElements() {
        DbGenesResponse dbGenes = (DbGenesResponse)load(DbGenesResponse.class, ELEMENTS_FILE_NAME);
        if (dbGenes != null) {
            elements = new ArrayList<>();
            for (SfsGene sfsGene : dbGenes.genesData) {
                elements.add(new Element(sfsGene));
            }
        }
    }

    private static void preloadSpecificMonsterSpecies() {
        DbMonstersResponse dbMonsters = (DbMonstersResponse)load(DbMonstersResponse.class, MONSTERS_FILE_NAME);
        if (dbMonsters != null) {
            islandSpecificMonsterSpecies = new ArrayList<>();
            for (SfsMonsterInfo sfsInfo : dbMonsters.monstersData) {
                islandSpecificMonsterSpecies.add(new IslandSpecificMonsterSpecies(sfsInfo));
            }
            loadGenericMonsterSpecies();
        }
    }

    private static void preloadStructureTypes() {
        DbStructuresResponse dbStructures = (DbStructuresResponse)load(DbStructuresResponse.class, STRUCTURES_FILE_NAME);
        if (dbStructures != null) {
            structureTypes = new ArrayList<>();
            for (SfsStructureInfo sfsInfo : dbStructures.structuresData) {
                structureTypes.add(new StructureType(sfsInfo));
            }
        }
    }

    public static void preload() {

        // Elements MUST be loaded BEFORE monster types
        // because monster types depend on elements

        preloadElements();

        if (elements != null) {
            try {
                preloadSpecificMonsterSpecies();
            }
            catch (ElementNotFoundException ex) {
                erase(ELEMENTS_FILE_NAME);
                erase(MONSTERS_FILE_NAME);
                elements = null;
                islandSpecificMonsterSpecies = null;
            }
        }

        preloadStructureTypes();

        initialized = true;

    }

    public static void update(SfsClient sfsClient) {

        // Elements MUST be loaded BEFORE monster types
        // because monster types depend on elements

        if (elements == null) {
            DbGenesResponse dbGenes = (DbGenesResponse)
                    sfsClient.requestResponses(new DbGenesRequest()).join().getFirst();
            elements = new ArrayList<>();
            for (SfsGene sfsGene : dbGenes.genesData) {
                elements.add(new Element(sfsGene));
            }
            save(dbGenes);
        }

        if (islandSpecificMonsterSpecies == null) {
            DbMonstersResponse dbMonsters = (DbMonstersResponse)
                    sfsClient.requestResponses(new DbMonstersRequest()).join().getFirst();
            islandSpecificMonsterSpecies = new ArrayList<>();
            for (SfsMonsterInfo sfsInfo : dbMonsters.monstersData) {
                islandSpecificMonsterSpecies.add(new IslandSpecificMonsterSpecies(sfsInfo));
            }
            save(dbMonsters);
            loadGenericMonsterSpecies();
        }

        if (structureTypes == null) {
            DbStructuresResponse dbStructures = (DbStructuresResponse)
                    sfsClient.requestResponses(new DbStructuresRequest()).join().getFirst();
            structureTypes = new ArrayList<>();
            for (SfsStructureInfo sfsInfo : dbStructures.structuresData) {
                structureTypes.add(new StructureType(sfsInfo));
            }
            save(dbStructures);
        }

        initialized = true;

    }

    public static void setCacheDirectory(Path directory) {
        if (initialized) throw new RuntimeException("""
                Failed to set new cache directory because
                cache has already been initialized.
                Please set the cache directory before creating any clients.""");
        CACHE_DIRECTORY = directory;
    }

    public static List<Element> getAllElements() {
        return new ArrayList<>(elements);
    }

    public static List<MonsterSpecies> getAllMonsterSpecies() {
        return new ArrayList<>(genericMonsterSpecies);
    }

    public static List<StructureType> getAllStructureTypes() {
        return new ArrayList<>(structureTypes);
    }

    public static Element getElementByLetter(char letter) {
        if (elements == null) return null;
        for (Element element : elements) {
            if (element.getCodeLetter() == letter) {
                return element;
            }
        }
        return null;
    }

    public static IslandSpecificMonsterSpecies getSpecificMonsterSpeciesBySpeciesId(int speciesId) {
        if (islandSpecificMonsterSpecies == null) return null;
        for (IslandSpecificMonsterSpecies islandSpecificMonsterSpecies : islandSpecificMonsterSpecies) {
            if (islandSpecificMonsterSpecies.getSpeciesId() == speciesId) {
                return islandSpecificMonsterSpecies;
            }
        }
        return null;
    }

    public static MonsterSpecies getGenericMonsterSpeciesBySpeciesId(int speciesId) {
        if (genericMonsterSpecies == null) return null;
        for (MonsterSpecies species : genericMonsterSpecies) {
            if (species.matchesSpeciesId(speciesId)) {
                return species;
            }
        }
        return null;
    }

    public static MonsterSpecies getGenericMonsterSpeciesByResourceKey(String resourceKey) {
        if (genericMonsterSpecies == null) return null;
        for (MonsterSpecies species : genericMonsterSpecies) {
            if (species._getResourceKey().equals(resourceKey)) {
                return species;
            }
        }
        return null;
    }

    public static StructureType getStructureTypeByTypeId(int typeId) {
        if (structureTypes == null) return null;
        for (StructureType structureType : structureTypes) {
            if (structureType.getTypeId() == typeId) {
                return structureType;
            }
        }
        return null;
    }

    public static Object getEntityByEntityId(int entityId) {
        if (islandSpecificMonsterSpecies != null) {
            for (IslandSpecificMonsterSpecies islandSpecificMonsterSpecies : Cache.islandSpecificMonsterSpecies) {
                if (islandSpecificMonsterSpecies.getEntityId() == entityId) {
                    return islandSpecificMonsterSpecies;
                }
            }
        }
        if (structureTypes != null) {
            for (StructureType structureType : structureTypes) {
                if (structureType.getEntityId() == entityId) {
                    return structureType;
                }
            }
        }
        return null;
    }

}