package org.academiadecodigo.tropadelete.mchunter.gameobject.movable;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tropadelete.mchunter.GameSettings;

public class Ghost extends MovableGameObject {
    public Ghost(Rectangle rectangle) {
        super(rectangle, GameSettings.GHOST_SPEED);
    }

    public void randomlyChangeDirection(){
        if (Math.random() > 0.90) {
            if (Math.random() > 0.7) {
                super.changeDirection(Direction.values()[(int)(Math.random() * Direction.values().length)]);
            } else {
                super.cancelChangeDirection();
            }
        }
    }
}

