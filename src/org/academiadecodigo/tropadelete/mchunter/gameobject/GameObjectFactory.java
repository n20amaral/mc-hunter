package org.academiadecodigo.tropadelete.mchunter.gameobject;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Ghost;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.academiadecodigo.tropadelete.mchunter.Settings.Game.*;
import static org.academiadecodigo.tropadelete.mchunter.Settings.Ghost.*;

public class GameObjectFactory {

    public Wall[][] createWalls() {
        return load(MAP_PATH);
    }

    public List<Ghost> createGhosts() {
        List<Ghost> ghosts = new LinkedList<>();

        for (int i = 0; i < GHOST_SPRITES.length; i++) {
            Rectangle rectangle = new Rectangle(PADDING + CELL_SIZE + (i * (CELL_SIZE * 2)), PADDING + CELL_SIZE, CELL_SIZE, CELL_SIZE);
            rectangle.setColor(Color.BLUE);
            rectangle.fill();
            ghosts.add(new Ghost(rectangle));
        }

        return ghosts;
    }


    public Player createPlayer() {

        Rectangle rectangle = new Rectangle(PADDING + CELL_SIZE, PADDING + CELL_SIZE * (GAME_SIZE - 2), CELL_SIZE, CELL_SIZE);
        rectangle.setColor(Color.YELLOW);
        rectangle.fill();
        return new Player(rectangle);
    }

    public static Wall[][] load(String path) {
        Wall[][] walls = new Wall[GAME_SIZE][GAME_SIZE];
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(path));

            String line = "";

            for (int row = 0; (line = reader.readLine()) != null; row++) {
                for (int col = 0; col < line.length(); col++) {
                    if (line.charAt(col) == '1') {
                        Rectangle rectangle = new  Rectangle(PADDING + col * CELL_SIZE, PADDING + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
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

