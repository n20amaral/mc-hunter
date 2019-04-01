package org.academiadecodigo.tropadelete.mchunter;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
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

    public void init() {
        loadBackground();
        loadGameObjects();
        collisionDetector = new CollisionDetector(walls);
        new KeyboardListener(player).init();
    }

    public void start() {
        while (!gameOver) {
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

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        gameOver();
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

    private void gameOver() {

    }
}
