package com.glisterbyte.singingmonsters.main.island;

import com.glisterbyte.singingmonsters.main.catalog.MonsterSpecies;
import com.glisterbyte.singingmonsters.main.catalog.MultiMonsterSpecies;
import com.glisterbyte.singingmonsters.main.catalog.StructureType;
import com.glisterbyte.singingmonsters.exceptions.ActionFailedException;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.exceptions.ClientIllegalArgumentException;
import com.glisterbyte.singingmonsters.main.client.Client;
import com.glisterbyte.singingmonsters.main.client.HasClient;
import com.glisterbyte.singingmonsters.main.common.Position;
import com.glisterbyte.singingmonsters.main.monster.MonsterPlacement;
import com.glisterbyte.singingmonsters.main.monster.Monster;
import com.glisterbyte.singingmonsters.main.monster.ReadableMonster;
import com.glisterbyte.singingmonsters.main.monster.eggbox.EggBoxMonster;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.main.structure.StructureCategory;
import com.glisterbyte.singingmonsters.main.structure.bakery.Bakery;
import com.glisterbyte.singingmonsters.main.structure.breeding.BreedingStructure;
import com.glisterbyte.singingmonsters.main.structure.castle.Castle;
import com.glisterbyte.singingmonsters.main.structure.mine.Mine;
import com.glisterbyte.singingmonsters.main.structure.nursery.Nursery;
import com.glisterbyte.singingmonsters.main.structure.obstacle.Obstacle;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsIsland;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonster;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;
import com.glisterbyte.singingmonsters.sfsmodels.events.*;
import com.glisterbyte.singingmonsters.sfsmodels.requests.BuyStructureRequest;

import java.util.List;

public class Island extends AbstractIsland implements ReadableIsland, ControllableIsland, HasClient {

    public Island(Client client, SfsIsland sfsIsland) {
        super(client, sfsIsland);
    }

    protected Monster buildMonster(SfsMonster sfsMonster) {
        if (sfsMonster.isEggBoxMonster(getCatalog())) return new EggBoxMonster(this, sfsMonster);
        else return new Monster(this, sfsMonster);
    }

    protected Structure buildStructure(SfsStructure sfsStructure) {
        StructureCategory category = getCatalog().getStructureType(sfsStructure.structure).getStructureCategory();
        return switch (category) {
            case NURSERY -> new Nursery(this, sfsStructure);
            case BREEDING -> new BreedingStructure(this, sfsStructure);
            case MINE -> new Mine(this, sfsStructure);
            case CASTLE -> new Castle(this, sfsStructure);
            case BAKERY -> new Bakery(this, sfsStructure);
            case OBSTACLE -> new Obstacle(this, sfsStructure);
            default -> new Structure(this, sfsStructure);
        };
    }

    public class EventHandler {

        public Structure handleBuyStructureEvent(BuyStructureResponse response) {
            synchronized (Island.this) {
                Structure structure = buildStructure(response.user_structure);
                structures.put(structure.getUserStructureId(), structure);
                return structure;
            }
        }

        public void handleSellStructureEvent(SellStructureResponse response) {
            synchronized (Island.this) {
                structures.remove(response.user_structure_id);
            }
        }

        public void handleClearObstacleEvent(ClearObstacleResponse response) {
            synchronized (Island.this) {
                structures.remove(response.user_structure_id);
            }
        }

        public void handleSellMonsterEvent(SellMonsterResponse response) {
            synchronized (Island.this) {
                Monster monster = getMonster(response.user_monster_id);
                monsters.remove(monster.getUserMonsterId());
            }
        }

        public void handleBuyEggEvent(BuyEggResponse response) {
            Nursery nursery = (Nursery)getStructure(response.user_egg.structure);
            nursery.getEventHandler().handleBuyEggEvent(response);
        }

        public Monster handleHatchEggEvent(HatchEggResponse response) {

            synchronized (Island.this) {

                if (!response.directPlace) {
                    getNurseryWithEggId(response.user_egg_id).getEventHandler().handleHatchEggEvent(response);
                }

                Monster monster = buildMonster(response.monster);
                monsters.put(monster.getUserMonsterId(), monster);

                return monster;

            }

        }

        public Nursery handleFinishBreedingEvent(FinishBreedingResponse response) {

            getBreedingStructureWithBreedingId(response.user_breeding_id).getEventHandler().handleFinishBreedingEvent(response);

            Nursery nursery = (Nursery)getStructure(response.user_egg.structure);
            nursery.getEventHandler().handleFinishBreedingEvent(response);
            return nursery;

        }

    }

    public EventHandler getEventHandler() {
        return new EventHandler();
    }

    public List<Monster> getMonsters() {
        return super.getMonsters().stream().map(m -> (Monster)m).toList();
    }

    public Monster getMonster(long userMonsterId) {
        return (Monster)super.getMonster(userMonsterId);
    }

    public List<Monster> getMonstersOfSpecies(MonsterSpecies species) {
        return super.getMonstersOfSpecies(species).stream()
                .map(m -> (Monster)m).toList();
    }

    public List<Monster> getMonstersOfSpecies(MultiMonsterSpecies multiSpecies) {
        return super.getMonstersOfSpecies(multiSpecies).stream()
                .map(m -> (Monster)m).toList();
    }

    public Monster getMonsterOfSpecies(MonsterSpecies species) {
        return (Monster)super.getMonsterOfSpecies(species);
    }

    public Monster getMonsterOfSpecies(MultiMonsterSpecies multiSpecies) {
        return (Monster)super.getMonsterOfSpecies(multiSpecies);
    }

