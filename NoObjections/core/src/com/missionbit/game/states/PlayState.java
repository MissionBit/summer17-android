package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by missionbit on 6/26/17.
 */

public class PlayState extends State{
    private Texture bg;
  
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("suckybg_copy.png");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}
