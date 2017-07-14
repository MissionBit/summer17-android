package com.missionbit.game.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.missionbit.game.NoObjectionGame;

/**
 * Created by missionbit on 7/13/17.
 */

public class MenuScreen implements Screen {
    private Viewport viewport;
    private Stage stage;
    private NoObjectionGame game;
    private Texture bg;

    public MenuScreen(NoObjectionGame game) {
        this.game = game;
        bg = new Texture("coolbg3.png");
        viewport = new StretchViewport(NoObjectionGame.V_WIDTH, NoObjectionGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((NoObjectionGame) game).batch);


    }


    @Override
    public void show() {

    }



    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new PlayScreen((NoObjectionGame) game));
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(bg, viewport.getScreenX(), viewport.getScreenY(), NoObjectionGame.V_WIDTH, NoObjectionGame.V_HEIGHT);
        stage.getBatch().end();
        stage.draw();
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
        // background.dispose(); 
        //stage.dispose(); 
        //myTexture1.dispose();

    }
}