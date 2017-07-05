package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.NoObjectionGame;




/**
 * Created by missionbit on 6/26/17.
 */

public class MenuState extends State {
    private Texture menu;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        menu = new Texture("menuBackground.png");
        cam.setToOrtho(false, NoObjectionGame.WIDTH, NoObjectionGame.HEIGHT);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 0 && Gdx.input.getX() < 600) {
                if (Gdx.input.getY() > 200 && Gdx.input.getY() < 500) {
                    gsm.set(new PlayState(gsm));
                }
            }

        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(menu, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();

    }


    public void dispose() {
        menu.dispose();
    }

}
