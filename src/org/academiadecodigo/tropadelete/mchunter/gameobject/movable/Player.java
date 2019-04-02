package org.academiadecodigo.tropadelete.mchunter.gameobject.movable;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.tropadelete.mchunter.Settings.Player.*;

public class Player extends MovableGameObject {

    public Player(Picture sprite, String[] spritePaths) {
        super(sprite, PLAYER_SPEED, spritePaths);
    }
}
