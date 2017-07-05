package com.missionbit.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.sprites.Hero;

/**
 * Created by missionbit on 6/26/17.
 */

public class PlayState extends State{
    private Hero hero;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        hero = new Hero(50, 100);

    }

    @Override
    public void handleInput() {


    }

    @Override
    public void update(float dt) {
        handleInput();
        hero.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(hero.getTexture(), hero.getPosition().x, hero.getPosition().y);

    }

    @Override
    public void dispose() {

    }
}
