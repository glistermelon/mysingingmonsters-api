package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.main.common.Position;
import com.glisterbyte.singingmonsters.sfsmodels.events.MoveStructureResponse;
import com.glisterbyte.singingmonsters.sfsmodels.requests.MoveStructureRequest;

public class MoveStructureHandler extends UncorrelatedResultResponseHandler<Void, MoveStructureResponse, Void> {

    public MoveStructureHandler(EventHandlerInitArg arg) {
        super(arg, MoveStructureResponse.class);
    }

    @Override
    public Void handleSuccess(MoveStructureResponse event, Void requestData) throws InterruptedException, ClientException {
        // UpdateStructureEvent does all the work
        return null;
    }

    public void request(Structure structure, Position newPosition, double newScale, float newVolume)
            throws InterruptedException, ClientException {

        long userStructureId = structure.getUserStructureId();
        Position originalPosition = structure.getPosition();

        MoveStructureRequest model = new MoveStructureRequest();
        model.user_structure_id = userStructureId;
        model.pos_x = newPosition.x();
        model.pos_y = newPosition.y();
        model.scale = newScale;
        model.volume = newVolume;

        Request request = new Request(client);
        var moveFuture = request.expectResponse(this);
        var updateFuture = request.expectResponse(handlerManager.getUpdateStructureHandler(), userStructureId);

        moveFuture.whenComplete((x, ex) -> {
            if (ex != null) updateFuture.completeExceptionally(ex);
        });

        request.run(model, structure.getIsland());

        handlerManager.getMultiMonsterUpdateHandler().consider(structure, originalPosition);

    }

}