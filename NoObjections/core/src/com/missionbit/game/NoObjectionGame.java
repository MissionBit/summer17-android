package com.missionbit.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.screens.MenuScreen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.missionbit.game.sprites.Hero;

public class NoObjectionGame extends Game {

	//Game screen visuals
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

	//Codes for object collision detection
	public static final int LADDER = 1000;
	public static final int PLANK = 1001;
	public static final int PORTAL = 1002;
	public static final int FLOOR = 1003;
	public static final int DOOR = 1004;
	public static final int DEFAULT = 0;


	//private GameStateManager gsm;
    private Music music;
	public static SpriteBatch batch;
    World world;
    public Viewport viewport;
    public Hero hero;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
        music = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT);
        world = new World(new Vector2(0, -10), true);
		Gdx.gl.glClearColor(0, 0, 0, 1);

	}

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
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
