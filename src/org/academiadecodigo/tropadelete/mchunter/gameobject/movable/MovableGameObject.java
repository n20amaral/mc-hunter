package org.academiadecodigo.tropadelete.mchunter.gameobject.movable;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tropadelete.mchunter.gameobject.GameObject;

public abstract class MovableGameObject extends GameObject {
    private int speed;
    private boolean isChangingDirection;
    private Direction currentDirection;
    private Direction nextDirection;
    private int initialX;
    private int initialY;

    public MovableGameObject(Rectangle rectangle, int speed) {
        super(rectangle);
        this.speed = speed;
        this.initialX = rectangle.getX();
        this.initialY = rectangle.getY();
    }

    public void moveToNextDirection() {
        if (nextDirection == null) {
            return;
        }

        currentDirection = nextDirection;
        isChangingDirection = false;
        move();
    }

    public void move() {
        if (currentDirection == null) {
            return;
        }

        rectangle.translate(currentDirection.getdX(), currentDirection.getdY());
        rectangle.fill();
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
        int dX = initialX - rectangle.getX();
        int dY = initialY - rectangle.getY();
        this.rectangle.delete();
        this.rectangle.translate(dX, dY);
        this.rectangle.fill();
        this.hidden = false;
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
