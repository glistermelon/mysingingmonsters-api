package com.glisterbyte.singingmonsters.main.structure.castle;

import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.structure.ReadableStructure;

public interface ReadableCastle extends ReadableStructure {

    default int getBedCount() {
        return getStructureType().getBedCount();
    }

    default String toString(String superStr) {
        return StringUtil.format("Castle({})", superStr);
    }

}