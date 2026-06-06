package com.glisterbyte.singingmonsters.handling;

import com.glisterbyte.singingmonsters.exceptions.ActionFailedException;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedResultResponse;

public abstract class CorrelatedResultResponseHandler <RequestData, EventT extends SfsCorrelatedResultResponse, HandledT>
        extends CorrelatedEventHandler<RequestData, EventT, HandledT> {

    public CorrelatedResultResponseHandler(EventHandlerInitArg arg, Class<EventT> eventType) {
        super(arg, eventType);
    }

    public abstract HandledT handleSuccess(EventT event, RequestData requestData)
            throws InterruptedException, ClientException;

    public HandledT handleFailure(EventT event, RequestData requestData) throws InterruptedException, ClientException {
        throw new ActionFailedException(event, client.getLocalizedTextManager());
    }

    @Override
    protected HandledT process(EventT event, RequestData requestData) throws InterruptedException, ClientException {
        if (event.success) return handleSuccess(event, requestData);
        else return handleFailure(event, requestData);
    }

}