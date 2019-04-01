package org.academiadecodigo.tropadelete.mchunter.gameobject;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public abstract class GameObject {
    protected Rectangle rectangle;
    protected boolean hidden;

    public GameObject(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void hide() {
        rectangle.delete();
        hidden = true;
    }

    public int getX() {
        return rectangle.getX();
    }

    public int getY(){
        return rectangle.getY();
    }

    public int getWidth() {
        return rectangle.getWidth();
    }

    public int getHeigth(){
        return rectangle.getHeight();
    }

    public boolean isHidden() {
        return hidden;
    }
}
