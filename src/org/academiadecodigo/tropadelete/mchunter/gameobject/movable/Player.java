package org.academiadecodigo.tropadelete.mchunter.gameobject.movable;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import static org.academiadecodigo.tropadelete.mchunter.Settings.Player.*;

public class Player extends MovableGameObject {

    public Player(Rectangle rectangle) {
        super(rectangle, PLAYER_SPEED);
    }
}
