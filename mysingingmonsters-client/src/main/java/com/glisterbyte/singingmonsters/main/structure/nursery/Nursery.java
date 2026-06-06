package com.glisterbyte.singingmonsters.main.structure.nursery;

import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.exceptions.ActionFailedException;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.monster.MonsterPlacement;
import com.glisterbyte.singingmonsters.main.monster.Monster;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;
import com.glisterbyte.singingmonsters.sfsmodels.events.BuyEggResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.FinishBreedingResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.HatchEggResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.SellEggResponse;

public class Nursery extends Structure implements ReadableNursery, ControllableNursery {

    private volatile NurseryState state;

    @Override
    public void internalRefresh(SfsStructure sfsStructure, boolean first) {
        super.internalRefresh(sfsStructure, first);
        state = NurseryState.fromSfsIsland(getIsland().getSfsModel(), sfsStructure, getCatalog());
    }

    public Nursery(Island island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
    }

    public class EventHandler extends Structure.EventHandler {

        public synchronized void handleBuyEggEvent(BuyEggResponse event) {
            synchronized (Nursery.this) {
                state = NurseryState.fromSfsMonsterEgg(event.user_egg, getCatalog());
            }
        }

        public synchronized void handleFinishBreedingEvent(FinishBreedingResponse event) {
            synchronized (Nursery.this) {
                validateUserStructureId(event.user_structure_id);
                state = NurseryState.fromSfsMonsterEgg(event.user_egg, getCatalog());
            }
        }

        public synchronized void handleHatchEggEvent(HatchEggResponse event) {
            synchronized (Nursery.this) {
                state = null;
            }
        }

        public synchronized void handleSellEggEvent(SellEggResponse event) {
            synchronized (Nursery.this) {
                state = null;
            }
        }

    }

    @Override
    public EventHandler getEventHandler() {
        return new EventHandler();
    }

    public NurseryState getNurseryState() {
        return state;
    }

    @Override
    public String toString() {
        return toString(super.toString());
    }

    public void buyEgg(MonsterSpecies species) throws InterruptedException, ClientException {
        // egg field is updated by the event handler
        getClient().getEventHandlerManager().getBuyEggHandler().request(this, species);
    }

    public Monster hatchEgg(MonsterPlacement placement) throws InterruptedException, ClientException {
        return getClient().getEventHandlerManager().getHatchEggHandler().request(this, placement);
    }

    public Monster hatchEgg() throws InterruptedException, ClientException {
        return hatchEgg(MonsterPlacement.createDefault());
    }

    public void sellEgg() throws InterruptedException, ClientException {
        if (!isDone()) throw new ActionFailedException("Nursery does not have a ready egg");
        getClient().getEventHandlerManager().getSellEggHandler().request(this);
    }

}