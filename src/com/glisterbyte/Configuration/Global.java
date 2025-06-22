package com.glisterbyte.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Global {

    // TODO: I hate this and I want it gone.
    public static String[] HEX_CHARS = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    // TODO: What are these? Also shouldn't these match vendors?
    public static String[] ANDROID_MODELS = {
            "SP1A.210812.016.C2", // Pixel 3 (XL)
            "SQ1D.220205.004", // Pixel 6 (Pro)
            "SP1A.211105.002.A1"
    };

    public static String[] ANDROID_VENDORS = {
            "Samsung", "Sony", "Lenovo",
            "Motorola", "Xiaomi", "Google"
    };

    public static String GAME_VERSION_STRING = "4.8.3";

    // The access key is hardcoded into each version of the game client.
    // It can be extracted from the APK itself
    // or by intercepting/sniffing HTTPS authentication traffic.
    public static String ACCESS_KEY = "70ba5d5d-d903-4587-93d6-655c4814844f";
}