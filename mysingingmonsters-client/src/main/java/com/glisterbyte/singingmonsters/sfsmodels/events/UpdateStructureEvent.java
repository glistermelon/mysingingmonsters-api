package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmapping.SfsKey;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.SfsEventModel;
import com.glisterbyte.singingmonsters.sfsmodels.data.UpdateStructureProperties;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedEventModel;

import java.util.Optional;

@SfsCmd("gs_update_structure")
public class UpdateStructureEvent extends SfsEventModel implements SfsCorrelatedEventModel {

    public Long user_structure_id;

    @SfsOptional
    @SfsKey("properties")
    public UpdateStructureProperties structureUpdate;

    @Override
    public Optional<Long> getCorrelationId() {
        return Optional.ofNullable(user_structure_id);
    }

}