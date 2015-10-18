package net.qrab.loaddumper.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import net.qrab.loaddumper.handlers.Config;
import net.qrab.loaddumper.stages.GridStage;

public class PlayScreen implements Screen {

	private OrthographicCamera cam      = new OrthographicCamera(Config.APP_WIDTH, Config.APP_HEIGHT);
	private ExtendViewport     viewport = new ExtendViewport(Config.APP_WIDTH, Config.APP_HEIGHT, cam);
	private Stage              stage    = new GridStage(viewport);


	@Override
	public void show() {
		System.out.println("show");
	}

	@Override
	public void render(float delta) {
		//if(stage!=null)
			stage.draw();
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
