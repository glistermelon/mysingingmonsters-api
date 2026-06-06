package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsArrayElementType;
import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonsterMultiUpdate;
import com.glisterbyte.singingmonsters.sfsmodels.SfsResultResponse;

import java.util.List;

@SfsCmd("gs_multi_update_monster")
public class MultiUpdateMonsterResponse extends SfsResultResponse {

    public Long user_monster_id;

    @SfsArrayElementType(SfsMonsterMultiUpdate.class)
    public List<SfsMonsterMultiUpdate> update_monster_list;

}