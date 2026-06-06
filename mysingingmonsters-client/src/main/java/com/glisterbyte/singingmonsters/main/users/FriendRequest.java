package com.glisterbyte.singingmonsters.main.users;

import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.catalog.Catalog;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsFriendRequest;

public class FriendRequest {

    private final long requestId;
    private final User recipient;

    private FriendRequest(Catalog catalog, SfsFriendRequest req) {
        requestId = req.request_id;
        recipient = User.buildUser(catalog, req);
    }

    public static FriendRequest buildFriendRequest(Catalog catalog, SfsFriendRequest req) {
        return new FriendRequest(catalog, req);
    }

    public long getRequestId() {
        return requestId;
    }

    public User getRecipient() {
        return recipient;
    }

    @Override
    public String toString() {
        return StringUtil.format(
                "FriendRequest(id={}, recipient={})",
                requestId, recipient
        );
    }

}