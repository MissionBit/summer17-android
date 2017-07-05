package com.missionbit.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.missionbit.game.GameTutorial;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameTutorial.WIDTH;
		config.height = GameTutorial.HEIGHT;
		config.title = GameTutorial.TITLE;
		new LwjglApplication(new GameTutorial(), config);
	}
}
