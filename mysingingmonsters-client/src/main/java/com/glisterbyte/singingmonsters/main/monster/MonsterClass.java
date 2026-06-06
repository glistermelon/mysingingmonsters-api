package com.glisterbyte.singingmonsters.main.monster;

public enum MonsterClass {

    NATURAL,
    SUPERNATURAL,
    ETHEREAL,
    FIRE,
    MYTHICAL,
    MAGICAL,
    CELESTIAL,
    SUPERETHEREAL,
    DIPSTER,
    PAIRONORMAL,
    TITANSOUL,
    PRIMORDIAL,
    BUDDY,

    LEGENDARY,
    LEGENDARY_WERDO,
    LEGENDARY_BBLIZARD,
    LEGENDARY_TPAIN,

    SEASON_HALLOWEEN,
    SEASON_CHRISTMAS,
    SEASON_VALENTINE,
    SEASON_EASTER,
    SEASON_SUMMER,
    SEASON_MINDBOGGLE,
    SEASON_THANKSGIVING,
    SEASON_SAINT_PATRICK,
    SEASON_LIFE_FORMULA,
    SEASON_FIREWORKS,
    SEASON_ANNIVERSARY,
    SEASON_EXPLORE,
    SEASON_ECO,
    SEASON_NEWYEAR,
    SEASON_DAYOFTHEDEAD,

    RARE_NATURAL,
    RARE_SUPERNATURAL,
    RARE_ETHEREAL,
    RARE_FIRE,
    RARE_MYTHICAL,
    RARE_MAGICAL,
    RARE_CELESTIAL,
    RARE_SUPERETHEREAL,
    RARE_SEASONAL,

    EPIC_NATURAL,
    EPIC_SUPERNATURAL,
    EPIC_ETHEREAL,
    EPIC_FIRE,
    EPIC_MYTHICAL,
    EPIC_MAGICAL,
    EPIC_SEASONAL;

    public static MonsterClass fromString(String s) {
        var exception = new IllegalArgumentException("Invalid monster class string: '" + s + "'");
        String prefix = "CLASS_";
        if (!s.startsWith(prefix)) throw exception;
        return switch (s.substring(prefix.length())) {
            case "NATURAL" -> NATURAL;
            case "SUPERNATURAL" -> SUPERNATURAL;
            case "ETHEREAL" -> ETHEREAL;
            case "FIRE" -> FIRE;
            case "MYTHICAL" -> MYTHICAL;
            case "MAGICAL" -> MAGICAL;
            case "CELESTIAL" -> CELESTIAL;
            case "SUPERETHEREAL" -> SUPERETHEREAL;
            case "DIPSTER" -> DIPSTER;
            case "PAIRONORMAL" -> PAIRONORMAL;
            case "TITANSOUL" -> TITANSOUL;
            case "PRIMORDIAL" -> PRIMORDIAL;
            case "BUDDY" -> BUDDY;
            case "LEGENDARY" -> LEGENDARY;
            case "LEGENDARY_WERDO" -> LEGENDARY_WERDO;
            case "LEGENDARY_BB" -> LEGENDARY_BBLIZARD;
            case "LEGENDARY_TPAIN" -> LEGENDARY_TPAIN;
            case "SEASON_HALLOWEEN" -> SEASON_HALLOWEEN;
            case "SEASON_CHRISTMAS" -> SEASON_CHRISTMAS;
            case "SEASON_VALENTINE" -> SEASON_VALENTINE;
            case "SEASON_EASTER" -> SEASON_EASTER;
            case "SEASON_SUMMER" -> SEASON_SUMMER;
            case "SEASON_BACKTOSCHOOL" -> SEASON_MINDBOGGLE;
            case "SEASON_THANKSGIVING" -> SEASON_THANKSGIVING;
            case "SEASON_STPATRICKS" -> SEASON_SAINT_PATRICK;
            case "SEASON_CREATION" -> SEASON_LIFE_FORMULA;
            case "SEASON_FIREWORKS" -> SEASON_FIREWORKS;
            case "SEASON_ANNIVERSARY" -> SEASON_ANNIVERSARY;
            case "SEASON_EXPLORE" -> SEASON_EXPLORE;
            case "SEASON_ECO" -> SEASON_ECO;
            case "SEASON_NEWYEAR" -> SEASON_NEWYEAR;
            case "SEASON_DAYOFTHEDEAD" -> SEASON_DAYOFTHEDEAD;
            case "RARE_NATURAL" -> RARE_NATURAL;
            case "RARE_SUPERNAT" -> RARE_SUPERNATURAL;
            case "RARE_ETH" -> RARE_ETHEREAL;
            case "RARE_FIRE" -> RARE_FIRE;
            case "RARE_MYTHICAL" -> RARE_MYTHICAL;
            case "RARE_MAGICAL" -> RARE_MAGICAL;
            case "RARE_CELESTIAL" -> RARE_CELESTIAL;
            case "RARE_SUPERETH" -> RARE_SUPERETHEREAL;
            case "RARE_SEASONAL" -> RARE_SEASONAL;
            case "EPIC_RARE" -> EPIC_NATURAL;
            case "EPIC_SUPERNAT" -> EPIC_SUPERNATURAL;
            case "EPIC_ETHEREAL" -> EPIC_ETHEREAL;
            case "EPIC_FIRE" -> EPIC_FIRE;
            case "EPIC_MYTHICAL" -> EPIC_MYTHICAL;
            case "MAGICAL_EPIC" -> EPIC_MAGICAL;
            case "EPIC_SEASONAL" -> EPIC_SEASONAL;
            default-> throw exception;
        };
    }

}
