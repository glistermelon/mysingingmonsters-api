package com.glisterbyte.singingmonsters.handling.implementations;

import com.glisterbyte.singingmonsters.main.common.Position;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.handling.EventHandlerInitArg;
import com.glisterbyte.singingmonsters.handling.Request;
import com.glisterbyte.singingmonsters.handling.UncorrelatedResultResponseHandler;
import com.glisterbyte.singingmonsters.main.common.Positioned;
import com.glisterbyte.singingmonsters.main.common.PositionedDummy;
import com.glisterbyte.singingmonsters.main.island.IslandType;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.main.monster.Monster;
import com.glisterbyte.singingmonsters.main.structure.Structure;
import com.glisterbyte.singingmonsters.sfsmodels.requests.MultiUpdateMonsterRequest;
import com.glisterbyte.singingmonsters.sfsmodels.events.MultiUpdateMonsterResponse;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;

import javax.annotation.Nullable;
import java.util.*;

/*
    TODO: Does buying/moving/whatever with a unity tree have island_needs_happiness_update=true?
 */

public class MultiMonsterUpdateHandler extends UncorrelatedResultResponseHandler<Void, MultiUpdateMonsterResponse, Void> {

    @Override
    public Void handleSuccess(MultiUpdateMonsterResponse event, Void requestData) throws InterruptedException, ClientException {
        for (var update : event.update_monster_list) {
            client.getMonster(update.user_monster_id).getEventHandler().handleUpdateEvent(update);
        }
        return null;
    }

    public record NeighborData(
            int entityId,
            int distance
    ) { }

    public static class NeighborsData {

        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        public Optional<Long> userMonsterId = Optional.empty();

        public Collection<NeighborData> neighborsData = new ArrayList<>();

        public SFSObject getSfsObject() {

            SFSObject sfsObject = new SFSObject();

            SFSArray neighbors = new SFSArray();
            for (NeighborData neighborData : neighborsData) {
                SFSObject neighbor = new SFSObject();
                neighbor.putInt("distance", neighborData.distance());
                neighbor.putInt("id", neighborData.entityId());
                neighbors.addSFSObject(neighbor);
            }

            sfsObject.putSFSArray("neighbors", neighbors);

            //noinspection OptionalIsPresent
            if (userMonsterId.isPresent()) {
                sfsObject.putLong("user_monster_id", userMonsterId.get());
            }

            return sfsObject;

        }

    }

    public MultiMonsterUpdateHandler(EventHandlerInitArg arg) {
        super(arg, MultiUpdateMonsterResponse.class);
    }

    public void requestWithRawData(Collection<NeighborsData> neighborsCollection, Island island) throws InterruptedException, ClientException {
        MultiUpdateMonsterRequest request = new MultiUpdateMonsterRequest();
        request.island_needs_happiness_update = false; // never seen this be true
        request.entity_array = neighborsCollection.stream().map(NeighborsData::getSfsObject).toList();
        Request.simpleRequest(this, request, island);
    }

    public void request(Collection<Positioned> updateObjects, Island island) throws InterruptedException, ClientException {

        List<NeighborsData> neighborsDataList = new ArrayList<>();
        for (Positioned object : updateObjects) {
            NeighborsData neighbors = new NeighborsData();
            for (Positioned other : updateObjects) {
                NeighborData neighbor = new NeighborData(
                        other.getEntity().getEntityId(),
                        object.distanceTo(other)
                );
                neighbors.neighborsData.add(neighbor);
            }
            if (object instanceof Monster monster) {
                neighbors.userMonsterId = Optional.of(monster.getUserMonsterId());
            }
            neighborsDataList.add(neighbors);
        }

        requestWithRawData(neighborsDataList, island);

    }

    public void consider(Positioned moved, @Nullable Position originalPosition, Island island) throws InterruptedException, ClientException {
        Set<Positioned> updateObjects = Collections.newSetFromMap(new IdentityHashMap<>());
        updateObjects.addAll(island.getNearbyObjects(moved, 2));
        if (originalPosition != null) {
            updateObjects.addAll(island.getNearbyObjects(
                    new PositionedDummy(moved.getEntity(), originalPosition, moved.getSize(), false), 2
            ));
        }
        if (updateObjects.stream().noneMatch(obj -> obj instanceof Monster)) return;
        request(updateObjects, island);
    }

    public void consider(Monster monster, @Nullable Position originalPosition) throws InterruptedException, ClientException {
        if (monster.getIsland().getIslandType() == IslandType.WUBLIN_ISLAND && !monster.isActivated()) return;
        consider(monster, originalPosition, monster.getIsland());
    }

    public void consider(Structure structure, @Nullable Position originalPosition) throws InterruptedException, ClientException {
        if (structure.getIsland().getIslandType() == IslandType.WUBLIN_ISLAND) return;
        consider(structure, originalPosition, structure.getIsland());
    }

}