    public List<Structure> getStructures() {
        return super.getStructures().stream().map(s -> (Structure)s).toList();
    }

    public Structure getStructure(long userStructureId) {
        return (Structure)super.getStructure(userStructureId);
    }

    public List<Structure> getStructuresOfCategory(StructureCategory category) {
        return super.getStructuresOfCategory(category).stream()
                .map(s -> (Structure)s).toList();
    }

    public Structure getStructureOfCategory(StructureCategory category) {
        return (Structure)super.getStructureOfCategory(category);
    }

    public List<Bakery> getBakeries() {
        return getStructuresOfClassType(Bakery.class);
    }

    public List<BreedingStructure> getBreedingStructures() {
        return getStructuresOfClassType(BreedingStructure.class);
    }

    public Castle getCastle() {
        return getStructureOfClassType(Castle.class);
    }

    public Mine getMine() {
        return getStructureOfClassType(Mine.class);
    }

    public List<Nursery> getNurseries() {
        return getStructuresOfClassType(Nursery.class);
    }

    public List<Obstacle> getObstacles() {
        return getStructuresOfClassType(Obstacle.class);
    }

    public List<Structure> getDecorations() {
        return getStructuresOfCategory(StructureCategory.DECORATION).stream().toList();
    }

    public Bakery getBakeryWithBakingId(long userBakingId) {
        return (Bakery)super.getBakeryWithBakingId(userBakingId);
    }

    public BreedingStructure getBreedingStructureWithBreedingId(long userBreedingId) {
        return (BreedingStructure)super.getBreedingStructureWithBreedingId(userBreedingId);
    }

    public Nursery getNurseryWithEggId(long userEggId) {
        return (Nursery)super.getNurseryWithEggId(userEggId);
    }

    public Structure buyStructure(StructureType type, Position position, boolean flip, double scale)
            throws InterruptedException, ClientException {
        BuyStructureRequest request = new BuyStructureRequest();
        synchronized (this) {
            request.flip = flip ? 1 : 0;
            request.pos_x = position.x();
            request.pos_y = position.y();
            request.quest_claim_id = 0;
            request.scale = scale;
            request.starpower_purchase = false;
            request.structure_id = type.getTypeId();
        }
        return client.getEventHandlerManager().getBuyStructureHandler().request(request, this);
    }

    public Structure buyStructure(StructureType type, Position position, boolean flip)
            throws InterruptedException, ClientException {
        return buyStructure(type, position, flip, 0); // real client seems to actually send 0 by default
    }

    public Structure buyStructure(StructureType type, Position position) throws InterruptedException, ClientException {
        return buyStructure(type, position, false, 0);
    }

    public Structure buyStructure(StructureType type) throws InterruptedException, ClientException {
        return buyStructure(type, new Position(0, 0), false, 0);
    }

    public Nursery buyMonsterEgg(MonsterSpecies species) throws InterruptedException, ClientException {
        if (getIslandType() == IslandType.WUBLIN_ISLAND) {
            throw new ClientIllegalArgumentException(
                    "buyMonsterEgg doesn't work on Wublin Island! Maybe you're looking for buyMonster instead?"
            );
        }
        for (Nursery nursery : getNurseries()) {
            if (nursery.isEmpty()) {
                nursery.buyEgg(species);
                return nursery;
            }
        }
        throw new ActionFailedException("Could not buy an egg because your island has no empty nursery");
    }

    public Nursery buyMonsterEgg(MultiMonsterSpecies multiSpecies) throws InterruptedException, ClientException {
        return buyMonsterEgg(multiSpecies.getSpecies(getIslandType()));
    }

    public Monster buyMonster(MonsterSpecies species, MonsterPlacement placement) throws InterruptedException, ClientException {
        if (getIslandType() != IslandType.WUBLIN_ISLAND) {
            throw new ClientIllegalArgumentException(
                    "buyMonster is only for Wublin Island! Maybe you're looking for buyMonsterEgg instead?"
            );
        }
        return client.getEventHandlerManager().getHatchEggHandler().request(
                species.getSpeciesId(), placement, this
        );
    }

    public Monster buyMonster(MultiMonsterSpecies multiSpecies, MonsterPlacement placement) throws InterruptedException, ClientException {
        return buyMonster(multiSpecies.getSpecies(getIslandType()), placement);
    }

    public Monster buyMonster(MonsterSpecies species) throws InterruptedException, ClientException {
        return buyMonster(species, MonsterPlacement.createDefault());
    }

    public Monster buyMonster(MultiMonsterSpecies multiSpecies) throws InterruptedException, ClientException {
        return buyMonster(multiSpecies, MonsterPlacement.createDefault());
    }

    public BreedingStructure breed(ReadableMonster firstMonster, ReadableMonster secondMonster) throws InterruptedException, ClientException {
        BreedingStructure breedingStructure = getBreedingStructures().getFirst();
        breedingStructure.breed(firstMonster, secondMonster);
        return breedingStructure;
    }

    public BreedingStructure breed(MultiMonsterSpecies multiSpecies1, MultiMonsterSpecies multiSpecies2)
            throws InterruptedException, ClientException {

        Monster monster1 = getMonsterOfSpecies(multiSpecies1);
        if (monster1 == null) throw new ActionFailedException("You do not have a monster of species " + multiSpecies1);

        Monster monster2 = getMonsterOfSpecies(multiSpecies2);
        if (monster2 == null) throw new ActionFailedException("You do not have a monster of species " + multiSpecies2);

        return breed(monster1, monster2);

    }

}