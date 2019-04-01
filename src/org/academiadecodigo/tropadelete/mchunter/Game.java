package org.academiadecodigo.tropadelete.mchunter;


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
    private boolean paused;
    private boolean restart;

    public void init() {
        loadBackground();
        loadGameObjects();
        collisionDetector = new CollisionDetector(walls);
        new KeyboardListener(this, player).init();
    }

    public void start() {
        while (!gameOver) {
            if (paused) {
                sleep();
                continue;
            }

            moveGameObject(player);

            for (Ghost ghost : ghosts) {
                if(collisionDetector.checkCollision(player, ghost)) {
                    gameOver = true;
                    break;
                }

                ghost.randomlyChangeDirection();
                moveGameObject(ghost);

                if(collisionDetector.checkCollision(player, ghost)) {
                    gameOver = true;
                    break;
                }
            }

            sleep();
        }

        showGameOver();
        start();
    }

    private void loadGameObjects() {
        walls = gameObjectFactory.createWalls();
        ghosts = gameObjectFactory.createGhosts();
        player = gameObjectFactory.createPlayer();
    }

    private void loadBackground() {
        Rectangle canvas = new Rectangle(PADDING, PADDING, GAME_SIZE * CELL_SIZE, GAME_SIZE * CELL_SIZE);
        canvas.setColor(Color.BLACK);
        canvas.fill();
    }

    private void moveGameObject(MovableGameObject object) {
        for (int i = 0; i < object.getSpeed(); i++) {
            if (object.isChangingDirection() && !collisionDetector.checkWallCollision(object, object.getNextDirection())) {
                object.moveToNextDirection();
            } else if(!collisionDetector.checkWallCollision(object, object.getCurrentDirection())) {
                object.move();
            }
        }
    }

    private void showGameOver() {
        Picture gameOver = new Picture((GAME_WIDTH - GAME_OVER_WIDTH) / 2, (GAME_HEIGHT - GAME_OVER_HEIGHT) / 2, GAME_OVER_IMG);
        gameOver.draw();
        while (!restart) {
            sleep();
        }
        reset();
        gameOver.delete();
    }

    private void reset() {
        player.reset();
        for (Ghost ghost : ghosts) {
            ghost.reset();
        }

        sleep();
        restart = false;
        gameOver = false;
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
