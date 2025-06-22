package com.glisterbyte.SingingMonsters;

public enum StructureCategory {

    NURSERY,
    BREEDING,
    MINE,
    CASTLE,
    BAKERY,
    DECORATION,
    OBSTACLE,
    TIME_MACHINE,
    HAPPINESS_TREE,
    WAREHOUSE,
    HOTEL,
    TORCH,
    RECORDING_STUDIO,
    GLOWBE,
    FUZER,
    BATTLE_GYM,
    CRUCIBLE,
    AWAKENER,
    ATTUNER,
    SYNTHESIZER,
    NUCLEUS,
    DISH_HARMONIZER,
    POLARITY_AMPLIFIER;

    public String getTypeString() {
        return switch (this) {
            case NURSERY -> "nursery";
            case BREEDING -> "breeding";
            case MINE -> "mine";
            case CASTLE -> "castle";
            case BAKERY -> "bakery";
            case DECORATION -> "decoration";
            case OBSTACLE -> "obstacle";
            case TIME_MACHINE -> "time_machine";
            case HAPPINESS_TREE -> "happiness_tree";
            case WAREHOUSE -> "warehouse";
            case HOTEL -> "hotel";
            case TORCH -> "torch";
            case RECORDING_STUDIO -> "recording_studio";
            case GLOWBE -> "buddy";
            case FUZER -> "fuzer";
            case BATTLE_GYM -> "battle_gym";
            case CRUCIBLE -> "crucible";
            case AWAKENER -> "awakener";
            case ATTUNER -> "attuner";
            case SYNTHESIZER -> "synthesizer";
            case NUCLEUS -> "nucleus";
            case DISH_HARMONIZER -> "dish_harmonizer";
            case POLARITY_AMPLIFIER -> "polarity_amplifier";
        };
    }

    public static StructureCategory fromTypeString(String typeString) {
        return switch (typeString) {
            case "nursery" -> NURSERY;
            case "breeding" -> BREEDING;
            case "mine" -> MINE;
            case "castle" -> CASTLE;
            case "bakery" -> BAKERY;
            case "decoration" -> DECORATION;
            case "obstacle" -> OBSTACLE;
            case "time_machine" -> TIME_MACHINE;
            case "happiness_tree" -> HAPPINESS_TREE;
            case "warehouse" -> WAREHOUSE;
            case "hotel" -> HOTEL;
            case "torch" -> TORCH;
            case "recording_studio" -> RECORDING_STUDIO;
            case "buddy" -> GLOWBE;
            case "fuzer" -> FUZER;
            case "battle_gym" -> BATTLE_GYM;
            case "crucible" -> CRUCIBLE;
            case "awakener" -> AWAKENER;
            case "attuner" -> ATTUNER;
            case "synthesizer" -> SYNTHESIZER;
            case "nucleus" -> NUCLEUS;
            case "dish_harmonizer" -> DISH_HARMONIZER;
            case "polarity_amplifier" -> POLARITY_AMPLIFIER;
            default -> throw new IllegalArgumentException(
                    "Invalid structure category type string: '" + typeString + "'"
            );
        };
    }

}
