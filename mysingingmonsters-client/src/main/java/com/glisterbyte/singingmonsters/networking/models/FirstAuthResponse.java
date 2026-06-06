package com.glisterbyte.singingmonsters.networking.models;

// Ignored 'ok', 'login_types', 'token_type', 'device_updated'

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glisterbyte.singingmonsters.networking.exceptions.ClientAuthParseException;

public record FirstAuthResponse(
        String userGameId,
        String accessToken,
        long expiresAt
) {

    public static FirstAuthResponse fromJson(ObjectNode responseJson) throws ClientAuthParseException {

        String userGameId;
        var userGameIdEx = new ClientAuthParseException(responseJson, "'user_game_id' missing or invalid");
        JsonNode userGameIdNode = responseJson.get("user_game_id");
        if (userGameIdNode == null) throw userGameIdEx;
        if (userGameIdNode.isTextual()) userGameId = userGameIdNode.asText();
        else {
            if (!userGameIdNode.isArray()) throw userGameIdEx;
            JsonNode elementNode = userGameIdNode.get(0);
            if (elementNode == null || !elementNode.isTextual())  throw userGameIdEx;
            userGameId = elementNode.asText();
        }

        JsonNode accessTokenNode = responseJson.get("access_token");
        if (accessTokenNode == null || !accessTokenNode.isTextual())
            throw new ClientAuthParseException(responseJson, "'access_token' missing or invalid");
        String accessToken = accessTokenNode.asText();

        JsonNode expiresAtNode = responseJson.get("expires_at");
        if (expiresAtNode == null || !expiresAtNode.isIntegralNumber())
            throw new ClientAuthParseException(responseJson, "'expires_at' missing or invalid");
        long expiresAt = expiresAtNode.asLong();

        return new FirstAuthResponse(userGameId, accessToken, expiresAt);

    }

}