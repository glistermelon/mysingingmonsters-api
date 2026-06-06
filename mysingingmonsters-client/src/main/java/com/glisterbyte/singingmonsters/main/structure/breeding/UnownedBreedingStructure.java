package com.glisterbyte.singingmonsters.main.structure.breeding;

import com.glisterbyte.singingmonsters.main.island.UnownedIsland;
import com.glisterbyte.singingmonsters.main.structure.UnownedStructure;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;

public class UnownedBreedingStructure extends UnownedStructure implements ReadableBreedingStructure {

    private final BreedingState breedingState;

    public UnownedBreedingStructure(UnownedIsland island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
        breedingState = BreedingState.fromSfsIsland(island.getSfsModel(), sfsStructure, getCatalog());
    }

    public BreedingState getBreedingState() {
        return breedingState;
    }

    @Override
    public synchronized String toString() {
        return toString(super.toString());
    }

}