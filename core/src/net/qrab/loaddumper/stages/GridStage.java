package net.qrab.loaddumper.stages;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.qrab.loaddumper.handlers.Config;

public class GridStage extends Stage{

	private ShapeRenderer renderer = new ShapeRenderer();
	private Viewport      viewport;
	private Batch         batch;

	public GridStage() {
		super();
		System.out.println("new");
	}

	public GridStage(Viewport viewport) {
		this();
		this.viewport = viewport;
	}

	public GridStage(Viewport viewport, Batch batch) {
		this(viewport);
		this.batch = batch;
	}


	public void draw() {
		System.out.print(".");
		renderer.setProjectionMatrix(viewport.getCamera().combined);
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(0.8f, 0.8f, 0.8f, 1.0f);
		renderer.circle(Config.APP_WIDTH/2, Config.APP_HEIGHT/2, 20);
		renderer.end();
	}

}
