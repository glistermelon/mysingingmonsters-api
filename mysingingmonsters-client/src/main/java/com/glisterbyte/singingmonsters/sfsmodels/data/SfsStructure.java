package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class SfsStructure extends SfsModel {

    /**
     * The structure type iD
     */
    public int structure;

    /**
     * The user island ID of the structure's island
     */
    public long island;

    public long user_structure_id;

    public int pos_x;
    public int pos_y;
    public int flip;
    public int muted;
    public double scale;

    public int in_warehouse;

    public int is_upgrading;
    public int is_complete;

    /**
     * A timestamp
     */
    @SfsOptional
    public Long last_collection;

    /**
        When a structure has some pending operation in the game that has
        a timer associated with it, the timer is between
        date_created and building_completed.
     */

    /**
     * The timestamp at which a timer on the structure starts (for example, clearing an obstacle)
     */
    @SfsOptional
    public Long date_created;

    /**
     * The timestamp at which a timer on the structure will finish (for example, clearing an obstacle)
     */
    @SfsOptional
    public Long building_completed;

}