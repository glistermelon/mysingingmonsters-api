package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsFriend;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsFriendRequest;
import com.glisterbyte.singingmonsters.sfsmodels.SfsResultResponse;

import java.util.List;

@SfsCmd("gs_friend_request")
public class RequestFriendResponse extends SfsResultResponse {

    @SfsOptional
    @SfsArrayElementType(SfsFriendRequest.class)
    public List<SfsFriendRequest> request;

    @SfsOptional
    @SfsArrayElementType(SfsFriend.class)
    public List<SfsFriend> friends;

}