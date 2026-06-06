package com.glisterbyte.singingmonsters.main.monster;

public enum MonsterFamily {

    NATURAL,
    SEASONAL,
    SUPERNATURAL,
    ETHEREAL,

    LEGENDARY,
    LEGENDARY_WERDO,
    LEGENDARY_BBLIZARD,
    LEGENDARY_TPAIN,

    SUPERNATURAL_WUBLIN,
    SUPERNATURAL_ETHEREAL, // any of the Wubbox variants on Ethereal Island
    DIPSTER,
    GLOWBE, // "BUDDY"
    CELESTIAL,
    FIRE,
    MYTHICAL,
    MAGICAL,
    PSYCHIC, // MAGICAL_ETHEREAL
    DREAMYTHICAL, // DREAMMYTHICAL
    PAIRONORMAL,
    PRIMORDIAL,
    TITANSOUL,
    MYTHICAL_CATALYST;

    public static MonsterFamily fromString(String s) {
        var exception = new IllegalArgumentException("Invalid monster family string: '" + s + "'");
        String prefix = "CLASS_";
        if (!s.startsWith(prefix)) throw exception;
        return switch (s.substring(prefix.length())) {
            case "NATURAL" -> NATURAL;
            case "SEASON" -> SEASONAL;
            case "SUPERNATURAL" -> SUPERNATURAL;
            case "ETHEREAL" -> ETHEREAL;
            case "LEGENDARY" -> LEGENDARY;
            case "LEGENDARY_WERDO" -> LEGENDARY_WERDO;
            case "LEGENDARY_BB" -> LEGENDARY_BBLIZARD;
            case "LEGENDARY_TPAIN" -> LEGENDARY_TPAIN;
            case "SUPERNATURAL_WUBLIN" -> SUPERNATURAL_WUBLIN;
            case "SUPERETHEREAL" -> SUPERNATURAL_ETHEREAL;
            case "DIPSTER" -> DIPSTER;
            case "BUDDY" -> GLOWBE;
            case "CELESTIAL" -> CELESTIAL;
            case "FIRE" -> FIRE;
            case "MYTHICAL" -> MYTHICAL;
            case "MAGICAL" -> MAGICAL;
            case "MAGICAL_ETHEREAL" -> PSYCHIC;
            case "DREAMMYTHICAL" -> DREAMYTHICAL;
            case "PAIRONORMAL" -> PAIRONORMAL;
            case "PRIMORDIAL" -> PRIMORDIAL;
            case "TITANSOUL" -> TITANSOUL;
            case "MYTHICAL_CATALYST" -> MYTHICAL_CATALYST;
            default -> throw exception;
        };
    }

}
