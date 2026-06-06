package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsBaking;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsUpdate;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedResultResponse;

import java.util.Optional;

@SfsCmd("gs_start_baking")
public class StartBakingResponse extends SfsCorrelatedResultResponse {

    public Long user_structure_id;
    public SfsBaking user_baking;

    @Override
    public Optional<Long> getCorrelationId() {
        return Optional.ofNullable(user_structure_id);
    }

}