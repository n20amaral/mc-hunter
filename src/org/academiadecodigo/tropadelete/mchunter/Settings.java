package org.academiadecodigo.tropadelete.mchunter;

public class Settings {

    public static class Game {

        public static final String MAP_PATH = "resources/maps/1.txt";
        public static final int GAME_SIZE = 25;
        public static final int CELL_SIZE = 30;
        public static final int PADDING = 10;
    }

    public static class Player {
        public static final int PLAYER_SPEED = 2;
    }

    public static class Ghost {
        public static final int GHOST_SPEED = 1;
        public static final double GHOST_CHANGE_DIRECTION_PROB = 0.1;
        public static final double GHOST_CANCEL_DIRECTION_PROB = 0.2;
        public static final String[] GHOST_SPRITES = {
                "Francisco.png",
                "Ruben.png",
                "Rudy.png",
                "Seringas.png",
                "Rui.png",
                "Nuno.png"
        };
    }


}
