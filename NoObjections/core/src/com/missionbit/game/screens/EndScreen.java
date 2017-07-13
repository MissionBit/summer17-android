package com.missionbit.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.missionbit.game.NoObjectionGame;

/**
 * Created by missionbit on 7/13/17.
 */

public class EndScreen implements Screen {
    private NoObjectionGame game;
    private Texture background;

    public EndScreen(NoObjectionGame game) {
        background = new Texture("pixil-layer-Background.png");

    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {

    }

    public void update(float dt) {
        handleInput(dt);

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

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