package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.CorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.sfsmodels.requests.ClearObstacleRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.ClearObstacleResponse;

public class ClearObstacleHandler extends CorrelatedResultResponseHandler<Void, ClearObstacleResponse, Void> {

    public ClearObstacleHandler(EventHandlerInitArg arg) {
        super(arg, ClearObstacleResponse.class);
    }

    @Override
    public Void handleSuccess(ClearObstacleResponse event, Void requestData) throws InterruptedException, ClientException {
        client.getActiveIsland().getEventHandler().handleClearObstacleEvent(event);
        return null;
    }

    public void request(Structure structure) throws InterruptedException, ClientException {

        long userStructureId = structure.getUserStructureId();

        ClearObstacleRequest request = new ClearObstacleRequest();
        request.user_structure_id = userStructureId;

        Request.simpleRequest(this, request, structure.getIsland(), userStructureId);

        handlerManager.getMultiMonsterUpdateHandler().consider(structure, null);

    }

}