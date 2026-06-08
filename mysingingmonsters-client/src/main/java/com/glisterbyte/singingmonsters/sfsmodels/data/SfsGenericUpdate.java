package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsEntryArray;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

@SfsEntryArray
public class SfsGenericUpdate extends SfsModel {
    public long coins_actual;
    public long diamonds_actual;
    public long food_actual;
    public long ethereal_currency_actual;
    public long keys_actual;
    public long relics_actual;
    public long egg_wildcards_actual;
    public long starpower_actual;
    public int xp;
    public int level;
    public String daily_bonus_type;
    public int daily_bonus_amount;
    public boolean has_free_ad_scratch;
    public long daily_relic_purchase_count;
    public int relic_diamond_cost;
    public long next_relic_reset;
    public int premium;
    public int earned_starpower;
    public long speed_up_credit;
    public int battle_xp;
    public int battle_level;
    public int medals;
}