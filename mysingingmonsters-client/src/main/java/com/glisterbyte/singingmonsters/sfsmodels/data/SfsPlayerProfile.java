package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmapping.SfsJsonObject;
import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class SfsPlayerProfile extends SfsModel {

    @SfsJsonObject
    public SfsUserDisplayData data;

    public String friend_code;

    public int level;
    public boolean discoverable;

    public int follow_permission;
    public int followback_permission;

}