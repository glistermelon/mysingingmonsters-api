package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.main.monster.Monster;
import com.glisterbyte.singingmonsters.sfsmodels.events.FlipMonsterResponse;
import com.glisterbyte.singingmonsters.sfsmodels.requests.FlipMonsterRequest;

public class FlipMonsterHandler extends UncorrelatedResultResponseHandler<Void, FlipMonsterResponse, Void> {

    public FlipMonsterHandler(EventHandlerInitArg arg) {
        super(arg, FlipMonsterResponse.class);
    }

    @Override
    public Void handleSuccess(FlipMonsterResponse event, Void requestData) throws InterruptedException, ClientException {
        // UpdateMonsterEvent does all the work
        return null;
    }

    public void request(long userMonsterId, Island island, boolean flipped)
            throws InterruptedException, ClientException {

        FlipMonsterRequest model = new FlipMonsterRequest();
        model.user_monster_id = userMonsterId;
        model.flipped = flipped;

        Request request = new Request(client);
        var flipFuture = request.expectResponse(this);
        var updateFuture = request.expectResponse(handlerManager.getUpdateMonsterHandler(), userMonsterId);

        flipFuture.whenComplete((x, ex) -> {
            if (ex != null) updateFuture.completeExceptionally(ex);
        });

        request.run(model, island);

    }

    public void request(Monster monster, boolean flipped)
            throws InterruptedException, ClientException {
        request(monster.getUserMonsterId(), monster.getIsland(), flipped);
    }

}