package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.sfsmodels.events.FlipStructureResponse;
import com.glisterbyte.singingmonsters.sfsmodels.requests.FlipStructureRequest;

public class FlipStructureHandler extends UncorrelatedResultResponseHandler<Void, FlipStructureResponse, Void> {

    public FlipStructureHandler(EventHandlerInitArg arg) {
        super(arg, FlipStructureResponse.class);
    }

    @Override
    public Void handleSuccess(FlipStructureResponse event, Void requestData) throws InterruptedException, ClientException {
        return null;
    }

    public void request(long userStructureId, Island island, boolean flipped)
            throws InterruptedException, ClientException {

        FlipStructureRequest model = new FlipStructureRequest();
        model.user_structure_id = userStructureId;
        model.flipped = flipped;

        Request request = new Request(client);
        var flipFuture = request.expectResponse(this);
        var updateFuture = request.expectResponse(handlerManager.getUpdateStructureHandler(), userStructureId);

        flipFuture.whenComplete((x, ex) -> {
            if (ex != null) updateFuture.completeExceptionally(ex);
        });

        request.run(model, island);

    }

    public void request(Structure structure, boolean flipped)
            throws InterruptedException, ClientException {
        request(structure.getUserStructureId(), structure.getIsland(), flipped);
    }

}