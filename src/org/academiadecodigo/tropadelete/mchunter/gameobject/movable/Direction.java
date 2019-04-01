package org.academiadecodigo.tropadelete.mchunter.gameobject.movable;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

public enum Direction {
    LEFT(KeyboardEvent.KEY_LEFT, -1, 0),
    UP(KeyboardEvent.KEY_UP, 0, -1),
    RIGHT(KeyboardEvent.KEY_RIGHT, 1, 0),
    DOWN(KeyboardEvent.KEY_DOWN, 0, 1);

    private int key;
    private int dX;
    private int dY;

    Direction(int key, int dX, int dY){
        this.key = key;
        this.dX = dX;
        this.dY = dY;
    }

    public static Direction getDirectionByKey(int key) {
        return values()[key - LEFT.getKey()];
    }

    public int getKey() {
        return key;
    }

    public int getdX() {
        return dX;
    }

    public int getdY() {
        return dY;
    }
}
