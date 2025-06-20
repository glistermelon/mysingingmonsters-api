package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SfsMapping.SfsOptional;
import com.glisterbyte.SfsMapping.SfsPropertyArray;

@SfsPropertyArray
public class Update {

    @SfsOptional
    public Long lastCollection;

    public long coinsActual;
    public long diamondsActual;
    public long foodActual;
    public long etherealCurrencyActual;
    public long keysActual;
    public long relicsActual;
    public long eggWildcardsActual;
    public long starpowerActual;
    public int xp;
    public int level;
    public String dailyBonusType;
    public int dailyBonusAmount;
    public boolean hasFreeAdScratch;
    public long dailyRelicPurchaseCount;
    public int relicDiamondCost;
    public long nextRelicReset;
    public int premium;
    public int earnedStarpower;
    public long speedUpCredit;
    public int battleXp;
    public int battleLevel;
    public int medals;

}