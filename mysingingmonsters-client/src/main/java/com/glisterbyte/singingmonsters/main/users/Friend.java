package com.glisterbyte.singingmonsters.main.users;

import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.catalog.Catalog;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsFriend;

import java.time.Instant;

public class Friend extends User {

    private final Instant joinTime;
    private final Instant lastLoginTime;

    private final String friendCode;

    private final boolean isFavorite;
    private final boolean hasUnlitTorches;
    private final boolean hasUnlitHighlightedTorches;
    private final boolean discoverable;

    private final int torchesLitBySelf;
    private final int torchesLitByFriend;

    private Friend(Catalog catalog, SfsFriend sfsFriend) {

        super(catalog, sfsFriend);

        joinTime = Instant.ofEpochMilli(sfsFriend.date_created);
        lastLoginTime = Instant.ofEpochMilli(sfsFriend.last_login);

        friendCode = StringUtil.formatFriendCode(sfsFriend.friend_code);

        isFavorite = sfsFriend.is_favorite;
        hasUnlitTorches = sfsFriend.has_unlit_torches;
        hasUnlitHighlightedTorches = sfsFriend.has_unlit_highlighted_torches;
        discoverable = sfsFriend.discoverable != 0;

        torchesLitBySelf = sfsFriend.litByMe;
        torchesLitByFriend = sfsFriend.litByFriend;

    }

    public static Friend buildFriend(Catalog catalog, SfsFriend sfsFriend) {
        return new Friend(catalog, sfsFriend);
    }

    public Instant getJoinTime() {
        return joinTime;
    }

    public Instant getLastLoginTime() {
        return lastLoginTime;
    }

    public String getFriendCode() {
        return friendCode;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public boolean isHasUnlitTorches() {
        return hasUnlitTorches;
    }

    public boolean isHasUnlitHighlightedTorches() {
        return hasUnlitHighlightedTorches;
    }

    public boolean isDiscoverable() {
        return discoverable;
    }

    public int getTorchesLitBySelf() {
        return torchesLitBySelf;
    }

    public int getTorchesLitByFriend() {
        return torchesLitByFriend;
    }

    @Override
    public String toString() {
        return StringUtil.format(
                "Friend(name={}, code={})",
                getName(), friendCode
        );
    }

}