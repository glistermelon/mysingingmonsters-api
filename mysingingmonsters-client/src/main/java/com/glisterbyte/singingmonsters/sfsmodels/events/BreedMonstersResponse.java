package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsBreeding;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedResultResponse;

import java.util.Optional;

@SfsCmd("gs_breed_monsters")
public class BreedMonstersResponse extends SfsCorrelatedResultResponse {

    public Long user_structure_id;
    public long user_monster_1;
    public long user_monster_2;
    public SfsBreeding user_breeding;


    @Override
    public Optional<Long> getCorrelationId() {
        return Optional.ofNullable(user_structure_id);
    }

}