package com.glisterbyte.singingmonsters.sfsmodels.events;

import com.glisterbyte.singingmonsters.sfsmapping.SfsCmd;
import com.glisterbyte.singingmonsters.sfsmodels.SfsResultResponse;

@SfsCmd("USER_LOGIN")
public class LoginResponse extends SfsResultResponse {

    // skipped keys: data (seems always empty), user (repeats the provided user id)

}