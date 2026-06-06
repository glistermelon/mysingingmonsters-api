package com.glisterbyte.singingmonsters.main.catalog;

import com.glisterbyte.singingmonsters.localization.Language;
import com.glisterbyte.singingmonsters.localization.LocalizedTextManager;
import com.glisterbyte.singingmonsters.common.StringUtil;
import com.glisterbyte.singingmonsters.main.common.HasSfsData;
import com.glisterbyte.singingmonsters.main.common.MultiCurrencyValue;
import com.glisterbyte.singingmonsters.main.common.Size;
import com.glisterbyte.singingmonsters.main.monster.MonsterClass;
import com.glisterbyte.singingmonsters.main.monster.MonsterFamily;
import com.glisterbyte.singingmonsters.main.monster.MonsterLevelData;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonsterLevel;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonsterSpecies;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.time.Duration;
import java.util.*;

public class MonsterSpecies extends Entity implements HasSfsData {

    private final Catalog catalog;

    private final SfsMonsterSpecies sfsSourceData;

    private final int speciesId;

    private final MultiCurrencyValue cost;
    private final Size size;
    private final Duration incubationTime;

    private final String nameCode;
    private final String descCode;
    private final String commonName;
    private final MonsterFamily family;
    private final MonsterClass monsterClass;

    private final List<Element> elements;

    private final boolean visibleInMarket;
    private final boolean visibleInStarshop;

    // Maps entity ID to affect on happiness, where happiness is between -4 (-100%) and 4 (100%)
    private final Map<Integer, Integer> happinessEffects;

    private final int hatchXpReward;
    private final int bedsRequired;

    private final String discoverer;

    private final List<String> defaultNames;

    private final Duration fillTimeLimit;

    private final List<MonsterLevelData> monsterLevelData = new ArrayList<>();

    public MonsterSpecies(Catalog catalog, SfsMonsterSpecies sfsSpecies) {

        super(sfsSpecies);

        this.catalog = catalog;

        sfsSourceData = sfsSpecies;

        speciesId = sfsSpecies.monster_id;

        cost = new MultiCurrencyValue(
                sfsSpecies.cost_coins,
                0,
                sfsSpecies.cost_keys,
                sfsSpecies.cost_relics,
                sfsSpecies.cost_diamonds,
                sfsSpecies.cost_starpower,
                sfsSpecies.cost_medals,
                sfsSpecies.cost_eth_currency,
                0
        );

        size = new Size(sfsSpecies.size_x, sfsSpecies.size_y);
        incubationTime = Duration.ofSeconds(sfsSpecies.build_time);
        nameCode = sfsSpecies.name;
        descCode = sfsSpecies.description;
        commonName = sfsSpecies.common_name;
        family = MonsterFamily.fromString(sfsSpecies.fam);
        monsterClass = MonsterClass.fromString(sfsSpecies.monsterClass);

        elements = new ArrayList<>();
        for (char letter : sfsSpecies.genes.toCharArray()) {
            elements.add(catalog.getElement(letter));
        }

        visibleInMarket = sfsSpecies.view_in_market != 0;
        visibleInStarshop = sfsSpecies.view_in_starmarket != 0;

        happinessEffects = new HashMap<>();
        for (var effect : sfsSpecies.happiness) {
            happinessEffects.put(effect.entity, effect.value);
        }

        hatchXpReward = sfsSpecies.xp;
        bedsRequired = sfsSpecies.beds;

        if (sfsSpecies.discovered_by == null) discoverer = null;
        else discoverer = sfsSpecies.discovered_by.equals("DISCOVERED_BY_COMMUNITY") ? null : sfsSpecies.discovered_by;

        defaultNames = new ArrayList<>(sfsSpecies.names);

        fillTimeLimit = sfsSpecies.time_to_fill_sec != -1 ? Duration.ofSeconds(sfsSpecies.time_to_fill_sec) : null;

        if (sfsSpecies.levels != null) {
            for (SfsMonsterLevel sfsMonsterLevel : sfsSpecies.levels) {
                monsterLevelData.add(MonsterLevelData.fromSfsMonsterLevel(sfsMonsterLevel));
            }
        }

    }

    public Catalog getCatalog() {
        return catalog;
    }

    public SFSObject getSfsData() {
        return sfsSourceData.sfsObject;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public MultiCurrencyValue getCost() {
        return cost;
    }

    public Size getSize() {
        return size;
    }

    public Duration getIncubationTime() {
        return incubationTime;
    }

    public String getNameCode() {
        return nameCode;
    }

    public String getDescCode() {
        return descCode;
    }

    public String getCommonName() {
        return commonName;
    }

    public MonsterFamily getFamily() {
        return family;
    }

    public MonsterClass getMonsterClass() {
        return monsterClass;
    }

    public List<Element> getElements() {
        return elements;
    }

    public boolean isVisibleInMarket() {
        return visibleInMarket;
    }

    public boolean isVisibleInStarshop() {
        return visibleInStarshop;
    }

    public List<Entity> getLikes() {
        List<Entity> likes = new ArrayList<>();
        for (var entry : happinessEffects.entrySet()) {
            int entityId = entry.getKey();
            int effect = entry.getValue();
            if (effect > 0) {
                likes.add(catalog.getEntity(entityId));
            }
        }
        return likes;
    }

    public int getHatchXpReward() {
        return hatchXpReward;
    }

    public int getBedsRequired() {
        return bedsRequired;
    }

    public Optional<String> getDiscoverer() {
        return Optional.ofNullable(discoverer);
    }

    public List<String> getDefaultNames() {
        return defaultNames;
    }

    public String getName() {
        return Optional.ofNullable(getCatalog().getLocalizedTextManager().getText(nameCode)).orElse(getCommonName());
    }

    public String getName(Language language) {
        return Optional.ofNullable(getCatalog().getLocalizedTextManager()
                .getText(nameCode, language)).orElse(getCommonName());
    }

    public String getDescription() {
        return getCatalog().getLocalizedTextManager().getText(descCode);
    }

    public String getDescription(Language language) {
        return getCatalog().getLocalizedTextManager().getText(descCode, language);
    }

    public Duration getFillTimeLimit() {
        return fillTimeLimit;
    }

    public MonsterLevelData getMonsterLevelData(int level) {
        return monsterLevelData.stream().filter(d -> d.level() == level)
                .findFirst().orElse(null);
    }

    public List<MonsterLevelData> getMonsterLevelDataList() {
        return Collections.unmodifiableList(monsterLevelData);
    }

    @Override
    public String toString() {
        return StringUtil.format("MonsterSpecies(name='{}', id={})", getName(), speciesId);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MonsterSpecies other)) return false;
        return other.getSpeciesId() == speciesId;
    }

    public boolean matches(MultiMonsterSpecies multiSpecies) {
        return multiSpecies.matches(this);
    }

    public boolean isWubbox() {
        MonsterFamily family = getFamily();
        return family == MonsterFamily.SUPERNATURAL || family == MonsterFamily.SUPERNATURAL_ETHEREAL;
    }

}