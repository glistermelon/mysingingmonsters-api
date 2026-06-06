package com.glisterbyte.singingmonsters.main.structure.obstacle;

import com.glisterbyte.singingmonsters.main.common.Timer;
import com.glisterbyte.singingmonsters.main.island.UnownedIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;
import com.glisterbyte.singingmonsters.main.structure.UnownedStructure;

public class UnownedObstacle extends UnownedStructure implements ReadableObstacle {

    public UnownedObstacle(UnownedIsland island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
    }

    public Timer getRemovalTimer() {
        return timer;
    }

    @Override
    public String toString() {
        return toString(super.toString());
    }

}