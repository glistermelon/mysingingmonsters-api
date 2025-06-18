package com.glisterbyte.SingingMonsters;

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
    COMPOSER_ISLAND,
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
    MIRROR_PLANT_ISLAND,
    MIRROR_COLD_ISLAND,
    MIRROR_AIR_ISLAND,
    MIRROR_WATER_ISLAND,
    MIRROR_EARTH_ISLAND,
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
            case COMPOSER_ISLAND -> 11;
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
            case MIRROR_PLANT_ISLAND -> 101;
            case MIRROR_COLD_ISLAND -> 102;
            case MIRROR_AIR_ISLAND -> 103;
            case MIRROR_WATER_ISLAND -> 104;
            case MIRROR_EARTH_ISLAND -> 105;
            case MIRROR_PSYCHIC_ISLAND -> 115;
            case MIRROR_FAERIE_ISLAND -> 116;
            case MIRROR_BONE_ISLAND -> 117;
            case MIRROR_LIGHT_ISLAND -> 118;
        };
    }

    public static IslandType getTypeFromId(int id) {
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
            case 11 -> COMPOSER_ISLAND;
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
            case 101 -> MIRROR_PLANT_ISLAND;
            case 102 -> MIRROR_COLD_ISLAND;
            case 103 -> MIRROR_AIR_ISLAND;
            case 104 -> MIRROR_WATER_ISLAND;
            case 105 -> MIRROR_EARTH_ISLAND;
            case 115 -> MIRROR_PSYCHIC_ISLAND;
            case 116 -> MIRROR_FAERIE_ISLAND;
            case 117 -> MIRROR_BONE_ISLAND;
            case 118 -> MIRROR_LIGHT_ISLAND;
            default -> throw new IllegalArgumentException("Unrecognized island ID: " + id);
        };
    }

}
