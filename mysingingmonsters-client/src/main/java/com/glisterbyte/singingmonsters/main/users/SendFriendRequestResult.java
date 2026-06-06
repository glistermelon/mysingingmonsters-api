package com.glisterbyte.singingmonsters.main.users;

import com.glisterbyte.singingmonsters.common.StringUtil;

public class SendFriendRequestResult {

    private final Friend friend;
    private final FriendRequest request;

    public SendFriendRequestResult(Friend friend) {
        this.friend = friend;
        request = null;
    }

    public SendFriendRequestResult(FriendRequest request) {
        friend = null;
        this.request = request;
    }

    public boolean friendAddedImmediately() {
        return friend != null;
    }

    public boolean friendRequestPending() {
        return request != null;
    }

    public Friend getFriend() {
        return friend;
    }

    public FriendRequest getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return StringUtil.format(
                "SendFriendRequestResult(friend={}, request={})",
                friend, request
        );
    }

}