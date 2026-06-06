package com.glisterbyte.singingmonsters.main.catalog;

import com.glisterbyte.singingmonsters.common.DbCacheNode;
import com.glisterbyte.singingmonsters.exceptions.ActionFailedException;
import com.glisterbyte.singingmonsters.localization.Language;
import com.glisterbyte.singingmonsters.localization.LocalizedTextManager;
import com.glisterbyte.singingmonsters.networking.WebsocketClient;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.sfsmodels.DbResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.DbBakeryFoodsResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.DbGenesResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.DbMonstersResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.DbStructuresResponse;
import com.glisterbyte.singingmonsters.sfsmodels.requests.DbBakeryFoodsRequest;
import com.glisterbyte.singingmonsters.sfsmodels.requests.DbGenesRequest;
import com.glisterbyte.singingmonsters.sfsmodels.requests.DbMonstersRequest;
import com.glisterbyte.singingmonsters.sfsmodels.requests.DbStructuresRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Catalog {

    private static final Logger logger = LoggerFactory.getLogger(Catalog.class);

    private LocalizedTextManager localizedTextManager;

    private final WebsocketClient wsClient;

    private final DbCacheNode elementsCache
            = new DbCacheNode("elements.bin", DbGenesRequest.class, DbGenesResponse.class);
    private final List<Element> elements = new ArrayList<>();

    private final DbCacheNode monsterSpeciesCache
            = new DbCacheNode("monsters.bin", DbMonstersRequest.class, DbMonstersResponse.class);
    private final List<MonsterSpecies> monsterSpecies = new ArrayList<>();

    private final DbCacheNode structureTypesCache
            = new DbCacheNode("structures.bin", DbStructuresRequest.class, DbStructuresResponse.class);
    private final List<StructureType> structureTypes = new ArrayList<>();

    private final DbCacheNode bakeryRecipesCache
            = new DbCacheNode("bakery.bin", DbBakeryFoodsRequest.class, DbBakeryFoodsResponse.class);
    private final List<BakeryRecipe> bakeryRecipes = new ArrayList<>();

    public Catalog() {
        wsClient = null;
        localizedTextManager = new LocalizedTextManager();
    }

    public Catalog(WebsocketClient wsClient, LocalizedTextManager localizedTextManager) {
        this.wsClient = wsClient;
        this.localizedTextManager = localizedTextManager;
    }

    public LocalizedTextManager getLocalizedTextManager() {
        return localizedTextManager;
    }

    public void setDefaultLanguage(Language language) {
        localizedTextManager = new LocalizedTextManager();
        localizedTextManager.setDefaultLanguage(language);
    }

    private synchronized <T> void syncListWithCache(
            List<T> list, DbCacheNode cacheNode, Function<DbResponse, List<T>> mapper
    ) {
        var cached = cacheNode.get();
        if (cached == null) return;
        for (var item : mapper.apply(cached)) {
            list.removeIf(item::equals);
            list.add(item);
        }
    }

    private synchronized void syncElementsWithCache() {
        syncListWithCache(
                elements, elementsCache,
                response -> ((DbGenesResponse)response).genes_data.stream()
                        .map(data -> new Element(this, data))
                        .toList()
        );
    }

    private synchronized void syncMonsterSpeciesWithCache() {
        syncListWithCache(
                monsterSpecies, monsterSpeciesCache,
                response -> ((DbMonstersResponse)response).monsters_data.stream()
                        .map(data -> new MonsterSpecies(this, data))
                        .toList()
        );
    }

    private synchronized void syncStructureTypesWithCache() {
        syncListWithCache(
                structureTypes, structureTypesCache,
                response -> ((DbStructuresResponse)response).structures_data.stream()
                        .map(data -> new StructureType(this, data))
                        .toList()
        );
    }

    private synchronized void syncBakeryRecipesWithCache() {
        syncListWithCache(
                bakeryRecipes, bakeryRecipesCache,
                response -> ((DbBakeryFoodsResponse)response).bakery_data.stream()
                        .map(data -> new BakeryRecipe(this, data))
                        .toList()
        );
    }

    private void checkWsClientNotNull() throws ClientException {
        if (wsClient == null) throw new ActionFailedException(
                "Cannot update catalog because it was not created by a client"
        );
    }

    public void updateElements() throws InterruptedException, ClientException {
        checkWsClientNotNull();
        elementsCache.update(wsClient);
        syncElementsWithCache();
        logger.info("Catalog loaded {} elements/genes", elements.size());
    }

    public void updateMonsterSpecies() throws InterruptedException, ClientException {
        checkWsClientNotNull();
        monsterSpeciesCache.update(wsClient);
        syncMonsterSpeciesWithCache();
        logger.info("Catalog loaded {} monster species", monsterSpecies.size());
    }

    public void updateStructureTypes() throws InterruptedException, ClientException {
        checkWsClientNotNull();
        structureTypesCache.update(wsClient);
        syncStructureTypesWithCache();
        logger.info("Catalog loaded {} structure types", structureTypes.size());
    }

    public void updateBakeryRecipes() throws InterruptedException, ClientException {
        checkWsClientNotNull();
        bakeryRecipesCache.update(wsClient);
        syncBakeryRecipesWithCache();
        logger.info("Catalog loaded {} bakery recipes", bakeryRecipes.size());
    }

    public void update() throws InterruptedException, ClientException {
        // Elements must be loaded before monster types because monster types depend on elements
        updateElements();
        updateMonsterSpecies();
        updateStructureTypes();
        updateBakeryRecipes();
    }

    public void loadFromCache() {

        elementsCache.loadFromCache();
        syncElementsWithCache();

        monsterSpeciesCache.loadFromCache();
        syncMonsterSpeciesWithCache();

        structureTypesCache.loadFromCache();
        syncStructureTypesWithCache();

        bakeryRecipesCache.loadFromCache();
        syncBakeryRecipesWithCache();

    }

    public synchronized Entity getEntity(int entityId) {
        synchronized (monsterSpecies) {
            for (MonsterSpecies monsterSpecies : monsterSpecies) {
                if (monsterSpecies.getEntityId() == entityId) {
                    return monsterSpecies;
                }
            }
        }
        synchronized (structureTypes) {
            for (StructureType structureType : structureTypes) {
                if (structureType.getEntityId() == entityId) {
                    return structureType;
                }
            }
        }
        logger.warn("Could not find entity with entity id {}", entityId);
        return null;
    }

    public synchronized List<MonsterSpecies> getAllMonsterSpecies() {
        return new ArrayList<>(monsterSpecies);
    }

    public synchronized List<StructureType> getAllStructureTypes() {
        return new ArrayList<>(structureTypes);
    }

    public synchronized List<BakeryRecipe> getBakeryRecipes() {
        return new ArrayList<>(bakeryRecipes);
    }

    public synchronized Element getElement(char letter) {
        for (Element element : elements) {
            if (element.getCodeLetter() == letter) {
                return element;
            }
        }
        logger.warn("Could not find element with letter {}", letter);
        return null;
    }

    public synchronized MonsterSpecies getMonsterSpecies(int speciesId) {
        for (MonsterSpecies species : monsterSpecies) {
            if (species.getSpeciesId() == speciesId) {
                return species;
            }
        }
        logger.warn("Could not find monster species with species ID {}", speciesId);
        return null;
    }

    public synchronized StructureType getStructureType(int typeId) {
        for (StructureType structureType : structureTypes) {
            if (structureType.getTypeId() == typeId) {
                return structureType;
            }
        }
        logger.warn("Could not find structure type with structure type ID {}", typeId);
        return null;
    }

    public synchronized BakeryRecipe getBakeryRecipe(int id) {
        for (BakeryRecipe recipe : bakeryRecipes) {
            if (recipe.getId() == id) return recipe;
        }
        logger.warn("Could not find bakery recipe with ID {}", id);
        return null;
    }

    public ElementCatalog getElementCatalog() {
        return new ElementCatalog(this);
    }

    public MonsterCatalog getMonsterCatalog() {
        return new MonsterCatalog(this);
    }

    public StructureCatalog getStructureCatalog() {
        return new StructureCatalog(this);
    }

    public BakingCatalog getBakingCatalog() {
        return new BakingCatalog(this);
    }

}