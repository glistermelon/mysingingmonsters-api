package com.glisterbyte.Configuration;

import com.glisterbyte.Localization.LocalizedResources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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

    public static String getResourceAsString(String resource) {

        InputStream input = LocalizedResources.class.getClassLoader().getResourceAsStream(resource);
        assert input != null;

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        try {
            for (int length; (length = input.read(buffer)) != -1; ) {
                result.write(buffer, 0, length);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return result.toString(StandardCharsets.UTF_8);

    }

}