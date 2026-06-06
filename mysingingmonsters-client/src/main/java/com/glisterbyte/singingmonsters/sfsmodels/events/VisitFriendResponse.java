package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsVisitData;
import com.glisterbyte.singingmonsters.sfsmodels.SfsResultResponse;

@SfsCmd("gs_get_friend_visit_data")
public class VisitFriendResponse extends SfsResultResponse {
    public SfsVisitData friend_object;
}