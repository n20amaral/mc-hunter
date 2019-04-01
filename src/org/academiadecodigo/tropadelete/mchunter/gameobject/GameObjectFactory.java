package org.academiadecodigo.tropadelete.mchunter.gameobject;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tropadelete.mchunter.GameSettings;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Ghost;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GameObjectFactory {

    public Wall[][] createWalls() {
        return load(GameSettings.MAP_PATH);
    }

    public List<Ghost> createGhosts() {
        List<Ghost> ghosts = new LinkedList<>();

        for (int i = 0; i < GameSettings.GHOST_SPRITES.length; i++) {
            Rectangle rectangle = new Rectangle(GameSettings.PADDING + GameSettings.CELL_SIZE + (i * (GameSettings.CELL_SIZE * 2)), GameSettings.PADDING + GameSettings.CELL_SIZE, GameSettings.CELL_SIZE, GameSettings.CELL_SIZE);
            rectangle.setColor(Color.BLUE);
            rectangle.fill();
            ghosts.add(new Ghost(rectangle));
        }

        return ghosts;
    }


    public Player createPlayer() {

        Rectangle rectangle = new Rectangle(GameSettings.PADDING + GameSettings.CELL_SIZE, GameSettings.PADDING + GameSettings.CELL_SIZE * (GameSettings.GAME_SIZE - 2), GameSettings.CELL_SIZE, GameSettings.CELL_SIZE);
        rectangle.setColor(Color.YELLOW);
        rectangle.fill();
        return new Player(rectangle);
    }

    public static Wall[][] load(String path) {
        Wall[][] walls = new Wall[GameSettings.GAME_SIZE][GameSettings.GAME_SIZE];
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(path));

            String line = "";

            for (int row = 0; (line = reader.readLine()) != null; row++) {
                for (int col = 0; col < line.length(); col++) {
                    if (line.charAt(col) == '1') {
                        Rectangle rectangle = new  Rectangle(GameSettings.PADDING + col * GameSettings.CELL_SIZE, GameSettings.PADDING + row * GameSettings.CELL_SIZE, GameSettings.CELL_SIZE, GameSettings.CELL_SIZE);
                        rectangle.setColor(Color.PINK);
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
}

