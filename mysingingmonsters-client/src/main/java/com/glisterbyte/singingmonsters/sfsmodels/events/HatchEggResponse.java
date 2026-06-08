package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonster;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedResultResponse;

import java.util.Optional;

@SfsCmd("gs_hatch_egg")
public class HatchEggResponse extends SfsCorrelatedResultResponse {

    public boolean directPlace;
    public long island;
    public Long user_egg_id;
    public boolean create_in_storage;
    public SfsMonster monster;

    @Override
    public Optional<Long> getCorrelationId() {
        return Optional.ofNullable(user_egg_id);
    }

}