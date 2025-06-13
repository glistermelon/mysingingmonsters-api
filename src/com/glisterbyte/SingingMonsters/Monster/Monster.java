package com.glisterbyte.SingingMonsters.Monster;

import com.glisterbyte.SingingMonsters.Costume;
import com.glisterbyte.SingingMonsters.Currency;
import com.glisterbyte.SingingMonsters.MonsterSpecies;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.glisterbyte.Utility.Coordinates;

import java.util.ArrayList;

public class Monster extends MonsterSpecies {
    String name;
    int level;
    int hunger;
    int happiness;
    Coordinates location;
    boolean muted;
    boolean biggified;
    Costume costume;
    int getRequiredBeds() { return 0; }
    int getFeedPrice() { return 0; }
    public Currency estimatedInventory() { return new Currency(Currency.Type.COINS, 0); }
    public Currency getCurrencyRate() { return new Currency(Currency.Type.COINS, 0); }
    public Currency getMaxInventory() { return new Currency(Currency.Type.COINS, 0); }
    public Currency getSellPrice() { return new Currency(Currency.Type.COINS, 0); }
    public Monster(SFSObject data) throws MonsterException {}
}
