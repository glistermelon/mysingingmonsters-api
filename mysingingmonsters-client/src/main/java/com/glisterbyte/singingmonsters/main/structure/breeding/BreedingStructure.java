package com.glisterbyte.singingmonsters.main.structure.breeding;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.monster.ReadableMonster;
import com.glisterbyte.singingmonsters.main.monster.eggbox.ReadableEggBoxMonster;
import com.glisterbyte.singingmonsters.main.structure.nursery.Nursery;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;
import com.glisterbyte.singingmonsters.sfsmodels.events.BoxEggResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.BreedMonstersResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.FinishBreedingResponse;
import org.apache.commons.lang3.Validate;

public class BreedingStructure extends Structure
        implements ReadableBreedingStructure, ControllableBreedingStructure {

    private volatile BreedingState breedingState;

    @Override
    public void internalRefresh(SfsStructure sfsStructure, boolean first) {
        super.internalRefresh(sfsStructure, first);
        breedingState = BreedingState.fromSfsIsland(getIsland().getSfsModel(), sfsStructure, getCatalog());
    }

    public BreedingStructure(Island island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
    }

    public class EventHandler extends Structure.EventHandler {

        private void validateBreedingEvent(long userBreedingId) {
            synchronized (BreedingStructure.this) {
                Validate.isTrue(
                        breedingState != null,
                        "Expected non-idle breeding structure for finish breeding event"
                );
                Validate.isTrue(
                        userBreedingId == breedingState.breedingId(),
                        "Expected finish event user breeding id {} to match actual user breeding id {}",
                        userBreedingId, breedingState.breedingId()
                );
            }
        }

        public void handleBreedMonstersEvent(BreedMonstersResponse event) {
            synchronized (BreedingStructure.this) {
                validateUserStructureId(event.user_structure_id);
                breedingState = BreedingState.fromSfsBreeding(event.user_breeding, getCatalog());
            }
        }

        public void handleFinishBreedingEvent(FinishBreedingResponse event) {
            synchronized (BreedingStructure.this) {
                validateBreedingEvent(event.user_breeding_id);
                breedingState = null;
            }
        }

        public void handleBoxEggEvent(BoxEggResponse event) {
            synchronized (BreedingStructure.this) {
                validateBreedingEvent(event.user_egg_id);
                breedingState = null;
            }
        }

    }

    @Override
    public EventHandler getEventHandler() {
        return new EventHandler();
    }

    public BreedingState getBreedingState() {
        return breedingState;
    }

    @Override
    public synchronized String toString() {
        return toString(super.toString());
    }

    public void breed(ReadableMonster firstMonster, ReadableMonster secondMonster) throws InterruptedException, ClientException {
        getClient().getEventHandlerManager().getBreedMonstersHandler().request(this, firstMonster, secondMonster);
    }

    public Nursery collectEgg() throws InterruptedException, ClientException {
        return getClient().getEventHandlerManager().getFinishBreedingHandler().request(this);
    }

    public void zapEgg(ReadableEggBoxMonster monster) throws InterruptedException, ClientException {
        getClient().getEventHandlerManager().getBoxEggHandler().request(this, monster);
    }

}