package com.glisterbyte.singingmonsters.sfsmodels.data;

import com.glisterbyte.singingmonsters.sfsmodels.SfsModel;

public class LoginData extends SfsModel {

    /**
     * A static access key
     */
    public String access_key;

    public boolean attempt_recovery;

    /**
     * What device the user is on (for instance, a specific model of iPhone)
     */
    public String client_device;

    /**
     * Two-letter code for the user's language
     */
    public String client_lang;

    /**
     * The version of the user's operating system (Android, iOS, etc.)
     */
    public String client_os;

    /**
     * Basically what platform the user is on (e.g., "android")
     */
    public String client_platform;

    /**
     * What version of the game the user has
     */
    public String client_version;

    public String last_update_version;

    /**
     * Likely the timestamp at which the user last updated the game
     */
    public long last_updated;

    /**
     * An ID of the user's device
     */
    public String raw_device_id;

    /**
     * A login token granted by the login API.
     */
    public String token;

}