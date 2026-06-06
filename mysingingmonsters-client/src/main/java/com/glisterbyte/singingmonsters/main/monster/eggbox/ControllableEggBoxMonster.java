package com.glisterbyte.singingmonsters.main.monster.eggbox;

import com.glisterbyte.singingmonsters.exceptions.ClientException;

public interface ControllableEggBoxMonster {
    void activate() throws InterruptedException, ClientException;
    void sellEggs() throws InterruptedException, ClientException;
}