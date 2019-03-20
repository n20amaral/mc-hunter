package org.academiadecodigo.tropadelete.mchunter;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class CollisionDetector {
    private Rectangle[][] walls;

    public CollisionDetector(Rectangle[][] walls) {
        this.walls = walls;
    }

    public boolean checkCollisionWithWalls(Movable movable, Direction direction) {
        if (direction == null) {
            return true;
        }

        boolean detected = false;

        int col = movable.getCol() + direction.getdX();
        int row = movable.getRow() + direction.getdY();

        detected = checkCollisionWithSingleWall(walls[row][col], movable, direction);


        int startX = movable.getCol() * Game.CELL_SIZE + Game.PADDING;
        int startY = movable.getRow() * Game.CELL_SIZE + Game.PADDING;

        int adjX = movable.getNextDirection().getdY() != 0 && movable.getX() > startX ? col + 1 : col;
        int adjY = movable.getNextDirection().getdX() != 0 && movable.getY() > startY ? row + 1 : row;


        if (adjY != row || adjX != col) {
            detected = detected || checkCollisionWithSingleWall(walls[adjY][adjX], movable, direction);
        }

        return detected;
    }

    private boolean checkCollisionWithSingleWall(Rectangle wall, Movable movable, Direction direction) {
        if (wall == null) {
            return false;
        }


        switch (direction) {
            case LEFT:
                return movable.getX() - movable.getSpeed() < wall.getX() + Game.CELL_SIZE;
            case RIGHT:
                return movable.getX() + Game.CELL_SIZE + movable.getSpeed() > wall.getX();
            case DOWN:
                return movable.getY() + Game.CELL_SIZE + movable.getSpeed() > wall.getY();
            case UP:
                return movable.getY() - movable.getSpeed() < wall.getY() + Game.CELL_SIZE;
            default:
                return true;
        }
    }
}
