package com.missionbit.game;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.screens.PlayScreen;
import com.missionbit.game.states.EndState;

public class NoObjectionGame extends Game {
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
	public static final float PPM = 100;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Flummox";
	public static final short HERO_BIT = 2;
	public static final short LADDER_BIT = 4;
	public static final short DOOR_BIT = 8;
	public static final short FLOOR_BIT = 16;
	public static final short DEFAULT_BIT = 1;
	//private GameStateManager gsm;
	public static SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));

//	gsm = new GameStateManager();
		//gsm.push(new MenuState(gsm));
		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
