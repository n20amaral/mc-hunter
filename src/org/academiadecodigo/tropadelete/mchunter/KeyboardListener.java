package org.academiadecodigo.tropadelete.mchunter;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardListener implements KeyboardHandler {

    Player player;

    public KeyboardListener(Player player) {
        this.player = player;
    }

    public void init() {
        Keyboard keyboard = new Keyboard(this);
        for (Direction direction : Direction.values()) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKey(direction.getKey());
            event.setKeyboardEventType(direction.getEventType());
            keyboard.addEventListener(event);
            KeyboardEvent keyRelease = new KeyboardEvent();
            keyRelease.setKey(direction.getKey());
            keyRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
            keyboard.addEventListener(keyRelease);
        }

        KeyboardEvent r = new KeyboardEvent();
        r.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        r.setKey(KeyboardEvent.KEY_R);
        keyboard.addEventListener(r);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) {
            player.setReborn(true);
            return;
        }

        Direction direction = Direction.getDirectionByKey(keyboardEvent.getKey());
        player.changeDirection(direction);
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        player.cancelChangeDirection();
    }
}
