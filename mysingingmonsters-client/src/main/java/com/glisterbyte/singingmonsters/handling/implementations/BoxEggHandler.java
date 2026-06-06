package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ActionFailedException;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.exceptions.ClientIllegalArgumentException;
import com.glisterbyte.singingmonsters.handling.CorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.main.monster.eggbox.ReadableEggBoxMonster;
import com.glisterbyte.singingmonsters.main.structure.breeding.BreedingStructure;
import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.sfsmodels.requests.BoxEggRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.BoxEggResponse;

import java.util.Optional;

public class BoxEggHandler extends CorrelatedResultResponseHandler<Void, BoxEggResponse, Void> {

    public BoxEggHandler(EventHandlerInitArg arg) {
        super(arg, BoxEggResponse.class);
    }

    @Override
    public Void handleSuccess(BoxEggResponse event, Void requestData) throws InterruptedException, ClientException {

        // Updating the monster is done by an UpdateMonsterEvent

        client.getActiveIsland().getBreedingStructureWithBreedingId(event.user_egg_id)
                .getEventHandler().handleBoxEggEvent(event);

        return null;

    }

    /*
        The API returns two errors with different/no messages for some reason if you try to use this
        command in certain invalid ways, so I've put in checks.
     */
    public void request(BreedingStructure breedingStructure, ReadableEggBoxMonster monster) throws InterruptedException, ClientException {

        MonsterSpecies resultingSpecies = breedingStructure.getResultingSpecies();
        if (!monster.needsEgg(resultingSpecies)) {
            throw new ActionFailedException(
                    "Monster statue already has all necessary eggs for species " + resultingSpecies
            );
        }

        Optional<Long> maybeBreedingId = breedingStructure.getUserBreedingId();
        if (maybeBreedingId.isEmpty()) throw new ClientIllegalArgumentException("Breeding structure is empty");
        long userBreedingId = maybeBreedingId.get();

        long userMonsterId = monster.getUserMonsterId();

        BoxEggRequest model = new BoxEggRequest();
        model.user_egg_id = userBreedingId;
        model.user_monster_id = userMonsterId;
        model.underling = true;

        Request request = new Request(client);
        var updateFuture = request.expectResponse(handlerManager.getUpdateMonsterHandler(), userMonsterId);
        request.expectResponse(this, userBreedingId)
                .whenComplete((x, ex) -> {
                    if (ex != null) updateFuture.completeExceptionally(ex);
                });

        request.run(model, breedingStructure.getIsland());

    }

}