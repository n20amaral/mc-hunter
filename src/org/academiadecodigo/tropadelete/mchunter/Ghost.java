package org.academiadecodigo.tropadelete.mchunter;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Ghost extends Player {
    public Ghost(Rectangle rectangle) {
        super(rectangle);
    }

    public void hide() {
        super.rectangle.delete();
    }
}
