package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsJsonArray;
import com.glisterbyte.singingmonsters.sfsmapping.SfsObjectField;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.List;

public class SfsStructureType extends SfsModel {

    // Skipped keys:
    // sound
    // y_offset
    // platforms
    // view_in_market
    // premium
    // view_in_starmarket
    // extra
    // min_server_version
    // graphic
    // requirements
    // show_in_levelup
    // entity_type (always "structure")
    // level (unlock level)
    // cost_sale

    public int cost_coins;
    public int cost_keys;
    public int cost_relics;
    public int cost_diamonds;
    public int cost_starpower;
    public int cost_medals;
    public int cost_eth_currency;

    /**
     * One of `nursery`, `breeding`, `mine`, `castle`, `bakery`, `decoration`,
     * `obstacle`, `time_machine`, `happiness_tree` (unity tree), `warehouse`,
     * `hotel`, `torch` (wishing torch), `recording_studio`, `buddy` (glowbe),
     * `fuzer`, `battle_gym`, `crucible`, `awakener`, `attuner`, `synthesizer`,
     * `nucleus`, `dish_harmonizer`, `polarity_amplifier`, `fugue`
     */
    public String structure_type;

    public int size_x;
    public int size_y;

    public int build_time;

    public String name; // codename
    public String description; // codename, usually name + "_DESC"

    public int structure_id;
    public int entity_id;

    public int xp;

    public int movable;
    public int sellable;

    /**
     * Structure type ID that this structure can be upgraded to, or 0 if it can't upgrade
     */
    public int upgrades_to; // structure ID, 0 if it doesn't upgrade

    /**
     * Serialized JSON array of island type IDs
     */
    @SfsOptional
    @SfsJsonArray
    @SfsArrayElementType(Integer.class)
    public List<Integer> allowed_on_island;

    /**
     * Contains extra data, whatever "extra" really means.
     * For castles, it has an integer "beds".
     */
    @SfsOptional
    @SfsObjectField
    public SFSObject extra;

}