package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.main.structure.mine.Mine;
import com.glisterbyte.singingmonsters.sfsmodels.requests.CollectMineRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.CollectMineResponse;

public class CollectMineHandler extends UncorrelatedResultResponseHandler<Void, CollectMineResponse, Void> {

    public CollectMineHandler(EventHandlerInitArg arg) {
        super(arg, CollectMineResponse.class);
    }

    public Void handleSuccess(CollectMineResponse event, Void requestData) {
        // real updating is done by UpdateStructureHandler
        return null;
    }

    public void request(Mine mine) throws InterruptedException, ClientException {

        Request request = new Request(client);

        var updateFuture = request.expectResponse(handlerManager.getUpdateStructureHandler(), mine.getUserStructureId());
        request.expectResponse(this)
                .whenComplete((x, ex) -> {
                    if (ex != null) updateFuture.completeExceptionally(ex);
                });

        request.run(new CollectMineRequest(), mine.getIsland());

    }

}