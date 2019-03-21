package org.academiadecodigo.tropadelete.mchunter;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Player implements Movable {

    protected Rectangle rectangle;
    private Direction currentDirection;
    private Direction nextDirection;
    private boolean changingDirection = false;
    private int speed;
    private boolean reborn;

    public boolean isReborn() {
        return reborn;
    }

    public void setReborn(boolean reborn) {
        this.reborn = reborn;
    }

    public Player(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.nextDirection = null;
        this.currentDirection = null;
        this.speed = 1;
    }

    public void show() {
        rectangle.fill();
    }


    public void hide() {
        rectangle.delete();
    }

    public void move() {
        if (nextDirection == null) {
            return;
        }

        currentDirection = nextDirection;

        move(currentDirection);
    }

    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                rectangle.translate(0 - speed, 0);
                break;
            case UP:
                rectangle.translate(0,0 - speed);
                break;
            case RIGHT:
                rectangle.translate(speed, 0);
                break;
            case DOWN:
                rectangle.translate(0, speed);
                break;
        }
        show();
    }

    @Override
    public int getCol() {
        return (rectangle.getX() - Game.PADDING) / Game.CELL_SIZE;
    }

    @Override
    public int getRow() {
        return (rectangle.getY() - Game.PADDING) / Game.CELL_SIZE;
    }

    @Override
    public int getX() {
        return rectangle.getX();
    }

    @Override
    public int getY() {
        return rectangle.getY();
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(Direction direction) {
        this.nextDirection = direction;
    }

    @Override
    public void changeDirection(Direction direction) {
        changingDirection = true;
        setNextDirection(direction);
    }

    public void cancelChangeDirection(){
        changingDirection = false;
        setNextDirection(currentDirection);
    }

    public boolean isChangingDirection() {
        return changingDirection;
    }

    public Direction getCurrentDirection(){
        return currentDirection;
    }

}
