package com.glisterbyte.singingmonsters.handling;

import com.glisterbyte.singingmonsters.networking.WebsocketClient;
import com.glisterbyte.singingmonsters.main.client.Client;
import com.glisterbyte.singingmonsters.handling.implementations.*;

import java.util.*;

public class EventHandlerManager {

    private final BoxActivateMonsterHandler boxActivateMonsterHandler;
    private final BoxEggHandler boxEggHandler;
    private final BreedMonstersHandler breedMonstersHandler;
    private final BuyEggHandler buyEggHandler;
    private final BuyIslandHandler buyIslandHandler;
    private final BuyStructureHandler buyStructureHandler;
    private final ClearObstacleHandler clearObstacleHandler;
    private final CollectMineHandler collectMineHandler;
    private final CollectMonsterHandler collectMonsterHandler;
    private final FeedMonsterHandler feedMonsterHandler;
    private final FinishBakingHandler finishBakingHandler;
    private final FinishBreedingHandler finishBreedingHandler;
    private final FlipMonsterHandler flipMonsterHandler;
    private final FlipStructureHandler flipStructureHandler;
    private final GetFriendsHandler getFriendsHandler;
    private final GetPlayerHandler getPlayerHandler;
    private final HatchEggHandler hatchEggHandler;
    private final MoveMonsterHandler moveMonsterHandler;
    private final MoveStructureHandler moveStructureHandler;
    private final MultiMonsterUpdateHandler multiMonsterUpdateHandler;
    private final MuteMonsterHandler muteMonsterHandler;
    private final RequestFriendHandler requestFriendHandler;
    private final SellEggHandler sellEggHandler;
    private final SellMonsterHandler sellMonsterHandler;
    private final SellStructureHandler sellStructureHandler;
    private final StartBakingHandler startBakingHandler;
    private final StartRemoveObstacleHandler startRemoveObstacleHandler;
    private final UpdateMonsterHandler updateMonsterHandler;
    private final UpdateStructureHandler updateStructureHandler;
    private final VisitFriendHandler visitFriendHandler;

    private final List<EventHandler<?, ?, ?>> allHandlers = new ArrayList<>();

    public EventHandlerManager(Client client, WebsocketClient wsClient) {

        EventHandlerInitArg arg = new EventHandlerInitArg(
                client, wsClient, this
        );

        boxActivateMonsterHandler = new BoxActivateMonsterHandler(arg);
        boxEggHandler = new BoxEggHandler(arg);
        breedMonstersHandler = new BreedMonstersHandler(arg);
        buyEggHandler = new BuyEggHandler(arg);
        buyIslandHandler = new BuyIslandHandler(arg);
        buyStructureHandler = new BuyStructureHandler(arg);
        clearObstacleHandler = new ClearObstacleHandler(arg);
        collectMineHandler = new CollectMineHandler(arg);
        collectMonsterHandler = new CollectMonsterHandler(arg);
        feedMonsterHandler = new FeedMonsterHandler(arg);
        finishBakingHandler = new FinishBakingHandler(arg);
        finishBreedingHandler = new FinishBreedingHandler(arg);
        flipMonsterHandler = new FlipMonsterHandler(arg);
        flipStructureHandler = new FlipStructureHandler(arg);
        getFriendsHandler = new GetFriendsHandler(arg);
        getPlayerHandler = new GetPlayerHandler(arg);
        hatchEggHandler = new HatchEggHandler(arg);
        moveMonsterHandler = new MoveMonsterHandler(arg);
        moveStructureHandler = new MoveStructureHandler(arg);
        multiMonsterUpdateHandler = new MultiMonsterUpdateHandler(arg);
        muteMonsterHandler = new MuteMonsterHandler(arg);
        requestFriendHandler = new RequestFriendHandler(arg);
        sellEggHandler = new SellEggHandler(arg);
        sellMonsterHandler = new SellMonsterHandler(arg);
        sellStructureHandler = new SellStructureHandler(arg);
        startBakingHandler = new StartBakingHandler(arg);
        startRemoveObstacleHandler = new StartRemoveObstacleHandler(arg);
        updateMonsterHandler = new UpdateMonsterHandler(arg);
        updateStructureHandler = new UpdateStructureHandler(arg);
        visitFriendHandler = new VisitFriendHandler(arg);

        for (var field : getClass().getDeclaredFields()) {
            if (EventHandler.class.isAssignableFrom(field.getType())) {
                boolean accessible = field.canAccess(this);
                field.setAccessible(true);
                try {
                    allHandlers.add((EventHandler<?, ?, ?>)field.get(this));
                }
                catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                field.setAccessible(accessible);
            }
        }

    }

