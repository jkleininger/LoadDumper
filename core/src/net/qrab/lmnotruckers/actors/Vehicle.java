package net.qrab.lmnotruckers.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import net.qrab.lmnotruckers.handlers.Config;
import net.qrab.lmnotruckers.handlers.Util;

public class Vehicle extends Actor {

	public Sprite sprite = new Sprite(new Texture(Gdx.files.internal("car.png")));

	public  Config.Behavior[] moves = {
			Config.Behavior.STRAIGHT,
			Config.Behavior.RIGHT,
			Config.Behavior.LEFT
	};

	public Vehicle() {
		super();
		setBounds(0, 0, 1, 1);
		setOrigin(0.5f, 0.5f);
		sprite.setSize(1f, 1f);
	}

	public Vehicle(int col, int row) {
		this();
		this.setPosition(col,row);
	}

	public int[] gridPosition() {
		Vector2 pos = new Vector2(0.5f,0.5f);
		this.localToStageCoordinates(pos);
		return new int[] { (int)Math.floor(pos.x), (int)Math.floor(pos.y) };
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		sprite.draw(batch);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		while(this.getRotation()>360) this.setRotation(this.getRotation()-360);
		while(this.getRotation()<0)   this.setRotation(this.getRotation()+360);

		if(!this.hasActions()) {
			// round to nearest 90 when not in motion
			this.setRotation( 90 * Math.round(this.getRotation()/90) );
			//TODO: this will eventually be something like "doAction" because some events will not involve moving
			Util.moveTo(this, Util.nextMove(this));
			//Util.goToAction(this, Util.nextMove(this));
		}

		sprite.setPosition(this.getX(), this.getY());
		sprite.setOrigin(this.getOriginX(), this.getOriginY());
		sprite.setRotation(this.getRotation());
		sprite.setScale(this.getScaleX(), this.getScaleY());


	}

	public Config.Direction getFacing() {
		int myAngle = (int)Math.floor(this.getRotation());
		while(myAngle >=  360) myAngle -= 360;
		while(myAngle <= -360) myAngle += 360;
		myAngle = 90 * Math.round(myAngle / 90);
		if(myAngle==0)   return Config.Direction.N;
		if(myAngle==90)  return Config.Direction.W;
		if(myAngle==180) return Config.Direction.S;
		if(myAngle==270) return Config.Direction.E;
		return null;
	}


}
