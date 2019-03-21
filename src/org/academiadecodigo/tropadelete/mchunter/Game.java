package org.academiadecodigo.tropadelete.mchunter;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

import java.util.LinkedList;
import java.util.List;

public class Game {
    public static final int GAME_SIZE = 25;
    public static final int CELL_SIZE = 30;
    public static final int PADDING = 10;

    private CollisionDetector collisionDetector;
    private Rectangle[][] walls;
    private List<Ghost> ghosts;
    private boolean gameOver;
    private Player player;

    public Game() {
        ghosts = new LinkedList<>();
    }

    public void init() {
        Rectangle canvas = new Rectangle(PADDING, PADDING, GAME_SIZE * CELL_SIZE, GAME_SIZE * CELL_SIZE);
        canvas.setColor(Color.BLACK);
        canvas.fill();

        walls = MapRender.load("resources/maps/1.txt");
        collisionDetector = new CollisionDetector(walls);

        Rectangle playerAsset = new Rectangle( 12 * CELL_SIZE + PADDING, 9 * CELL_SIZE + PADDING, CELL_SIZE, CELL_SIZE);
        playerAsset.setColor(Color.YELLOW);
        player = new Player(playerAsset);
        player.show();

        generateGhosts(6);
        gameOver = false;
        new KeyboardListener(player).init();
    }

    public void start() {
        while (!gameOver) {

//            if (!collisionDetector.checkCollisionWithWalls(player, player.getNextDirection())) {
//                player.move();
//            } else {
//                if (!collisionDetector.checkCollisionWithWalls(player, player.getCurrentDirection())) {
//                    player.move(player.getCurrentDirection());
//                }
//            }
            for (int i = 0; i < 2; i++) {
                moveObject(player);

            }

//            for (Ghost g : ghosts) {
//            }

            if (gameOver) {
                break;
            }

            for (Ghost g : ghosts) {

                if (Math.random() > 0.90) {
                    if (Math.random() > 0.7) {
                        g.changeDirection(Direction.values()[(int)(Math.random() * Direction.values().length)]);
                    } else {
                        g.cancelChangeDirection();
                    }
                }
                moveObject(g);
                if (collisionDetector.checkCollisionWithGhost(player, g)) {
                    gameOver = true;
                    break;
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Text text = new Text(GAME_SIZE * CELL_SIZE / 2, GAME_SIZE * CELL_SIZE / 2, "GAME OVER");
        Color color = Color.RED;

        while (!player.isReborn()) {
            text.draw();
            color = color == Color.RED ? Color.GREEN : Color.RED;
            text.setColor(color);
        }
        text.delete();
        resetGame();
    }

    public void generateGhosts(int numberOfGhosts) {

        for (int i = 0; i < numberOfGhosts; i++) {
            Rectangle rectangle = new Rectangle(7 * CELL_SIZE + (i * (CELL_SIZE * 2)) + Game.PADDING, 12 * CELL_SIZE + Game.PADDING, CELL_SIZE, CELL_SIZE);
            rectangle.setColor(Color.BLUE);
            rectangle.fill();
            Ghost ghost = new Ghost(rectangle);
            Direction startingDirection = i % 2 == 0 ? Direction.LEFT : Direction.RIGHT;
            ghost.changeDirection(startingDirection);

            ghosts.add(new Ghost(rectangle));
        }
    }

    public void moveObject(Player movable) {
        if (!collisionDetector.checkCollisionWithWalls(movable, movable.getNextDirection())) {
            movable.move();
        } else {
            if (!collisionDetector.checkCollisionWithWalls(movable, movable.getCurrentDirection())) {
                movable.move(movable.getCurrentDirection());
            }
        }
    }

    private void resetGame() {
        for (Ghost g : ghosts) {
            g.hide();
        }
        ghosts = new LinkedList<>();

        player.hide();

        init();
        start();
    }

}
