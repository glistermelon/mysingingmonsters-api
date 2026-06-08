package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class SfsBakeryRecipe extends SfsModel {

    // ignored fields
    // 'sprite'
    // 'min_server_version'
    // 'spritesheet'
    // 'last_changed'

    public int id;

    /**
     * The key for the local text resource corresponding to the recipe's name
     */
    public String label;

    /**
     * How much food one gets from baking it
     */
    public int food;

    /**
     * How much it costs, in coins, to bake
     */
    public int cost;

    /**
     * How much XP one gets from baking it
     */
    public int xp;

    /**
     * How long it takes to bake, in seconds
     */
    public int time; // in seconds

    /**
     * Whether or not the recipe is always available (i.e., not seasonal)
     */
    public int always_avail;

}