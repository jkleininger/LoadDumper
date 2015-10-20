package net.qrab.lmnotruckers.handlers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Renderer {

	private static ShapeRenderer renderer = new ShapeRenderer(500);

	public static void fCircle(float x, float y, float rad, float r, float g, float b) {
		renderer.end();
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(r, g, b, 1.0f);
		renderer.circle(x, y, rad);
		renderer.end();
	}

	public static void grid(Vector2 p, Vector2 s, int cols, int rows, Color fill, Color line) {
		renderer.end();
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(fill);
		renderer.rect(p.x, p.y, s.x, s.y);
		renderer.end();
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.setColor(line);
		for(float r=p.y ; r<=p.y+s.y ; r+=s.y/rows) {
			renderer.line(p.x,r,p.x+s.x,r);
		}
		for(float c=p.x ; c<=p.x+s.x ; c+=s.x/cols) {
			renderer.line(c,p.y,c,p.y+s.y);
		}

		renderer.end();
	}

}
