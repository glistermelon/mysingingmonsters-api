package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedResultResponse;

import java.util.Optional;

@SfsCmd("gs_sell_egg")
public class SellEggResponse extends SfsCorrelatedResultResponse {

    public Long user_egg_id;

    @Override
    public Optional<Long> getCorrelationId() {
        return Optional.ofNullable(user_egg_id);
    }

}