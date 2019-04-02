package org.academiadecodigo.tropadelete.mchunter;

import org.academiadecodigo.simplegraphics.graphics.Color;

public class Settings {

    public static class Game {

        public static final String MAP_PATH = "resources/maps/1.txt";
        public static final int GAME_SIZE = 25;
        public static final int CELL_SIZE = 30;
        public static final int PADDING = 50;
        public static final int WINDOW_WIDTH = GAME_SIZE * CELL_SIZE + PADDING * 2;
        public static final int WINDOW_HEIGHT = GAME_SIZE * CELL_SIZE + PADDING * 2;

        public static final String GAME_OVER_IMG = "resources/texts/gameover.png";
        public static final int GAME_OVER_WIDTH = 225;
        public static final int GAME_OVER_HEIGHT = 118;

        public static final String WIN_IMG = "resources/texts/win.png";
        public static final int WIN_WIDTH = 313;
        public static final int WIN_HEIGHT = 232;

        public static final String TITLE_IMG = "resources/texts/title.png";
        public static final int TITLE_WIDTH = 500;
        public static final int TITLE_HEIGHT = 73;

        public static final String START_IMG = "resources/texts/start.png";
        public static final int START_WIDTH = 300;
        public static final int START_HEIGHT = 63;

        public static final String[] INTRO_SPRITES = {
                "resources/sprites/intro/pacman_eating.png",
                "resources/sprites/intro/pacman_running.png"
        };
        public static final int INTRO_WIDTH = 350;
        public static final int INTRO_HEIGHT = 70;

        public static final String BACKGROUND_IMG = "resources/maps/background.jpg";
        public static final Color BACKGROÛND_COLOR = new Color(41, 191, 161);
        public static final Color WALL_COLOR = new Color(55, 28, 71);
    }

    public static class Player {
        public static final int PLAYER_SPEED = 2;
        public static final int PLAYER_X = 12 * Game.CELL_SIZE + Game.PADDING;
        public static final int PLAYER_Y = 9 * Game.CELL_SIZE + Game.PADDING;
        public static final String[] PLAYER_SPRITES = {
                "resources/sprites/player/left.png",
                "resources/sprites/player/up.png",
                "resources/sprites/player/right.png",
                "resources/sprites/player/down.png"
        };
    }

    public static class Ghost {
        public static final int GHOST_SPEED = 1;
        public static final int GHOST_X = 6 * Game.CELL_SIZE + Game.PADDING;
        public static final int GHOST_Y = 12 * Game.CELL_SIZE + Game.PADDING;
        public static final double GHOST_CHANGE_DIRECTION_PROB = 0.05;
        public static final double GHOST_CANCEL_DIRECTION_PROB = 0.15;

        private static final String[] xico = {
                "resources/sprites/ghost/xico.png",
                "resources/sprites/ghost/xico.png",
                "resources/sprites/ghost/xico.png",
                "resources/sprites/ghost/xico.png"
        };

        private static final String[] nuno = {
                "resources/sprites/ghost/nuno.png",
                "resources/sprites/ghost/nuno.png",
                "resources/sprites/ghost/nuno.png",
                "resources/sprites/ghost/nuno.png"
        };

        private static final String[] jojo = {
                "resources/sprites/ghost/jojo.png",
                "resources/sprites/ghost/jojo.png",
                "resources/sprites/ghost/jojo.png",
                "resources/sprites/ghost/jojo.png"
        };
        private static final String[] rudy = {
                "resources/sprites/ghost/rudy.png",
                "resources/sprites/ghost/rudy.png",
                "resources/sprites/ghost/rudy.png",
                "resources/sprites/ghost/rudy.png"
        };

        private static final String[] seringas = {
                "resources/sprites/ghost/seringas.png",
                "resources/sprites/ghost/seringas.png",
                "resources/sprites/ghost/seringas.png",
                "resources/sprites/ghost/seringas.png"
        };

        private static final String[] ruben = {
                "resources/sprites/ghost/ruben.png",
                "resources/sprites/ghost/ruben.png",
                "resources/sprites/ghost/ruben.png",
                "resources/sprites/ghost/ruben.png",
        };

        public static final String[][] GHOST_SPRITES = {xico, nuno, jojo, rudy, seringas, ruben};

    }


}
