package com.glisterbyte.singingmonsters.main.structure;

import com.glisterbyte.singingmonsters.main.catalog.StructureType;
import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.catalog.Entity;
import com.glisterbyte.singingmonsters.main.client.Client;
import com.glisterbyte.singingmonsters.main.client.HasClient;
import com.glisterbyte.singingmonsters.main.common.Position;
import com.glisterbyte.singingmonsters.main.common.Timer;
import com.glisterbyte.singingmonsters.main.island.AbstractIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;
import com.smartfoxserver.v2.entities.data.SFSObject;
import org.apache.commons.lang3.Validate;

import java.time.Instant;

public abstract class AbstractStructure implements ReadableStructure, HasClient {

    private final AbstractIsland island;

    private SFSObject sfsData;
    private SfsStructure initialSfsModel;

    protected final long userStructureId;

    protected StructureType structureType;

    protected Position position;
    protected boolean flipped;
    protected boolean muted;
    protected double scale;

    protected boolean inWarehouse;
    protected boolean isUpgrading;

    protected Timer timer;

    public void internalRefresh(SfsStructure sfsStructure, boolean first) {

        if (!first) {

            Validate.isTrue(
                    userStructureId == sfsStructure.user_structure_id,
                    "User structure id refresh mismatch; expected %s, got %s",
                    userStructureId, sfsStructure.user_structure_id
            );

            assert structureType != null;
            StructureCategory category = structureType.getStructureCategory();
            StructureCategory newCategory
                    = getCatalog().getStructureType(sfsStructure.structure).getStructureCategory();
            Validate.isTrue(
                    category == newCategory,
                    "Structure category refresh mismatch; expected %s, got %s",
                    category, newCategory
            );

        }

        sfsData = sfsStructure.sfsObject;
        initialSfsModel = sfsStructure;
        structureType = getCatalog().getStructureType(sfsStructure.structure);
        position = new Position(sfsStructure.pos_x, sfsStructure.pos_y);
        flipped = sfsStructure.flip == 1;
        muted = sfsStructure.muted == 1;
        scale = sfsStructure.scale;
        inWarehouse = sfsStructure.in_warehouse == 1;
        isUpgrading = sfsStructure.is_upgrading == 1;
        if (sfsStructure.date_created != null && sfsStructure.building_completed != null) {
            timer = new Timer(
                    Instant.ofEpochMilli(sfsStructure.date_created),
                    Instant.ofEpochMilli(sfsStructure.building_completed)
            );
        }
        else timer = null;

        Validate.isTrue(
                structureType != null, "Unrecognized structure type id %s", sfsStructure.structure
        );

    }

    protected AbstractStructure(AbstractIsland island, SfsStructure sfsStructure) {
        this.island = island;
        userStructureId = sfsStructure.user_structure_id;
        internalRefresh(sfsStructure, true);
    }

    public synchronized AbstractIsland getIsland() {
        return island;
    }

    public synchronized Client getClient() {
        return island.getClient();
    }

    public synchronized SFSObject getSfsData() {
        return sfsData;
    }

    public synchronized Entity getEntity() {
        return structureType;
    }

    public synchronized SfsStructure getSfsModel() {
        return initialSfsModel;
    }

    public synchronized long getUserStructureId() {
        return userStructureId;
    }

    public synchronized StructureType getStructureType() {
        return structureType;
    }

    public synchronized Position getPosition() {
        return position;
    }

    public synchronized boolean isFlipped() {
        return flipped;
    }

    public synchronized boolean isMuted() {
        return muted;
    }

    public synchronized double getScale() {
        return scale;
    }

    public synchronized boolean isInWarehouse() {
        return inWarehouse;
    }

    public synchronized boolean isUpgrading() {
        return isUpgrading;
    }

    public synchronized String getName() {
        return structureType.getName();
    }

    @Override
    public String toString() {
        return StringUtil.format(
                "Structure(type={}, id={}, position={})",
                structureType, userStructureId, position
        );
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AbstractStructure other)) return false;
        return getIsland().equals(other.getIsland()) && userStructureId == other.getUserStructureId();
    }

}