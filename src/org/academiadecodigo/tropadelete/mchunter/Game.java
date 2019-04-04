package org.academiadecodigo.tropadelete.mchunter;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.tropadelete.mchunter.gameobject.GameObjectFactory;
import org.academiadecodigo.tropadelete.mchunter.gameobject.Wall;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Direction;
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
        new KeyboardListener(this).init();
    }

    public void start() {
        int ghostsAlive = Settings.Ghost.GHOST_SPRITES.length;

        showInitialScreen();

        Sound sound = new Sound(BACKGROUND_SOUND);
        Sound ping = new Sound(PING_SOUND);

        sound.setLoop(-1);
        sound.play(true);
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
                        ping.play(true);
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
                    for (int i = 0; i < CELL_SIZE / 6; i++) {
                        ghost.move();
                    }
                    gameOver = true;
                    break;
                }
            }

            sleep();
        }
        sound.stop();
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

    public void handleKeyPressed(int key) {
        switch (key) {
            case KeyboardEvent.KEY_Q:
                System.exit(1);
                break;
            case KeyboardEvent.KEY_P:
                if (!gameOver) {
                    paused = !paused;
                }
                break;
            case KeyboardEvent.KEY_R:
                if (gameOver) {
                    restart = true;
                }
                break;
            default:
                if (!paused) {
                    Direction direction = Direction.getDirectionByKey(key);
                    player.changeDirection(direction);
                }
                break;
        }
    }

    public void handleKeyReleased(int key) {
        player.cancelChangeDirection();
    }

    private void showInitialScreen() {
        Sound sound = new Sound(INTRO_SOUND);
        Picture title = new Picture((WINDOW_WIDTH - TITLE_WIDTH) / 2, TITLE_HEIGHT * 2, TITLE_IMG);
        Picture start = new Picture((WINDOW_WIDTH - START_WIDTH) / 2, WINDOW_HEIGHT - WINDOW_HEIGHT / 3, START_IMG);
        Picture intro = new Picture(0 - INTRO_WIDTH, WINDOW_HEIGHT - INTRO_HEIGHT * 2, INTRO_SPRITES[0]);
        boolean movingIntroToRight = true;

        sound.play(true);

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
        sound.stop();
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
        String soundPath = win ? WIN_SOUND : GAME_OVER_SOUND;
        String imgPath = win ? WIN_IMG : GAME_OVER_IMG;
        int imgHeight = win ? WIN_HEIGHT : GAME_OVER_HEIGHT;
        int imgWidth = win ? WIN_WIDTH : GAME_OVER_WIDTH;
        Picture endImg = new Picture((WINDOW_WIDTH - imgWidth) / 2, (WINDOW_HEIGHT - imgHeight) / 2, imgPath);
        Sound sound = new Sound(soundPath);

        sound.setLoop(-1);
        sound.play(true);
        endImg.draw();

        while (!restart) {
            sleep();
        }

        reset();
        endImg.delete();
        sound.stop();
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
}
