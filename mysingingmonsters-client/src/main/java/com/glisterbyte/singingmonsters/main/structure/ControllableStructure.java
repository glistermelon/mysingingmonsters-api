package com.glisterbyte.singingmonsters.main.structure;

import com.glisterbyte.singingmonsters.main.common.Position;
import com.glisterbyte.singingmonsters.exceptions.ClientException;

public interface ControllableStructure {

    double MIN_SCALE = 0.7;
    double MAX_SCALE = 1.1;

    void sell() throws InterruptedException, ClientException;
    void setFlipped(boolean flipped) throws InterruptedException, ClientException;
    void flip() throws InterruptedException, ClientException;
    void move(Position newPosition, double newScale) throws InterruptedException, ClientException;
    void move(Position newPosition) throws InterruptedException, ClientException;
    void setScale(double newScale) throws InterruptedException, ClientException;

}