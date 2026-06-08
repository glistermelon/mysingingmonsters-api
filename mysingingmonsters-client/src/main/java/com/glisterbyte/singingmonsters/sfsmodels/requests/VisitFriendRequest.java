package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Gets visit data for a user.
 */
@SfsCmd("gs_get_friend_visit_data")
public class VisitFriendRequest extends SfsRequestModel {
    public long user_id;
}