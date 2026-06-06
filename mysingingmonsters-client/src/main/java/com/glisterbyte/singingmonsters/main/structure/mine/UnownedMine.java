package com.glisterbyte.singingmonsters.main.structure.mine;

import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.island.UnownedIsland;
import com.glisterbyte.singingmonsters.main.structure.UnownedStructure;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;

import java.time.Instant;

public class UnownedMine extends UnownedStructure implements ReadableMine {

    private final Instant lastCollectionTime;

    public UnownedMine(UnownedIsland island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
        lastCollectionTime = Instant.ofEpochMilli(sfsStructure.last_collection);
    }

    public synchronized Instant getLastCollectionTime() {
        return lastCollectionTime;
    }

    @Override
    public synchronized String toString() {
        return StringUtil.format("Mine(structure={}, ready={})", super.toString(), isReady());
    }

}