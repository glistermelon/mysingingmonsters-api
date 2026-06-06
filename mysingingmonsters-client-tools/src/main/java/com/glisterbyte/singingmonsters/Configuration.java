package com.glisterbyte.singingmonsters;

import io.github.cdimascio.dotenv.Dotenv;

public class Configuration {

    private Configuration() { }

    private static final Dotenv env = Dotenv.load();

    public static final String email = env.get("MSM_EMAIL");
    public static final String password = env.get("MSM_PASSWORD");

}