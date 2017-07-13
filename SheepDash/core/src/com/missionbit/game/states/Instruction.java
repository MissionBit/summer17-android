package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.Sheep;

/**
 * Created by MissionBit on 7/7/17.
 */

public class Instruction extends State {

    private Sheep sheep;
    private Farmer farmer;
    private Texture background;


    public Instruction(GameStateManager gsm) {
        super(gsm);
        background = new Texture("INST.png");
        sheep = new Sheep (150,280);
        farmer = new Farmer (400, 280);
        cam.setToOrtho(false, GameTutorial.WIDTH, GameTutorial.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void create() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        sheep.update(dt);
        farmer.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        sb.draw(sheep.getSheep(), 100, 220, 140, 90);
        sb.draw(farmer.getFarmer(), 400, 220, 180, 180);
        sb.end();
    }

    @Override
    public void dispose() {
        sheep.dispose();
        farmer.dispose();
    }
}
