package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmapping.SfsJsonArray;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.SfsEventModel;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedEventModel;

import java.util.List;
import java.util.Optional;

@SfsCmd("gs_update_monster")
public class UpdateMonsterEvent extends SfsEventModel implements SfsCorrelatedEventModel {

    // Ignored 'collected_coins', 'collected_relics', 'collected_ethereal'
    // Not sure what they are, but they aren't the amount of currency you've actually collected
    // They're also all optional.

    public Long user_monster_id;

    @SfsOptional
    public Integer pos_x;

    @SfsOptional
    public Integer pos_y;

    @SfsOptional
    public Integer flip;

    @SfsOptional
    public Integer muted;

    @SfsOptional
    public Double volume;

    @SfsOptional
    public Long last_collection;

    @SfsOptional
    public Integer times_fed;

    @SfsOptional
    public Integer level;

    @SfsOptional
    public Long egg_timer_start;

    @SfsOptional
    @SfsJsonArray
    @SfsArrayElementType(Integer.class)
    public List<Integer> boxed_eggs;

    @SfsOptional
    public Integer random_underling_collection_min;

    @SfsOptional
    public String collection_type;

    @Override
    public Optional<Long> getCorrelationId() {
        return Optional.ofNullable(user_monster_id);
    }

}