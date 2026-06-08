package com.glisterbyte.singingmonsters.sfsmapping.exceptions;

import com.glisterbyte.singingmonsters.common.StringUtil;
import com.smartfoxserver.v2.entities.data.ISFSObject;

public class MissingKeyException extends MapFromSfsException {

    public MissingKeyException(String missingKey, Class<?> type, ISFSObject sfsObject) {
        super(StringUtil.format(
                "Missing key '{}' with type '{}' in SFSObject: {}",
                missingKey, type.getName(), sfsObject.getDump()
        ));
    }

}