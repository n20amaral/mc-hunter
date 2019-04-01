package org.academiadecodigo.tropadelete.mchunter;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.io.*;

import static org.academiadecodigo.tropadelete.mchunter.GameSettings.CELL_SIZE;
import static org.academiadecodigo.tropadelete.mchunter.GameSettings.GAME_SIZE;

public class MapRender {

    public static void resetMap(String path) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            for (int i = 0; i < GAME_SIZE; i++) {
                for (int j = 0; j < GAME_SIZE; j++) {
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
        Rectangle[][] walls = new Rectangle[GAME_SIZE][GAME_SIZE];
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));

            String line = "";

            for (int row = 0; (line = reader.readLine()) != null; row++) {
                for (int col = 0; col < line.length(); col++) {
                    if (line.charAt(col) == '1') {
                        Rectangle r = new  Rectangle(10 + col * CELL_SIZE, 10 + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
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
