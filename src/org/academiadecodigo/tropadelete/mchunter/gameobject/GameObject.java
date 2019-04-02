package org.academiadecodigo.tropadelete.mchunter.gameobject;

import org.academiadecodigo.simplegraphics.graphics.Shape;

public abstract class GameObject {
    private Shape shape;
    protected boolean hidden;

    public GameObject(Shape shape) {
        this.shape = shape;
    }

    public void hide() {
        shape.delete();
        hidden = true;
    }

    public int getX() {
        return shape.getX();
    }

    public int getY(){
        return shape.getY();
    }

    public int getWidth() {
        return shape.getWidth();
    }

    public int getHeigth(){
        return shape.getHeight();
    }

    public boolean isHidden() {
        return hidden;
    }
}
