package net.qrab.lmnotruckers.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public abstract class Util {

	public static TiledMap           roadMap;
	public static TiledMapRenderer   roadRenderer;
	public static TiledMapTileLayer  mapLayer;
	public static OrthographicCamera mapCam;

	public static TextureAtlas       roadTilesAtlas;

	static {
		mapCam = new OrthographicCamera();
		mapCam.setToOrtho(false, Config.APP_WIDTH, Config.APP_HEIGHT);
		//mapCam.translate(Config.GRID_OFFSET_X, Config.GRID_OFFSET_Y);
		mapCam.update();
	}

	public static void loadTiles() {
		roadTilesAtlas = new TextureAtlas(Gdx.files.internal("32/r32.pack"));
	}

	public static void loadMap(String mapfile) {
		roadMap      = new TmxMapLoader().load(mapfile);
		roadRenderer = new OrthogonalTiledMapRenderer(roadMap);
		mapLayer     = (TiledMapTileLayer) roadMap.getLayers().get("map");

	}

	public static void moveActorTo(Actor actor, float x, float y) {
		MoveToAction moveToAction = new MoveToAction();
		moveToAction.setPosition(x,y);
		moveToAction.setDuration(1f);
		actor.addAction(moveToAction);
	}

	public static Vector3 fixCoords(float x, float y) {
		return mapCam.unproject(new Vector3(x,y,0));
	}

	public static TextureRegion getTile(String name) {
		return Util.roadTilesAtlas.findRegion(name);
	}



}
