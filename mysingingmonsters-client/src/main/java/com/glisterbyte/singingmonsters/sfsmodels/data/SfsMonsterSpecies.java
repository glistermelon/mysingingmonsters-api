package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsKey;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

import java.util.List;

public class SfsMonsterSpecies extends SfsModel {

    // Skipped Keys:
    // select_sound
    // keywords
    // portrait_graphic
    // y_offset
    // view_in_market
    // premium
    // view_in_starmarket
    // min_server_version
    // graphic
    // level (is this unlock level?)
    // time_availability
    // entity_type (always "monster")
    // spore_graphic
    // last_changed
    // class
    // cost_sale
    // season_event_name
    // month_string
    // fanfare_graphic
    // extra
    // sigil_graphic (this is how seasonal elements are shown without actually existing as genes)
    // requirements
    // movable
    // time_to_fill_sec
    // native_island
    // levelup_island

    /**
     * Monster species ID
     */
    public int monster_id;

    public int entity_id;
    
    public int cost_coins;
    public int cost_keys;
    public int cost_relics;
    public int cost_diamonds;
    public int cost_starpower;
    public int cost_medals;
    public int cost_eth_currency;
    
    public int size_x;
    public int size_y;

    /**
     * Incubation time (without enhancements)
     */
    public int build_time;

    /**
     * Key for the local text resource corresponding to the monster's name
     */
    public String name;

    /**
     * Key for the local text resource corresponding to the monster's description; typically name+"_DESC"
     */
    public String description;

    /**
     * A distinct English name for the species
     */
    public String common_name;

    /**
     * Family
     */
    public String fam;

    @SfsKey("class")
    public String monsterClass;

    /**
     * Each letter in the string corresponds to an <SfsGene> (an element).
     */
    public String genes; // this is empty for seasonal monsters

    /**
     * Whether or not the monster is visible in the market (not including the starmarket or buybacks).
     */
    public int view_in_market;

    /**
     * Whether or not the monster is visible in the starmarket.
     */
    public int view_in_starmarket;

    /**
     * The monster's likes.
     */
    @SfsArrayElementType(SfsMonsterLike.class)
    public List<SfsMonsterLike> happiness;

    public int xp;
    public int beds;

    /**
     * Who "discovered" the monster. Defaults to "DISCOVERED_BY_COMMUNITY"
     */
    @SfsOptional
    public String discovered_by;

    /**
     * The default names for the monster.
     */
    @SfsArrayElementType(String.class)
    public List<String> names;

    @SfsOptional
    @SfsArrayElementType(SfsMonsterLevel.class)
    public List<SfsMonsterLevel> levels;

    /**
     * For monsters that have an activation time limit,
     * the number of seconds the user has to fill the monster after starting.
     */
    public int time_to_fill_sec;

}