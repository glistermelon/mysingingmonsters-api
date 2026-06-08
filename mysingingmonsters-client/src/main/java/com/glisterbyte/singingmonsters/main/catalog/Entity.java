package com.glisterbyte.singingmonsters.main.catalog;

import com.glisterbyte.singingmonsters.main.common.Size;
import com.glisterbyte.singingmonsters.main.common.HasSfsData;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonsterSpecies;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructureType;

public abstract class Entity implements HasSfsData {

    private final int entityId;
    private final Size size;

    public Entity(SfsMonsterSpecies data) {
        entityId = data.entity_id;
        size = new Size(data.size_x, data.size_y);
    }

    public Entity(SfsStructureType data) {
        entityId = data.entity_id;
        size = new Size(data.size_x, data.size_y);
    }

    public abstract Catalog getCatalog();

    public int getEntityId() {
        return entityId;
    }

    public Size getSize() {
        return size;
    }

}
