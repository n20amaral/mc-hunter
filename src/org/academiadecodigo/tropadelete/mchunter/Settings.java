package org.academiadecodigo.tropadelete.mchunter;

public class Settings {

    public static class Game {

        public static final String MAP_PATH = "resources/maps/1.txt";
        public static final int GAME_SIZE = 25;
        public static final int CELL_SIZE = 30;
        public static final int PADDING = 10;
        public static final int GAME_WIDTH = GAME_SIZE * CELL_SIZE + PADDING;
        public static final int GAME_HEIGHT = GAME_SIZE * CELL_SIZE + PADDING;

        public static final String GAME_OVER_IMG = "resources/texts/gameover.png";
        public static final int GAME_OVER_WIDTH = 225;
        public static final int GAME_OVER_HEIGHT = 118;

        public static final String WIN_IMG = "resources/texts/win.png";
        public static final int WIN_WIDTH = 313;
        public static final int WIN_HEIGHT = 232;
    }

    public static class Player {
        public static final int PLAYER_SPEED = 2;
        public static final int PLAYER_X = 12 * Game.CELL_SIZE + Game.PADDING;
        public static final int PLAYER_Y = 9 * Game.CELL_SIZE + Game.PADDING;
    }

    public static class Ghost {
        public static final int GHOST_SPEED = 1;
        public static final int GHOST_X = 6 * Game.CELL_SIZE + Game.PADDING;
        public static final int GHOST_Y = 12 * Game.CELL_SIZE + Game.PADDING;
        public static final double GHOST_CHANGE_DIRECTION_PROB = 0.05;
        public static final double GHOST_CANCEL_DIRECTION_PROB = 0.15;
        public static final String[] GHOST_SPRITES = {
                "Francisco.png",
//                "Ruben.png",
//                "Rudy.png",
//                "Seringas.png",
//                "Rui.png",
                "Nuno.png"
        };
    }


}
