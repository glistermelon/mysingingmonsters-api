package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Sends a friend request.
 */
@SfsCmd("gs_friend_request")
public class RequestFriendRequest extends SfsRequestModel {
    public String friend_code;
}