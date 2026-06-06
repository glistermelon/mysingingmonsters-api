package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.CorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.main.structure.breeding.BreedingStructure;
import com.glisterbyte.singingmonsters.main.structure.nursery.Nursery;
import com.glisterbyte.singingmonsters.sfsmodels.requests.FinishBreedingRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.FinishBreedingResponse;
import org.apache.commons.lang3.Validate;

import java.util.Optional;

public class FinishBreedingHandler extends CorrelatedResultResponseHandler<Void, FinishBreedingResponse, Nursery> {

    public FinishBreedingHandler(EventHandlerInitArg arg) {
        super(arg, FinishBreedingResponse.class);
    }

    @Override
    public Nursery handleSuccess(FinishBreedingResponse event, Void requestData) throws InterruptedException, ClientException {
        return client.getActiveIsland().getEventHandler().handleFinishBreedingEvent(event);
    }

    public Nursery request(BreedingStructure breedingStructure) throws InterruptedException, ClientException {

        Optional<Long> maybeBreedingId = breedingStructure.getUserBreedingId();
        Validate.isTrue(
                maybeBreedingId.isPresent(),
                "Finish breeding handler expected breeding structure to have a breeding id"
        );
        long userBreedingId = maybeBreedingId.get();

        FinishBreedingRequest request = new FinishBreedingRequest();
        request.user_breeding_id = userBreedingId;
        request.speedup = false;
        request.purchase_type = 47;

        return Request.simpleRequest(this, request, breedingStructure.getIsland(), userBreedingId);

    }

}