package com.glisterbyte.singingmonsters.main.users;

import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.client.Client;
import com.glisterbyte.singingmonsters.main.client.HasClient;
import com.glisterbyte.singingmonsters.main.common.HasSfsData;
import com.glisterbyte.singingmonsters.main.island.IslandType;
import com.glisterbyte.singingmonsters.main.island.UnownedIsland;
import com.glisterbyte.singingmonsters.main.monster.UnownedMonster;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsVisitData;
import com.smartfoxserver.v2.entities.data.SFSObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VisitData implements HasClient, HasSfsData {

    private final Client client;

    private final SFSObject sfsData;

    private final String userName;
    private final int userLevel;
    private final String userCountryCode;

    private final long userStarpower;
    private final long userTotalStarpowerAllTime;
    private final long userKeys;

    private final int userId;
    private final long userBbbId;

    private final List<UnownedIsland> islands = new ArrayList<>();

    private final UnownedIsland activeIsland;

    private VisitData(Client client, SfsVisitData data) {

        this.sfsData = data.sfsObject;

        this.client = client;

        userName = data.display_name;
        userLevel = data.level;
        userCountryCode = data.country;

        userStarpower = data.starpower;
        userTotalStarpowerAllTime = data.total_starpower_collected;
        userKeys = data.keys;

        userId = data.user_id;
        userBbbId = data.bbb_id;

        for (SfsIsland sfsIsland : data.islands) {
            islands.add(new UnownedIsland(client, sfsIsland));
        }

        activeIsland = getIsland(data.active_island);

    }

    public static VisitData buildVisitData(Client client, SfsVisitData sfsVisitData) {
        return new VisitData(client, sfsVisitData);
    }

    public Client getClient() {
        return client;
    }

    public SFSObject getSfsData() {
        return sfsData;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public @Nullable String getUserCountryCode() {
        return userCountryCode;
    }

    public long getUserStarpower() {
        return userStarpower;
    }

    public long getUserTotalStarpowerAllTime() {
        return userTotalStarpowerAllTime;
    }

    public long getUserKeys() {
        return userKeys;
    }

    public int getUserId() {
        return userId;
    }

    public long getUserBbbId() {
        return userBbbId;
    }

    public List<UnownedIsland> getIslands() {
        return Collections.unmodifiableList(islands);
    }

    public UnownedIsland getActiveIsland() {
        return activeIsland;
    }

    public UnownedIsland getIsland(long userIslandId) {
        return getIslands().stream()
                .filter(i -> i.getUserIslandId() == userIslandId)
                .findFirst().orElse(null);
    }

    public synchronized UnownedIsland getIsland(IslandType type) {
        return getIslands().stream()
                .filter(i -> i.getIslandType() == type)
                .findFirst().orElse(null);
    }

    public synchronized UnownedMonster getMonster(long userMonsterId) {
        for (UnownedIsland island : islands) {
            UnownedMonster monster = island.getMonster(userMonsterId);
            if (monster != null) return monster;
        }
        return null;
    }

    @Override
    public String toString() {
        return StringUtil.format(
                "VisitData(userName={}, userId={}, islandCount={})",
                userName, userId, islands.size()
        );
    }

}