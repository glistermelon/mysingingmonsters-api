package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.sfsmodels.requests.VisitFriendRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.VisitFriendResponse;
import com.glisterbyte.singingmonsters.main.users.VisitData;

public class VisitFriendHandler extends UncorrelatedResultResponseHandler<Void, VisitFriendResponse, VisitData> {

    public VisitFriendHandler(EventHandlerInitArg arg) {
        super(arg, VisitFriendResponse.class);
    }

    @Override
    public VisitData handleSuccess(VisitFriendResponse event, Void requestData) throws InterruptedException, ClientException {
        return VisitData.buildVisitData(client, event.friend_object);
    }

    public VisitData request(long userId) throws InterruptedException, ClientException {
        VisitFriendRequest request = new VisitFriendRequest();
        request.user_id = userId;
        return Request.simpleRequest(this, request, null);
    }

}