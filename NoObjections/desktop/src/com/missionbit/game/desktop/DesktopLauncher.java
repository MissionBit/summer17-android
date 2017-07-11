package com.missionbit.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.missionbit.game.NoObjectionGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = NoObjectionGame.HEIGHT;
        config.width = NoObjectionGame.WIDTH;
        config.title = NoObjectionGame.TITLE;
		new LwjglApplication(new NoObjectionGame(), config);
	}
}
