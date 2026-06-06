package com.glisterbyte.singingmonsters.sfsmodels.requests;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.data.LoginData;
import com.glisterbyte.singingmonsters.sfsmodels.SfsRequestModel;

@SfsCmd("USER_LOGIN")
public class LoginRequest extends SfsRequestModel {
    public LoginData data;
    public String password; // always empty?
    public String user;
    public String zone; // "MySingingMonsters"
}