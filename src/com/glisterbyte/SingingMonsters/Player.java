package com.glisterbyte.SingingMonsters;

import com.glisterbyte.Network.SfsClient;
import com.glisterbyte.SingingMonsters.Binds.ClientBound;
import com.glisterbyte.SingingMonsters.SfsModels.Client.*;
import com.glisterbyte.SingingMonsters.SfsModels.Server.*;
import com.glisterbyte.SingingMonsters.Structures.Mine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Player extends ClientBound {

    private static final Logger logger = LoggerFactory.getLogger(Player.class);

    private final SfsPlayer initialSfsModel;

    private int uniqueId;
    private String country;
    private String displayName;

    private long coins;
    private long diamonds;
    private int diamondsSpent;
    private long shards;
    private long food;
    private long keys;
    private long relics;
    private long starpower;

    private int level;
    private int xp;

    private final List<Island> islands = new ArrayList<>();
    private Island activeIsland;

    private Player(SfsClient sfsClient, SfsPlayer sfsPlayer) {

        super(sfsClient);

        initialSfsModel = sfsPlayer;
        uniqueId = sfsPlayer.userId;
        country = sfsPlayer.country;
        displayName = sfsPlayer.displayName;
        coins = sfsPlayer.coinsActual;
        diamonds = sfsPlayer.diamondsActual;
        diamondsSpent = sfsPlayer.diamondsSpent;
        shards = sfsPlayer.etherealCurrencyActual;
        food = sfsPlayer.foodActual;
        keys = sfsPlayer.keysActual;
        relics = sfsPlayer.relicsActual;
        starpower = sfsPlayer.starpowerActual;
        level = sfsPlayer.level;
        xp = sfsPlayer.xp;

        for (var sfsIsland : sfsPlayer.islands) {
            islands.add(Island.buildIsland(this, sfsIsland));
        }

        for (Island island : islands) {
            if (island.getUniqueId() == sfsPlayer.activeIsland) {
                activeIsland = island;
                break;
            }
        }

    }

    public static Player buildPlayer(SfsClient sfsClient, SfsPlayer sfsPlayer) {
        return new Player(sfsClient, sfsPlayer);
    }

    public void update(Update properties) {
        coins = properties.coinsActual;
        diamonds = properties.diamondsActual;
        food = properties.foodActual;
        shards = properties.etherealCurrencyActual;
        keys = properties.keysActual;
        relics = properties.relicsActual;
        // skipped egg_wildcards_actual
        starpower = properties.starpowerActual;
        xp = properties.xp;
        level = properties.level;
        // skipped all other fields
    }

    public SfsPlayer getInitialSfsModel() {
        return initialSfsModel;
    }

    public String getCountryCode() {
        return country;
    }

    public String getName() {
        return displayName;
    }

    public long getCoins() {
        return coins;
    }

    public long getDiamonds() {
        return diamonds;
    }

    public long getGems() {
        return diamonds;
    }

    public long getShards() {
        return shards;
    }

    public long getFood() {
        return food;
    }

    public long getTreats() {
        return food;
    }

    public long getKeys() {
        return keys;
    }

    public long getRelics() {
        return relics;
    }

    public long getStarpower() {
        return starpower;
    }

    public long getLevel() {
        return level;
    }

    public long getTotalXp() {
        return xp;
    }

    public List<Island> getIslands() {
        return islands;
    }

    public Island getIsland(IslandType type) {
        for (Island island : islands) {
            if (island.getIslandType() == type) {
                return island;
            }
        }
        return null;
    }

    public Island getActiveIsland() {
        return activeIsland;
    }

    public void setActiveIsland(long islandUniqueId) {

        // API returns failed response with no error message if
        // you try to switch to the island you're already on
        if (islandUniqueId == activeIsland.getUniqueId()) return;

        ChangeIslandRequest request = new ChangeIslandRequest();
        request.userIslandId = islandUniqueId;

        ChangeIslandResponse response;
        try {
            response = (ChangeIslandResponse)sfsClient.requestResponses(request).get().getFirst();
        } catch (InterruptedException | ExecutionException ex) {
            throw new ChangeIslandFailed("", ex);
        }

        if (response.failed()) throw new ChangeIslandFailed();

    }

    public void setActiveIsland(Island island) {
        setActiveIsland(island.getUniqueId());
    }

    public void setActiveIsland(IslandType islandType) {
        Island island = getIsland(islandType);
        if (island == null) throw new ChangeIslandFailed("Cannot switch to unowned island: " + islandType);
        setActiveIsland(island);
    }

    // TODO: successful collection has not been tested yet
    public void collectMineOnActiveIsland() {

        CollectMineResponse mainResponse;
        UpdateStructure updateStructure;

        try {
            List<SfsResponseModel> responses = sfsClient.requestResponses(new CollectMineRequest()).get();
            mainResponse = (CollectMineResponse)responses.getFirst();
            updateStructure = (UpdateStructure)responses.get(1);
        }
        catch (ExecutionException | InterruptedException | IndexOutOfBoundsException ex) {
            throw new CollectMineFailed("", ex);
        }

        if (mainResponse.failed()) {
            throw new CollectMineFailed(mainResponse.message);
        }

        update(updateStructure.properties);

        Mine mine = activeIsland.getMine();
        if (mine != null) mine.update(updateStructure.properties);

    }

    public boolean isMineOnActiveIslandReady() {
        Mine mine = activeIsland.getMine();
        if (mine == null) return false;
        return mine.isReady();
    }

    public Island buyIsland(int typeId, String name) {

        if (typeId == IslandType.TRIBAL_ISLAND.getId() && name.isBlank()) {
            throw new BuyIslandFailed("A name must be specified to buy Tribal Island");
        }

        if (typeId != IslandType.TRIBAL_ISLAND.getId() && name != null && !name.isEmpty()) {
            logger.warn("Name parameter (probably) has no effect when buying islands other than Tribal Island");
        }

        BuyIslandRequest request = new BuyIslandRequest();
        request.islandId = typeId;
        request.islandName = name;
        request.starpowerPurchase = false;

        BuyIslandResponse response;
        try {
            response = (BuyIslandResponse)sfsClient.requestResponses(request).get().getFirst();
        }
        catch (ExecutionException | InterruptedException | IndexOutOfBoundsException ex) {
            throw new BuyIslandFailed("", ex);
        }

        if (response.failed()) {
            throw new BuyIslandFailed(response.message);
        }

        update(response.properties);

        Island island = Island.buildIsland(this, response.userIsland);
        islands.add(island);

        return island;

        // TODO
        // If this is ever documented, note that
        // the actual game will switch to the new island
        // as the active island afterwards, always.

    }

    public Island buyIsland(IslandType islandType, String name) {
        return buyIsland(islandType.getId(), name);
    }

    public Island buyIsland(int typeId) {
        return buyIsland(typeId, "");
    }

    public Island buyIsland(IslandType islandType) {
        return buyIsland(islandType.getId());
    }

}