    public BoxActivateMonsterHandler getBoxActivateMonsterHandler() {
        return boxActivateMonsterHandler;
    }

    public BoxEggHandler getBoxEggHandler() {
        return boxEggHandler;
    }

    public BreedMonstersHandler getBreedMonstersHandler() {
        return breedMonstersHandler;
    }

    public BuyEggHandler getBuyEggHandler() {
        return buyEggHandler;
    }

    public BuyIslandHandler getBuyIslandHandler() {
        return buyIslandHandler;
    }

    public BuyStructureHandler getBuyStructureHandler() {
        return buyStructureHandler;
    }

    public ClearObstacleHandler getClearObstacleHandler() {
        return clearObstacleHandler;
    }

    public CollectMineHandler getCollectMineHandler() {
        return collectMineHandler;
    }

    public CollectMonsterHandler getCollectMonsterHandler() {
        return collectMonsterHandler;
    }

    public FeedMonsterHandler getFeedMonsterHandler() {
        return feedMonsterHandler;
    }

    public FinishBakingHandler getFinishBakingHandler() {
        return finishBakingHandler;
    }

    public FinishBreedingHandler getFinishBreedingHandler() {
        return finishBreedingHandler;
    }

    public FlipMonsterHandler getFlipMonsterHandler() {
        return flipMonsterHandler;
    }

    public FlipStructureHandler getFlipStructureHandler() {
        return flipStructureHandler;
    }

    public GetFriendsHandler getGetFriendsHandler() {
        return getFriendsHandler;
    }

    public GetPlayerHandler getGetPlayerHandler() {
        return getPlayerHandler;
    }

    public HatchEggHandler getHatchEggHandler() {
        return hatchEggHandler;
    }

    public MoveMonsterHandler getMoveMonsterHandler() {
        return moveMonsterHandler;
    }

    public MoveStructureHandler getMoveStructureHandler() {
        return moveStructureHandler;
    }

    public MultiMonsterUpdateHandler getMultiMonsterUpdateHandler() {
        return multiMonsterUpdateHandler;
    }

    public MuteMonsterHandler getMuteMonsterHandler() {
        return muteMonsterHandler;
    }

    public RequestFriendHandler getRequestFriendHandler() {
        return requestFriendHandler;
    }

    public SellEggHandler getSellEggHandler() {
        return sellEggHandler;
    }

    public SellMonsterHandler getSellMonsterHandler() {
        return sellMonsterHandler;
    }

    public SellStructureHandler getSellStructureHandler() {
        return sellStructureHandler;
    }

    public StartBakingHandler getStartBakingHandler() {
        return startBakingHandler;
    }

    public StartRemoveObstacleHandler getStartRemoveObstacleHandler() {
        return startRemoveObstacleHandler;
    }

    public UpdateMonsterHandler getUpdateMonsterHandler() {
        return updateMonsterHandler;
    }

    public UpdateStructureHandler getUpdateStructureHandler() {
        return updateStructureHandler;
    }

    public VisitFriendHandler getVisitFriendHandler() {
        return visitFriendHandler;
    }

    public List<EventHandler<?, ?, ?>> getAllHandlers() {
        return allHandlers;
    }

}