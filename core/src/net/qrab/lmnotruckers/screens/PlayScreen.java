package net.qrab.lmnotruckers.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import net.qrab.lmnotruckers.actors.Vehicle;
import net.qrab.lmnotruckers.handlers.Config;
import net.qrab.lmnotruckers.handlers.Util;

public class PlayScreen implements Screen {

	private Game               game;
	private Stage              vehicleStage;
	private SpriteBatch        batch;
	private Camera             cam;

	private float              aspect;

	public PlayScreen(final Game game) {
		this.game = game;
		vehicleStage = new Stage();
		vehicleStage.addActor(new Vehicle());
		batch = new SpriteBatch();
		Util.loadTiles();
		vehicleStage.getActors().peek().setPosition(0,5);
	}

	public void touch(float x, float y) {
		//
		Vector2 scaled = vehicleStage.getViewport().unproject(new Vector2(x,y));
		System.out.println("touch: " + scaled.x + "," + scaled.y);
		//vehicleStage.getActors().peek().addAction(Actions.moveTo(x, y, 1f));
	}


	@Override
	public void show() {
		//
		//
	}

	@Override
	public void render(float delta) {
		if(Gdx.input.justTouched()) touch(Gdx.input.getX(),  Gdx.input.getY());

		//batch.setProjectionMatrix(cam.combined);
		batch.begin();
		for(int r = 0 ; r<9 ; r++) {
			for(int c=0 ; c<9 ; c++) {
				batch.draw(
						Util.getTile(Config.testMap[8-r][c]),
						(float)c-4.5f,(float)r+11,1,1
				);
			}
		}
		batch.end();

		vehicleStage.act(delta);
		vehicleStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		aspect = (float)width / (float)height;
		float newwd, newht;
		if(aspect>0.6f) {
			newht = Config.VIRTUAL_HEIGHT;
			newwd = (float)width * newht / (float)height;
		} else {
			newwd = Config.VIRTUAL_WIDTH;
			newht = (float)height * newwd / (float)width;
		}
		cam = new OrthographicCamera(newwd,newht);
		cam.translate(0, newht / 2, 0);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		vehicleStage.setViewport(new FitViewport(newwd, newht, cam));
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
		batch.dispose();
		vehicleStage.dispose();
	}


}
