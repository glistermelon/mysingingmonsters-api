package com.glisterbyte.singingmonsters.main.island;

import com.glisterbyte.singingmonsters.main.common.CurrencyType;

import java.util.Arrays;
import java.util.List;

public enum IslandType {

    PLANT_ISLAND,
    COLD_ISLAND,
    AIR_ISLAND,
    WATER_ISLAND,
    EARTH_ISLAND,
    GOLD_ISLAND,
    ETHEREAL_ISLAND,
    SHUGABUSH_ISLAND,
    TRIBAL_ISLAND,
    WUBLIN_ISLAND,
    FIRST_COMPOSER_ISLAND,
    CELESTIAL_ISLAND,
    FIRE_HAVEN,
    FIRE_OASIS,
    PSYCHIC_ISLAND,
    FAERIE_ISLAND,
    BONE_ISLAND,
    LIGHT_ISLAND,
    MAGICAL_SANCTUM,
    THE_COLOSSINGUM,
    SEASONAL_SHANTY,
    AMBER_ISLAND,
    MYTHICAL_ISLAND,
    ETHEREAL_WORKSHOP,
    MAGICAL_NEXUS,
    PLASMA_ISLET,
    MECH_ISLET,
    SHADOW_ISLET,
    CRYSTAL_ISLET,
    PAIRONORMAL_CARNIVAL,
    MIRROR_PLANT_ISLAND,
    MIRROR_COLD_ISLAND,
    MIRROR_AIR_ISLAND,
    MIRROR_WATER_ISLAND,
    MIRROR_EARTH_ISLAND,
    SECOND_COMPOSER_ISLAND,
    THIRD_COMPOSER_ISLAND,
    FOURTH_COMPOSER_ISLAND,
    FIFTH_COMPOSER_ISLAND,
    MIRROR_PSYCHIC_ISLAND,
    MIRROR_FAERIE_ISLAND,
    MIRROR_BONE_ISLAND,
    MIRROR_LIGHT_ISLAND;

    public int getId() {
        return switch (this) {
            case PLANT_ISLAND -> 1;
            case COLD_ISLAND -> 2;
            case AIR_ISLAND -> 3;
            case WATER_ISLAND -> 4;
            case EARTH_ISLAND -> 5;
            case GOLD_ISLAND -> 6;
            case ETHEREAL_ISLAND -> 7;
            case SHUGABUSH_ISLAND -> 8;
            case TRIBAL_ISLAND -> 9;
            case WUBLIN_ISLAND -> 10;
            case FIRST_COMPOSER_ISLAND -> 11;
            case CELESTIAL_ISLAND -> 12;
            case FIRE_HAVEN -> 13;
            case FIRE_OASIS -> 14;
            case PSYCHIC_ISLAND -> 15;
            case FAERIE_ISLAND -> 16;
            case BONE_ISLAND -> 17;
            case LIGHT_ISLAND -> 18;
            case MAGICAL_SANCTUM -> 19;
            case THE_COLOSSINGUM -> 20;
            case SEASONAL_SHANTY -> 21;
            case AMBER_ISLAND -> 22;
            case MYTHICAL_ISLAND -> 23;
            case ETHEREAL_WORKSHOP -> 24;
            case MAGICAL_NEXUS -> 25;
            case PLASMA_ISLET -> 26;
            case MECH_ISLET -> 27;
            case SHADOW_ISLET -> 28;
            case CRYSTAL_ISLET -> 29;
            case PAIRONORMAL_CARNIVAL -> 31;
            case MIRROR_PLANT_ISLAND -> 101;
            case MIRROR_COLD_ISLAND -> 102;
            case MIRROR_AIR_ISLAND -> 103;
            case MIRROR_WATER_ISLAND -> 104;
            case MIRROR_EARTH_ISLAND -> 105;
            case SECOND_COMPOSER_ISLAND -> 106;
            case THIRD_COMPOSER_ISLAND -> 107;
            case FOURTH_COMPOSER_ISLAND -> 108;
            case FIFTH_COMPOSER_ISLAND -> 109;
            case MIRROR_PSYCHIC_ISLAND -> 115;
            case MIRROR_FAERIE_ISLAND -> 116;
            case MIRROR_BONE_ISLAND -> 117;
            case MIRROR_LIGHT_ISLAND -> 118;
        };
    }

