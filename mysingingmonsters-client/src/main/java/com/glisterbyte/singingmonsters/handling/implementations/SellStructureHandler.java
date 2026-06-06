package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.CorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.sfsmodels.requests.SellStructureRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.SellStructureResponse;

public class SellStructureHandler extends CorrelatedResultResponseHandler<Void, SellStructureResponse, Void> {

    public SellStructureHandler(EventHandlerInitArg arg) {
        super(arg, SellStructureResponse.class);
    }

    public Void handleSuccess(SellStructureResponse event, Void requestData) throws InterruptedException, ClientException {
        client.getActiveIsland().getEventHandler().handleSellStructureEvent(event);
        return null;
    }

    public void request(Structure structure) throws InterruptedException, ClientException {

        SellStructureRequest request = new SellStructureRequest();
        request.user_structure_id = structure.getUserStructureId();

        Request.simpleRequest(this, request, structure.getIsland(), structure.getUserStructureId());

        handlerManager.getMultiMonsterUpdateHandler().consider(structure, null);

    }

}