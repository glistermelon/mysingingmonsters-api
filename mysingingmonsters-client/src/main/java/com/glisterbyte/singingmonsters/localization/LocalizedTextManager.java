package com.glisterbyte.singingmonsters.localization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glisterbyte.singingmonsters.common.GlobalConfig;

import java.util.HashMap;
import java.util.Map;

public class LocalizedTextManager {

    private static final Map<Language, LocalizedTextStore> localizedTextStoreMap = new HashMap<>();

    private Language defaultLanguage = Language.ENGLISH;

    private static void loadLanguage(Language language) {

        String json = GlobalConfig.getResourceAsString("/msm-text/" + language.getCode() + ".json");
        try {
            JsonNode node = new ObjectMapper().readTree(json);
            localizedTextStoreMap.put(language, new LocalizedTextStore(node));
        }
        catch (JsonProcessingException ex) {
            throw new RuntimeException("Failed to load language '" + language + "'", ex);
        }

    }

    static {
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

    public String getText(String key, Language language) {
        return localizedTextStoreMap.get(language).getText(key);
    }

    public String getText(String key) {
        return getText(key, defaultLanguage);
    }

    public void setDefaultLanguage(Language language) {
        defaultLanguage = language;
    }

}