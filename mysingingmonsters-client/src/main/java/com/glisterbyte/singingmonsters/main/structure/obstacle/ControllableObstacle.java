package com.glisterbyte.singingmonsters.main.structure.obstacle;

import com.glisterbyte.singingmonsters.exceptions.ClientException;

import java.time.Duration;

public interface ControllableObstacle {
    Duration startRemoval() throws InterruptedException, ClientException;
    void finishRemoval() throws InterruptedException, ClientException;
}