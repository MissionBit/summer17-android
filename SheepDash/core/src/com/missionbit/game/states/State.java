package com.missionbit.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by missionbit on 6/26/17.
 */

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    public State(GameStateManager gsm) {
        cam = new OrthographicCamera();
        mouse = new Vector3();
        this.gsm = gsm;
    }

    protected void gameOver(final int level, final int character) {
        System.out.println("Game Over");
        float delay = 0.9f; // seconds
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                GameOver gameOver = new GameOver(gsm, level, character);
                gsm.set(gameOver);
            }
        }, delay);
    }

    protected abstract void handleInput();

    public abstract void create();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();
}
