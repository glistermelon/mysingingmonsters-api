package com.glisterbyte.SingingMonsters.Sales;

public class DecorationSale extends Sale {
    public enum Type {
        STRUCTURAL,
        AGRICULTURAL,
        MUSICAL,
        MECHANICAL,
        FOOTPATH,
        NATURAL_OBSTACLES,
        SPECIAL
    }
    Type type;
    boolean buy() { return false; }
}
