package com.glisterbyte.Network;

import com.glisterbyte.Configuration.Settings;
import com.glisterbyte.Utility.Credentials;
import com.glisterbyte.Utility.NetUtil;

import java.lang.Math;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class Client {
    public final static Logger ClientErrorLogger = Logger.getLogger(Client.class.getName());
    public static void logError(Exception ex) {
        ClientErrorLogger.log(Level.SEVERE, "Connection.Client Error", ex);
    }
    protected static String GAME_ID = "com.bigbluebubble.singingmonsters.full";
    protected static String BASIC_AUTH_HOST = "auth.bbbgame.net";
    protected static String MSM_AUTH_HOST = "msm-" + BASIC_AUTH_HOST;
    protected static String URI_PREPEND = "";
    private ObjectNode authenticationParams;
    public GameServer gameServer;

    private static ObjectNode generateDevice() {
        ObjectNode device = new ObjectMapper().createObjectNode();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 16; i++)
            id.append(Settings.HEX_CHARS[(int) (16 * Math.random())]);
        device.put("device_id", id.toString());
        device.put("device_model", Settings.ANDROID_MODELS[(int)(Settings.ANDROID_MODELS.length * Math.random())]);
        device.put("device_vendor", Settings.ANDROID_VENDORS[(int)(Settings.ANDROID_VENDORS.length * Math.random())]);
        device.put("os_version", "12");
        device.put("lang", "en");

        return device;
    }
    private void initializeAuthParams() {

        ObjectNode authData = new ObjectMapper().createObjectNode();
        authData.put("g", 1);
        authData.put("advertiser_id", "");
        authData.put("auth_version", "2.0.0");
        authData.put("client_version", Settings.GAME_VERSION_STRING);
        authData.put("package", GAME_ID);
        authData.put("platform", "android");

        generateDevice().fields().forEachRemaining(
                entry -> authData.set(entry.getKey(), entry.getValue())
        );

        authenticationParams = authData;
    }

    private ObjectNode generateAnonAccount() {

        ObjectNode authData = authenticationParams.deepCopy();
        authData.put("update_device", 1);

        String[] authDataOrder = {
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
                "MSM/" + Settings.GAME_VERSION_STRING
                        + " (android; " + authenticationParams.get("os_version").asText() + ")"
        );
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        String[] order = {
                "Host",
                "Accept",
                "Accept-Encoding",
                "User-Agent",
                "Content-Length",
                "Content-Type"
        };

        try {
            String response = NetUtil.basicHttpsPost(
                    URI_PREPEND + "/auth/api/anon_account/",
                    BASIC_AUTH_HOST,
                    NetUtil.JsonToHttpQueryStr(authData, authDataOrder),
                    headers, order);
            return (ObjectNode)(new ObjectMapper().readTree(response));
        } catch (Exception ex) {
            logError(ex);
        }
        return null;
    }
    private ObjectNode doFirstAuthentication(Credentials credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        ObjectNode authData = authenticationParams.deepCopy();
        authData.put("u", username);
        authData.put("p", password);
        authData.put("t", credentials.getType());
        authData.put("fr", "1");

        String[] authDataOrder = {
                "g", "u", "p", "t", "fr",
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
        headers.put("User-Agent",
                "MSM/" + Settings.GAME_VERSION_STRING
                        + " (android; " + authenticationParams.get("os_version").asText() + ")"
        );
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Connection", "close");

        String[] order = {
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
                    URI_PREPEND + "/auth/api/token",
                    BASIC_AUTH_HOST,
                    NetUtil.JsonToHttpQueryStr(authData, authDataOrder),
                    headers, order);
            return (ObjectNode)(new ObjectMapper().readTree(response));
        } catch (Exception ex) {
            logError(ex);
        }
        return null;
    }
    private ObjectNode doSecondAuthentication(String apiToken) {

        ObjectNode params = authenticationParams.deepCopy();
        params.put("advertiser_id", "");
        params.put("access_key", Settings.ACCESS_KEY);
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
                "MSM/" + Settings.GAME_VERSION_STRING
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
                    URI_PREPEND + "/pregame_setup.php",
                    MSM_AUTH_HOST,
                    NetUtil.JsonToHttpQueryStr(params, paramOrder),
                    headers, order);
            return (ObjectNode)(new ObjectMapper().readTree(response));
        } catch (Exception ex) {
            logError(ex);
        }
        return null;
    }

    public boolean connect(boolean anonymous) {

        try {
            initializeAuthParams();

            ObjectNode firstAuthenticationData;
            String apiToken;

            Credentials credentials;
            try {
                String credentialsString = Files.readString(Paths.get("res/credentials.txt"));
                String[] split = credentialsString.split("\\r\\n");
                credentials = new Credentials(split[0], split[1]);
            }
            catch (java.io.IOException e) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter username (email address): ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                credentials = new Credentials(username, password);
            }

            firstAuthenticationData = anonymous ? generateAnonAccount() : doFirstAuthentication(credentials);
            if (firstAuthenticationData == null)
                throw new Exception("First authentication failed");

            apiToken = firstAuthenticationData.get("access_token").asText().replaceAll("\\\\", "");

            ObjectNode secondAuthenticationData = doSecondAuthentication(apiToken);
            if (secondAuthenticationData == null)
                throw new Exception("Second authentication failed");

            SFSObject loginInfo = new SFSObject();
            loginInfo.putUtfString("access_key", Settings.ACCESS_KEY);
            loginInfo.putBool("attempt_recovery", false);
            loginInfo.putUtfString("client_device", authenticationParams.get("device_model").asText());
            loginInfo.putUtfString("client_lang", "");
            loginInfo.putUtfString("client_os", authenticationParams.get("os_version").asText());
            loginInfo.putUtfString("client_platform", "android");
            String version = authenticationParams.get("client_version").asText();
            loginInfo.putUtfString("client_version", version);
            loginInfo.putUtfString("last_update_version", version);
            loginInfo.putLong("last_updated", 0L); // TODO actually put something here
            loginInfo.putUtfString("raw_device_id", authenticationParams.get("device_id").asText());
            loginInfo.putUtfString("token", apiToken);

            String userGameId = firstAuthenticationData.get("user_game_id").get(0).asText();
            loginInfo.putUtfString("user_game_id", userGameId);

            System.out.println(loginInfo);

            gameServer = new GameServer(secondAuthenticationData.get("serverIp").asText(), loginInfo);

            gameServer.waitForResponse("gs_initialized");

            return true;

        } catch (Exception ex) {
            logError(ex);
        }
        return false;
    }

    public void terminate() {
        gameServer.terminateConnection();
    }
}
