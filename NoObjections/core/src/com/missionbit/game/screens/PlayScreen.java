package com.missionbit.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.missionbit.game.NoObjectionGame;
import com.missionbit.game.scenes.Hud;
import com.missionbit.game.sprites.Hero;
import com.missionbit.game.states.EndState;
import com.missionbit.game.states.MenuState;
import com.missionbit.game.tools.B2WorldCreator;
import com.missionbit.game.tools.WorldContactListener;

/**
 * Created by missionbit on 7/10/17.
 */

public class PlayScreen implements Screen {
    private NoObjectionGame game;
    private TextureAtlas atlas;
    private Texture bg;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader maploader;
    public TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;

    private Hero hero;

    private com.missionbit.game.Controller controller;

    //box2d
    private World world;
    private Box2DDebugRenderer b2dr;

    public PlayScreen(NoObjectionGame game) {
        atlas = new TextureAtlas("dudeRun4.pack");

        this.game = game;
        bg = new Texture("main_background.png");
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(NoObjectionGame.V_WIDTH/ NoObjectionGame.PPM, NoObjectionGame.V_HEIGHT/ NoObjectionGame.PPM, gameCam);
        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("map1.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map, 1/ NoObjectionGame.PPM);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world,map);
        hero = new Hero(world, this);

        world.setContactListener(new WorldContactListener(hero));

        controller = new com.missionbit.game.Controller(hero);
        }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        //if our user is holding down mouse move our camer
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) || controller.upPressed && hero.isLanded() ){
            hero.b2body.applyLinearImpulse(new Vector2(0, 4f), hero.b2body.getWorldCenter(),
                    true );
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && hero.b2body.getLinearVelocity().x <= 2){
            hero.b2body.applyLinearImpulse(new Vector2(0.1f, 0), hero.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && hero.b2body.getLinearVelocity().x >= -2){
            hero.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), hero.b2body.getWorldCenter(), true);
        }
    }

    public void update(float dt) {
        handleInput(dt);
        world.step(1/60f, 6, 2);

        hero.update(dt);

        gameCam.position.x = hero.b2body.getPosition().x;
        gameCam.position.y = hero.b2body.getPosition().y;
        gameCam.update();
        renderer.setView(gameCam);


    }

    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //renderer our box2ddebuglines
        b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        hero.draw(game.batch);
        game.batch.end();

        //Set our batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(hero.currentState == Hero.State.DEAD){
            game.setScreen(new EndScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        //hud.dispose();
    }
}