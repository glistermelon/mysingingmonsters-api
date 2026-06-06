package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.CorrelatedEventHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.main.monster.Monster;
import com.glisterbyte.singingmonsters.sfsmodels.events.UpdateMonsterEvent;

public class UpdateMonsterHandler extends CorrelatedEventHandler<Void, UpdateMonsterEvent, Void> {

    public UpdateMonsterHandler(EventHandlerInitArg arg) {
        super(arg, UpdateMonsterEvent.class);
    }

    @Override
    protected Void process(UpdateMonsterEvent event, Void requestData) throws InterruptedException, ClientException {
        Monster monster = client.getMonster(event.user_monster_id);
        if (monster != null) monster.getEventHandler().handleUpdateEvent(event);
        return null;
    }

}