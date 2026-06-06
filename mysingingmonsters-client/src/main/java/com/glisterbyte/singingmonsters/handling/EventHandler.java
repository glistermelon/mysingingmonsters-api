package com.glisterbyte.singingmonsters.handling;

import com.glisterbyte.singingmonsters.networking.WebsocketClient;
import com.glisterbyte.singingmonsters.main.client.Client;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.sfsmodels.SfsEventModel;

import javax.annotation.Nullable;

public abstract class EventHandler <RequestData, EventT extends SfsEventModel, HandledT>  {

    final protected Client client;
    final protected WebsocketClient wsClient;
    final protected EventHandlerManager handlerManager;

    final protected Class<EventT> eventType;

    public EventHandler(EventHandlerInitArg arg, Class<EventT> eventType) {
        this.client = arg.client();
        wsClient = arg.wsClient();
        handlerManager = arg.eventHandlerManager();
        this.eventType = eventType;
    }

    public Class<? extends SfsEventModel> getEventType() {
        return eventType;
    }

    protected HandledT process(EventT event, RequestData requestData) throws InterruptedException, ClientException {
        return null;
    }

    protected abstract @Nullable ResponseWaiter<RequestData, EventT, HandledT>takeWaiter(EventT event);

    public final synchronized void handle(SfsEventModel event) throws InterruptedException, ClientException {

        if (event.properties != null) client.update(event.properties);

        //noinspection unchecked
        EventT castedEvent = (EventT)event;
        var waiter = takeWaiter(castedEvent);
        try {
            RequestData requestData = waiter != null ? waiter.request() : null;
            var result = process(castedEvent, requestData);
            if (waiter != null) waiter.future().complete(new HandledEvent<>(waiter.request(), castedEvent, result));
        }
        catch (Throwable ex) {
            if (waiter != null) waiter.future().completeExceptionally(ex);
            else throw ex;
        }

    }

}