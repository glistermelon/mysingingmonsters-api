package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.main.structure.nursery.Nursery;
import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.sfsmodels.requests.BuyEggRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.BuyEggResponse;

public class BuyEggHandler extends UncorrelatedResultResponseHandler<Void, BuyEggResponse, Void> {

    public BuyEggHandler(EventHandlerInitArg arg) {
        super(arg, BuyEggResponse.class);
    }

    @Override
    public Void handleSuccess(BuyEggResponse response, Void requestData) throws InterruptedException, ClientException {
        client.getActiveIsland().getEventHandler().handleBuyEggEvent(response);
        return null;
    }

    public void request(Nursery nursery, MonsterSpecies species) throws InterruptedException, ClientException {
        BuyEggRequest request = new BuyEggRequest();
        request.monster_id = species.getSpeciesId();
        request.quest_claim_id = 0;
        request.starpower_purchase = false;
        request.structure_id = nursery.getUserStructureId();
        Request.simpleRequest(this, request, nursery.getIsland());
    }

}