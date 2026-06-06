package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.CorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.main.structure.nursery.Nursery;
import com.glisterbyte.singingmonsters.sfsmodels.requests.SellEggRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.SellEggResponse;
import org.apache.commons.lang3.Validate;

import java.util.Optional;

public class SellEggHandler extends CorrelatedResultResponseHandler<Void, SellEggResponse, Void> {

    public SellEggHandler(EventHandlerInitArg arg) {
        super(arg, SellEggResponse.class);
    }

    @Override
    public Void handleSuccess(SellEggResponse event, Void requestData) throws InterruptedException, ClientException {
        client.getActiveIsland().getNurseryWithEggId(event.user_egg_id).getEventHandler().handleSellEggEvent(event);
        return null;
    }

    public void request(Nursery nursery) throws InterruptedException, ClientException {

        Optional<Long> maybeEggId = nursery.getUserEggId();
        Validate.isTrue(
                maybeEggId.isPresent(), "Sell egg handler expected nursery to have an egg id"
        );
        long userEggId = maybeEggId.get();

        SellEggRequest request = new SellEggRequest();
        request.user_egg_id = userEggId;

        Request.simpleRequest(this, request, nursery.getIsland(), userEggId);

    }

}