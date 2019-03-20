package org.academiadecodigo.tropadelete.mchunter;

public interface Movable {
    void changeDirection(Direction direction);
    Direction getNextDirection();
    boolean isChangingDirection();
    void cancelChangeDirection();
    Direction getCurrentDirection();
    void move();
    int getCol();
    int getRow();
    int getX();
    int getY();
    int getSpeed();
}
