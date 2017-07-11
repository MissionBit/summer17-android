package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.states.EndState;
import com.missionbit.game.states.GameStateManager;
import com.missionbit.game.states.MenuState;
import com.missionbit.game.states.PlayState;

public class NoObjectionGame extends ApplicationAdapter {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Flummox";
	private GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();

		gsm = new GameStateManager();
<<<<<<< HEAD
		gsm.push(new PlayState(gsm));
=======
		gsm.push(new MenuState(gsm));
		Gdx.gl.glClearColor(0, 0, 0, 1);
>>>>>>> 9e62d7afe8e0431aa9cdef9bcc0723f9daa7b924
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
