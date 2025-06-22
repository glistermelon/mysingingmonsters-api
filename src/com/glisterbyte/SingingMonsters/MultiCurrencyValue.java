package com.glisterbyte.SingingMonsters;

public record MultiCurrencyValue(
        long coins,
        long keys,
        long relics,
        long diamonds,
        long starpower,
        long medals,
        long shards
) { }