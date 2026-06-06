package com.glisterbyte.singingmonsters.main.common;

import com.glisterbyte.singingmonsters.common.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public record MultiCurrencyValue(
        long coins,
        long food,
        long keys,
        long relics,
        long diamonds,
        long starpower,
        long medals,
        long shards,
        long wildcards
) {

    public static MultiCurrencyValue ofCoins(long amount) {
        return new MultiCurrencyValue(
                amount, 0, 0, 0, 0, 0, 0, 0, 0
        );
    }

    public static MultiCurrencyValue ofFood(long amount) {
        return new MultiCurrencyValue(
                0, amount, 0, 0, 0, 0, 0, 0, 0
        );
    }

    public static MultiCurrencyValue ofKeys(long amount) {
        return new MultiCurrencyValue(
                0, 0, amount, 0, 0, 0, 0, 0, 0
        );
    }

    public static MultiCurrencyValue ofRelics(long amount) {
        return new MultiCurrencyValue(
                0, 0, 0, amount, 0, 0, 0, 0, 0
        );
    }

    public static MultiCurrencyValue ofDiamonds(long amount) {
        return new MultiCurrencyValue(
                0, 0, 0, 0, amount, 0, 0, 0, 0
        );
    }

    public static MultiCurrencyValue ofStarpower(long amount) {
        return new MultiCurrencyValue(
                0, 0, 0, 0, 0, amount, 0, 0, 0
        );
    }

    public static MultiCurrencyValue ofMedals(long amount) {
        return new MultiCurrencyValue(
                0, 0, 0, 0, 0, 0, amount, 0, 0
        );
    }

    public static MultiCurrencyValue ofShards(long amount) {
        return new MultiCurrencyValue(
                0, 0, 0, 0, 0, 0, 0, amount, 0
        );
    }

    public static MultiCurrencyValue ofWildcards(long amount) {
        return new MultiCurrencyValue(
                0, 0, 0, 0, 0, 0, 0, 0, amount
        );
    }

    public static MultiCurrencyValue zero() {
        return new MultiCurrencyValue(
                0, 0, 0, 0, 0, 0, 0, 0, 0
        );
    }

    public boolean isZero() {
        return equals(zero());
    }

    public MultiCurrencyValue add(MultiCurrencyValue other) {
        return new MultiCurrencyValue(
                coins + other.coins(),
                food + other.food(),
                keys + other.keys(),
                relics + other.relics(),
                diamonds + other.diamonds(),
                starpower + other.starpower(),
                medals + other.medals(),
                shards + other.shards(),
                wildcards + other.wildcards()
        );
    }

    public MultiCurrencyValue subtract(MultiCurrencyValue other) {
        return new MultiCurrencyValue(
                coins - other.coins(),
                food - other.food(),
                keys - other.keys(),
                relics - other.relics(),
                diamonds - other.diamonds(),
                starpower - other.starpower(),
                medals - other.medals(),
                shards - other.shards(),
                wildcards - other.wildcards()
        );
    }

    public List<CurrencyType> getCurrencyTypes() {
        List<CurrencyType> types = new ArrayList<>();
        if (coins != 0) types.add(CurrencyType.COINS);
        if (food != 0) types.add(CurrencyType.FOOD);
        if (keys != 0) types.add(CurrencyType.KEYS);
        if (relics != 0) types.add(CurrencyType.RELICS);
        if (diamonds != 0) types.add(CurrencyType.DIAMONDS);
        if (starpower != 0) types.add(CurrencyType.STARPOWER);
        if (medals != 0) types.add(CurrencyType.MEDALS);
        if (shards != 0) types.add(CurrencyType.SHARDS);
        if (wildcards != 0) types.add(CurrencyType.WILDCARDS);
        return types;
    }

    public CurrencyType getCurrencyType() {
        var types = getCurrencyTypes();
        if (types.size() != 1) return null;
        return types.getFirst();
    }

    public boolean isSingleCurrencyType() {
        return getCurrencyTypes().size() == 1;
    }

    @NotNull
    @Override
    public String toString() {
        List<String> parts = new ArrayList<>();
        if (coins != 0) parts.add(coins + " coins");
        if (food != 0) parts.add(food + " food");
        if (keys != 0) parts.add(keys + " keys");
        if (relics != 0) parts.add(relics + " relics");
        if (diamonds != 0) parts.add(diamonds + " diamonds");
        if (starpower != 0) parts.add(starpower + " starpower");
        if (medals != 0) parts.add(medals + " medals");
        if (shards != 0) parts.add(shards + " shards");
        if (wildcards != 0) parts.add(wildcards + " wildcards");
        String string = String.join(", ", parts);
        if (string.isEmpty()) string = "0";
        return StringUtil.format("MultiCurrencyValue({})", string);
    }

}