package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsJsonArray;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.main.catalog.Catalog;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

import java.util.List;

public class SfsMonster extends SfsModel {

    // Skipped Keys: book_value, costume

    /**
     * The monster's species ID
     */
    public int monster;

    /**
     * The user island ID of the monster's island
     */
    public long island;

    public long user_monster_id;

    public String name;
    public int pos_x;
    public int pos_y;
    public int flip;
    public int muted;
    public double volume;

    public int level;

    /**
     * Not cumulative; indicates progress towards the next level
     */
    public int times_fed;

    /**
     * The monster's happiness as a percentage. Can be negative (Wublin polarity is happiness under the hood)
     */
    public int happiness;

    public int in_hotel;

    public long last_collection;
    public long last_feeding;

    /**
     * Certain monsters, like Wublins, indicate what kind of currency will be collected at next collection
     */
    @SfsOptional
    public String collection_type;

    /**
     * When the timer started on filling a timed deactivated monster (like a Wublin)
     */
    @SfsOptional
    public Long egg_timer_start;

    /**
     * An array of monster species IDs, where an ID occurs as many times in the list as it is required to be zapped
     */
    @SfsOptional
    @SfsJsonArray
    @SfsArrayElementType(Integer.class)
    public List<Integer> box_requirements;

    /**
     * An array of monster species IDs, where an ID occurs as many times in the list as it has already been zapped.
     * This field is no longer present after activation (before activation, it is always present and can be empty).
     */
    @SfsOptional
    @SfsJsonArray
    @SfsArrayElementType(Integer.class)
    public List<Integer> boxed_eggs;

    /**
     * Duration (in minutes) after the last collection time at which currency is available for collection
     */
    @SfsOptional
    public Integer random_underling_collection_min;

    public boolean isEggBoxMonster(Catalog catalog) {
        if (catalog.getMonsterSpecies(monster).isWubbox()) return false;
        return box_requirements != null;
    }

}