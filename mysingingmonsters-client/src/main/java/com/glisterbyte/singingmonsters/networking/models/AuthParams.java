package com.glisterbyte.singingmonsters.networking.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glisterbyte.singingmonsters.common.GlobalConfig;

public record AuthParams(
        int g,
        String advertiserId,
        String authVersion,
        String clientVersion,
        String packageStr,
        String platform,
        DeviceParams device
) {

    public String getUserAgent() {
        return "MSM/" + GlobalConfig.GAME_VERSION_STRING + " (android; " + device.os_version() + ")";
    }

    public ObjectNode getJson() {
        ObjectNode json = new ObjectMapper().createObjectNode();
        json.put("g", g);
        json.put("advertiser_id", advertiserId);
        json.put("auth_version", authVersion);
        json.put("client_version", clientVersion);
        json.put("package", packageStr);
        json.put("platform", platform);
        device.getJson().properties().forEach(
                entry -> json.set(entry.getKey(), entry.getValue())
        );
        return json;
    }

}