package org.academiadecodigo.tropadelete.mchunter.gameobject;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Ghost;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.academiadecodigo.tropadelete.mchunter.Settings.Game.*;
import static org.academiadecodigo.tropadelete.mchunter.Settings.Ghost.*;
import static org.academiadecodigo.tropadelete.mchunter.Settings.Player.*;

public class GameObjectFactory {

    public static Wall[][] createWalls() {
        return load(MAP_PATH);
    }

    public static List<Ghost> createGhosts() {
        List<Ghost> ghosts = new LinkedList<>();

        for (int i = 0; i < GHOST_SPRITES.length; i++) {
            ghosts.add(new Ghost(loadSprites(GHOST_X + (i * (CELL_SIZE * 2)), GHOST_Y, GHOST_SPRITES[i])));
        }

        return ghosts;
    }


    public static Player createPlayer() {
        return new Player(loadSprites(PLAYER_X, PLAYER_Y, PLAYER_SPRITES));
    }

    private static Wall[][] load(String path) {
        Wall[][] walls = new Wall[GAME_SIZE][GAME_SIZE];
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(path));

            String line = "";

            for (int row = 0; (line = reader.readLine()) != null; row++) {
                for (int col = 0; col < line.length(); col++) {
                    if (line.charAt(col) == '1') {
                        Rectangle rectangle = new  Rectangle(PADDING + col * CELL_SIZE, PADDING + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        rectangle.setColor(WALL_COLOR);
                        rectangle.fill();
                        walls[row][col] = new Wall(rectangle);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return walls;
    }

    private static Picture[] loadSprites(int initialX, int initialY, String[] spritePaths) {
        Picture[] sprites = new Picture[spritePaths.length];

        for (int i = 0; i < sprites.length; i++) {
            Picture sprite = new Picture(initialX, initialY, spritePaths[i]);
            if (i == 0) {
                sprite.draw();
            }
            sprites[i] = sprite;
        }

        return sprites;
    }
}

