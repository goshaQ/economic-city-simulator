package com.itproject.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.itproject.game.EconomicCitySimulator;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Economic City Simulator";
		config.width =  1920;
		config.height = 1080;
		//set fullscreen after creating exit button
		//config.fullscreen = true;
		new LwjglApplication(new EconomicCitySimulator(), config);
	}
}
