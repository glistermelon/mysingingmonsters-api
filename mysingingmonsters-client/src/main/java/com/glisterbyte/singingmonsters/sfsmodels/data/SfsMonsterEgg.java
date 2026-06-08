package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class SfsMonsterEgg extends SfsModel {

    // skipped: 'book_value', 'island', 'costume'

    public long user_egg_id;

    /**
     * The user structure ID of the nursery
     */
    public long structure;

    /**
     * The monster species ID for the egg
     */
    public int monster;

    /**
     * Timestamp at which the egg hatches
     */
    public long hatches_on;

    /**
     * Timestamp when the egg began incubating
     */
    public long laid_on;

}