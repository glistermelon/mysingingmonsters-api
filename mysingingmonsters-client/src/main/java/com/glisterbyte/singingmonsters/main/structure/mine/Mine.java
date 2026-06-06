package com.glisterbyte.singingmonsters.main.structure.mine;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;
import com.glisterbyte.singingmonsters.sfsmodels.events.UpdateStructureEvent;

import java.time.Instant;

public class Mine extends Structure implements ReadableMine, ControllableMine {

    private Instant lastCollectionTime;

    @Override
    public void internalRefresh(SfsStructure sfsStructure, boolean first) {
        super.internalRefresh(sfsStructure, first);
        lastCollectionTime = Instant.ofEpochMilli(sfsStructure.last_collection);
    }

    public Mine(Island island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
    }

    public class EventHandler extends Structure.EventHandler {

        @Override
        public void handleUpdateEvent(UpdateStructureEvent event) {
            super.handleUpdateEvent(event);
            synchronized (Mine.this) {
                if (event.structureUpdate.last_collection != null)
                    lastCollectionTime = Instant.ofEpochMilli(event.structureUpdate.last_collection);
            }
        }

    }

    @Override
    public EventHandler getEventHandler() {
        return new EventHandler();
    }

    public synchronized Instant getLastCollectionTime() {
        return lastCollectionTime;
    }

    public void collect() throws InterruptedException, ClientException {
        getClient().getEventHandlerManager().getCollectMineHandler().request(this);
    }

    @Override
    public synchronized String toString() {
        return toString(super.toString());
    }

}