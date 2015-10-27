package net.qrab.lmnotruckers.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.utils.Array;
import net.qrab.lmnotruckers.actors.Vehicle;

public abstract class Util {

	public static OrthographicCamera mapCam;
	public static TiledMap           roadMap;
	public static TiledMapRenderer   mapRenderer;
	public static TiledMapTileLayer  mapLayer;

	static {
		mapCam = new OrthographicCamera();
		mapCam.setToOrtho(false, Config.APP_WIDTH, Config.APP_HEIGHT);
		mapCam.update();
		loadMap("road/9x9.tmx");
	}


	public static void moveTo(Vehicle vehicle, Config.Direction direction) {
		//if(direction==null) return;
		vehicle.addAction(Actions.sequence(
				rotateToFaceDirection(vehicle,direction),
				moveInDirection(vehicle,direction)
				//Util.completeAction
		));
	}

	public static Action rotateToFaceDirection(Vehicle vehicle, Config.Direction direction) {
		float angle = Config.directionToAngle.get(direction);
		RotateToAction action = new RotateToAction();
		action.setRotation(angle);
		action.setDuration(Math.abs(vehicle.getRotation()-angle)<90?0:0.25f);
		return action;
	}

	public static Action moveInDirection(Vehicle vehicle, Config.Direction direction) {
		Vector2 moveVector = Config.directionToVector2.get(direction);
		int[] p = ((Vehicle) vehicle).gridPosition();
		MoveToAction action = new MoveToAction();
		action.setPosition(p[0] + moveVector.x, p[1] + moveVector.y);
		action.setDuration(0.25f);
		return action;
	}

	public static Action goToAction(Vehicle vehicle, Config.Direction direction) {
		final Action moveToAction = moveInDirection(vehicle,direction);
		final Action rotateAction = rotateToFaceDirection(vehicle,direction);
		Action action = new Action() {
			@Override
			public boolean act(float delta) {
				boolean ret = true;
				ret &= moveToAction.act(delta);
				ret &= rotateAction.act(delta);
				return ret;
			}
			@Override
			public void setActor(Actor actor) {
			}
		};
		return action;
	}

	//TODO: is this done?
	public static Config.Direction nextMove(Vehicle v) {
		int[] p = v.gridPosition();
		Array<Config.Direction> myOptions = Util.getMovementOptions(p[0], p[1]);

		// if only one way out
		if(myOptions.size==1) return myOptions.first();

		for(Config.Behavior b : v.moves) {
			Config.Direction d = behaviorToDirection(v.getFacing(),b);
			if(myOptions.contains(d,true)) return d;
		}



		return v.getFacing();
	}

	public static Array<Config.Direction> getMovementOptions(int col, int row) {
		String s_options = getTileName(mapLayer, col, row);
		Array<Config.Direction> ret = new Array<Config.Direction>();
		char c='X';
		for(int i=0 ; i<s_options.length() ; i++) {
			c=s_options.charAt(i);
			if(c=='E') ret.add(Config.Direction.E);
			if(c=='N') ret.add(Config.Direction.N);
			if(c=='S') ret.add(Config.Direction.S);
			if(c=='W') ret.add(Config.Direction.W);
		}
		return ret;
	}

	public static Config.Direction opp(Config.Direction d) {
		if(d== Config.Direction.N) return Config.Direction.S;
		if(d== Config.Direction.S) return Config.Direction.N;
		if(d== Config.Direction.E) return Config.Direction.W;
		if(d== Config.Direction.W) return Config.Direction.E;
		return d;
	}

	//give the ENSW direction based on facing direction and LRS behavior
	public static Config.Direction behaviorToDirection(Config.Direction facing, Config.Behavior b) {
		Config.Direction ret = facing;
		if(b!= Config.Behavior.STRAIGHT) {
			if (facing == Config.Direction.N) ret = Config.Direction.W;
			if (facing == Config.Direction.S) ret = Config.Direction.E;
			if (facing == Config.Direction.E) ret = Config.Direction.N;
			if (facing == Config.Direction.W) ret = Config.Direction.S;
			if (b == Config.Behavior.RIGHT) ret = opp(ret);
		}
		return ret;
	}

	//TODO: this, low priority
	public static float rotateShortest(float startAngle, float finishAngle) {
		return 0;
	}

	public static Action completeAction = new Action(){
		public boolean act( float delta ) {
			System.out.println("i'm done!  wipe me!");
			return true;
		}
	};

	public static void loadMap(String mapfile) {
		roadMap      = new TmxMapLoader().load(mapfile);
		mapRenderer  = new OrthogonalTiledMapRenderer(roadMap,1/32f);
		mapLayer     = (TiledMapTileLayer) roadMap.getLayers().get("map");
	}

	public static String getTileProperty(TiledMapTile tile, String key) {
		return tile.getProperties().get(key).toString();
	}

	public static TiledMapTile getTile(TiledMapTileLayer layer,  int col, int row) {
		return layer.getCell(col, row).getTile();
	}

	public static String getTileName(TiledMapTileLayer layer,  int col, int row) {
		return getTileProperty(getTile(layer, col, row), "name");
	}


}
