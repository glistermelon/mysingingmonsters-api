package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedEventHandler;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsPlayer;
import com.glisterbyte.singingmonsters.sfsmodels.events.GetFriendsResponse;
import com.glisterbyte.singingmonsters.sfsmodels.requests.PlayerRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.PlayerResponse;

public class GetPlayerHandler extends UncorrelatedEventHandler<Void, PlayerResponse, Void> {

    public GetPlayerHandler(EventHandlerInitArg arg) {
        super(arg, PlayerResponse.class);
    }

    @Override
    protected Void process(PlayerResponse event, Void requestData) throws InterruptedException, ClientException {
        return null;
    }

    public record ResponseData(
            SfsPlayer sfsPlayer,
            GetFriendsResponse getFriendsResponse
    ) { }

    public ResponseData request() throws InterruptedException, ClientException {

        PlayerRequest model = new PlayerRequest();
        model.last_updated = " 0"; // intentional space; matches what the real game actually sends

        Request request = new Request(client);
        var playerFuture = request.expectResponse(this);
        var friendsFuture = request.expectResponse(handlerManager.getGetFriendsHandler());
        request.run(model, null);

        return new ResponseData(
                playerFuture.getNow(null).getEvent().player_object,
                friendsFuture.getNow(null).getEvent()
        );

    }

}