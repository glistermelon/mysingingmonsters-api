package com.glisterbyte.Network;

import com.glisterbyte.Configuration.Global;
import com.glisterbyte.Network.ClientException.FetchFailed;
import com.glisterbyte.Network.ClientException.InitializationFailed;
import com.glisterbyte.SfsMapping.SfsMapper;
import com.glisterbyte.SingingMonsters.Player;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsPlayer;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.concurrent.ExecutionException;

public class Client {

    private SfsClient sfsClient = null;

    public void connect(Credentials credentials) {

        var authClient = new AuthClient();
        var authParams = authClient.getAuthParams();
        var authResults = authClient.authenticate(credentials);

        String clientVersion = authParams.get("client_version").asText();

        SFSObject loginParams = new SFSObject();
        loginParams.putUtfString("access_key", Global.ACCESS_KEY);
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

        sfsClient = new SfsClient(authResults.serverIp(), authResults.userGameId(), loginParams);
        var future = sfsClient.waitForEvent(event -> event.getCmd().equals("gs_initialized"));
        sfsClient.connect();
        try {
            future.get();
        }
        catch (ExecutionException | InterruptedException ex) {
            throw new InitializationFailed(ex);
        }

    }

    public Player fetchPlayer() {

        var params = new SFSObject();
        params.putUtfString("last_updated", " 0");
        ISFSObject data;
        try {
            data = sfsClient.requestResponse(
                    "gs_player",
                    params,
                    event -> event.getCmd().equals("gs_player")
            ).get().getSFSObject("player_object");
        }
        catch (ExecutionException | InterruptedException ex) {
            throw new FetchFailed(ex);
        }
        return Player.buildPlayer(sfsClient, SfsMapper.mapSFSObjectToClass(SfsPlayer.class, data));

    }

}
