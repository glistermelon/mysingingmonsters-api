package com.glisterbyte.SingingMonsters.Structures;

import com.glisterbyte.SingingMonsters.Island;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsStructure;
import com.glisterbyte.SingingMonsters.SfsModels.Server.UpdateStructure;
import com.glisterbyte.SingingMonsters.Structure;

import java.time.Duration;
import java.time.Instant;

public class Mine extends Structure {

    private Instant lastCollectTime;

    public Mine(Island island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
        setLastCollectTime(sfsStructure.lastCollection);
    }

    private void setLastCollectTime(long epochMilli) {
        lastCollectTime = Instant.ofEpochMilli(epochMilli);
    }

    public void update(UpdateStructure.UpdateStructureProperties properties) {
        setLastCollectTime(properties.lastCollection);
    }

    public Instant getLastCollectTime() {
        return lastCollectTime;
    }

    public void collect() {
        player.setActiveIsland(island);
        player.collectMineOnActiveIsland();
    }

    public boolean isReady() {
        return Duration.between(lastCollectTime, Instant.now()).toHours() >= 24;
    }

}