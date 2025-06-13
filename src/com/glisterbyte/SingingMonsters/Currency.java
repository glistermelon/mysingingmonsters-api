package com.glisterbyte.SingingMonsters;

public class Currency {
    public enum Type {
        COINS,
        SHARDS,
        DIAMONDS,
        FOOD,
        STARPOWER,
        RELICS,
        KEYS
    }
    public Type type;
    public long amount;
    public Currency(Type type, long amount) {
        this.type = type;
        this.amount = amount;
    }
}
