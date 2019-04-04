package org.academiadecodigo.tropadelete.mchunter.gameobject.movable;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.tropadelete.mchunter.Settings.Player.*;

public class Player extends MovableGameObject {

    public Player(Picture[] sprites) {
        super(PLAYER_SPEED, sprites);
    }
}
