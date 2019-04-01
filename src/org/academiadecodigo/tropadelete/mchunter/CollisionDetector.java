package org.academiadecodigo.tropadelete.mchunter;

import org.academiadecodigo.tropadelete.mchunter.gameobject.Wall;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Direction;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Ghost;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.MovableGameObject;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Player;

import static org.academiadecodigo.tropadelete.mchunter.GameSettings.*;

public class CollisionDetector {

    private Wall[][] walls;

    public CollisionDetector(Wall[][] walls) {
        this.walls = walls;
    }

    public boolean checkCollision(Player player, Ghost ghost) {
        return player.getX() < ghost.getX() + ghost.getWidth() &&
                player.getX() + player.getWidth() > ghost.getX() &&
                player.getY() < ghost.getY() + ghost.getHeigth() &&
                player.getY() + player.getHeigth() > ghost.getY();
    }

    public boolean checkWallCollision(MovableGameObject movable, Direction direction) {
        if (direction == null) {
            return true;
        }

        boolean detected = false;

        int col = getColFromX(movable.getX()) + direction.getdX();
        int row = getRowFromY(movable.getY()) + direction.getdY();

        detected = checkCollisionWithSingleWall(walls[row][col], movable, direction);

        int startX = getColFromX(movable.getX()) * CELL_SIZE + PADDING;
        int startY = getRowFromY(movable.getY()) * CELL_SIZE + PADDING;

        int adjCol = direction.getdY() != 0 && movable.getX() > startX ? col + 1 : col;
        int adjRow = direction.getdX() != 0 && movable.getY() > startY ? row + 1 : row;


        if (adjRow != row || adjCol != col) {
            detected = detected || checkCollisionWithSingleWall(walls[adjRow][adjCol], movable, direction);
        }

        return detected;
    }

    private boolean checkCollisionWithSingleWall(Wall wall, MovableGameObject movable, Direction direction) {
        if (wall == null) {
            return false;
        }

        switch (direction) {
            case LEFT:
                return movable.getX() + direction.getdX() < wall.getX() + wall.getWidth();
            case RIGHT:
                return movable.getX() + movable.getWidth() + direction.getdX() > wall.getX();
            case DOWN:
                return movable.getY() + movable.getHeigth() + direction.getdY() > wall.getY();
            case UP:
                return movable.getY() + direction.getdY() < wall.getY() + wall.getHeigth();
            default:
                return true;
        }
    }

    private int getColFromX(int x) {
        return (x - PADDING) / CELL_SIZE;
    }

    private int getRowFromY(int y) {
        return (y - PADDING) / CELL_SIZE;
    }
}
