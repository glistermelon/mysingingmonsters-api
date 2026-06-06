package com.glisterbyte.singingmonsters.networking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.glisterbyte.singingmonsters.common.GlobalConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glisterbyte.singingmonsters.main.client.Credentials;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.client.LoginMethod;
import com.glisterbyte.singingmonsters.networking.exceptions.*;
import com.glisterbyte.singingmonsters.networking.models.*;
import org.jetbrains.annotations.Nullable;

public class AuthClient {

    protected static final String FIRST_AUTH_HOST = "auth.bbbgame.net";
    protected static final String SECOND_AUTH_HOST = "msm-auth.bbbgame.net";

    private final Credentials credentials;
    private final LoginMethod method;
    private final AuthParams authParams;

    private FirstAuthResponse suppliedFirstAuth;

    private AuthClient(Credentials credentials, LoginMethod method, @Nullable FirstAuthResponse firstAuthResponse) {
        this.credentials = credentials;
        this.method = method;
        authParams = createAuthParams(credentials.username());
        suppliedFirstAuth = firstAuthResponse;
    }

    public AuthClient(Credentials credentials, LoginMethod method) {
        this(credentials, method, null);
    }

    public AuthClient(NewGuestAccountResponse newGuestAccountResponse) {
        this(newGuestAccountResponse.credentials(), LoginMethod.GUEST, newGuestAccountResponse.authResponse());
    }

    private static AuthParams createAuthParams(@Nullable String username) {
        return new AuthParams(
                1,
                "",
                "2.0.0",
                GlobalConfig.GAME_VERSION_STRING,
                GlobalConfig.ANDROID_PACKAGE,
                "android",
                DeviceParams.getDeviceParams(username)
        );
    }

    public AuthParams getAuthParams() {
        return authParams;
    }

    private static void checkResponseForError(ObjectNode responseJson) throws ClientAuthException {

        JsonNode okNode = responseJson.get("ok");
        if (okNode == null || !okNode.isBoolean())
            throw new ClientAuthParseException(responseJson, "'ok' missing or invalid");
        boolean okVal = okNode.asBoolean();

        if (okVal) return;

        JsonNode codeNode = responseJson.get("error");
        if (codeNode == null || !codeNode.isInt())
            throw new ClientAuthParseException(responseJson, "'error' missing or invalid");
        int code = codeNode.asInt();

        JsonNode messageNode = responseJson.get("message");
        if (messageNode == null || !messageNode.isTextual())
            throw new ClientAuthParseException(responseJson, "'message' missing or invalid");
        String message = messageNode.asText();

        throw new ClientAuthFailedException(code, message);

    }

    private FirstAuthResponse doFirstAuthentication() throws ClientAuthException, ClientHttpRequestException {

        ObjectNode params = authParams.getJson().deepCopy();
        params.put("u", credentials.username());
        params.put("p", credentials.password());
        params.put("t", method == LoginMethod.GUEST ? "anon" : "email");

        String[] paramOrder = {
                "g", "u", "p", "t",
                "advertiser_id", "auth_version",
                "client_version", "device_id",
                "device_model", "device_vendor",
                "lang", "os_version",
                "package", "platform"
        };
        // todo is 'update device' always sent?

        ObjectNode headers = new ObjectMapper().createObjectNode();
        headers.put("Host", FIRST_AUTH_HOST);
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("User-Agent", authParams.getUserAgent());
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Connection", "close");

        String[] headerOrder = {
                "Host",
                "Accept",
                "Accept-Encoding",
                "User-Agent",
                "Content-Length",
                "Content-Type",
                "Connection"
        };

        String response = NetUtil.httpPostBlocking(
                "//auth/api/token",
                FIRST_AUTH_HOST,
                NetUtil.jsonToHttpQueryStr(params, paramOrder),
                headers, headerOrder);

        ObjectNode responseJson;
        try {
            responseJson = (ObjectNode)(new ObjectMapper().readTree(response));
        }
        catch (JsonProcessingException ex) {
            throw new ClientAuthNetworkException(ex);
        }
        checkResponseForError(responseJson);
        return FirstAuthResponse.fromJson(responseJson);

    }

    private SecondAuthResponse doSecondAuthentication(String apiToken)
            throws ClientAuthException, ClientHttpRequestException {

        ObjectNode params = authParams.getJson();
        params.put("access_key", GlobalConfig.ACCESS_KEY);
        params.put("tcs", "1");

        String[] paramOrder = {
                "g",
                "access_key",
                "tcs",
                "advertiser_id",
                "auth_version",
                "client_version",
                "device_id",
                "device_model",
                "device_vendor",
                "lang",
                "os_version",
                "package",
                "platform"
        };

        ObjectNode headers = new ObjectMapper().createObjectNode();
        headers.put("Host", "msm-auth.bbbgame.net");
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "deflate, gzip");
        headers.put("User-Agent", authParams.getUserAgent());
        headers.put("Authorization", apiToken);
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        String[] order = {
                "Host",
                "Accept",
                "Accept-Encoding",
                "User-Agent",
                "Authorization",
                "Content-Length",
                "Content-Type"
        };

        String response = NetUtil.httpPostBlocking(
                "/pregame_setup.php",
                SECOND_AUTH_HOST,
                NetUtil.jsonToHttpQueryStr(params, paramOrder),
                headers, order
        );

        ObjectNode responseJson;
        try {
            responseJson = (ObjectNode)(new ObjectMapper().readTree(response));
        }
        catch (JsonProcessingException ex) {
            throw new ClientAuthNetworkException(ex);
        }
        checkResponseForError(responseJson);
        return SecondAuthResponse.fromJson(responseJson);


    }

    public static NewGuestAccountResponse createGuestAccount() throws ClientException {

        AuthParams authParams = createAuthParams(null);

        ObjectNode params = authParams.getJson().deepCopy();

        String[] paramOrder = {
                "g",
                "advertiser_id", "auth_version",
                "client_version", "device_id",
                "device_model", "device_vendor",
                "lang", "os_version",
                "package", "platform"
        };

        ObjectNode headers = new ObjectMapper().createObjectNode();
        headers.put("Host", "auth.bbbgame.net");
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("User-Agent", authParams.getUserAgent());
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        String[] headerOrder = {
                "Host",
                "Accept",
                "Accept-Encoding",
                "User-Agent",
                "Content-Length",
                "Content-Type"
        };

        String response = NetUtil.httpPostBlocking(
                "/auth/api/anon_account/",
                FIRST_AUTH_HOST,
                NetUtil.jsonToHttpQueryStr(params, paramOrder),
                headers, headerOrder
        );

        ObjectNode responseJson;
        try {
            responseJson = (ObjectNode)(new ObjectMapper().readTree(response));
        }
        catch (JsonProcessingException ex) {
            throw new ClientAuthNetworkException(ex);
        }
        checkResponseForError(responseJson);
        return NewGuestAccountResponse.fromJson(responseJson);

    }

    public AuthResults authenticate() throws ClientException {
        FirstAuthResponse firstAuth;
        if (suppliedFirstAuth != null) {
            firstAuth = suppliedFirstAuth;
            // Don't reuse it for reauthentication
            suppliedFirstAuth = null;
        }
        else firstAuth = doFirstAuthentication();
        String apiToken = firstAuth.accessToken();
        SecondAuthResponse secondAuth = doSecondAuthentication(apiToken);
        return new AuthResults(apiToken, firstAuth.userGameId(), secondAuth.serverIp());
    }

}