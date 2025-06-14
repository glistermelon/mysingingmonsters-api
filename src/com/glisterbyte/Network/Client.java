package com.glisterbyte.Network;

import com.glisterbyte.Configuration.Settings;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class Client {

    SmartFoxClient sfsClient = null;

    public void connect(Credentials credentials) {

        var authClient = new AuthClient();
        var authParams = authClient.getAuthParams();
        var authResults = authClient.authenticate(credentials);

        String clientVersion = authParams.get("client_version").asText();

        SFSObject loginParams = new SFSObject();
        loginParams.putUtfString("access_key", Settings.ACCESS_KEY);
        loginParams.putBool("attempt_recovery", false);
        loginParams.putUtfString("client_device", authParams.get("device_model").asText());
        loginParams.putUtfString("client_lang", "");
        loginParams.putUtfString("client_os", authParams.get("os_version").asText());
        loginParams.putUtfString("client_platform", "android");
        loginParams.putUtfString("client_version", clientVersion);
        loginParams.putUtfString("last_update_version", clientVersion);
        loginParams.putLong("last_updated", 0L); // TODO actually put something here
        loginParams.putUtfString("raw_device_id", authParams.get("device_id").asText());
        loginParams.putUtfString("token", authResults.apiToken());

        sfsClient = new SmartFoxClient(authResults.serverIp(), authResults.userGameId(), loginParams);

    }

}
