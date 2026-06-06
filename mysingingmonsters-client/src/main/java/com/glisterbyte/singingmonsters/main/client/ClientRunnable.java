package com.glisterbyte.singingmonsters.main.client;

import com.glisterbyte.singingmonsters.exceptions.ClientException;

@FunctionalInterface
public interface ClientRunnable<R> {
    R run() throws InterruptedException, ClientException;
}