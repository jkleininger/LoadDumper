package net.qrab.lmnotruckers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import net.qrab.lmnotruckers.screens.PlayScreen;

public class ElementalTruckersGame extends Game {

	@Override
	public void create() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		setScreen(new PlayScreen(this));
	}

	@Override
	public void resize(int width, int height) {
		getScreen().resize(width,height);
	}

	@Override
	public void render() {
		super.render();
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(Gdx.graphics.getDeltaTime());
	}

	private void update(float delta) {
		//
		this.getScreen().render(delta);
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}

}
