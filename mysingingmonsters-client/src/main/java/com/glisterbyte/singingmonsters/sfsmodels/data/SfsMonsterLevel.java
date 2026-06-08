package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class SfsMonsterLevel extends SfsModel {

    /**
     * Which level (1-20) this data corresponds to
     */
    public int level;

    /**
     * How much food is required for a single feeding
     */
    public int food;

    /**
     * Coins produced per minute
     */
    @SfsOptional
    public Integer coins;

    @SfsOptional
    public Integer max_coins;

    /**
     * Shards produced per hour
     */
    @SfsOptional
    public Integer ethereal_currency;

    @SfsOptional
    public Integer max_ethereal;

}