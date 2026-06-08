package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.LoginData;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

/**
 * The first request to be sent by the client to the server.
 * Authenticates the user.
 */
@SfsCmd("USER_LOGIN")
public class LoginRequest extends SfsRequestModel {

    public LoginData data;

    /**
     * Always an empty string
     */
    public String password;

    /**
     * A username provided by the login API
     */
    public String user;

    /**
     * Always "MySingingMonsters"
     */
    public String zone;

}