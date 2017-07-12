package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.Obstacle;
import com.missionbit.game.sprites.Sheep;


/**
 * Created by missionbit on 6/28/17.
 */

public class Level3 extends State {
    private Sheep sheep;
    private Farmer farmer;
    private Texture ground;
    private Vector2 groundPos1, groundPos2, groundPos3;
    private Texture sky;
    private Vector2 skyPos, skyPos2;
    private Texture hills;
    private Vector2 hillsPos, hillsPos2, hillsPos3, hillsPos4, hillsPos5;
    private Texture spikeTexture;
    private Obstacle spikes;
    private static final int hills_width = 1024;
    private static final int ground_width = 1024;
    private static final int OBS_GAP = 400;


    public Level3(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, GameTutorial.WIDTH / 2, GameTutorial.HEIGHT / 2);
        sheep = new Sheep(150, 60);
        farmer = new Farmer(-50, 60);
        ground = new Texture("plains-ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        groundPos2 = new Vector2(ground.getWidth(), 0);
        groundPos3 = new Vector2(ground.getWidth() + groundPos2.x, 0);
        sky = new Texture("plains-background.png");
        skyPos = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        skyPos2 = new Vector2(sky.getWidth() + skyPos.x, 0);
        hills = new Texture("plains-hills2.png");
        hillsPos = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        hillsPos2 = new Vector2(hills_width + hillsPos.x, 0);
        hillsPos3 = new Vector2(2 * hills_width + hillsPos.x, 0);
        hillsPos4 = new Vector2(3 * hills_width + hillsPos.x, 0);
        hillsPos5 = new Vector2(4 * hills_width + hillsPos.x, 0);
        spikeTexture = new Texture("SPIKESdeathtotouch.png");
        spikes = new Obstacle(spikeTexture, 250, 50);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            sheep.jump();
        }


    }

    @Override
    public void update(float dt) {
        handleInput();
        sheep.update(dt);
        cam.position.x = sheep.getPosition().x + 80;
        farmer.update(dt);
        updateGround();
        updateSky();
        updateHills();
        updateSpikes();
        collisionCheck();
        cam.update();

    }

    public void updateGround() {
        if (groundPos1.x + ground.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            groundPos1.add(3 * ground.getWidth(), 0);
        }
        if (groundPos2.x + ground.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            groundPos2.add(3 * ground.getWidth(), 0);
        }
        if (groundPos3.x + ground.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            groundPos3.add(3 * ground.getWidth(), 0);
        }
    }

    public void updateSky() {
        if (skyPos.x + sky.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            skyPos.add(2 * sky.getWidth(), 0);
        }
        if (skyPos2.x + sky.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            skyPos2.add(2 * sky.getWidth(), 0);
        }

    }

    public void updateHills() {
        if (hillsPos.x + hills.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            hillsPos.add(5 * hills.getWidth(), 0);
        }
        if (hillsPos2.x + hills.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            hillsPos2.add(5 * hills.getWidth(), 0);
        }
        if (hillsPos3.x + hills.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            hillsPos3.add(5 * hills.getWidth(), 0);
        }
        if (hillsPos4.x + hills.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            hillsPos4.add(5 * hills.getWidth(), 0);
        }
        if (hillsPos5.x + hills.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            hillsPos5.add(5 * hills.getWidth(), 0);
        }
    }

    public void updateSpikes() {
        if (cam.position.x - cam.viewportWidth / 2 > spikes.getPosObs().x + spikes.getWidth()) {
            //spikes.reposition(spikes.getPosObs().x + (spikes.getWidth());
            spikes.reposition(spikes.getPosObs().x + (spikes.getWidth() + (OBS_GAP*2)));
        }
        /*
        if (spikes.getPosObs().x + OBS_GAP <= cam.position.x-cam.viewportWidth/2){
            spikes.getPosObs().add(2*OBS_GAP,0);
            spikes.getBoundsObs().setPosition(spikes.getPosObs().x,spikes.getPosObs().y);
        }

        if (spikes.getPosObs().x+POOP_WIDTH <= cam.position.x-cam.viewportWidth/2){
            poop.getPosPoop2().add(2*POOP_SPACING,0);
            poop.getBoundsPoop2().setPosition(poop.getPosPoop2().x,poop.getPosPoop2().y);
        }
        */
    }


    public void collisionCheck() {
        if (farmer.collides(sheep.getBounds1())) {
            sheep.getSheepDead();
            gsm.set(new MenuState(gsm));
        }
        if (spikes.collides(sheep.getBounds1())) {
            sheep.reduceSpd();
        }
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(sky, skyPos.x, 0, 1024, 400);
        sb.draw(sky, skyPos2.x, 0, 1024, 400);
        sb.draw(hills, hillsPos.x, 0, hills_width, 250);
        sb.draw(hills, hillsPos2.x, 0, hills_width, 250);
        sb.draw(hills, hillsPos3.x, 0, hills_width, 250);
        sb.draw(hills, hillsPos4.x, 0, hills_width, 250);
        sb.draw(hills, hillsPos5.x, 0, hills_width, 250);
        sb.draw(ground, groundPos1.x, 0, ground_width, 1100);
        sb.draw(ground, groundPos2.x, 0, ground_width, 1100);
        sb.draw(ground, groundPos3.x, 0, ground_width, 1100);
        sb.draw(spikes.getObstacle(), spikes.getPosObs().x, spikes.getPosObs().y);
        if (farmer.collides(sheep.getBounds1())) {
            sb.draw(sheep.getSheepDead(), sheep.getPosition().x, sheep.getPosition().y, 70, 45);
        } else {
            sb.draw(sheep.getSheep(), sheep.getPosition().x, sheep.getPosition().y, 70, 45);
        }
        sb.draw(farmer.getFarmer(), farmer.getPosition().x, farmer.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {
        sky.dispose();
        hills.dispose();
        ground.dispose();
        sheep.dispose();
        farmer.dispose();
    }
}
