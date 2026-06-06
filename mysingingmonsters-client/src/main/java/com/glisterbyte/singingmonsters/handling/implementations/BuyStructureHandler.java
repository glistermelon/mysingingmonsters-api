package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.sfsmodels.requests.BuyStructureRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.BuyStructureResponse;
import org.jetbrains.annotations.NotNull;

public class BuyStructureHandler extends UncorrelatedResultResponseHandler<Void, BuyStructureResponse, Structure> {

    public BuyStructureHandler(EventHandlerInitArg arg) {
        super(arg, BuyStructureResponse.class);
    }

    @Override
    public Structure handleSuccess(BuyStructureResponse response, Void requestData) throws InterruptedException, ClientException {
        return client.getActiveIsland().getEventHandler().handleBuyStructureEvent(response);
    }

    public Structure request(BuyStructureRequest request, @NotNull Island island) throws InterruptedException, ClientException {
        Structure structure = Request.simpleRequest(this, request, island);
        handlerManager.getMultiMonsterUpdateHandler().consider(structure, null);
        return structure;
    }

}