package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Feeds a monster once.
 */
@SfsCmd("gs_feed_monster")
public class FeedMonsterRequest extends SfsRequestModel {
    public long user_monster_id;
}