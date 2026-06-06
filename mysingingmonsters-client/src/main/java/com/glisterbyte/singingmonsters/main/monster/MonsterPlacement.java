package com.glisterbyte.singingmonsters.main.monster;

import com.glisterbyte.singingmonsters.main.common.Position;

public record MonsterPlacement(
        Position position,
        boolean flipped
) {

    public static MonsterPlacement createDefault() {
        return new MonsterPlacement(Position.createDefault(), false);
    }

    public MonsterPlacement withPosition(Position position) {
        return new MonsterPlacement(position, flipped);
    }

    public MonsterPlacement withPositionX(int x) {
        return new MonsterPlacement(new Position(x, position.y()), flipped);
    }

    public MonsterPlacement withPositionY(int y) {
        return new MonsterPlacement(new Position(position.x(), y), flipped);
    }

    public MonsterPlacement withFlipped(boolean flipped) {
        return new MonsterPlacement(position, flipped);
    }

}