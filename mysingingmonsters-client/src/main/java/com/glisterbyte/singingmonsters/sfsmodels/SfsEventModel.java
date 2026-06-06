package com.glisterbyte.singingmonsters.sfsmodels;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsUpdate;

public abstract class SfsEventModel extends SfsModel {

    @SfsOptional
    public SfsUpdate properties;

    public String getCommand() {
        SfsCmd sfsCmd = this.getClass().getAnnotation(SfsCmd.class);
        if (sfsCmd == null) throw new IllegalStateException(
                this.getClass().getName() + " lacks SfsCmd annotation"
        );
        return sfsCmd.value();
    }

}