package com.glisterbyte.SingingMonsters.Structures;

import com.glisterbyte.SingingMonsters.Island;
import com.glisterbyte.SingingMonsters.SfsModels.Client.CollectMineFailed;
import com.glisterbyte.SingingMonsters.SfsModels.Client.CollectMineRequest;
import com.glisterbyte.SingingMonsters.SfsModels.Server.CollectMineResponse;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsResponseModel;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsStructure;
import com.glisterbyte.SingingMonsters.SfsModels.Server.UpdateStructure;
import com.glisterbyte.SingingMonsters.Structure;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Mine extends Structure {

    private Instant lastCollectTime;

    public Mine(Island island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
        setLastCollectTime(sfsStructure.lastCollection);
    }

    private void setLastCollectTime(long epochMilli) {
        lastCollectTime = Instant.ofEpochMilli(epochMilli);
    }

    public Instant getLastCollectTime() {
        return lastCollectTime;
    }

    // TODO: successful collection has not been tested yet
    public void collect() {

        CollectMineResponse mainResponse;
        UpdateStructure updateStructure;

        try {
            List<SfsResponseModel> responses = sfsClient.requestResponses(new CollectMineRequest()).get();
            mainResponse = (CollectMineResponse)responses.getFirst();
            updateStructure = (UpdateStructure)responses.get(1);
        }
        catch (ExecutionException | InterruptedException | IndexOutOfBoundsException ex) {
            throw new CollectMineFailed("", ex);
        }

        if (mainResponse.failed()) {
            throw new CollectMineFailed(mainResponse.message);
        }

        setLastCollectTime(updateStructure.properties.lastCollection);
        player.update(updateStructure.properties);

    }

}