    public static IslandType fromId(int id) {
        return switch (id) {
            case 1 -> PLANT_ISLAND;
            case 2 -> COLD_ISLAND;
            case 3 -> AIR_ISLAND;
            case 4 -> WATER_ISLAND;
            case 5 -> EARTH_ISLAND;
            case 6 -> GOLD_ISLAND;
            case 7 -> ETHEREAL_ISLAND;
            case 8 -> SHUGABUSH_ISLAND;
            case 9 -> TRIBAL_ISLAND;
            case 10 -> WUBLIN_ISLAND;
            case 11 -> FIRST_COMPOSER_ISLAND;
            case 12 -> CELESTIAL_ISLAND;
            case 13 -> FIRE_HAVEN;
            case 14 -> FIRE_OASIS;
            case 15 -> PSYCHIC_ISLAND;
            case 16 -> FAERIE_ISLAND;
            case 17 -> BONE_ISLAND;
            case 18 -> LIGHT_ISLAND;
            case 19 -> MAGICAL_SANCTUM;
            case 20 -> THE_COLOSSINGUM;
            case 21 -> SEASONAL_SHANTY;
            case 22 -> AMBER_ISLAND;
            case 23 -> MYTHICAL_ISLAND;
            case 24 -> ETHEREAL_WORKSHOP;
            case 25 -> MAGICAL_NEXUS;
            case 26 -> PLASMA_ISLET;
            case 27 -> MECH_ISLET;
            case 28 -> SHADOW_ISLET;
            case 29 -> CRYSTAL_ISLET;
            case 31 -> PAIRONORMAL_CARNIVAL;
            case 101 -> MIRROR_PLANT_ISLAND;
            case 102 -> MIRROR_COLD_ISLAND;
            case 103 -> MIRROR_AIR_ISLAND;
            case 104 -> MIRROR_WATER_ISLAND;
            case 105 -> MIRROR_EARTH_ISLAND;
            case 106 -> SECOND_COMPOSER_ISLAND;
            case 107 -> THIRD_COMPOSER_ISLAND;
            case 108 -> FOURTH_COMPOSER_ISLAND;
            case 109 -> FIFTH_COMPOSER_ISLAND;
            case 115 -> MIRROR_PSYCHIC_ISLAND;
            case 116 -> MIRROR_FAERIE_ISLAND;
            case 117 -> MIRROR_BONE_ISLAND;
            case 118 -> MIRROR_LIGHT_ISLAND;
            default -> throw new IllegalArgumentException("Unrecognized island ID: " + id);
        };
    }

//    public static IslandType fromString(String str) {
//        return switch (str) {
//            case "plant" -> PLANT_ISLAND;
//            case "cold" -> COLD_ISLAND;
//            case "air" -> AIR_ISLAND;
//            case "water" -> WATER_ISLAND;
//            case "earth" -> EARTH_ISLAND;
//            case "gold" -> GOLD_ISLAND;
//            case "ethereal" -> ETHEREAL_ISLAND;
//            case "shugabush" -> SHUGABUSH_ISLAND;
//            case "tribal" -> TRIBAL_ISLAND;
//            case "wublin" -> WUBLIN_ISLAND;
//            case "composer" -> FIRST_COMPOSER_ISLAND;
//            case "celestial" -> CELESTIAL_ISLAND;
//            case "fire_haven" -> FIRE_HAVEN;
//            case "fire_oasis" -> FIRE_OASIS;
//            case "psychic" -> PSYCHIC_ISLAND;
//            case "faerie" -> FAERIE_ISLAND;
//            case "bone" -> BONE_ISLAND;
//            case "light" -> LIGHT_ISLAND;
//            case "magical_sanctum" -> MAGICAL_SANCTUM;
//            case "colossingum" -> THE_COLOSSINGUM;
//            case "seasonal" -> SEASONAL_SHANTY;
//            case "amber" -> AMBER_ISLAND;
//            case "mythical" -> MYTHICAL_ISLAND;
//            case "ethereal_workshop" -> ETHEREAL_WORKSHOP;
//            case "magical_nexus" -> MAGICAL_NEXUS;
//            case "plasma_islet" -> PLASMA_ISLET;
//            case "mirror_plant" -> MIRROR_PLANT_ISLAND;
//            case "mirror_cold" -> MIRROR_COLD_ISLAND;
//            case "mirror_air" -> MIRROR_AIR_ISLAND;
//            case "mirror_water" -> MIRROR_WATER_ISLAND;
//            case "mirror_earth" -> MIRROR_EARTH_ISLAND;
//            case "mirror_psychic" -> MIRROR_PSYCHIC_ISLAND;
//            case "mirror_faerie" -> MIRROR_FAERIE_ISLAND;
//            case "mirror_bone" -> MIRROR_BONE_ISLAND;
//            case "mirror_light" -> MIRROR_LIGHT_ISLAND;
//            default -> throw new IllegalArgumentException("Unrecognized island string: " + str);
//        };
//    }

