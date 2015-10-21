package net.qrab.lmnotruckers.handlers;

public abstract class Config {

	public static final int APP_WIDTH = 480;
	public static final int APP_HEIGHT = 800;

	public static final int GAME_GRID = 9;
	public static final int GAME_GRID_SIZE = 32;

	public static final float GRID_OFFSET_X = -((APP_WIDTH / 2)-(GAME_GRID_SIZE*GAME_GRID/2));
	public static final float GRID_OFFSET_Y = -100;

	public static String[][] testMap = new String[][] {
			{"X", "X", "X", "X", "X", "X", "X", "X",  "X" },
			{"X", "ES", "EW", "EW", "ESW", "EW", "EW", "SW",  "X" },
			{"X", "NS", "X", "X", "NS", "X", "X", "NS",  "X" },
			{"X", "ENS", "EW", "EW", "NW", "X", "X", "NS",  "X" },
			{"X", "NS", "X", "X", "X", "X", "X", "NS",  "X" },
			{"X", "NS", "X", "X", "X", "X", "X", "NS",  "X" },
			{"X", "NS", "X", "X", "X", "X", "X", "NS",  "X" },
			{"X", "EN", "EW", "EW", "EW", "EW", "EW", "NW", "X" },
			{"X", "X", "X", "X", "X", "X", "X", "X",  "X" }
	};

}
