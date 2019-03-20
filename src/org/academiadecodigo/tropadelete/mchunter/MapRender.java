package org.academiadecodigo.tropadelete.mchunter;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class MapRender {

    public static void resetMap(String path) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            for (int i = 0; i < Game.GAME_SIZE; i++) {
                for (int j = 0; j < Game.GAME_SIZE; j++) {
                    writer.write('0');
                }
                writer.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Rectangle[][] load(String path) {
        Rectangle[][] walls = new Rectangle[Game.GAME_SIZE][Game.GAME_SIZE];
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));

            String line = "";

            for (int row = 0; (line = reader.readLine()) != null; row++) {
                for (int col = 0; col < line.length(); col++) {
                    if (line.charAt(col) == '1') {
                        Rectangle r = new  Rectangle(10 + col * Game.CELL_SIZE, 10 + row * Game.CELL_SIZE, Game.CELL_SIZE, Game.CELL_SIZE);
                        r.setColor(Color.PINK);
                        r.fill();
                        walls[row][col] = r;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
