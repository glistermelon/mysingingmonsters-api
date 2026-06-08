package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * Collects from a monster.
 * Fails if there is nothing to be collected.
 * ```{warning}
 * If a monster has accumulated 0 coins/shards/etc., the action is considered a failure by the server,
 * and it sets an error message containing "nothing to collect".
 * The full error message is typically something like "Normal monster: nothing to collect".
 * It is easier to catch this error and deal with it than it is to approximate when a monster will be ready to collect.
 * ```
 * ```{warning}
 * For a deactivated Wublin, this command sells all its eggs.
 * ```
 */
@SfsCmd("gs_collect_monster")
public class CollectMonsterRequest extends SfsRequestModel {
    public long user_monster_id;
}
