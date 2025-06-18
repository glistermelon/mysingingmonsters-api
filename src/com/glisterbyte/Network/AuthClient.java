package com.glisterbyte.Network;

import com.glisterbyte.Configuration.Global;
import com.glisterbyte.Network.AuthClientException.AuthenticationFailed;
import com.glisterbyte.Network.AuthClientException.GenerateAnonAccountFailed;

import java.lang.Math;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

class AuthClient {

    protected static final String ANDROID_PACKAGE = "com.bigbluebubble.singingmonsters.full";
    protected static final String FIRST_AUTH_HOST = "auth.bbbgame.net";
    protected static final String SECOND_AUTH_HOST = "msm-auth.bbbgame.net";

    private ObjectNode authenticationParams;

    public AuthClient() {
        initializeAuthParams();
    }

    public ObjectNode getAuthParams() {
        return authenticationParams;
    }

    // TODO: generating a random device every time is a vulnerability
    // TODO: always using os_version 12 and lang en might also be a vulnerability?
    // TODO: make sure these are consistent with actual game requests
    private static ObjectNode generateRandomDevice() {

        ObjectNode device = new ObjectMapper().createObjectNode();

        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 16; i++)
            id.append(Global.HEX_CHARS[(int) (16 * Math.random())]);

        device.put("device_id", id.toString());
        device.put("device_model", Global.ANDROID_MODELS[(int)(Global.ANDROID_MODELS.length * Math.random())]);
        device.put("device_vendor", Global.ANDROID_VENDORS[(int)(Global.ANDROID_VENDORS.length * Math.random())]);
        device.put("os_version", "12");
        device.put("lang", "en");

        return device;

    }

    // TODO: make sure these are consistent with actual game requests
    private void initializeAuthParams() {

        ObjectNode authData = new ObjectMapper().createObjectNode();
        authData.put("g", 1);
        authData.put("advertiser_id", "");
        authData.put("auth_version", "2.0.0");
        authData.put("client_version", Global.GAME_VERSION_STRING);
        authData.put("package", ANDROID_PACKAGE);
        authData.put("platform", "android");

        generateRandomDevice().properties().forEach(
                entry -> authData.set(entry.getKey(), entry.getValue())
        );

        authenticationParams = authData;
    }

    private ObjectNode generateAnonAccount() {

        ObjectNode params = authenticationParams.deepCopy();
        params.put("update_device", 1);

        String[] paramOrder = {
                "g",
                "advertiser_id", "auth_version",
                "client_version", "device_id",
                "device_model", "device_vendor",
                "lang", "os_version",
                "package", "platform",
                "update_device"
        };

        ObjectNode headers = new ObjectMapper().createObjectNode();
        headers.put("Host", "auth.bbbgame.net");
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("User-Agent",
                "MSM/" + Global.GAME_VERSION_STRING
                        + " (android; " + authenticationParams.get("os_version").asText() + ")"
        );
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        String[] headerOrder = {
                "Host",
                "Accept",
                "Accept-Encoding",
                "User-Agent",
                "Content-Length",
                "Content-Type"
        };

        try {
            String response = NetUtil.basicHttpsPost(
                    "/auth/api/anon_account/",
                    FIRST_AUTH_HOST,
                    NetUtil.JsonToHttpQueryStr(params, paramOrder),
                    headers, headerOrder
            );
            return (ObjectNode)(new ObjectMapper().readTree(response));
        } catch (Exception ex) {
            throw new GenerateAnonAccountFailed();
        }
    }
    private ObjectNode doFirstAuthentication(Credentials credentials) {

        ObjectNode params = authenticationParams.deepCopy();
        params.put("u", credentials.getUsername());
        params.put("p", credentials.getPassword());
        params.put("t", credentials.getType());
        params.put("fr", "1");

        String[] paramOrder = {
                "g", "u", "p", "t", "fr",
                "advertiser_id", "auth_version",
                "client_version", "device_id",
                "device_model", "device_vendor",
                "lang", "os_version",
                "package", "platform"
        };

        ObjectNode headers = new ObjectMapper().createObjectNode();
        headers.put("Host", FIRST_AUTH_HOST);
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("User-Agent",
                "MSM/" + Global.GAME_VERSION_STRING
                        + " (android; " + authenticationParams.get("os_version").asText() + ")"
        );
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

        try {
            String response = NetUtil.basicHttpsPost(
                    "/auth/api/token",
                    FIRST_AUTH_HOST,
                    NetUtil.JsonToHttpQueryStr(params, paramOrder),
                    headers, headerOrder);
            return (ObjectNode)(new ObjectMapper().readTree(response));
        } catch (Exception ex) {
            throw new AuthenticationFailed();
        }
    }
    private ObjectNode doSecondAuthentication(String apiToken) {

        ObjectNode params = authenticationParams.deepCopy();
        params.put("advertiser_id", "");
        params.put("access_key", Global.ACCESS_KEY);
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
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("User-Agent",
                "MSM/" + Global.GAME_VERSION_STRING
                        + " (android; " + params.get("os_version").asText() + ")"
        );
        headers.put("Authorization", apiToken);
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Connection", "close");

        String[] order = {
                "Host",
                "Accept",
                "Accept-Encoding",
                "User-Agent",
                "Authorization",
                "Content-Length",
                "Content-Type",
                "Connection"
        };


        try {
            String response = NetUtil.basicHttpsPost(
                    "/pregame_setup.php",
                    SECOND_AUTH_HOST,
                    NetUtil.JsonToHttpQueryStr(params, paramOrder),
                    headers, order);
            return (ObjectNode)(new ObjectMapper().readTree(response));
        } catch (Exception ex) {
            throw new AuthenticationFailed();
        }
    }

    // pass null for anonymous
    // returns server IP
    public AuthResults authenticate(Credentials credentials) throws AuthenticationFailed {
        var firstAuthData = credentials == null ? generateAnonAccount() : doFirstAuthentication(credentials);
        String apiToken = firstAuthData.get("access_token").asText().replaceAll("\\\\", "");
        String userGameId = firstAuthData.get("user_game_id").get(0).asText();
        String serverIp = doSecondAuthentication(apiToken).get("serverIp").asText();
        return new AuthResults(apiToken, userGameId, serverIp);
    }

}
