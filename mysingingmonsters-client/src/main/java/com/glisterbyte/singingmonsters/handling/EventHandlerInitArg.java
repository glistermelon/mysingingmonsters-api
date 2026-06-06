package com.glisterbyte.singingmonsters.handling;

import com.glisterbyte.singingmonsters.networking.WebsocketClient;
import com.glisterbyte.singingmonsters.main.client.Client;

public record EventHandlerInitArg(
        Client client,
        WebsocketClient wsClient,
        EventHandlerManager eventHandlerManager
) { }