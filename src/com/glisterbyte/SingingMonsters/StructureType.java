package com.glisterbyte.SingingMonsters;

public enum StructureType {

    UNKNOWN,
    MINE;

    public Integer getId() {
        return switch (this) {
            case UNKNOWN -> null;
            case MINE -> 186;
        };
    }

    public static StructureType fromId(int id) {
        return switch (id) {
            case 186 -> MINE;
            default -> UNKNOWN;
        };
    }

}
