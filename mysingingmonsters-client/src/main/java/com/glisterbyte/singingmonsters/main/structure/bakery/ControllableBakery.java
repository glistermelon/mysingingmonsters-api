package com.glisterbyte.singingmonsters.main.structure.bakery;

import com.glisterbyte.singingmonsters.main.catalog.BakeryRecipe;
import com.glisterbyte.singingmonsters.exceptions.ClientException;

public interface ControllableBakery {
    void bake(BakeryRecipe recipe) throws InterruptedException, ClientException;
    int collect() throws InterruptedException, ClientException;
}