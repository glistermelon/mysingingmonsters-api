package com.glisterbyte.Localization;

public enum Language {

    ENGLISH,
    SPANISH,
    FRENCH,
    GERMAN,
    ITALIAN,
    PORTUGUESE,
    RUSSIAN,
    JAPANESE,
    TURKISH;

    public String getCode() {
        return switch (this) {
            case ENGLISH -> "en";
            case SPANISH -> "es";
            case FRENCH -> "fr";
            case GERMAN -> "de";
            case ITALIAN -> "it";
            case PORTUGUESE -> "pt";
            case RUSSIAN -> "ru";
            case JAPANESE -> "ja";
            case TURKISH -> "tr";
        };
    }

}
