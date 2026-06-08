package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedResultResponse;

import java.util.Optional;

@SfsCmd("gs_start_obstacle")
public class StartRemoveObstacleResponse extends SfsCorrelatedResultResponse {

    public Long user_structure_id;
    public SfsStructure user_structure;

    @Override
    public Optional<Long> getCorrelationId() {
        return Optional.ofNullable(user_structure_id);
    }

}
