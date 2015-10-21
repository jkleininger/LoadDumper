package net.qrab.lmnotruckers.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.qrab.lmnotruckers.handlers.Config;

public class GridStage extends Stage {

	private TiledMap           tiledMap;
	private OrthographicCamera camera;
	private TiledMapRenderer   tiledMapRenderer;
	private TiledMapTileLayer  layer;

	private int cols;
	private int rows;

	private int xOffset = -100;
	private int yOffset = -100;

	public GridStage() {
		super();
	}

	public GridStage(Viewport viewport) {
		super(viewport);
	}

	public GridStage(Viewport viewport, Batch batch) {
		super(viewport,batch);
	}

	public void init() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Config.APP_WIDTH, Config.APP_HEIGHT);
		camera.translate(xOffset, yOffset);
		camera.update();

		tiledMap = new TmxMapLoader().load("maps/11x11.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);

		layer = (TiledMapTileLayer) tiledMap.getLayers().get("map");

		cols = layer.getWidth();
		rows = layer.getHeight();

	}

	private boolean canMove(char c, int col, int row) {
		String moves = getTileProperty(layer, col, row, "name");
		if(moves.indexOf(c)>=0) return true;
		return false;
	}

	private String getTileProperty(TiledMapTileLayer layer,  int col, int row, String property) {
		Object ret = layer.getCell(col, row).getTile().getProperties().get(property);
		if(ret!=null) {
			return ret.toString();
		}
		return "";
	}

	@Override
	public void draw() {
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

	}

}
