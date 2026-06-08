package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class SfsBreeding extends SfsModel {

    public long user_breeding_id;

    /**
     * User island id of the island where the breeding structure is
     */
    public long island;

    /**
     * User structure id of the breeding structure
     */
    public long structure;

    /**
     * Species ID of the first monster bred
     */
    public int monster_1;

    /**
     * Species ID of the second monster bred
     */
    public int monster_2;

    /**
     * Species ID of the resulting monster
     */
    public int new_monster;

    /**
     * Timestamp at which breeding started
     */
    public long started_on;

    /**
     * Timestamp at which breeding finishes
     */
    public long complete_on;

}