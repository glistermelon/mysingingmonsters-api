package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsPlayer;
import com.glisterbyte.singingmonsters.sfsmodels.SfsEventModel;

@SfsCmd("gs_player")
public class PlayerResponse extends SfsEventModel {
    public SfsPlayer player_object;
}