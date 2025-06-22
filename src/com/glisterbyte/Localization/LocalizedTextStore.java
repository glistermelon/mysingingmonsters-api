package com.glisterbyte.Localization;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

public class LocalizedTextStore {

    private final int crcSeed;
    private final Map<Long, String> data = new HashMap<>();

    public LocalizedTextStore(JsonNode node) {
        crcSeed = node.get("seed").asInt();
        for (var entry : node.get("data").properties()) {
            data.put(Long.parseLong(entry.getKey()), entry.getValue().asText());
        }
    }

    public String getText(String key) {
        int keyHashSigned = CRC32.crc32(crcSeed, key.getBytes());
        long keyHash = Integer.toUnsignedLong(keyHashSigned);
        return data.getOrDefault(keyHash, null);
    }

}
