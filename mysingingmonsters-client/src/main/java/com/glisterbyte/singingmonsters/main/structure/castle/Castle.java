package com.glisterbyte.singingmonsters.main.structure.castle;

import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;

public class Castle extends Structure implements ReadableCastle {

    public Castle(Island island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
    }

    @Override
    public String toString() {
        return toString(super.toString());
    }

}