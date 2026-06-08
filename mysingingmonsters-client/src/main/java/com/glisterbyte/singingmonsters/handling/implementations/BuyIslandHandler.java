package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.exceptions.ClientIllegalArgumentException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.main.island.IslandType;
import com.glisterbyte.singingmonsters.sfsmodels.requests.BuyIslandRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.BuyIslandResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuyIslandHandler extends UncorrelatedResultResponseHandler<Void, BuyIslandResponse, Island> {

    private final Logger logger = LoggerFactory.getLogger(BuyIslandHandler.class);

    public BuyIslandHandler(EventHandlerInitArg arg) {
        super(arg, BuyIslandResponse.class);
    }

    @Override
    public Island handleSuccess(BuyIslandResponse response, Void requestData) throws InterruptedException, ClientException {
        return client.getEventHandler().handleBuyIslandEvent(response);
    }

    public Island request(int typeId, String name) throws InterruptedException, ClientException {

        if (typeId == IslandType.TRIBAL_ISLAND.getId() && name.isBlank()) {
            throw new ClientIllegalArgumentException("A name must be specified to buy Tribal Island");
        }

        if (typeId != IslandType.TRIBAL_ISLAND.getId() && name != null && !name.isEmpty()) {
            logger.warn("Name parameter (probably) has no effect when buying islands other than Tribal Island");
        }

        BuyIslandRequest request = new BuyIslandRequest();
        request.island_id = typeId;
        request.island_name = name;
        request.starpower_purchase = false;

        return Request.simpleRequest(this, request, null);

    }

}