package net.qrab.lmnotruckers.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import net.qrab.lmnotruckers.handlers.Config;
import net.qrab.lmnotruckers.stages.GridStage;

public class PlayScreen implements Screen {

	private ExtendViewport     viewport  = new ExtendViewport(Config.APP_WIDTH, Config.APP_HEIGHT);
	private GridStage          gridStage = new GridStage(viewport);
	private Game               game;

	public PlayScreen(final Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	@Override
	public void show() {
		gridStage.init();
	}

	@Override
	public void render(float delta) {
		gridStage.draw();
	}

	@Override
	public void resize(int width, int height) {
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
