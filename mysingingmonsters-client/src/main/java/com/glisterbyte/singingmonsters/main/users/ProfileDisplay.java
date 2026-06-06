package com.glisterbyte.singingmonsters.main.users;

import com.glisterbyte.singingmonsters.main.catalog.Catalog;
import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.main.island.IslandType;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsUserDisplayData;

public class ProfileDisplay {

    private final int backgroundId;
    private final int cardId;
    private final int frameId;
    private final int avatarId;
    private final int phraseId;
    private final int monikerId;

    private final MonsterSpecies favoriteMonster1;
    private final MonsterSpecies favoriteMonster2;
    private final MonsterSpecies favoriteMonster3;
    private final IslandType favoriteIsland;

    private ProfileDisplay(Catalog catalog, SfsUserDisplayData data) {

        backgroundId = data.bg_id;
        cardId = data.card_id;
        frameId = data.frame_id;
        avatarId = data.avatar_id;
        phraseId = data.phrase_id;
        monikerId = data.moniker_id;

        favoriteMonster1 = catalog.getMonsterSpecies(data.fav_mon_1_id);
        favoriteMonster2 = catalog.getMonsterSpecies(data.fav_mon_2_id);
        favoriteMonster3 = catalog.getMonsterSpecies(data.fav_mon_3_id);
        favoriteIsland = IslandType.fromId(data.fav_island_id);

    }

    public static ProfileDisplay buildProfileDisplay(Catalog catalog, SfsUserDisplayData sfsUserDisplayData) {
        return new ProfileDisplay(catalog, sfsUserDisplayData);
    }

    public int getBackgroundId() {
        return backgroundId;
    }

    public int getCardId() {
        return cardId;
    }

    public int getFrameId() {
        return frameId;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public int getPhraseId() {
        return phraseId;
    }

    public int getMonikerId() {
        return monikerId;
    }

    public MonsterSpecies getFavoriteMonster1() {
        return favoriteMonster1;
    }

    public MonsterSpecies getFavoriteMonster2() {
        return favoriteMonster2;
    }

    public MonsterSpecies getFavoriteMonster3() {
        return favoriteMonster3;
    }

    public IslandType getFavoriteIsland() {
        return favoriteIsland;
    }

}