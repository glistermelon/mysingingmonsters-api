package com.glisterbyte.singingmonsters.main.monster;

public enum MonsterHappiness {

    // Wublins use negative happiness for negative polarity

    NEGATIVE_100_PERCENT,
    NEGATIVE_75_PERCENT,
    NEGATIVE_50_PERCENT,
    NEGATIVE_25_PERCENT,
    PERCENT_0,
    PERCENT_25,
    PERCENT_50,
    PERCENT_75,
    PERCENT_100;

    public static MonsterHappiness fromPercentage(int percent) {

        return switch (percent) {
            case -100 -> NEGATIVE_100_PERCENT;
            case -75 -> NEGATIVE_75_PERCENT;
            case -50 -> NEGATIVE_50_PERCENT;
            case -25 -> NEGATIVE_25_PERCENT;
            case 0 -> PERCENT_0;
            case 25 -> PERCENT_25;
            case 50 -> PERCENT_50;
            case 75 -> PERCENT_75;
            case 100 -> PERCENT_100;
            default -> throw new IllegalArgumentException("Invalid happiness percentage " + percent);
        };

    }

    public int asInteger() {
        return switch (this) {
            case NEGATIVE_100_PERCENT -> -100;
            case NEGATIVE_75_PERCENT -> -75;
            case NEGATIVE_50_PERCENT -> -50;
            case NEGATIVE_25_PERCENT -> -25;
            case PERCENT_0 -> 0;
            case PERCENT_25 -> 25;
            case PERCENT_50 -> 50;
            case PERCENT_75 -> 75;
            case PERCENT_100 -> 100;
        };
    }

    public int asIndex() {
        return switch (this) {
            case NEGATIVE_100_PERCENT -> -4;
            case NEGATIVE_75_PERCENT -> -3;
            case NEGATIVE_50_PERCENT -> -2;
            case NEGATIVE_25_PERCENT -> -1;
            case PERCENT_0 -> 0;
            case PERCENT_25 -> 1;
            case PERCENT_50 -> 2;
            case PERCENT_75 -> 3;
            case PERCENT_100 -> 4;
        };
    }

    public boolean isZero() {
        return this == PERCENT_0;
    }

    public boolean isNegative() {
        return asInteger() < 0;
    }

    public boolean isPositive() {
        return asInteger() > 0;
    }

}