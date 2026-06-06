package com.glisterbyte.singingmonsters.networking.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glisterbyte.singingmonsters.common.CacheManager;
import com.glisterbyte.singingmonsters.common.GlobalConfig;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record DeviceParams(
        String device_id,
        String device_model,
        String device_vendor,
        String os_version,
        String lang
) {

    private static final Logger logger = LoggerFactory.getLogger(DeviceParams.class);

    @JsonIgnore
    public ObjectNode getJson() {
        return new ObjectMapper()
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .valueToTree(this);
    }

    public static DeviceParams fromJson(ObjectNode json) throws JsonProcessingException {
        return new ObjectMapper().treeToValue(json, DeviceParams.class);
    }

    private static final String cacheFileName = "device.json";
    private static final int MAX_ANDROID_VERSION = 16;
    private record DevicePreset(String vendor, String model, int minAndroidVersion) { }
    private static final Map<String, DevicePreset> devicePresets = new HashMap<>();

    static {

        CSVReader reader = new CSVReader(new StringReader(GlobalConfig.getResourceAsString("/devices.csv")));
        while (true) {
            try {
                String[] csv;
                if ((csv = reader.readNext()) == null) break;
                String vendor = csv[0];
                String model = csv[3];
                int minAndroidVersion = Integer.parseInt(csv[4]);
                devicePresets.put(vendor, new DevicePreset(vendor, model, minAndroidVersion));

            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static DevicePreset getRandomDevicePreset() {
        List<String> vendors = devicePresets.keySet().stream().toList();
        return devicePresets.get(vendors.get((int)(vendors.size() * Math.random())));
    }

    public static DeviceParams getDeviceParams(@Nullable String username) {

        String cacheJsonStr = CacheManager.readFileAsString(cacheFileName);
        ObjectNode cacheJson = null;
        if (cacheJsonStr != null) {
            try {
                cacheJson = (ObjectNode)(new ObjectMapper().readTree(cacheJsonStr));
                ObjectNode paramsJson;
                if (username == null) paramsJson = (ObjectNode)cacheJson.get("default");
                else {
                    var mainJson = cacheJson.get("main");
                    paramsJson = mainJson != null ? (ObjectNode)mainJson.get(username) : null;
                }
                if (paramsJson != null) {
                    return DeviceParams.fromJson(paramsJson);
                }
            }
            catch (JsonProcessingException | ClassCastException ex) {
                logger.warn("Failed to load cached device params due to exception", ex);
                CacheManager.eraseFile(cacheFileName);
            }
        }

        String[] hexChars = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
        StringBuilder deviceId = new StringBuilder();
        for (int i = 0; i < 16; i++) deviceId.append(hexChars[(int)(16 * Math.random())]);

        DevicePreset preset = getRandomDevicePreset();

        int androidVersion = preset.minAndroidVersion +
                (int)((MAX_ANDROID_VERSION - preset.minAndroidVersion() + 1) * Math.random());

        DeviceParams params = new DeviceParams(
                deviceId.toString(),
                preset.model(),
                preset.vendor(),
                String.valueOf(androidVersion),
                "en"
        );

        ObjectNode paramsJson = params.getJson();

        ObjectMapper mapper = new ObjectMapper();
        if (cacheJson == null) cacheJson = mapper.createObjectNode();
        if (username == null) cacheJson.set("default", paramsJson);
        else {
            if (!cacheJson.has("main")) cacheJson.set("main", mapper.createObjectNode());
            ((ObjectNode)(cacheJson.get("main"))).set(username, paramsJson);
        }
        try {
            cacheJsonStr = new ObjectMapper().writeValueAsString(cacheJson);
            CacheManager.writeFile(cacheFileName, cacheJsonStr);
        }
        catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        return params;

    }

}