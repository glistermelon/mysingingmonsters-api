package com.glisterbyte.singingmonsters.main.monster;

import com.glisterbyte.singingmonsters.main.common.CurrencyType;
import com.glisterbyte.singingmonsters.main.common.MultiCurrencyValue;
import com.glisterbyte.singingmonsters.main.common.Position;
import com.glisterbyte.singingmonsters.exceptions.ClientException;
import com.glisterbyte.singingmonsters.main.client.Client;
import com.glisterbyte.singingmonsters.main.island.Island;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonster;
import com.glisterbyte.singingmonsters.sfsmodels.data.SfsMonsterMultiUpdate;
import com.glisterbyte.singingmonsters.sfsmodels.events.BoxActivateMonsterResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.FeedMonsterResponse;
import com.glisterbyte.singingmonsters.sfsmodels.events.UpdateMonsterEvent;
import com.glisterbyte.singingmonsters.sfsmodels.requests.NameMonsterRequest;
import org.apache.commons.lang3.Validate;

import java.time.Instant;

public class Monster extends AbstractMonster implements ControllableMonster {

    public Monster(Island island, SfsMonster sfsMonster) {
        super(island, sfsMonster);
    }

    public class EventHandler {

        private void validateUserMonsterId(long userMonsterId) {
            synchronized (Monster.this) {
                Validate.isTrue(
                        Monster.this.userMonsterId == userMonsterId,
                        "Expected event user monster id {} to match actual user monster id {}",
                        userMonsterId, Monster.this.userMonsterId
                );
            }
        }

        public void handleUpdateEvent(UpdateMonsterEvent event) {
            synchronized (Monster.this) {
                validateUserMonsterId(event.user_monster_id);
                if (event.pos_x != null) position = new Position(event.pos_x, position.y());
                if (event.pos_y != null) position = new Position(position.x(), event.pos_y);
                if (event.flip != null) flipped = event.flip != 0;
                if (event.muted != null) muted = event.muted != 0;
                if (event.volume != null) volume = event.volume;
                if (event.last_collection != null) lastCollectTime = Instant.ofEpochMilli(event.last_collection);
                if (event.times_fed != null) timesFed = event.times_fed;
                if (event.level != null) level = event.level;
                if (event.collection_type != null) collectionCurrencyType = CurrencyType.fromString(event.collection_type);
            }
        }

        public void handleUpdateEvent(SfsMonsterMultiUpdate event) {
            synchronized (Monster.this) {
                validateUserMonsterId(event.user_monster_id);
                if (event.last_collection != null) lastCollectTime = Instant.ofEpochMilli(event.last_collection);
                if (event.happiness != null) happiness = MonsterHappiness.fromPercentage(event.happiness);
            }
        }

        public void handleActivateEvent(BoxActivateMonsterResponse event) {
            synchronized (Monster.this) {
                validateUserMonsterId(event.user_monster_id);
                activated = true;
            }

        }

        public void handleFeedEvent(FeedMonsterResponse event) {
            synchronized (Monster.this) {
                // The server doesn't actually provide any data, so we just have to approximate locally
                lastFeedTime = Instant.now();
            }
        }

    }

    public EventHandler getEventHandler() {
        return new EventHandler();
    }

    public synchronized Island getIsland() {
        return (Island)super.getIsland();
    }

    public MultiCurrencyValue collect() throws InterruptedException, ClientException {
        return getClient().getEventHandlerManager().getCollectMonsterHandler().request(this);
    }

    public void sell() throws InterruptedException, ClientException {
        getClient().getEventHandlerManager().getSellMonsterHandler().request(this);
    }

    public void feed() throws InterruptedException, ClientException {
        getClient().getEventHandlerManager().getFeedMonsterHandler().request(this);
    }

    public void feedToLevel(int newLevel) throws InterruptedException, ClientException {
        while (true) {
            synchronized (this) {
                if (level >= newLevel) break;
            }
            feed();
        }
    }

    public void feedToNextLevel() throws InterruptedException, ClientException {
        int nextLevel;
        synchronized (this) {
            nextLevel = level + 1;
        }
        feedToLevel(nextLevel);
    }

    public void setFlipped(boolean flipped) throws InterruptedException, ClientException {
        getClient().getEventHandlerManager().getFlipMonsterHandler().request(this, flipped);
    }

    public void flip() throws InterruptedException, ClientException {
        setFlipped(!flipped);
    }

    public void move(MonsterPlacement newPlacement, double newVolume) throws InterruptedException, ClientException {
        if (newPlacement.flipped() != flipped) flip();
        getClient().getEventHandlerManager().getMoveMonsterHandler().request(this, newPlacement.position(), newVolume);
    }

    public void move(MonsterPlacement newPlacement) throws InterruptedException, ClientException {
        move(newPlacement, volume);
    }

    public void move(Position newPosition, double newVolume) throws InterruptedException, ClientException {
        move(getPlacement().withPosition(newPosition), newVolume);
    }

    public void move(Position newPosition) throws InterruptedException, ClientException {
        move(newPosition, volume);
    }

    public void setVolume(double newVolume) throws InterruptedException, ClientException {
        move(getPlacement(), newVolume);
    }

    public void setMuted(boolean muted) throws InterruptedException, ClientException {
        if (this.muted == muted) return;
        getClient().getEventHandlerManager().getMuteMonsterHandler().request(this, muted);
    }

    public void mute() throws ClientException, InterruptedException {
        setMuted(true);
    }

    public void unmute() throws ClientException, InterruptedException {
        setMuted(false);
    }

    public void setName(String newName) throws ClientException {

        /*
            Server doesn't respond so no handler is necessary,
            but we also just have to hope the server didn't reject the new name.
         */

        NameMonsterRequest request = new NameMonsterRequest();
        request.user_monster_id = userMonsterId;
        request.name = newName;
        getClient().getWebsocketClient().send(request);

        name = newName;

    }

}