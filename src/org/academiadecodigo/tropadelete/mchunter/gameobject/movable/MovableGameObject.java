package org.academiadecodigo.tropadelete.mchunter.gameobject.movable;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.mchunter.gameobject.GameObject;

public abstract class MovableGameObject extends GameObject {
    private int activeSprite;
    private Picture[] sprites;
    private int speed;
    private boolean isChangingDirection;
    private Direction currentDirection;
    private Direction nextDirection;
    private int initialX;
    private int initialY;

    public MovableGameObject(int speed, Picture[] sprites) {
        super(sprites[0]);
        this.sprites = sprites;
        this.activeSprite = 0;
        this.speed = speed;
        this.initialX = sprites[activeSprite].getX();
        this.initialY = sprites[activeSprite].getY();
    }

    public void moveToNextDirection() {
        if (nextDirection == null) {
            return;
        }

        currentDirection = nextDirection;
        isChangingDirection = false;

        updateSprite(nextDirection);
        move();
    }

    public void move() {
        if (currentDirection == null) {
            return;
        }

        sprites[activeSprite].translate(currentDirection.getdX(), currentDirection.getdY());
        sprites[activeSprite].draw();
    }

    public void changeDirection(Direction direction) {
        isChangingDirection = true;
        nextDirection = direction;
    }

    public void cancelChangeDirection() {
        isChangingDirection = false;
        nextDirection = currentDirection;
    }

    private void updateSprite(Direction direction) {
        int nextSprite = direction.ordinal();
        int dY = sprites[activeSprite].getY() - sprites[nextSprite].getY();
        int dX = sprites[activeSprite].getX() - sprites[nextSprite].getX();

        sprites[activeSprite].delete();
        sprites[nextSprite].translate(dX, dY);

        activeSprite = nextSprite;
        super.setShape(sprites[activeSprite]);
    }

    public void reset() {
        int dY = initialY - sprites[activeSprite].getY();
        int dX = initialX - sprites[activeSprite].getX();

        sprites[activeSprite].delete();
        sprites[activeSprite].translate(dX, dY);
        sprites[activeSprite].draw();

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
