package net.qrab.lmnotruckers.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import net.qrab.lmnotruckers.actors.Vehicle;
import net.qrab.lmnotruckers.handlers.Config;
import net.qrab.lmnotruckers.handlers.Util;

public class PlayScreen implements Screen {

	private ExtendViewport     viewport  = new ExtendViewport(Config.APP_WIDTH, Config.APP_HEIGHT);
	private Game               game;
	private Stage              vehicleStage;
	private SpriteBatch        batch;

	public PlayScreen(final Game game) {
		this.game = game;
		vehicleStage = new Stage(viewport);
		vehicleStage.addActor(new Vehicle());
		batch = new SpriteBatch();
		batch.setProjectionMatrix(Util.mapCam.combined);
		Util.loadTiles();
	}

	public void touch(float x, float y) {
		vehicleStage.getActors().peek().addAction(Actions.moveTo(x, y, 1f));
	}


	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		batch.begin();
		for(int r = 0 ; r<9 ; r++) {
			for(int c=0 ; c<9 ; c++) {
				batch.draw(Util.getTile(Config.testMap[8-r][c]),c*32, r*32);
			}
		}
		batch.end();

		vehicleStage.act(delta);
		vehicleStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		//
		viewport.update(width,height);
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

	}
}
