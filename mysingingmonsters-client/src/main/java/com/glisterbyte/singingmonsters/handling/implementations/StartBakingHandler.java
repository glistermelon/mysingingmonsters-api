package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.main.catalog.BakeryRecipe;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.CorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.main.structure.bakery.Bakery;
import com.glisterbyte.singingmonsters.sfsmodels.requests.StartBakingRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.StartBakingResponse;

public class StartBakingHandler extends CorrelatedResultResponseHandler<Void, StartBakingResponse, Void> {

    public StartBakingHandler(EventHandlerInitArg arg) {
        super(arg, StartBakingResponse.class);
    }

    @Override
    public Void handleSuccess(StartBakingResponse response, Void requestData) throws InterruptedException, ClientException {
        Bakery bakery = (Bakery)client.getActiveIsland().getStructure(response.user_structure_id);
        bakery.getEventHandler().handleStartBakingEvent(response);
        return null;
    }

    public void request(Bakery bakery, BakeryRecipe recipe) throws ClientException, InterruptedException {
        StartBakingRequest request = new StartBakingRequest();
        request.user_structure_id = bakery.getUserStructureId();;
        request.food_id = recipe.getId();
        Request.simpleRequest(this, request, bakery.getIsland(), bakery.getUserStructureId());
    }

}