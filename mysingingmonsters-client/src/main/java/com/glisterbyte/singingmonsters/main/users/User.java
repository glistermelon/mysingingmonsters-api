package com.glisterbyte.singingmonsters.main.users;

import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.catalog.Catalog;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsFriend;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsFriendRequest;

import java.util.Optional;

public class User {

    private final String name;
    private final int level;
    private final int xp;
    private final Long starpower;

    private final ProfileDisplay profileDisplay;

    private final int userId;
    private final long bbbId;

    private final int totalTorchesLit;

    private User(Catalog catalog, SfsFriendRequest req) {

        name = req.display_name;
        level = req.level;
        xp = req.xp;
        starpower = req.total_starpower_collected;

        profileDisplay = ProfileDisplay.buildProfileDisplay(catalog, req.data);

        userId = req.user_id;
        bbbId = req.bbb_id;

        totalTorchesLit = req.data.total_torches_lit;

    }

    protected User(Catalog catalog, SfsFriend friend) {

        name = friend.display_name;
        level = friend.level;
        xp = friend.xp;
        starpower = friend.total_starpower_collected;

        profileDisplay = ProfileDisplay.buildProfileDisplay(catalog, friend.data);

        userId = friend.user_id;
        bbbId = friend.bbb_id;

        totalTorchesLit = friend.data.total_torches_lit;

    }

    public static User buildUser(Catalog catalog, SfsFriendRequest sfsFriendRequest) {
        return new User(catalog, sfsFriendRequest);
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public Optional<Long> getStarpower() {
        return Optional.ofNullable(starpower);
    }

    public ProfileDisplay getProfileDisplay() {
        return profileDisplay;
    }

    public int getUserId() {
        return userId;
    }

    public long getBbbId() {
        return bbbId;
    }

    public int getTotalTorchesLit() {
        return totalTorchesLit;
    }

    @Override
    public String toString() {
        return StringUtil.format(
                "User(name={}, id={})",
                name, userId
        );
    }

}