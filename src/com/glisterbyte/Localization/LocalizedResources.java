package com.glisterbyte.Localization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glisterbyte.Configuration.Global;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LocalizedResources {

    private static final Map<Language, LocalizedTextStore> localizedTextStoreMap = new HashMap<>();
    private static Language defaultLanguage = Language.ENGLISH;

    private static void loadLanguage(Language language) {

        String json = Global.getResourceAsString("msm-text/" + language.getCode() + ".json");
        try {
            JsonNode node = new ObjectMapper().readTree(json);
            localizedTextStoreMap.put(language, new LocalizedTextStore(node));
        }
        catch (JsonProcessingException ex) {
            throw new RuntimeException("Failed to load language '" + language + "'", ex);
        }

    }

    public static void loadAllLanguages() {
        loadLanguage(Language.ENGLISH);
        loadLanguage(Language.SPANISH);
        loadLanguage(Language.FRENCH);
        loadLanguage(Language.GERMAN);
        loadLanguage(Language.ITALIAN);
        loadLanguage(Language.PORTUGUESE);
        loadLanguage(Language.RUSSIAN);
        loadLanguage(Language.JAPANESE);
        loadLanguage(Language.TURKISH);
    }

    public static String getText(String key, Language language) {
        return localizedTextStoreMap.get(language).getText(key);
    }

    public static String getText(String key) {
        return getText(key, defaultLanguage);
    }

    public static void setDefaultLanguage(Language language) {
        defaultLanguage = language;
    }

}