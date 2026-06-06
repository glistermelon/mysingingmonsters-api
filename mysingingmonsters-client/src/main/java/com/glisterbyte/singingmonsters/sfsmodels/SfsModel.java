package com.glisterbyte.singingmonsters.sfsmodels;

import com.glisterbyte.singingmonsters.sfsmapping.exceptions.MissingCommandException;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmapping.SfsObjectField;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class SfsModel {

    @SfsObjectField
    public SFSObject sfsObject;

    public void setSfsObject(SFSObject sfsObject) {
        this.sfsObject = sfsObject;
    }

    public String getCommand() throws MissingCommandException {
        var classType = this.getClass();
        SfsCmd sfsCmd = classType.getAnnotation(SfsCmd.class);
        if (sfsCmd == null) {
            throw new MissingCommandException("Class '" + classType.getName() + "' has no sfs cmd annotation");
        }
        return sfsCmd.value();
    }

}