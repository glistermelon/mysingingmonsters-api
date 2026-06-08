package com.glisterbyte.singingmonsters.main.structure.bakery;

import com.glisterbyte.singingmonsters.main.catalog.BakeryRecipe;
import com.glisterbyte.singingmonsters.exceptions.ActionFailedException;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.sfsmodels.events.FinishBakingResponse;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;
import com.glisterbyte.singingmonsters.sfsmodels.events.StartBakingResponse;
import org.apache.commons.lang3.Validate;

public class Bakery extends Structure implements ReadableBakery {

    private volatile BakeryState bakeryState;

    @Override
    public void internalRefresh(SfsStructure sfsStructure, boolean first) {
        super.internalRefresh(sfsStructure, first);
        bakeryState = BakeryState.fromSfsIsland(getIsland().getSfsModel(), sfsStructure);
    }

    public Bakery(Island island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
    }

    public class EventHandler extends Structure.EventHandler {

        public void handleStartBakingEvent(StartBakingResponse event) {
            synchronized (Bakery.this) {
                validateUserStructureId(event.user_structure_id);
                bakeryState = BakeryState.fromSfsBaking(event.user_baking);
            }
        }

        // This is called when the user collects the finished food, not when the food becomes ready.
        public void handleFinishBakingEvent(FinishBakingResponse event) {
            synchronized (Bakery.this) {
                Validate.isTrue(bakeryState != null, "Expected non-idle bakery for finish baking event");
                Validate.isTrue(
                        event.user_baking_id == bakeryState.bakingId(),
                        "Expected finish event user baking id %s to match actual user baking id %s",
                        event.user_baking_id, bakeryState.bakingId()
                );
                bakeryState = null;
            }
        }

    }

    @Override
    public EventHandler getEventHandler() {
        return new EventHandler();
    }

    public BakeryState getBakeryState() {
        return bakeryState;
    }

    @Override
    public synchronized String toString() {
        return toString(super.toString());
    }

    public void bake(BakeryRecipe recipe) throws InterruptedException, ClientException {
        getClient().getEventHandlerManager().getStartBakingHandler().request(this, recipe);
    }

    public int collect() throws InterruptedException, ClientException {
        if (isIdle()) throw new ActionFailedException("Cannot collect from idle bakery");
        int amount = getCurrentRecipe().getAmount();
        getClient().getEventHandlerManager().getFinishBakingHandler().request(this);
        return amount;
    }

}