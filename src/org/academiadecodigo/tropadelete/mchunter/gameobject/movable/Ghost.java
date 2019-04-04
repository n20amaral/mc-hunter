package org.academiadecodigo.tropadelete.mchunter.gameobject.movable;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.tropadelete.mchunter.Settings.Ghost.*;

public class Ghost extends MovableGameObject {
    public Ghost(Picture[] sprites) {
        super(GHOST_SPEED, sprites);
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

