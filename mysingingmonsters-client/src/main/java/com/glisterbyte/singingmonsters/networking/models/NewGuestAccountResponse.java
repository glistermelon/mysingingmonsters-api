package com.glisterbyte.singingmonsters.networking.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glisterbyte.singingmonsters.main.client.Credentials;
import com.glisterbyte.singingmonsters.networking.exceptions.ClientAuthParseException;

public record NewGuestAccountResponse(
        FirstAuthResponse authResponse,
        Credentials credentials
) {

    public static NewGuestAccountResponse fromJson(ObjectNode responseJson) throws ClientAuthParseException {

        FirstAuthResponse authResponse = FirstAuthResponse.fromJson(responseJson);

        JsonNode usernameNode = responseJson.get("username");
        if (usernameNode == null || !usernameNode.isTextual())
            throw new ClientAuthParseException(responseJson, "'username' missing or invalid");
        String username = usernameNode.asText();

        JsonNode passwordNode = responseJson.get("password");
        if (passwordNode == null || !passwordNode.isTextual())
            throw new ClientAuthParseException(responseJson, "'password' missing or invalid");
        String password = passwordNode.asText();

        return new NewGuestAccountResponse(authResponse, new Credentials(username, password));

    }

}