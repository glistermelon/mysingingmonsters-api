package com.glisterbyte.singingmonsters.networking.models;

// Ignored 'contentUrl'

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glisterbyte.singingmonsters.networking.exceptions.ClientAuthParseException;

public record SecondAuthResponse(
        int serverId,
        String serverIp // format example: "tomcat|ssl|msm-mobile-prod-v2.bbbgame.net"
) {

    public static SecondAuthResponse fromJson(ObjectNode responseJson) throws ClientAuthParseException {

        JsonNode idNode = responseJson.get("serverId");
        if (idNode == null || !idNode.isIntegralNumber())
            throw new ClientAuthParseException(responseJson, "'serverId' missing or invalid");
        int id = idNode.asInt();

        JsonNode ipNode = responseJson.get("serverIp");
        if (ipNode == null || !ipNode.isTextual())
            throw new ClientAuthParseException(responseJson, "'serverIp' missing or invalid");
        String ip = ipNode.asText();

        return new SecondAuthResponse(id, ip);

    }

}