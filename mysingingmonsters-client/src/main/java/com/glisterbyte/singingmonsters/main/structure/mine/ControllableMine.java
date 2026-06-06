package com.glisterbyte.singingmonsters.main.structure.mine;

import com.glisterbyte.singingmonsters.exceptions.ClientException;

public interface ControllableMine {
    void collect() throws InterruptedException, ClientException;
}