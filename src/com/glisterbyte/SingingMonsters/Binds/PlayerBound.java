package com.glisterbyte.SingingMonsters.Binds;

import com.glisterbyte.SingingMonsters.Player;

public class PlayerBound extends ClientBound {

    protected Player player;

    public PlayerBound(Player player) {
        super(player.sfsClient);
        this.player = player;
    }

    public final Player getPlayer() {
        return player;
    }

}