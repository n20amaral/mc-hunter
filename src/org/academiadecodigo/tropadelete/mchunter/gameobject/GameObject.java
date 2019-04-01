package org.academiadecodigo.tropadelete.mchunter.gameobject;


import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public abstract class GameObject {
    protected Rectangle rectangle;

    public GameObject(Rectangle rectangle) {
        this.rectangle = rectangle;
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

}
