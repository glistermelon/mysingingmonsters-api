package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.main.monster.Monster;
import com.glisterbyte.singingmonsters.main.common.Position;
import com.glisterbyte.singingmonsters.sfsmodels.events.MoveMonsterResponse;
import com.glisterbyte.singingmonsters.sfsmodels.requests.MoveMonsterRequest;

public class MoveMonsterHandler extends UncorrelatedResultResponseHandler<Void, MoveMonsterResponse, Void> {

    public MoveMonsterHandler(EventHandlerInitArg arg) {
        super(arg, MoveMonsterResponse.class);
    }

    @Override
    public Void handleSuccess(MoveMonsterResponse event, Void requestData) throws InterruptedException, ClientException {
        // UpdateMonsterEvent does all the work
        return null;
    }

    public void request(Monster monster, Position newPosition, double newVolume)
            throws InterruptedException, ClientException {

        long userMonsterId = monster.getUserMonsterId();
        Position originalPosition = monster.getPosition();

        MoveMonsterRequest model = new MoveMonsterRequest();
        model.user_monster_id = userMonsterId;
        model.pos_x = newPosition.x();
        model.pos_y = newPosition.y();
        model.volume = newVolume;

        Request request = new Request(client);
        var moveFuture = request.expectResponse(this);
        var updateFuture = request.expectResponse(handlerManager.getUpdateMonsterHandler(), userMonsterId);

        moveFuture.whenComplete((x, ex) -> {
            if (ex != null) updateFuture.completeExceptionally(ex);
        });

        request.run(model, monster.getIsland());

        handlerManager.getMultiMonsterUpdateHandler().consider(monster, originalPosition);

    }

}