package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

import java.util.List;

public class SfsVisitData extends SfsModel {

    /*
        skipped: clubboxes, active_island_themes, friend_gift, tracks, songs,
                 owned_island_themes, user, ratings, torch_gifts
     */

    public String display_name;
    public int level;

    @SfsOptional
    public String country;

    public long starpower;
    public long total_starpower_collected;
    public long keys;

    public int user_id;
    public long bbb_id;

    @SfsArrayElementType(SfsIsland.class)
    public List<SfsIsland> islands;

    public long active_island;

}