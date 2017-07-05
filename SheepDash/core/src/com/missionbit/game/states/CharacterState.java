package com.missionbit.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.GameTutorial;

/**
 * Created by missionbit on 6/26/17.
 */

public class CharacterState extends State {
    private Texture bg;
    private Texture sheep;
    private Texture cow;
    private Texture pig;
    private Texture chick;
    private Texture bunny;

    public CharacterState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("CharScreen.png");
        sheep = new Texture("SheepButton.png");
        cow = new Texture("CowButton.png");
        pig = new Texture("PigButton.png");
        chick = new Texture("ChickButton.png");
        bunny = new Texture("BunnyButton.png");
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
        sb.draw(sheep, 100, 230, 100, 100);
        sb.draw(cow, 250, 230, 100, 100);
        sb.draw(pig, 400, 230, 100, 100);
        sb.draw(chick, 175, 80, 100, 100);
        sb.draw(bunny, 325, 80, 100, 100);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        sheep.dispose();
        cow.dispose();
        pig.dispose();
        chick.dispose();
        bunny.dispose();
    }

}
