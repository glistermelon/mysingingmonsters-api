package com.glisterbyte.SingingMonsters;

import com.glisterbyte.Localization.Language;
import com.glisterbyte.Localization.LocalizedResources;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsMonsterInfo;
import com.glisterbyte.SingingMonsters.SfsModels.Server.SfsMonsterLike;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class IslandSpecificMonsterSpecies {

    private final int speciesId;
    private final int entityId;

    private final MultiCurrencyValue cost;
    private final Size size;
    private final Duration hatchDuration;
    private final String nameCode;
    private final String descCode;
    private final List<Integer> likedEntityIds;

    private final List<Element> elements;
    private final int hatchXpReward;
    private final int bedsRequired;
    private final List<String> defaultNames;

    public IslandSpecificMonsterSpecies(SfsMonsterInfo sfsInfo) {

        speciesId = sfsInfo.monsterId;
        entityId = sfsInfo.entityId;

        cost = new MultiCurrencyValue(
                sfsInfo.costCoins,
                sfsInfo.costKeys,
                sfsInfo.costRelics,
                sfsInfo.costDiamonds,
                sfsInfo.costStarpower,
                sfsInfo.costMedals,
                sfsInfo.costEthCurrency
        );

        size = new Size(sfsInfo.sizeX, sfsInfo.sizeY);
        hatchDuration = Duration.ofSeconds(sfsInfo.buildTime);
        nameCode = sfsInfo.name;
        descCode = sfsInfo.description;

        likedEntityIds = new ArrayList<>();
        for (SfsMonsterLike like : sfsInfo.happiness) {
            likedEntityIds.add(like.entity);
        }

        elements = new ArrayList<>();
        for (int i = 0; i < sfsInfo.genes.length(); i++) {
            char letter = sfsInfo.genes.charAt(i);
            Element element = Cache.getElementByLetter(letter);
            if (element == null) {
                throw new ElementNotFoundException(String.valueOf(letter));
            }
            elements.add(element);
        }

        hatchXpReward = sfsInfo.xp;
        bedsRequired = sfsInfo.beds;

        defaultNames = new ArrayList<>();
        defaultNames.addAll(sfsInfo.names);

    }

    public static IslandSpecificMonsterSpecies fromId(int typeId) {
        return Cache.getSpecificMonsterSpeciesBySpeciesId(typeId);
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public int getEntityId() {
        return entityId;
    }

    public MultiCurrencyValue getCost() {
        return cost;
    }

    public Size getSize() {
        return size;
    }

    public Duration getHatchDuration() {
        return hatchDuration;
    }

    public String getName(Language language) {
        return LocalizedResources.getText(nameCode, language);
    }

    public String getName() {
        return LocalizedResources.getText(nameCode);
    }

    public String getDescription(Language language) {
        return LocalizedResources.getText(descCode, language);
    }

    public String getDescription() {
        return LocalizedResources.getText(descCode);
    }

    public List<Object> getLikes() {
        List<Object> likedEntities = new ArrayList<>();
        for (int entityId : likedEntityIds) {
            likedEntities.add(Cache.getEntityByEntityId(entityId));
        }
        return likedEntities;
    }

    public List<StructureType> getLikedStructures() {
        List<StructureType> likedStructures = new ArrayList<>();
        for (Object obj : getLikes()) {
            if (obj instanceof StructureType) {
                likedStructures.add((StructureType)obj);
            }
        }
        return likedStructures;
    }

    public List<IslandSpecificMonsterSpecies> getLikedMonsters() {
        List<IslandSpecificMonsterSpecies> likedMonsters = new ArrayList<>();
        for (Object obj : getLikes()) {
            if (obj instanceof IslandSpecificMonsterSpecies) {
                likedMonsters.add((IslandSpecificMonsterSpecies)obj);
            }
        }
        return likedMonsters;
    }

    public List<Element> getElements() {
        return elements;
    }

    public int getHatchXpReward() {
        return hatchXpReward;
    }

    public int getBedsRequired() {
        return bedsRequired;
    }

    public List<String> getDefaultNames() {
        return defaultNames;
    }

    public MonsterSpecies getGenericSpecies() {
        return Cache.getGenericMonsterSpeciesBySpeciesId(speciesId);
    }

}