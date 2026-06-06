package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonsterEgg;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedResultResponse;

import java.util.Optional;

@SfsCmd("gs_finish_breeding")
public class FinishBreedingResponse extends SfsCorrelatedResultResponse {

    public Long user_breeding_id;
    public long user_structure_id; // nursery structure id
    public SfsMonsterEgg user_egg;

    @Override
    public Optional<Long> getCorrelationId() {
        return Optional.ofNullable(user_breeding_id);
    }

}