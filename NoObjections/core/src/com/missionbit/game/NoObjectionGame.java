package com.missionbit.game;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.screens.PlayScreen;
//import com.missionbit.game.states.EndState;
//import com.missionbit.game.states.GameStateManager;
//import com.missionbit.game.states.MenuState;
//import com.missionbit.game.states.PlayState;

public class NoObjectionGame extends Game {
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
	public static final float PPM = 100;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Flummox";
	//private GameStateManager gsm;
	public SpriteBatch batch;
    private Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
        music = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

//		gsm = new GameStateManager();
//		gsm.push(new MenuState(gsm));
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
