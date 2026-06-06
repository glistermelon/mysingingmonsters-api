package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.sfsmodels.events.GetFriendsResponse;

public class GetFriendsHandler extends UncorrelatedResultResponseHandler<Void, GetFriendsResponse, Void> {

    public GetFriendsHandler(EventHandlerInitArg arg) {
        super(arg, GetFriendsResponse.class);
    }

    @Override
    public Void handleSuccess(GetFriendsResponse event, Void requestData) throws InterruptedException, ClientException {
        return null;
    }

}
