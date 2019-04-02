package org.academiadecodigo.tropadelete.mchunter;


import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.mchunter.gameobject.GameObjectFactory;
import org.academiadecodigo.tropadelete.mchunter.gameobject.Wall;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Ghost;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.MovableGameObject;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Player;

import java.util.List;

import static org.academiadecodigo.tropadelete.mchunter.Settings.Game.*;

public class Game {

    private GameObjectFactory gameObjectFactory = new GameObjectFactory();
    private Wall[][] walls;
    private List<Ghost> ghosts;
    private Player player;
    private CollisionDetector collisionDetector;
    private boolean gameOver;
    private boolean win;
    private boolean paused;
    private boolean restart;

    public void init() {
        loadBackground();
        loadGameObjects();
        collisionDetector = new CollisionDetector(walls);
        new KeyboardListener(this, player).init();
    }

    public void start() {
        int ghostsAlive = Settings.Ghost.GHOST_SPRITES.length;

        showInitialScreen();

        while (!gameOver) {
            if (paused) {
                sleep();
                continue;
            }


            moveGameObject(player);

            for (Ghost ghost : ghosts) {
                if (ghost.isHidden()) {
                    continue;
                }

                if (collisionDetector.checkCollision(player, ghost)) {
                    if (player.getCurrentDirection() == ghost.getCurrentDirection()) {
                        ghost.hide();
                        ghostsAlive--;
                        if (ghostsAlive == 0) {
                            win = true;
                            gameOver = true;
                            break;
                        }
                        continue;
                    }
                    gameOver = true;
                    break;
                }

                ghost.randomlyChangeDirection();
                moveGameObject(ghost);

                if (collisionDetector.checkCollision(player, ghost)) {
                    gameOver = true;
                    break;
                }
            }

            sleep();
        }

        end();
        start();
    }

    private void loadGameObjects() {
        walls = gameObjectFactory.createWalls();
        ghosts = gameObjectFactory.createGhosts();
        player = gameObjectFactory.createPlayer();
    }

    private void loadBackground() {


        Picture background = new Picture(PADDING, PADDING, BACKGROUND_IMG);
        background.draw();
        background.grow(PADDING * 4, PADDING * 4);
        Rectangle canvas = new Rectangle(PADDING, PADDING, GAME_SIZE * CELL_SIZE, GAME_SIZE * CELL_SIZE);
        canvas.setColor(BACKGROÛND_COLOR);
        canvas.fill();
    }

    private void showInitialScreen() {
        Picture title = new Picture((WINDOW_WIDTH - TITLE_WIDTH) / 2, TITLE_HEIGHT * 2, TITLE_IMG);
        Picture start = new Picture((WINDOW_WIDTH - START_WIDTH) / 2, (WINDOW_HEIGHT - START_HEIGHT) / 2, START_IMG);
        Picture intro = new Picture(0 - INTRO_WIDTH, WINDOW_HEIGHT - INTRO_HEIGHT * 2, INTRO_SPRITES[0]);

        boolean movingIntroToRight = true;


        title.draw();
        start.draw();

        while (player.getNextDirection() == null) {
            int dX = movingIntroToRight ? 1 : -1;

            if (movingIntroToRight && intro.getX() > WINDOW_WIDTH) {
                movingIntroToRight = false;
                intro.load(INTRO_SPRITES[1]);
                continue;
            }

            if (!movingIntroToRight && intro.getX() < 0 - INTRO_WIDTH) {
                movingIntroToRight = true;
                intro.load(INTRO_SPRITES[0]);
                continue;
            }

            intro.translate(dX, 0);
            intro.draw();
            sleep();
        }

        title.delete();
        start.delete();
        intro.delete();
    }

    private void moveGameObject(MovableGameObject object) {
        for (int i = 0; i < object.getSpeed(); i++) {
            if (object.isChangingDirection() && !collisionDetector.checkWallCollision(object, object.getNextDirection())) {
                object.moveToNextDirection();
            } else if (!collisionDetector.checkWallCollision(object, object.getCurrentDirection())) {
                object.move();
            }
        }
    }

    private void end() {
        String imgPath = win ? WIN_IMG : GAME_OVER_IMG;
        int imgHeight = win ? WIN_HEIGHT : GAME_OVER_HEIGHT;
        int imgWidth = win ? WIN_WIDTH : GAME_OVER_WIDTH;
        Picture endImg = new Picture((WINDOW_WIDTH - imgWidth) / 2, (WINDOW_HEIGHT - imgHeight) / 2, imgPath);
        endImg.draw();

        while (!restart) {
            sleep();
        }

        reset();
        endImg.delete();
    }

    private void reset() {
        player.reset();
        for (Ghost ghost : ghosts) {
            ghost.reset();
        }

        restart = false;
        win = false;
        gameOver = false;

        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switchPause() {
        this.paused = !this.paused;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setRestart(boolean restart) {
        this.restart = restart;
    }
}
