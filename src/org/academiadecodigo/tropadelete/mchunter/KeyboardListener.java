package org.academiadecodigo.tropadelete.mchunter;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Direction;
import org.academiadecodigo.tropadelete.mchunter.gameobject.movable.Player;

public class KeyboardListener implements KeyboardHandler {

    Player player;

    public KeyboardListener(Player player) {
        this.player = player;
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
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

//        if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) {
//            player.setReborn(true);
//            return;
//        }

        Direction direction = Direction.getDirectionByKey(keyboardEvent.getKey());
        player.changeDirection(direction);
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        player.cancelChangeDirection();
    }
}
