package com.glisterbyte.singingmonsters.main.monster;

import com.glisterbyte.singingmonsters.main.common.MultiCurrencyValue;
import com.glisterbyte.singingmonsters.main.common.Position;
import com.glisterbyte.singingmonsters.exceptions.ClientException;

public interface ControllableMonster {

    MultiCurrencyValue collect() throws InterruptedException, ClientException;

    void sell() throws InterruptedException, ClientException;

    void feed() throws InterruptedException, ClientException;

    void feedToLevel(int newLevel) throws InterruptedException, ClientException;

    void feedToNextLevel() throws InterruptedException, ClientException;

    void setFlipped(boolean flipped) throws InterruptedException, ClientException;

    void flip() throws InterruptedException, ClientException;

    void move(MonsterPlacement newPlacement, double newVolume) throws InterruptedException, ClientException;
    void move(MonsterPlacement newPlacement) throws InterruptedException, ClientException;
    void move(Position newPosition, double newVolume) throws InterruptedException, ClientException;
    void move(Position newPosition) throws InterruptedException, ClientException;

    void setVolume(double newVolume) throws InterruptedException, ClientException;

    void setMuted(boolean muted) throws InterruptedException, ClientException;

    void mute() throws ClientException, InterruptedException;

    void unmute() throws ClientException, InterruptedException;

    void setName(String newName) throws ClientException;
    
}