package com.glisterbyte.singingmonsters.main.structure;

import com.glisterbyte.singingmonsters.main.island.UnownedIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;

public class UnownedStructure extends AbstractStructure {

    public UnownedStructure(UnownedIsland island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
    }

    public synchronized UnownedIsland getIsland() {
        return (UnownedIsland)super.getIsland();
    }

}