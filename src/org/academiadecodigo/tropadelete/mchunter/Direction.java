package org.academiadecodigo.tropadelete.mchunter;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;

public enum Direction {
    LEFT(KeyboardEvent.KEY_LEFT, KeyboardEventType.KEY_PRESSED, -1, 0),
    UP(KeyboardEvent.KEY_UP, KeyboardEventType.KEY_PRESSED, 0, -1),
    RIGHT(KeyboardEvent.KEY_RIGHT, KeyboardEventType.KEY_PRESSED, 1, 0),
    DOWN(KeyboardEvent.KEY_DOWN, KeyboardEventType.KEY_PRESSED, 0, 1);

    private int key;
    private KeyboardEventType eventType;
    private int dX;
    private int dY;

    Direction(int key, KeyboardEventType eventType, int dX, int dY){
        this.key = key;
        this.eventType = eventType;
        this.dX = dX;
        this.dY = dY;
    }

    public static Direction getDirectionByKey(int key) {
        return values()[key - LEFT.getKey()];
    }

    public int getKey() {
        return key;
    }

    public KeyboardEventType getEventType() {
        return eventType;
    }

    public int getdX() {
        return dX;
    }

    public int getdY() {
        return dY;
    }
}
