package org.academiadecodigo.tropadelete.mchunter;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Direction;

public class KeyboardListener implements KeyboardHandler {

    private Game game;

    public KeyboardListener(Game game) {
        this.game = game;
    }

    public void init() {
        Keyboard keyboard = new Keyboard(this);
        for (Direction direction : Direction.values()) {
            KeyboardEvent keyPressedEvent = new KeyboardEvent();
            keyPressedEvent.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyPressedEvent.setKey(direction.getKey());

            KeyboardEvent keyReleasedEvent = new KeyboardEvent();
            keyReleasedEvent.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
            keyPressedEvent.setKey(direction.getKey());

            keyboard.addEventListener(keyPressedEvent);
            keyboard.addEventListener(keyReleasedEvent);
        }

        KeyboardEvent reset = new KeyboardEvent();
        reset.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        reset.setKey(KeyboardEvent.KEY_R);
        keyboard.addEventListener(reset);

        KeyboardEvent pause = new KeyboardEvent();
        pause.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        pause.setKey(KeyboardEvent.KEY_P);
        keyboard.addEventListener(pause);

        KeyboardEvent quit = new KeyboardEvent();
        quit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        quit.setKey(KeyboardEvent.KEY_Q);
        keyboard.addEventListener(quit);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
       game.handleKeyPressed(keyboardEvent.getKey());

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        game.handleKeyReleased(keyboardEvent.getKey());
    }
}
