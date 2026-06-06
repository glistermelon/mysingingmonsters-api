package com.glisterbyte.singingmonsters.main.catalog;

import com.glisterbyte.singingmonsters.main.common.MultiCurrencyValue;
import com.glisterbyte.singingmonsters.main.common.Size;
import com.glisterbyte.singingmonsters.main.structure.StructureCategory;
import com.glisterbyte.singingmonsters.localization.Language;
import com.glisterbyte.singingmonsters.localization.LocalizedTextManager;
import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.common.HasSfsData;
import com.glisterbyte.singingmonsters.main.island.IslandType;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructureInfo;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StructureType extends Entity implements HasSfsData {

    private final Catalog catalog;

    private final SfsStructureInfo sfsSourceData;

    private final int structureId;

    private final StructureCategory category;

    private final MultiCurrencyValue cost;
    private final Size size;
    private final Duration removalDuration;
    private final String nameCode;
    private final String descCode;

    private final int actionXpReward;

    private final boolean movable;
    private final boolean sellable;

    private final int upgradeStructureId;
    private final List<IslandType> allowedIslandTypes;

    // for a castle type
    public int beds;

    public StructureType(Catalog catalog, SfsStructureInfo sfsInfo) {

        super(sfsInfo);

        this.catalog = catalog;

        sfsSourceData = sfsInfo;

        structureId = sfsInfo.structure_id;

        category = StructureCategory.fromString(sfsInfo.structure_type);

        cost = new MultiCurrencyValue(
                sfsInfo.cost_coins,
                0,
                sfsInfo.cost_keys,
                sfsInfo.cost_relics,
                sfsInfo.cost_diamonds,
                sfsInfo.cost_starpower,
                sfsInfo.cost_medals,
                sfsInfo.cost_eth_currency,
                0
        );

        size = new Size(sfsInfo.size_x, sfsInfo.size_y);
        removalDuration = Duration.ofSeconds(sfsInfo.build_time);
        nameCode = sfsInfo.name;
        descCode = sfsInfo.description;

        actionXpReward = sfsInfo.xp;

        movable = sfsInfo.movable == 1;
        sellable = sfsInfo.sellable == 1;

        upgradeStructureId = sfsInfo.upgrades_to;

        if (sfsInfo.allowed_on_island == null) allowedIslandTypes = null;
        else {
            allowedIslandTypes = new ArrayList<>();
            for (int typeId : sfsInfo.allowed_on_island) {
                allowedIslandTypes.add(IslandType.fromId(typeId));
            }
        }

        if (sfsInfo.extra != null) {
            if (sfsInfo.extra.containsKey("beds")) {
                beds = sfsInfo.extra.getInt("beds");
            }
        }

    }

    public Catalog getCatalog() {
        return catalog;
    }

    public SFSObject getSfsData() {
        return sfsSourceData.sfsObject;
    }

    public int getTypeId() {
        return structureId;
    }

    public StructureCategory getStructureCategory() {
        return category;
    }

    public MultiCurrencyValue getCost() {
        return cost;
    }

    public Size getSize() {
        return size;
    }

    public Duration getRemovalDuration() {
        return removalDuration;
    }

    public String getName(Language language) {
        return getCatalog().getLocalizedTextManager().getText(nameCode, language);
    }

    public String getName() {
        return getCatalog().getLocalizedTextManager().getText(nameCode);
    }

    public String getDescription(Language language) {
        return getCatalog().getLocalizedTextManager().getText(descCode, language);
    }

    public String getDescription() {
        return getCatalog().getLocalizedTextManager().getText(descCode);
    }

    public int getActionXpReward() {
        return actionXpReward;
    }

    public boolean isMovable() {
        return movable;
    }

    public boolean isSellable() {
        return sellable;
    }

    public StructureType getUpgradeStructureType() {
        if (upgradeStructureId == 0) return null;
        else return catalog.getStructureType(upgradeStructureId);
    }

    public List<IslandType> getAllowedIslandTypes() {
        return allowedIslandTypes == null ? IslandType.allIslandTypes() : allowedIslandTypes;
    }

    public int getBedCount() {
        return beds;
    }

    @Override
    public String toString() {
        return StringUtil.format("StructureType(name='{}')", getName());
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof StructureType other)) return false;
        return other.getTypeId() == structureId;
    }

}