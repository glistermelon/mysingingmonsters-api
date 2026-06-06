package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.CorrelatedEventHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.sfsmodels.events.UpdateStructureEvent;

public class UpdateStructureHandler extends CorrelatedEventHandler<Void, UpdateStructureEvent, Void> {

    public UpdateStructureHandler(EventHandlerInitArg arg) {
        super(arg, UpdateStructureEvent.class);
    }

    @Override
    protected Void process(UpdateStructureEvent event, Void requestData) throws InterruptedException, ClientException {
        Structure structure = client.getActiveIsland().getStructure(event.user_structure_id);
        if (structure != null) structure.getEventHandler().handleUpdateEvent(event);
        return null;
    }

}