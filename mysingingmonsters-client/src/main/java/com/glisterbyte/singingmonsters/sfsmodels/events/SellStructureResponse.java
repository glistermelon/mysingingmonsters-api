package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedResultResponse;

import java.util.Optional;

@SfsCmd("gs_sell_structure")
public class SellStructureResponse extends SfsCorrelatedResultResponse {

    public Long user_structure_id;

    @Override
    public Optional<Long> getCorrelationId() {
        return Optional.ofNullable(user_structure_id);
    }

}
