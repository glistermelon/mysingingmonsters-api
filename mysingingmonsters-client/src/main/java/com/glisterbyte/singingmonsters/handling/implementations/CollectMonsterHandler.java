package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ActionFailedException;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.CorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.main.monster.Monster;
import com.glisterbyte.singingmonsters.main.common.MultiCurrencyValue;
import com.glisterbyte.singingmonsters.sfsmodels.requests.CollectMonsterRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.CollectMonsterEvent;

public class CollectMonsterHandler extends CorrelatedResultResponseHandler
        <Void, CollectMonsterEvent, MultiCurrencyValue> {

    public CollectMonsterHandler(EventHandlerInitArg arg) {
        super(arg, CollectMonsterEvent.class);
    }

    @Override
    public MultiCurrencyValue handleSuccess(CollectMonsterEvent event, Void requestData) {
        return new MultiCurrencyValue(
                event.coins != null ? event.coins : 0,
                event.food != null ? event.food : 0,
                event.keys != null ? event.keys : 0,
                event.relics != null ? event.relics : 0,
                event.diamonds != null ? event.diamonds : 0,
                event.starpower != null ? event.starpower : 0,
                event.medals != null ? event.medals : 0,
                event.ethereal_currency != null ? event.ethereal_currency : 0,
                event.egg_wildcards != null ? event.egg_wildcards : 0
        );
    }

    @Override
    public MultiCurrencyValue handleFailure(CollectMonsterEvent event, Void requestData) throws InterruptedException, ClientException {
        if (event.message != null && event.message.contains("nothing to collect")) {
            return MultiCurrencyValue.zero();
        }
        else throw new ActionFailedException(event, client.getLocalizedTextManager());
    }

    public MultiCurrencyValue request(Monster monster) throws InterruptedException, ClientException {

        long userMonsterId = monster.getUserMonsterId();

        CollectMonsterRequest model = new CollectMonsterRequest();
        model.user_monster_id = userMonsterId;

        Request request = new Request(client);

        var updateFuture = request.expectResponse(handlerManager.getUpdateMonsterHandler(), userMonsterId);
        var collectFuture = request.expectResponse(this, userMonsterId);

        collectFuture.whenComplete((val, ex) -> {
            if (ex != null) updateFuture.completeExceptionally(ex);
            else if (val.getResult().isZero()) updateFuture.complete(null);
        });

        request.run(model, monster.getIsland());

        return collectFuture.getNow(null).getResult();

    }

}