package com.glisterbyte.singingmonsters.main.structure.obstacle;

import com.glisterbyte.singingmonsters.main.common.Timer;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;

import java.time.Duration;

public class Obstacle extends Structure implements ReadableObstacle, ControllableObstacle {

    public Obstacle(Island island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
    }

    public Timer getRemovalTimer() {
        return timer;
    }

    public Duration startRemoval() throws InterruptedException, ClientException {
        return getClient().getEventHandlerManager().getStartRemoveObstacleHandler().request(this);
    }

    public void finishRemoval() throws InterruptedException, ClientException {
        getClient().getEventHandlerManager().getClearObstacleHandler().request(this);
    }

    @Override
    public String toString() {
        return toString(super.toString());
    }

}