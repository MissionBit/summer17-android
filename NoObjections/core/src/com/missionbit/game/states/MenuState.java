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
    private Texture playBtn;
    private Texture instructBtn;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        menu = new Texture("suckybg_copy.png");
        cam.setToOrtho(false, NoObjectionGame.WIDTH / 2, NoObjectionGame.HEIGHT / 2);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(menu, 0, 0);
        sb.end();

    }




    public void dispose() {
        menu.dispose();
    }

}
