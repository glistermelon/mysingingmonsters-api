package com.glisterbyte.singingmonsters.main.structure.nursery;

import com.glisterbyte.singingmonsters.main.island.UnownedIsland;
import com.glisterbyte.singingmonsters.main.structure.UnownedStructure;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;

public class UnownedNursery extends UnownedStructure implements ReadableNursery {

    private final NurseryState state;

    public UnownedNursery(UnownedIsland island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
        state = NurseryState.fromSfsIsland(island.getSfsModel(), sfsStructure, getCatalog());
    }

    public NurseryState getNurseryState() {
        return state;
    }

    @Override
    public String toString() {
        return toString(super.toString());
    }

}