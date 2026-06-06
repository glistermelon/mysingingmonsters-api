package com.glisterbyte.singingmonsters.main.structure.bakery;

import com.glisterbyte.singingmonsters.main.catalog.BakeryRecipe;
import com.glisterbyte.singingmonsters.main.island.UnownedIsland;
import com.glisterbyte.singingmonsters.main.structure.UnownedStructure;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsBaking;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;

import java.util.Optional;

public class UnownedBakery extends UnownedStructure implements ReadableBakery {

    private final BakeryState bakeryState;

    public UnownedBakery(UnownedIsland island, SfsStructure sfsStructure) {

        super(island, sfsStructure);

        BakeryState bakeryState = null;
        for (SfsBaking sfsBaking : island.getSfsModel().baking) {
            if (sfsBaking.user_structure == sfsStructure.user_structure_id) {
                bakeryState = BakeryState.fromSfsBaking(sfsBaking);
                break;
            }
        }
        this.bakeryState = bakeryState;

    }

    public BakeryState getBakeryState() {
        return bakeryState;
    }

    public BakeryRecipe getCurrentRecipe() {
        return Optional.ofNullable(getBakeryState())
                .map(s -> getCatalog().getBakeryRecipe(s.foodId()))
                .orElse(null);
    }

    @Override
    public synchronized String toString() {
        return toString(super.toString());
    }

}