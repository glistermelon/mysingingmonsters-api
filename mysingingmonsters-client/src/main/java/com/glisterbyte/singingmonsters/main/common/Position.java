package com.glisterbyte.singingmonsters.main.common;

public record Position(int x, int y) {

    public static Position createDefault() {
        return new Position(0, 0);
    }

    public int distanceTo(Position other) {
        // Diagonal steps are allowed
        int dx = Math.abs(other.x() - x);
        int dy = Math.abs(other.y() - y);
        return Math.max(dx, dy);
    }

}