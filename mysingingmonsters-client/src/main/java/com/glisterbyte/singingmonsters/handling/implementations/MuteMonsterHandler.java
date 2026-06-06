package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.main.monster.Monster;
import com.glisterbyte.singingmonsters.sfsmodels.events.MuteMonsterResponse;
import com.glisterbyte.singingmonsters.sfsmodels.requests.MuteMonsterRequest;

public class MuteMonsterHandler extends UncorrelatedResultResponseHandler<Void, MuteMonsterResponse, Void> {

    public MuteMonsterHandler(EventHandlerInitArg arg) {
        super(arg, MuteMonsterResponse.class);
    }

    @Override
    public Void handleSuccess(MuteMonsterResponse event, Void requestData) throws InterruptedException, ClientException {
        // UpdateMonsterHandler mutes the monster
        return null;
    }

    public void request(Monster monster, boolean mute) throws InterruptedException, ClientException {

        long userMonsterId = monster.getUserMonsterId();

        MuteMonsterRequest model = new MuteMonsterRequest();
        model.user_monster_id = userMonsterId;
        model.muted = mute ? 1 : 0;

        Request request = new Request(client);

        var muteFuture = request.expectResponse(this);
        var updateFuture = request.expectResponse(handlerManager.getUpdateMonsterHandler(), userMonsterId);

        muteFuture.whenComplete((x, ex) -> {
            if (ex != null) updateFuture.completeExceptionally(ex);
        });

        request.run(model, monster.getIsland());

    }

}