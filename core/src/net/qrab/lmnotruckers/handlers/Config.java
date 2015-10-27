package net.qrab.lmnotruckers.handlers;

import com.badlogic.gdx.math.Vector2;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public abstract class Config {

	public static final int   APP_WIDTH      = 480;
	public static final int   APP_HEIGHT     = 800;

	public static final float VIRTUAL_WIDTH  = 12;
	public static final float VIRTUAL_HEIGHT = 20;

	public static final int   GAME_GRID      = 9;
	public static final int   GAME_GRID_SIZE = 32;

	public static final float GRID_OFFSET_X  = -((APP_WIDTH / 2)-(GAME_GRID_SIZE*GAME_GRID/2));
	public static final float GRID_OFFSET_Y  = -100;


	public enum Direction {
		N, S, E, W, NE, NW, SE, SW
	}

	public enum Behavior {
		LEFT, RIGHT, STRAIGHT
	}

	public static final int THETA_NORTH = 0;
	public static final int THETA_WEST  = 90;
	public static final int THETA_SOUTH = 180;
	public static final int THETA_EAST  = 270;

	public static final Map<Direction, Integer> directionToAngle;
	static {
		directionToAngle = Collections.synchronizedMap(
				new EnumMap<Direction,Integer>(Direction.class)
		);
		directionToAngle.put(Direction.N,THETA_NORTH);
		directionToAngle.put(Direction.W,THETA_WEST);
		directionToAngle.put(Direction.S,THETA_SOUTH);
		directionToAngle.put(Direction.E,THETA_EAST);
	}

	public static final Map<Direction, Vector2> directionToVector2;
	static {
		directionToVector2 = Collections.synchronizedMap(
				new EnumMap<Direction, Vector2>(Direction.class)
		);
		directionToVector2.put(Direction.N, new Vector2( 0, 1));
		directionToVector2.put(Direction.W, new Vector2(-1, 0));
		directionToVector2.put(Direction.S, new Vector2( 0,-1));
		directionToVector2.put(Direction.E, new Vector2( 1, 0));
	}



	//public static final Map<Facing,int[]> adjacent = new Map<Facing, int[]>();

}
