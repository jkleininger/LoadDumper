package net.qrab.lmnotruckers.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.qrab.lmnotruckers.ElementalTruckersGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.height = 800;
		config.width  = 480;

		new LwjglApplication(new ElementalTruckersGame(), config);
	}
}
