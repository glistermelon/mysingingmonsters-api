package com.glisterbyte.SingingMonsters.SfsModels.Server;

import com.glisterbyte.SfsMapping.SfsPropertyArray;
import com.glisterbyte.SingingMonsters.SfsModels.SfsCmd;

@SfsCmd("gs_update_structure")
public class UpdateStructure extends SfsResponseModel {

    @SfsPropertyArray
    public static class UpdateStructureProperties {

        public long lastCollection;
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

    public UpdateStructureProperties properties;
    public long userStructureId;

}