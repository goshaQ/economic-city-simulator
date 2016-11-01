package com.itproject.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.itproject.game.EconomicCitySimulator;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Economic City Simulator";
		config.width =  1366;
		config.height = 768;
		new LwjglApplication(new EconomicCitySimulator(), config);
	}
}
