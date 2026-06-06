package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.*;
import com.glisterbyte.singingmonsters.main.monster.Monster;
import com.glisterbyte.singingmonsters.sfsmodels.requests.SellMonsterRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.SellMonsterResponse;

public class SellMonsterHandler extends CorrelatedResultResponseHandler<Void, SellMonsterResponse, Void> {

    public SellMonsterHandler(EventHandlerInitArg arg) {
        super(arg, SellMonsterResponse.class);
    }

    @Override
    public Void handleSuccess(SellMonsterResponse response, Void requestData) throws InterruptedException, ClientException {
        client.getMonster(response.user_monster_id).getIsland().getEventHandler().handleSellMonsterEvent(response);
        return null;
    }

    public void request(Monster monster) throws InterruptedException, ClientException {

        long userMonsterId = monster.getUserMonsterId();

        SellMonsterRequest model = new SellMonsterRequest();
        model.user_monster_id = userMonsterId;
        model.pure_destroy = false;

        Request request = new Request(client);

        request.expectResponse(this, userMonsterId);
        if (monster.isActivated()) {
            var updateFuture = request.expectResponse(handlerManager.getUpdateMonsterHandler(), userMonsterId);
            request.expectResponse(handlerManager.getCollectMonsterHandler(), userMonsterId)
                    .whenComplete((val, ex) -> {
                        if (val != null && val.getResult().isZero()) updateFuture.complete(null);
                        if (ex != null) updateFuture.completeExceptionally(ex);
                    });
        }

        request.run(model, monster.getIsland());

        handlerManager.getMultiMonsterUpdateHandler().consider(monster, null);

    }

}