package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsJsonObject;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class SfsFriendRequest extends SfsModel {

    @SfsJsonObject
    public SfsUserDisplayData data;

    public String request_type;
    public int level;
    public long last_login;
    public long date_created;

    @SfsOptional
    public Long total_starpower_collected;

    public String pp_info;
    public String display_name;
    public int pp_type;
    public long bbb_id;
    public int user_id;
    public int xp;
    public long request_id;

}