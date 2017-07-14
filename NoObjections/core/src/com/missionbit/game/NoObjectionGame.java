package com.missionbit.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.missionbit.game.screens.PlayScreen;
import com.missionbit.game.sprites.Hero;
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
    private Music music;
	public static SpriteBatch batch;
    World world;
    public Viewport viewport;
    public Hero hero;

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
        viewport = new FitViewport(V_WIDTH, V_HEIGHT);
        world = new World(new Vector2(0, -10), true);
//	gsm = new GameStateManager();
		//gsm.push(new MenuState(gsm));
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
