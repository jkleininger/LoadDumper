package net.qrab.lmnotruckers.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import net.qrab.lmnotruckers.actors.Vehicle;
import net.qrab.lmnotruckers.handlers.Config;
import net.qrab.lmnotruckers.handlers.InProc;
import net.qrab.lmnotruckers.handlers.Util;

public class PlayScreen implements Screen {

	private Game game;

	private Stage   mapStage;
	private OrthographicCamera  mapCamera;
	private Batch   mapBatch;

	private Array<Vehicle> vehicles;

	//private TextButton debugButton;

	public PlayScreen(final Game game) {
		this.game = game;
		mapCamera = new OrthographicCamera(9,9);
		mapBatch  = new SpriteBatch();

	}

	@Override
	public void show() {
		mapStage = new Stage(new FitViewport(11,11, mapCamera), mapBatch);
		mapCamera.translate(-1, -1, 0);
	}

	private void checkInput() {
		if(InProc.justTapped) {
			InProc.justTapped=false;
			Vector2 tap = stageToGrid(InProc.tapx, InProc.tapy);

			mapStage.addActor(new Vehicle((int)tap.x, (int)tap.y));

		}
	}

	private Vector2 stageToGrid(Vector2 loc) {
		return stageToGrid(loc.x, loc.y);
	}
	private Vector2 stageToGrid(float x, float y) {
		Vector2 ret = mapStage.getViewport().unproject(new Vector2(x, y));
		ret.set((int) Math.floor(ret.x), (int) Math.floor(ret.y));
		return ret;
	}

	@Override
	public void render(float delta) {
		checkInput();

		mapStage.getViewport().apply();
		mapStage.act(delta);
		Util.mapRenderer.setView(mapCamera);
		Util.mapRenderer.render();
		mapStage.draw();
	}

	private int[] adjacent(Config.Direction facing) {
		switch(facing) {
			case W:
				return new int[] { -1,  0};
			case E:
				return new int[] {  1,  0};
			case N:
				return new int[] {  0,  1};
			case S:
				return new int[] {  0, -1};
		}
		return new int[] {0,0};
	}
/*
	private boolean canMove(int col,  int row, Config.Direction d) {
		String possibilites = Config.testMap[row][col];
		switch(d) {
			case W:
				return possibilites.indexOf('W')>=0;
			case E:
				return possibilites.indexOf('E')>=0;
			case N:
				return possibilites.indexOf('N')>=0;
			case S:
				return possibilites.indexOf('S')>=0;
		}
		return false;
	}
*/
	@Override
	public void resize(int width, int height) {
		mapStage.getViewport().update(width, height, false);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		mapBatch.dispose();
		mapStage.dispose();
	}


}
