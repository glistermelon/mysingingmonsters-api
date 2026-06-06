package com.glisterbyte.singingmonsters.handling;

public class HandledEvent<RequestT, EventT, HandledT> {

    private final RequestT request;
    private final EventT event;
    private final HandledT result;

    public HandledEvent(RequestT request, EventT event, HandledT result) {
        this.request = request;
        this.event = event;
        this.result = result;
    }

    public RequestT getRequest() {
        return request;
    }

    public EventT getEvent() {
        return event;
    }

    public HandledT getResult() {
        return result;
    }

}