package com.missionbit.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.GameTutorial;

/**
 * Created by missionbit on 7/6/17.
 */

public class Level1 extends State  {

     private Texture bg;

    public Level1(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("FarmBg1.png");
        }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
