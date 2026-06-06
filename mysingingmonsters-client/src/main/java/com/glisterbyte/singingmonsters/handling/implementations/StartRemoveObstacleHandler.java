package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.CorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.main.structure.obstacle.Obstacle;
import com.glisterbyte.singingmonsters.sfsmodels.requests.StartRemoveObstacleRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.StartRemoveObstacleResponse;

import java.time.Duration;
import java.time.Instant;

public class StartRemoveObstacleHandler extends CorrelatedResultResponseHandler<Void, StartRemoveObstacleResponse, Duration> {

    public StartRemoveObstacleHandler(EventHandlerInitArg arg) {
        super(arg, StartRemoveObstacleResponse.class);
    }

    private Duration getDuration(StartRemoveObstacleResponse response) {
        Instant completed = Instant.ofEpochMilli(response.user_structure.building_completed);
        Duration duration = Duration.between(Instant.now(), completed);
        if (duration.isNegative()) return Duration.ZERO;
        return duration;
    }

    @Override
    public Duration handleSuccess(StartRemoveObstacleResponse event, Void requestData) throws InterruptedException, ClientException {
        client.getActiveIsland().getStructure(event.user_structure_id).getEventHandler().handleStartRemoveObstacleEvent(event);
        return getDuration(event);
    }

    public Duration request(Obstacle obstacle) throws InterruptedException, ClientException {
        StartRemoveObstacleRequest request = new StartRemoveObstacleRequest();
        request.user_structure_id = obstacle.getUserStructureId();
        return Request.simpleRequest(this, request, obstacle.getIsland(), obstacle.getUserStructureId());
    }

}