package com.glisterbyte.Localization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LocalizedResources {

    private static final Map<Language, LocalizedTextStore> localizedTextStoreMap = new HashMap<>();
    private static Language defaultLanguage = Language.ENGLISH;
    private static boolean initialized = false;

    private static String getResourceAsString(String resource) {

        InputStream input = LocalizedResources.class.getClassLoader().getResourceAsStream(resource);
        assert input != null;

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        try {
            for (int length; (length = input.read(buffer)) != -1; ) {
                result.write(buffer, 0, length);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return result.toString(StandardCharsets.UTF_8);

    }

    private static void loadLanguage(Language language) {

        String json = getResourceAsString("msm-text/" + language.getCode() + ".json");
        try {
            JsonNode node = new ObjectMapper().readTree(json);
            localizedTextStoreMap.put(language, new LocalizedTextStore(node));
        }
        catch (JsonProcessingException ex) {
            throw new RuntimeException("Failed to load language '" + language + "'", ex);
        }

    }

    public static void loadAllLanguages() {
        if (initialized) return;
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