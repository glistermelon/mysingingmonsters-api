package com.glisterbyte.singingmonsters.handling;

import com.glisterbyte.singingmonsters.sfsmodels.SfsEventModel;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public abstract class UncorrelatedEventHandler <RequestData, EventT extends SfsEventModel, HandledT>
        extends EventHandler<RequestData, EventT, HandledT> {

    private final ReentrantLock waiterLock = new ReentrantLock();
    private final AtomicReference<ResponseWaiter<RequestData, EventT, HandledT>> waiterRef
            = new AtomicReference<>(null);

    public UncorrelatedEventHandler(EventHandlerInitArg arg, Class<EventT> eventType) {
        super(arg, eventType);
    }

    @Override
    protected @Nullable ResponseWaiter<RequestData, EventT, HandledT> takeWaiter(EventT event) {
        return waiterRef.getAndUpdate(x -> null);
    }

    public CompletableFuture<HandledEvent<RequestData, EventT, HandledT>> waitForEventAsync(
            @Nullable RequestData requestData
    ) {
        waiterLock.lock();
        System.out.println("locked " + getEventType().getName());
        CompletableFuture<HandledEvent<RequestData, EventT, HandledT>> future = new CompletableFuture<>();
        waiterRef.set(new ResponseWaiter<>(requestData, future));
        future.whenComplete((x1, x2) -> {
            waiterLock.unlock();
            System.out.println("unlocked " + getEventType().getName());
        });
        return future;
    }

    public CompletableFuture<HandledEvent<RequestData, EventT, HandledT>> waitForEventAsync() {
        return waitForEventAsync(null);
    }

}