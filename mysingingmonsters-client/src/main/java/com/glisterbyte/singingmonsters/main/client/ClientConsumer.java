package com.glisterbyte.singingmonsters.main.client;

import com.glisterbyte.singingmonsters.exceptions.ClientException;

@FunctionalInterface
public interface ClientConsumer<T, R> {
    R run(T arg) throws InterruptedException, ClientException;
}