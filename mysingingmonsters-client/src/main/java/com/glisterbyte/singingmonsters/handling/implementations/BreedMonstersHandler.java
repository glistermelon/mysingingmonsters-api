package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.CorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.main.monster.ReadableMonster;
import com.glisterbyte.singingmonsters.main.structure.breeding.BreedingStructure;
import com.glisterbyte.singingmonsters.sfsmodels.requests.BreedMonstersRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.BreedMonstersResponse;

public class BreedMonstersHandler extends CorrelatedResultResponseHandler<Void, BreedMonstersResponse, Void> {

    public BreedMonstersHandler(EventHandlerInitArg arg) {
        super(arg, BreedMonstersResponse.class);
    }

    @Override
    public Void handleSuccess(BreedMonstersResponse event, Void requestData) throws InterruptedException, ClientException {
        BreedingStructure structure = (BreedingStructure)
                client.getActiveIsland().getStructure(event.user_structure_id);
        structure.getEventHandler().handleBreedMonstersEvent(event);
        return null;
    }

    public void request(BreedingStructure structure, ReadableMonster monster1, ReadableMonster monster2)
            throws InterruptedException, ClientException {
        BreedMonstersRequest request = new BreedMonstersRequest();
        request.structure_id = structure.getUserStructureId();
        request.user_monster_id_1 = monster1.getUserMonsterId();
        request.user_monster_id_2 = monster2.getUserMonsterId();
        request.time_mask = 8;
        Request.simpleRequest(this, request, structure.getIsland(), structure.getUserStructureId());
    }

}