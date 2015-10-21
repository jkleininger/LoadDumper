package net.qrab.lmnotruckers.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Vehicle extends Actor {

	Sprite sprite = new Sprite(new Texture(Gdx.files.internal("circle.png")));

	public Vehicle() {
		super();
		//setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		setBounds(0,0,1,1);
		sprite.setSize(1,1);
		System.out.println(this.sprite.getBoundingRectangle());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		//
		sprite.draw(batch);
	}

	@Override
	protected void positionChanged() {
		sprite.setPosition(this.getX(), this.getY());
		super.positionChanged();
	}

	@Override
	public void act(float delta) {
		//
		super.act(delta);
	}



}
