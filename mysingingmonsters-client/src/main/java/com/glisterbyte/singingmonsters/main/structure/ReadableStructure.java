package com.glisterbyte.singingmonsters.main.structure;

import com.glisterbyte.singingmonsters.main.catalog.StructureType;
import com.glisterbyte.singingmonsters.main.client.HasClient;
import com.glisterbyte.singingmonsters.main.common.HasSfsData;
import com.glisterbyte.singingmonsters.main.common.Positioned;
import com.glisterbyte.singingmonsters.main.common.Size;
import com.glisterbyte.singingmonsters.main.island.ReadableIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;

public interface ReadableStructure extends HasClient, HasSfsData, Positioned {

    ReadableIsland getIsland();
    SfsStructure getSfsModel();
    long getUserStructureId();
    StructureType getStructureType();
    boolean isFlipped();
    boolean isMuted();
    double getScale();
    boolean isInWarehouse();
    boolean isUpgrading();
    String getName();

    default StructureCategory getStructureCategory() {
        return getStructureType().getStructureCategory();
    }

    @Override
    default Size getSize() {
        return getStructureType().getSize();
    }

}