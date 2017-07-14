package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by missionbit on 7/11/17.
 */

public class Controller {
    private SpriteBatch batch;
    Viewport viewport;
    Stage stage;
    boolean upPressed, downPressed, leftPressed, rightPressed;
    OrthographicCamera cam;

    public Controller() {
        cam = new OrthographicCamera();
        viewport = new FitViewport(NoObjectionGame.WIDTH, NoObjectionGame.HEIGHT, cam);
        stage = new Stage(viewport, NoObjectionGame.batch);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.left().bottom();

        float arrows = 50;

        Image upImg = new Image(new Texture("upArrow.png"));
        upImg.setSize(arrows, arrows);

        upImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = false;
            }
        });
        Image downImg = new Image(new Texture("downArrow.png"));
        downImg.setSize(arrows, arrows);
        downImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = false;
            }
        });
        Image leftImg = new Image(new Texture("leftArrow.png"));
        leftImg.setSize(arrows, arrows);
        leftImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }
        });
        Image rightImg = new Image(new Texture("rightArrow.png"));
        rightImg.setSize(arrows, arrows);
        rightImg.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
            }
        });

        float padding = 5;
        table.add().pad(padding);
        table.add(upImg).size(upImg.getWidth(), upImg.getHeight()).pad(padding);
        table.add().pad(padding);
        table.row();
        table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight()).pad(padding);
        table.add().pad(padding);
        table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight()).pad(padding);
        table.row();
        table.add().pad(padding);
        table.add(downImg).size(downImg.getWidth(), downImg.getHeight()).pad(padding);
        table.add().pad(padding);


        stage.addActor(table);

    }

    public void draw() {
        stage.draw();
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
