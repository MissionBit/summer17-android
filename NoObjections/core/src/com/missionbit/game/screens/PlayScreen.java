package com.missionbit.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.missionbit.game.NoObjectionGame;
import com.missionbit.game.scenes.Hud;
import com.missionbit.game.sprites.Hero;
import com.sun.org.apache.bcel.internal.generic.NOP;

/**
 * Created by missionbit on 7/10/17.
 */

public class PlayScreen implements Screen {
    private NoObjectionGame game;
    private Texture bg;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;

    private Hero hero;

    //box2d
    private World world;
    private Box2DDebugRenderer b2dr;

    public PlayScreen(NoObjectionGame game) {
        this.game = game;
        bg = new Texture("main_background.png");
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(NoObjectionGame.V_WIDTH/ NoObjectionGame.PPM, NoObjectionGame.V_HEIGHT/ NoObjectionGame.PPM, gameCam);
        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("map.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map, 1/ NoObjectionGame.PPM);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //this is for the door
        for(MapObject object : map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / NoObjectionGame.PPM, (rect.getY() + rect.getHeight() / 2 )/ NoObjectionGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / NoObjectionGame.PPM, rect.getHeight() / 2 / NoObjectionGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //ladder
        for(MapObject object : map.getLayers().get(13).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / NoObjectionGame.PPM, (rect.getY() + rect.getHeight() / 2 )/ NoObjectionGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / NoObjectionGame.PPM, rect.getHeight() / 2 / NoObjectionGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //floors
        for(MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / NoObjectionGame.PPM, (rect.getY() + rect.getHeight() / 2 )/ NoObjectionGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / NoObjectionGame.PPM, rect.getHeight() / 2 / NoObjectionGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //plank
        for(MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / NoObjectionGame.PPM, (rect.getY() + rect.getHeight() / 2 )/ NoObjectionGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / NoObjectionGame.PPM, rect.getHeight() / 2 / NoObjectionGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //portal
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / NoObjectionGame.PPM, (rect.getY() + rect.getHeight() / 2 )/ NoObjectionGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / NoObjectionGame.PPM, rect.getHeight() / 2 / NoObjectionGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //pitfall
        for(MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / NoObjectionGame.PPM, (rect.getY() + rect.getHeight() / 2 )/ NoObjectionGame.PPM);

            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / NoObjectionGame.PPM, rect.getHeight() / 2 / NoObjectionGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        hero = new Hero(world);
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        //if our user is holding down mouse move our camer
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
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

        gameCam.position.x = hero.b2body.getPosition().x;
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

//        game.batch.setProjectionMatrix(gameCam.combined);
//        game.batch.begin();
//        game.batch.draw(bg, 0, 0);
//        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
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

    }
}
