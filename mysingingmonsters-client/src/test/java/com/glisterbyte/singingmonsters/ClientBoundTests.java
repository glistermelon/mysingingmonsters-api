package com.glisterbyte.singingmonsters;

import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.client.Client;
import com.glisterbyte.singingmonsters.main.common.Position;
import com.glisterbyte.singingmonsters.main.common.Size;
import com.glisterbyte.singingmonsters.main.island.Island;
import io.github.cdimascio.dotenv.Dotenv;

import javax.annotation.Nullable;

public abstract class ClientBoundTests {

    private static final Dotenv env = Dotenv.load();

    public static final String email = env.get("MSM_EMAIL");
    public static final String password = env.get("MSM_PASSWORD");

    protected static Client client;

    public static void initClient() throws ClientException, InterruptedException {
        client = new Client();
        client.connectWithEmail(email, password);
    }

    protected static Position getNewPosition(Island island, Size size, @Nullable Position excludePosition) {
        for (int sum = 1; sum < 200; sum++) {
            for (int x = 1; x <= sum; x++) {
                int y = sum - x;
                if (y == 0) continue;
                Position position = new Position(x, y);
                if (island.areTilesOccupied(position, size)) continue;
                if (position.equals(excludePosition)) continue;
                return position;
            }
        }
        throw new RuntimeException("Couldn't find an unoccupied position");
    }

}