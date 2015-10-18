package net.qrab.loaddumper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import net.qrab.loaddumper.screens.PlayScreen;

public class LoadDumperGame extends Game {

	Screen screen;

	@Override
	public void create () {
		screen = new PlayScreen();
		this.setScreen(screen);
	}

	@Override
	public void render () {
		super.render();
		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void update(float delta) {
		screen.render(delta);
	}
}
