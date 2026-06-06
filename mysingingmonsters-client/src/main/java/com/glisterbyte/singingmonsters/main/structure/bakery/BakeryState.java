package com.glisterbyte.singingmonsters.main.structure.bakery;

import com.glisterbyte.singingmonsters.sfsmodels.data.SfsBaking;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;

import java.time.Instant;

public record BakeryState(
        long bakingId,
        int foodId,
        Instant startTime,
        Instant finishTime
) {

    public static BakeryState fromSfsBaking(SfsBaking sfsBaking) {
        return new BakeryState(
                sfsBaking.user_baking_id,
                sfsBaking.food_option_id,
                Instant.ofEpochMilli(sfsBaking.started_at),
                Instant.ofEpochMilli(sfsBaking.finished_at)
        );
    }

    public static BakeryState fromSfsIsland(SfsIsland island, SfsStructure sfsStructure) {
        for (SfsBaking sfsBaking : island.baking) {
            if (sfsBaking.user_structure == sfsStructure.user_structure_id) {
                return BakeryState.fromSfsBaking(sfsBaking);
            }
        }
        return null;
    }

}