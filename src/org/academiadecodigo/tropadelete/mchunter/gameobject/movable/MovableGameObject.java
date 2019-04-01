package org.academiadecodigo.tropadelete.mchunter.gameobject.movable;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tropadelete.mchunter.gameobject.GameObject;

public abstract class MovableGameObject extends GameObject {
    private int speed;
    private boolean isChangingDirection;
    private Direction currentDirection;
    private Direction nextDirection;

    public MovableGameObject(Rectangle rectangle, int speed) {
        super(rectangle);
        this.speed = speed;
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
