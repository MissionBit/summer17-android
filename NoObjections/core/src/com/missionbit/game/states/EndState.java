package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


/**
 * Created by missionbit on 6/26/17.
 */

public class EndState extends State {
    private Texture background;
    private Texture playBtn;
    private Texture instructBtn;
    private TextButton tb;
    private TextButton.TextButtonStyle textButtonStyle;

    public EndState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("pixil-layer-Background.png");
        textButtonStyle = new TextButton.TextButtonStyle();
        tb = new TextButton("Restart", textButtonStyle);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }
    }


    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();
    }

    public void dispose() {
        background.dispose();
    }

}
