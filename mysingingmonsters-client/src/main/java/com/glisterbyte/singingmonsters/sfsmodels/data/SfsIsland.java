package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

import java.util.List;

public class SfsIsland extends SfsModel {

    /* Skipped Keys: last_bred, num_torches, type, buyback, last_baked
      tiles (always empty?), costumes_owned, monsters_sold, torches,
      fuzer, last_player_level, costume_data, user
    */

    /**
     * The island's type ID
     */
    public int island;

    public long date_created;
    public long user_island_id;

    public int likes;
    public int dislikes;

    /**
     * Whether the island is marked for wishing torch lighting
     */
    public boolean light_torch_flag;

    /**
     * The Time Machine speed
     */
    public double warp_speed;

    @SfsArrayElementType(SfsMonster.class)
    public List<SfsMonster> monsters;

    @SfsArrayElementType(SfsStructure.class)
    public List<SfsStructure> structures;

    @SfsArrayElementType(SfsBaking.class)
    public List<SfsBaking> baking;

    @SfsArrayElementType(SfsMonsterEgg.class)
    public List<SfsMonsterEgg> eggs;

    @SfsArrayElementType(SfsBreeding.class)
    public List<SfsBreeding> breeding;

}
