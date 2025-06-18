package com.glisterbyte.SingingMonsters.SfsModels;

import com.glisterbyte.SfsMapping.SfsArrayElementType;

import java.util.List;

public class SfsPlayer {

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

    public int userId;
    public String country;
    public String displayName;

    public long coinsActual;
    public long diamondsActual;
    public int diamondsSpent;
    public long etherealCurrencyActual;
    public long foodActual;
    public long keysActual;
    public long relicsActual;
    public long starpowerActual;

    public int level;
    public int xp;

    @SfsArrayElementType(SfsIsland.class)
    public List<SfsIsland> islands;

}