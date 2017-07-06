package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.GameTutorial;

/**
 * Created by MissionBit on 6/22/17.
 */

public class MenuState extends State {


    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("MenuScreen.png");
        playBtn = new Texture("PlayBtn.png");
    }
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new MapState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        sb.draw(playBtn,(GameTutorial.WIDTH - playBtn.getWidth())/2, (GameTutorial.HEIGHT - playBtn.getHeight())/2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
