package com.glisterbyte.singingmonsters.main.catalog;

import com.glisterbyte.singingmonsters.main.common.HasSfsData;
import com.glisterbyte.singingmonsters.localization.Language;
import com.glisterbyte.singingmonsters.localization.LocalizedTextManager;
import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsBakeryRecipe;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.time.Duration;

public class BakeryRecipe implements HasSfsData {

    private final Catalog catalog;

    private final SFSObject sfsData;

    private final int id;
    private final String nameCode;
    private final int amount;
    private final int costCoins;
    private final int xpReward;
    private final Duration bakeDuration;
    private final boolean seasonal;

    public BakeryRecipe(Catalog catalog, SfsBakeryRecipe sfsRecipe) {
        this.catalog = catalog;
        sfsData = sfsRecipe.sfsObject;
        id = sfsRecipe.id;
        nameCode = sfsRecipe.label;
        amount = sfsRecipe.food;
        costCoins = sfsRecipe.cost;
        xpReward = sfsRecipe.xp;
        bakeDuration = Duration.ofSeconds(sfsRecipe.time);
        seasonal = sfsRecipe.always_avail == 0;
    }

    public SFSObject getSfsData() {
        return sfsData;
    }

    public int getId() {
        return id;
    }

    public String getNameCode() {
        return nameCode;
    }

    public int getAmount() {
        return amount;
    }

    public int getCostCoins() {
        return costCoins;
    }

    public int getXpReward() {
        return xpReward;
    }

    public Duration getBakeDuration() {
        return bakeDuration;
    }

    public boolean isSeasonal() {
        return seasonal;
    }

    public String getName() {
        return catalog.getLocalizedTextManager().getText(nameCode);
    }

    public String getName(Language language) {
        return catalog.getLocalizedTextManager().getText(nameCode, language);
    }

    @Override
    public String toString() {
        return StringUtil.format("BakeryRecipe(name='{}', amount={}, cost={})", getName(), amount, costCoins);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BakeryRecipe other)) return false;
        return other.getId() == id;
    }



}
