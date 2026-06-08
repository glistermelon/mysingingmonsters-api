package com.glisterbyte.singingmonsters.main.structure;

import com.glisterbyte.singingmonsters.main.common.Position;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.client.Client;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsStructure;
import com.glisterbyte.singingmonsters.sfsmodels.events.StartRemoveObstacleResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.UpdateStructureEvent;
import org.apache.commons.lang3.Validate;

public class Structure extends AbstractStructure implements ControllableStructure {

    public Structure(Island island, SfsStructure sfsStructure) {
        super(island, sfsStructure);
    }

    public class EventHandler {

        protected void validateUserStructureId(long userStructureId) {
            synchronized (Structure.this) {
                Validate.isTrue(
                        Structure.this.userStructureId == userStructureId,
                        "Expected event user structure id %s to match actual user structure id %s",
                        userStructureId, Structure.this.userStructureId
                );
            }
        }

        public void handleUpdateEvent(UpdateStructureEvent event) {
            synchronized (Structure.this) {
                validateUserStructureId(event.user_structure_id);
                if (event.structureUpdate != null) {
                    var update = event.structureUpdate;
                    if (update.pos_x != null) position = new Position(update.pos_x, position.y());
                    if (update.pos_y != null) position = new Position(position.x(), update.pos_y);
                    if (update.flip != null) flipped = update.flip != 0;
                }
            }
        }

        public void handleStartRemoveObstacleEvent(StartRemoveObstacleResponse event) {
            validateUserStructureId(event.user_structure_id);
            internalRefresh(event.user_structure, false);
        }

    }

    public EventHandler getEventHandler() {
        return new EventHandler();
    }

    public synchronized Island getIsland() {
        return (Island)super.getIsland();
    }

    public void sell() throws InterruptedException, ClientException {
        getClient().getEventHandlerManager().getSellStructureHandler().request(this);
    }

    public void setFlipped(boolean flipped) throws InterruptedException, ClientException {
        getClient().getEventHandlerManager().getFlipStructureHandler().request(this, flipped);
    }

    public void flip() throws InterruptedException, ClientException {
        setFlipped(!flipped);
    }

    public void move(Position newPosition, double newScale) throws InterruptedException, ClientException {

        // The server enforces these scale bounds.
        newScale = Math.clamp(newScale, MIN_SCALE, MAX_SCALE);

        getClient().getEventHandlerManager().getMoveStructureHandler().request(this, newPosition, newScale, 0);

        // The server doesn't send a new scale back so we have to set it manually.
        scale = newScale;

    }

    public void move(Position newPosition) throws InterruptedException, ClientException {
        move(newPosition, scale);
    }

    public void setScale(double newScale) throws InterruptedException, ClientException {
        move(position, newScale);
    }

}