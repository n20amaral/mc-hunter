package org.academiadecodigo.tropadelete.mchunter.gameobject.movable;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.mchunter.gameobject.GameObject;

public abstract class MovableGameObject extends GameObject {
    private Picture sprite;
    private String[] spritePaths;
    private int speed;
    private boolean isChangingDirection;
    private Direction currentDirection;
    private Direction nextDirection;
    private int initialX;
    private int initialY;

    public MovableGameObject(Picture sprite, int speed, String[] spritePaths) {
        super(sprite);
        this.sprite = sprite;
        this.spritePaths = spritePaths;
        this.speed = speed;
        this.initialX = sprite.getX();
        this.initialY = sprite.getY();
    }

    public void moveToNextDirection() {
        if (nextDirection == null) {
            return;
        }

        currentDirection = nextDirection;
        isChangingDirection = false;
        sprite.load(spritePaths[nextDirection.ordinal()]);
        move();
    }

    public void move() {
        if (currentDirection == null) {
            return;
        }

        sprite.translate(currentDirection.getdX(), currentDirection.getdY());
        sprite.draw();
    }

    public void changeDirection(Direction direction) {
        isChangingDirection = true;
        nextDirection = direction;
    }

    public void cancelChangeDirection() {
        isChangingDirection = false;
        nextDirection = currentDirection;
    }

    public void reset() {
        int dX = initialX - sprite.getX();
        int dY = initialY - sprite.getY();
        sprite.delete();
        sprite.translate(dX, dY);
        sprite.draw();
        hidden = false;
        currentDirection = null;
        nextDirection = null;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isChangingDirection() {
        return isChangingDirection;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }
}