    public static List<IslandType> allIslandTypes() {
        return Arrays.asList(
                PLANT_ISLAND,
                COLD_ISLAND,
                AIR_ISLAND,
                WATER_ISLAND,
                EARTH_ISLAND,
                GOLD_ISLAND,
                ETHEREAL_ISLAND,
                SHUGABUSH_ISLAND,
                TRIBAL_ISLAND,
                WUBLIN_ISLAND,
                FIRST_COMPOSER_ISLAND,
                CELESTIAL_ISLAND,
                FIRE_HAVEN,
                FIRE_OASIS,
                PSYCHIC_ISLAND,
                FAERIE_ISLAND,
                BONE_ISLAND,
                LIGHT_ISLAND,
                MAGICAL_SANCTUM,
                THE_COLOSSINGUM,
                SEASONAL_SHANTY,
                AMBER_ISLAND,
                MYTHICAL_ISLAND,
                ETHEREAL_WORKSHOP,
                MAGICAL_NEXUS,
                PLASMA_ISLET,
                MECH_ISLET,
                SHADOW_ISLET,
                CRYSTAL_ISLET,
                PAIRONORMAL_CARNIVAL,
                MIRROR_PLANT_ISLAND,
                MIRROR_COLD_ISLAND,
                MIRROR_AIR_ISLAND,
                MIRROR_WATER_ISLAND,
                MIRROR_EARTH_ISLAND,
                SECOND_COMPOSER_ISLAND,
                THIRD_COMPOSER_ISLAND,
                FOURTH_COMPOSER_ISLAND,
                FIFTH_COMPOSER_ISLAND,
                MIRROR_PSYCHIC_ISLAND,
                MIRROR_FAERIE_ISLAND,
                MIRROR_BONE_ISLAND,
                MIRROR_LIGHT_ISLAND
        );

    }

    public static List<IslandType> allComposerTypes() {
        return Arrays.asList(
                FIRST_COMPOSER_ISLAND,
                SECOND_COMPOSER_ISLAND,
                THIRD_COMPOSER_ISLAND,
                FOURTH_COMPOSER_ISLAND,
                FIFTH_COMPOSER_ISLAND
        );
    }

    @SuppressWarnings("DuplicateBranchesInSwitch")
    public CurrencyType getCollectionCurrencyType() {
        return switch (this) {
            case PLANT_ISLAND -> CurrencyType.COINS;
            case COLD_ISLAND -> CurrencyType.COINS;
            case AIR_ISLAND -> CurrencyType.COINS;
            case WATER_ISLAND -> CurrencyType.COINS;
            case EARTH_ISLAND -> CurrencyType.COINS;
            case GOLD_ISLAND -> null;
            case ETHEREAL_ISLAND -> CurrencyType.SHARDS;
            case SHUGABUSH_ISLAND -> CurrencyType.COINS;
            case TRIBAL_ISLAND -> null;
            case WUBLIN_ISLAND -> null;
            case FIRST_COMPOSER_ISLAND -> null;
            case CELESTIAL_ISLAND -> null;
            case FIRE_HAVEN -> CurrencyType.COINS;
            case FIRE_OASIS -> CurrencyType.COINS;
            case PSYCHIC_ISLAND -> CurrencyType.COINS;
            case FAERIE_ISLAND -> CurrencyType.COINS;
            case BONE_ISLAND -> CurrencyType.COINS;
            case LIGHT_ISLAND -> CurrencyType.COINS;
            case MAGICAL_SANCTUM -> CurrencyType.SHARDS;
            case THE_COLOSSINGUM -> null;
            case SEASONAL_SHANTY -> CurrencyType.COINS;
            case AMBER_ISLAND -> CurrencyType.RELICS;
            case MYTHICAL_ISLAND -> CurrencyType.COINS;
            case ETHEREAL_WORKSHOP -> CurrencyType.SHARDS;
            case MAGICAL_NEXUS -> null;
            case PLASMA_ISLET -> CurrencyType.SHARDS;
            case MECH_ISLET -> CurrencyType.SHARDS;
            case SHADOW_ISLET -> CurrencyType.SHARDS;
            case CRYSTAL_ISLET -> CurrencyType.SHARDS;
            case PAIRONORMAL_CARNIVAL -> null;
            case MIRROR_PLANT_ISLAND -> CurrencyType.COINS;
            case MIRROR_COLD_ISLAND -> CurrencyType.COINS;
            case MIRROR_AIR_ISLAND -> CurrencyType.COINS;
            case MIRROR_WATER_ISLAND -> CurrencyType.COINS;
            case MIRROR_EARTH_ISLAND -> CurrencyType.COINS;
            case SECOND_COMPOSER_ISLAND -> null;
            case THIRD_COMPOSER_ISLAND -> null;
            case FOURTH_COMPOSER_ISLAND -> null;
            case FIFTH_COMPOSER_ISLAND -> null;
            case MIRROR_PSYCHIC_ISLAND -> CurrencyType.COINS;
            case MIRROR_FAERIE_ISLAND -> CurrencyType.COINS;
            case MIRROR_BONE_ISLAND -> CurrencyType.COINS;
            case MIRROR_LIGHT_ISLAND -> CurrencyType.COINS;
        };
    }

}