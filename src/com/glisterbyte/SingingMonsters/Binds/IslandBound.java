package com.glisterbyte.SingingMonsters.Binds;

import com.glisterbyte.SingingMonsters.Island;

public class IslandBound extends PlayerBound {

    protected Island island;

    public IslandBound(Island island) {
        super(island.player);
        this.island = island;
    }

    public final Island getIsland() {
        return island;
    }

}