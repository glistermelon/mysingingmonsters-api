package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsJsonObject;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class SfsFriend extends SfsModel {

    public String display_name;
    public int level;
    public int xp;
    public long total_starpower_collected;
    public long tribe;
    public long date_created;
    public long last_login;

    public int user_id;
    public long bbb_id;
    public String friend_code;

    public boolean is_favorite;
    public boolean has_unlit_torches;
    public boolean has_unlit_highlighted_torches;
    public int discoverable;
    public int follow_permission;
    public int followback_permission;

    public int canPvp;
    public int wonBattles;
    public int lostBattles;
    public int tier;
    public int prev_tier;
    public long rank;
    public long prev_rank;
    public int battle_level;

    @SfsJsonObject
    public SfsUserDisplayData data;

    public String pp_info;
    public int pp_type;

    public int litByMe;
    public int litByFriend;

    public boolean reciprocal;

}