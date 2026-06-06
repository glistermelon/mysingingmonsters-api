package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsUpdate;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedResultResponse;

import java.util.Optional;

@SfsCmd("gs_finish_baking")
public class FinishBakingResponse extends SfsCorrelatedResultResponse {

    public Long user_baking_id;

    @Override
    public Optional<Long> getCorrelationId() {
        return Optional.ofNullable(user_baking_id);
    }

}
