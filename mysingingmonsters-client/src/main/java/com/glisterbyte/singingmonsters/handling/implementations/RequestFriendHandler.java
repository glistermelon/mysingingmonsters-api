package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.main.users.SendFriendRequestResult;
import com.glisterbyte.singingmonsters.sfsmodels.requests.RequestFriendRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.RequestFriendResponse;

public class RequestFriendHandler extends UncorrelatedResultResponseHandler<Void, RequestFriendResponse, SendFriendRequestResult> {

    public RequestFriendHandler(EventHandlerInitArg arg) {
        super(arg, RequestFriendResponse.class);
    }

    @Override
    public SendFriendRequestResult handleSuccess(RequestFriendResponse event, Void requestData) throws InterruptedException, ClientException {
        return client.getEventHandler().handleRequestFriendEvent(event);
    }

    public SendFriendRequestResult request(String friendCode) throws InterruptedException, ClientException {
        RequestFriendRequest request = new RequestFriendRequest();
        request.friend_code = friendCode;
        return Request.simpleRequest(this, request, null);
    }

}