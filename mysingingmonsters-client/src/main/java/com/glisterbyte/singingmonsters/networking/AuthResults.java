package com.glisterbyte.singingmonsters.networking;

public record AuthResults(
        String apiToken,
        String userGameId,
        String serverIp
) { }