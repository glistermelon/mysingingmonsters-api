package com.glisterbyte.SingingMonsters;

public enum MonsterHappiness {

    PERCENT_0,
    PERCENT_25,
    PERCENT_50,
    PERCENT_75,
    PERCENT_100;

    public static MonsterHappiness fromPercentage(int percent) {
        return switch (percent) {
            case 0 -> PERCENT_0;
            case 25 -> PERCENT_25;
            case 50 -> PERCENT_50;
            case 75 -> PERCENT_75;
            case 100 -> PERCENT_100;
            default -> throw new IllegalArgumentException("Invalid happiness percentage: " + percent);
        };
    }

}