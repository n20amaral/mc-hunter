package org.academiadecodigo.tropadelete.mchunter.gameobject.movable;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tropadelete.mchunter.GameSettings;

public class Player extends MovableGameObject {

    public Player(Rectangle rectangle) {
        super(rectangle, GameSettings.PLAYER_SPEED);
    }
}
