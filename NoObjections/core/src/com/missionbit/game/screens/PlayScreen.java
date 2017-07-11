package com.missionbit.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.missionbit.game.NoObjectionGame;
import com.missionbit.game.scenes.Hud;

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

    public PlayScreen(NoObjectionGame game) {
        this.game = game;
        bg = new Texture("main_background.png");
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(NoObjectionGame.V_WIDTH, NoObjectionGame.V_HEIGHT, gameCam);
        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("map.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        if(Gdx.input.isTouched()) {
            gameCam.position.x += 100 * dt;
        }
    }

    public void update(float dt) {
        handleInput(dt);
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        game.batch.end();
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
