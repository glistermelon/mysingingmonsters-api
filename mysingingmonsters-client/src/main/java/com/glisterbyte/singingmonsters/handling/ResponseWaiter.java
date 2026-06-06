package com.glisterbyte.singingmonsters.handling;

import java.util.concurrent.CompletableFuture;

public record ResponseWaiter <RequestT, ResponseT, HandledT> (
        RequestT request,
        CompletableFuture<HandledEvent<RequestT, ResponseT, HandledT>> future
) { }