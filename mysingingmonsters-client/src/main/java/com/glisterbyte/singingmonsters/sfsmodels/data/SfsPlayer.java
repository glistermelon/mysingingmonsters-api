package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

import java.util.List;

public class SfsPlayer extends SfsModel {

    // Skipped Keys:
    // achievements
    // active_island_themes
    // bbb_id
    // client_platform
    // daily_cumulative_login
    // last_client_version
    // last_login
    // relic_diamond_cost
    // scaled_daily_reward
    // show_welcomeback
    // total_starpower_collected
    // + all undetermined values @ commit 96e34ad045d3aa08f4cc8b79ea33cd2b3b4f7372
    // new_mail


    public int user_id;

    /**
     * The user's country's [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) code
     */
    public String country;

    public String display_name;

    public long coins_actual;
    public long diamonds_actual;
    public long ethereal_currency_actual;
    public long food_actual;
    public long keys_actual;
    public long relics_actual;
    public long starpower_actual;
    public long egg_wildcards_actual;

    public BattleData battle;

    public int level;
    public int xp;

    @SfsArrayElementType(SfsIsland.class)
    public List<SfsIsland> islands;

    public long active_island;

    public SfsPlayerProfile profile;

    /**
        Currently, the below aren't actually used by the client.
    */

    public int diamonds_spent;

}