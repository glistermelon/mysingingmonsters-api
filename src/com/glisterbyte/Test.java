package com.glisterbyte;

import com.glisterbyte.Network.Client;
import com.glisterbyte.Network.Credentials;
import com.glisterbyte.SingingMonsters.IslandType;
import com.glisterbyte.SingingMonsters.Player;
import com.glisterbyte.SingingMonsters.SfsModels.Client.BuyIslandFailed;
import com.glisterbyte.SingingMonsters.Structures.Mine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class Test {
    public static void main(String[] args) throws IOException {
        String credentialsString = Files.readString(Paths.get("res/credentials.txt"));
        String[] split = credentialsString.split("\\r\\n");
        Credentials credentials = new Credentials(split[0], split[1]);
        Client client = new Client();
        client.connect(credentials);
        Player player = client.fetchPlayer();
    }
}