package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedResultResponse;

import java.util.Optional;

@SfsCmd("gs_sell_monster")
public class SellMonsterResponse extends SfsCorrelatedResultResponse {

    // skipped 'buyback_properties'

    public Long user_monster_id;

    @Override
    public Optional<Long> getCorrelationId() {
        return Optional.ofNullable(user_monster_id);
    }

}