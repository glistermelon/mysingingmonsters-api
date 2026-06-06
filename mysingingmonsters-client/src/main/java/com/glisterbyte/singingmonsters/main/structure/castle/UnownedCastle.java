package com.glisterbyte.singingmonsters.main.structure.castle;

import com.glisterbyte.singingmonsters.main.island.UnownedIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;
import com.glisterbyte.singingmonsters.main.structure.UnownedStructure;

public class UnownedCastle extends UnownedStructure implements ReadableCastle {

    public UnownedCastle(UnownedIsland island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
    }

    @Override
    public String toString() {
        return toString(super.toString());
    }

}