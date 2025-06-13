package com.glisterbyte.SingingMonsters.Sales;

import com.glisterbyte.SingingMonsters.Currency;
import com.glisterbyte.SingingMonsters.Market;

public abstract class Sale {
    Market market;
    Currency price;
    String name;
    String description;
    Long timeRemaining;
    int currentPossessions;
    abstract boolean buy();
}
