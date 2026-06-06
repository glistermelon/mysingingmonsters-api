package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsFriend;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsFriendRequest;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsTribe;
import com.glisterbyte.singingmonsters.sfsmodels.SfsResultResponse;

import java.util.List;

@SfsCmd("gs_get_friends")
public class GetFriendsResponse extends SfsResultResponse {

    // Incoming requests have key 'requests' but I haven't tested it yet

    @SfsArrayElementType(SfsFriendRequest.class)
    public List<SfsFriendRequest> pending;

    @SfsArrayElementType(SfsFriend.class)
    public List<SfsFriend> friends;

    @SfsArrayElementType(SfsTribe.class)
    public List<SfsTribe> tribes;

    @SfsArrayElementType(SfsTribe.class)
    public List<SfsTribe> top_tribes;

}