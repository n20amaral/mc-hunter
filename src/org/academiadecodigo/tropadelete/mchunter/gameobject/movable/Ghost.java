package org.academiadecodigo.tropadelete.mchunter.gameobject.movable;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import static org.academiadecodigo.tropadelete.mchunter.Settings.Ghost.*;

public class Ghost extends MovableGameObject {
    public Ghost(Rectangle rectangle) {
        super(rectangle, GHOST_SPEED);
    }

    public void randomlyChangeDirection(){
        if (Math.random() < GHOST_CHANGE_DIRECTION_PROB) {
            if (Math.random() > GHOST_CANCEL_DIRECTION_PROB) {
                super.changeDirection(Direction.values()[(int)(Math.random() * Direction.values().length)]);
            } else {
                super.cancelChangeDirection();
            }
        }
    }
}

