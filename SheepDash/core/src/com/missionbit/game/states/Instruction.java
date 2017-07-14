package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.animals.Sheep;

/**
 * Created by MissionBit on 7/7/17.
 */

public class Instruction extends State {

    private Sheep sheep;
    private Sheep theOneThatDies;
    private Farmer farmer;
    private Texture background;

    public Instruction(GameStateManager gsm) {
        super(gsm);
        background = new Texture("INST.png");
        sheep = new Sheep (50,280);
        theOneThatDies = new Sheep(650, 280);
        farmer = new Farmer (400, 280);
        farmer.getPosition().x = 400;
        farmer.setBoundsFarmer(180, 180);
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
        theOneThatDies.update(dt);
        farmer.update(dt);
        collisionCheck();
        //ifs and butts
        if (sheep.getPosition().x > 150) {
            sheep.jump();
        }
        if (sheep.getPosition().x > 250) {
            sheep.getPosition().x = 50;
        }
        if (farmer.getPosition().x > 650){
            farmer.getPosition().x = 400;
        }
    }

    public void collisionCheck() {
        if (farmer.collides(theOneThatDies.getBounds1())){
            theOneThatDies.getSheepDead();
        } else {
            theOneThatDies.setDead(false);
        }
    }

    @Override
    public void render(final SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        sb.draw(sheep.getSheep(), sheep.getPosition().x, 220, 140, 90);
        if (farmer.collides(theOneThatDies.getBounds1())) {
                sb.draw(theOneThatDies.getSheepDead(), theOneThatDies.getPosition().x, 220, 140, 90);
                theOneThatDies.noSpd();
            }
        else {
            sb.draw(theOneThatDies.getSheep(), theOneThatDies.getPosition().x, 220, 140,90);
            theOneThatDies.noSpd();
        }
        sb.draw(farmer.getFarmer(), farmer. getPosition().x, 220, 180, 180);
        sb.end();
    }

    @Override
    public void dispose() {
        sheep.dispose();
        farmer.dispose();
        theOneThatDies.dispose();
        background.dispose();
    }
}
