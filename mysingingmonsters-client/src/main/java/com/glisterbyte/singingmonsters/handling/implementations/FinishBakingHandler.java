package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.CorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.main.structure.bakery.Bakery;
import com.glisterbyte.singingmonsters.sfsmodels.requests.FinishBakingRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.FinishBakingResponse;
import org.apache.commons.lang3.Validate;

import java.util.Optional;

/*
    This is sent by the user to collect from a bakery.
    It is **NOT** sent by the server to indicate baking completion!
 */
public class FinishBakingHandler extends CorrelatedResultResponseHandler<Void, FinishBakingResponse, Void> {

    public FinishBakingHandler(EventHandlerInitArg arg) {
        super(arg, FinishBakingResponse.class);
    }

    @Override
    public Void handleSuccess(FinishBakingResponse event, Void requestData) throws InterruptedException, ClientException {
        client.getActiveIsland().getBakeryWithBakingId(event.user_baking_id).getEventHandler().handleFinishBakingEvent(event);
        return null;
    }

    public void request(Bakery bakery) throws InterruptedException, ClientException {

        Optional<Long> maybeBakingId = bakery.getUserBakingId();
        Validate.isTrue(
                maybeBakingId.isPresent(), "Finish baking handler expected bakery to have a baking id"
        );
        long userBakingId = maybeBakingId.get();

        FinishBakingRequest request = new FinishBakingRequest();
        request.user_baking_id = userBakingId;

        Request.simpleRequest(this, request, bakery.getIsland(), userBakingId);

    }

}