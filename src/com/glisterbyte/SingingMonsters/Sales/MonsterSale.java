package com.glisterbyte.SingingMonsters.Sales;

import com.glisterbyte.SingingMonsters.Currency;
import com.glisterbyte.SingingMonsters.Market;
import com.glisterbyte.SingingMonsters.MonsterSpecies;

public class MonsterSale extends Sale {
    MonsterSpecies species;
    int getBedsRequired() { return 0; }

    boolean buy() { return false; }

}
