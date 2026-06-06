package com.glisterbyte.singingmonsters.main.common;

public enum CurrencyType {

    COINS,
    FOOD,
    KEYS,
    RELICS,
    DIAMONDS,
    STARPOWER,
    MEDALS,
    SHARDS,
    WILDCARDS;

    public static CurrencyType fromString(String s) {
        return switch (s) {
            case "coins", "coin" -> COINS;
            case "food", "treats" -> FOOD;
            case "keys", "key" -> KEYS;
            case "relics", "relic" -> RELICS;
            case "diamonds", "diamond" -> DIAMONDS;
            case "starpower" -> STARPOWER;
            case "medals", "medal" -> MEDALS;
            case "shards", "eth_currency", "ethereal_currency" -> SHARDS;
            case "egg_wildcards", "egg_wildcard", "wildcards", "wildcard" -> WILDCARDS;
            default -> null;
        };
    }

}