package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.CorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.main.monster.Monster;
import com.glisterbyte.singingmonsters.main.structure.nursery.Nursery;
import com.glisterbyte.singingmonsters.main.monster.MonsterPlacement;
import com.glisterbyte.singingmonsters.sfsmodels.requests.HatchEggRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.HatchEggResponse;
import org.apache.commons.lang3.Validate;

import java.util.Optional;

public class HatchEggHandler extends CorrelatedResultResponseHandler<Void, HatchEggResponse, Monster> {

    public HatchEggHandler(EventHandlerInitArg arg) {
        super(arg, HatchEggResponse.class);
    }

    @Override
    public Monster handleSuccess(HatchEggResponse response, Void requestData) throws InterruptedException, ClientException {
        client.update(response.properties);
        return client.getIsland(response.island).getEventHandler().handleHatchEggEvent(response);
    }

    public Monster request(Nursery nursery, MonsterPlacement placement) throws InterruptedException, ClientException {

        Optional<Long> maybeEggId = nursery.getUserEggId();
        Validate.isTrue(
                maybeEggId.isPresent(), "Hatch egg handler expected nursery to have an egg id"
        );
        long userEggId = maybeEggId.get();

        HatchEggRequest request = new HatchEggRequest();
        request.flip = placement.flipped() ? 1 : 0;
        request.pos_x = placement.position().x();
        request.pos_y = placement.position().y();
        request.store_in_hotel = false;
        request.user_egg_id = userEggId;

        return Request.simpleRequest(this, request, nursery.getIsland(), userEggId);

    }

    // For buying monsters on Wublin island (and maybe others too?)
    public Monster request(long speciesId, MonsterPlacement placement, Island island)
            throws InterruptedException, ClientException {

        HatchEggRequest request = new HatchEggRequest();
        request.flip = placement.flipped() ? 1 : 0;
        request.pos_x = placement.position().x();
        request.pos_y = placement.position().y();
        request.store_in_hotel = false;
        request.user_egg_id = speciesId;

        Monster monster = Request.simpleRequest(this, request, island, speciesId);

        handlerManager.getMultiMonsterUpdateHandler().consider(monster, null);

        return monster;

    }

}