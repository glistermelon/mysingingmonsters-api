package com.glisterbyte.singingmonsters.handling;

import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedEventModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class CorrelatedEventHandler <RequestData, EventT extends SfsCorrelatedEventModel, HandledT>
        extends EventHandler<RequestData, EventT, HandledT> {

    private final Logger logger = LoggerFactory.getLogger(CorrelatedEventHandler.class);

    private final RefCountSemaphoreMap<Long> waiterLocks = new RefCountSemaphoreMap<>();
    private final Map<Long, ResponseWaiter<RequestData, EventT, HandledT>> waiters = new HashMap<>();

    public CorrelatedEventHandler(EventHandlerInitArg arg, Class<EventT> eventType) {
        super(arg, eventType);
    }

    @Override
    protected @Nullable ResponseWaiter<RequestData, EventT, HandledT> takeWaiter(EventT event) {

        /*
            Some events that are supposed to be correlated don't include the correlation ID
            if whatever action they're associated with failed. In the case that only one
            request is outstanding, the issue is mitigated by the extra checks here.
         */

        Long maybeCorrId = event.getCorrelationId().orElse(null);

        if (maybeCorrId != null) {
            return waiters.remove(maybeCorrId);
        }
        else if (waiters.size() == 1) {
            return waiters.values().stream().toList().getFirst();
        }
        else {
            logger.warn(
                    "Handler {} that supports concurrency received event with no correlation ID",
                    getClass().getName()
            );
            return null;
        }

    }

    public CompletableFuture<HandledEvent<RequestData, EventT, HandledT>> waitForEventAsync(
            long correlationId, @Nullable RequestData requestData
    ) {
        var lock = waiterLocks.lock(correlationId);
        CompletableFuture<HandledEvent<RequestData, EventT, HandledT>> future = new CompletableFuture<>();
        waiters.put(correlationId, new ResponseWaiter<>(requestData, future));
        future.whenComplete((val, ex) -> {
            lock.unlock();
        });
        return future;
    }

    public CompletableFuture<HandledEvent<RequestData, EventT, HandledT>> waitForEventAsync(long correlationId) {
        return waitForEventAsync(correlationId, null);
    }

}