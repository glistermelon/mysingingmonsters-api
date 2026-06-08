package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class SfsBaking extends SfsModel {

    public long user_baking_id;

    /**
     * Timestamp when baking started
     */
    public long started_at;

    /**
     * Timestamp at which baking finishes
     */
    public long finished_at;

    /**
     * User island id of the island where the corresponding bakery is
     */
    public long island; // unique island id

    /**
     * User structure id of the corresponding bakery
     */
    public long user_structure; // user structure id (the bakery)

    /**
     * How much food the baking will produce
     */
    public int food_count;

    /**
     * The ID of the bakery recipe
     */
    public int food_option_id;

}