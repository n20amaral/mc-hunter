package org.academiadecodigo.tropadelete.mchunter;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.List;

public class Game {
    public static final int GAME_SIZE = 25;
    public static final int CELL_SIZE = 20;
    public static final int PADDING = 10;

    private CollisionDetector collisionDetector;
    private Rectangle[][] walls;
    private boolean gameOver = false;

    Player player;

    public void init() {
        Rectangle canvas = new Rectangle(PADDING, PADDING, GAME_SIZE * CELL_SIZE, GAME_SIZE * CELL_SIZE);
        canvas.setColor(Color.BLACK);
        canvas.fill();

        walls = MapRender.load("resources/maps/1.txt");
        collisionDetector = new CollisionDetector(walls);

        Rectangle playerAsset = new Rectangle((1) * CELL_SIZE + PADDING, 1 * CELL_SIZE + PADDING, CELL_SIZE, CELL_SIZE);
        playerAsset.setColor(Color.YELLOW);
        player = new Player(playerAsset);
        player.show();

        new KeyboardListener(player).init();

    }

    public void start() {
        while (!gameOver) {
            System.out.println(player.getRow() + " - " + player.getCol());
            System.out.println(player.getX());

            if (!collisionDetector.checkCollisionWithWalls(player, player.getNextDirection())) {
                player.move();
            } else {
                if (!collisionDetector.checkCollisionWithWalls(player, player.getCurrentDirection())) {
                    player.move(player.getCurrentDirection());
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
