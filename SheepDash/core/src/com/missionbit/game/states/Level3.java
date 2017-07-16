package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.obstacles.Obstacle;
import com.missionbit.game.sprites.animals.Sheep;

import java.util.Random;


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
    private Texture mudTexture;
    private Obstacle mud;
    private Texture carrotTexture;
    private Obstacle carrot;
    private boolean carrotIsTouched;
    private Texture spikesTexture;
    private Obstacle spikes;
    private static final int hills_width = 1024;
    private static final int ground_width = 1024;
    private static final int OBS_GAP = 400;
    long startTime;

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
        Texture spikeTexture = new Texture("SPIKES2.0.18.png");
        spikes = new Obstacle(spikeTexture, 400, 50, 2, 0.5f);
        startTime = System.currentTimeMillis();
        mudTexture = new Texture("mud.png");
        mud = new Obstacle(mudTexture, 700, 58, 1, 0.5f);
        carrotTexture = new Texture("Carrott.png");
        carrot = new Obstacle(carrotTexture, 1100, 50, 2, 0.3f);
        spikesTexture = new Texture("SPIKES2.0.18.png");
        spikes = new Obstacle(spikesTexture, 1700, 50, 2, 0.5f);
        carrotIsTouched = false;
    }

    @Override
    protected void handleInput() {
        if (sheep.getPosition().y == 60) {
            if (Gdx.input.justTouched()) {
                sheep.jump();
            }
        }
    }

    @Override
    public void create() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        sheep.update(dt);
        carrot.update(dt);
        spikes.update(dt);
        cam.position.x = sheep.getPosition().x + 80;
        farmer.update(dt);
        updateGround();
        updateSky();
        updateHills();
        updateMud();
        updateCarrot();
        updateSpikes();
        timerCheck(dt);
        collisionCheck();
        cam.update();
        if(((System.currentTimeMillis() - startTime) > 30000 & farmer.collides(sheep.getBounds1()) == false)) {
            gsm.set(new Level4(gsm));
        }
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


    public void updateMud() {
        if (cam.position.x - cam.viewportWidth / 2 > mud.getPosObs().x + mud.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 300) + GameTutorial.WIDTH;
            mud.reposition(mud.getPosObs().x + distance, 58);
        }
    }

    public void updateCarrot() {
        if (cam.position.x - cam.viewportWidth / 2 > carrot.getPosObs().x + carrot.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 500) + GameTutorial.WIDTH;
            carrot.reposition(carrot.getPosObs().x + distance, 58);
            carrotIsTouched = false;
        }
    }

    public void updateSpikes() {
        if (cam.position.x - cam.viewportWidth / 2 > spikes.getPosObs().x + spikes.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 900) + GameTutorial.WIDTH;
            spikes.reposition(spikes.getPosObs().x + distance, 58);
        }
    }

    public void collisionCheck() {
        if (farmer.collides(sheep.getBounds1())) {
            sheep.getSheepDead();
            sheep.sheepDied();
            farmer.killedSheep();
        }
        if (mud.collides(sheep.getBounds1())) {
            sheep.reduceSpd();
            sheep.startTimer();
        }
        if (spikes.collides(sheep.getBounds1())) {
            sheep.reduceSpd();
            sheep.startTimer();
        }
        if (carrot.collides(sheep.getBounds1())) {
            carrotIsTouched = true;
            sheep.startTimer();
            sheep.increaseSpd();
        }
    }

    public void timerCheck(float timePassed) {
        sheep.updateTimer(timePassed);
        if (sheep.isTimerDone()) {
            sheep.resetSpd();
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
        sb.draw(mud.getObstacle(), mud.getPosObs().x, mud.getPosObs().y);
        if (carrotIsTouched == false) {
            sb.draw(carrot.getObsAnimation(), carrot.getPosObs().x, carrot.getPosObs().y);
        }
        sb.draw(spikes.getObsAnimation(), spikes.getPosObs().x, spikes.getPosObs().y);
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
        sheep.dispose();
        farmer.dispose();
        sky.dispose();
        hills.dispose();
        ground.dispose();
        sheep.dispose();
        farmer.dispose();
        mudTexture.dispose();
        mud.dispose();
        carrotTexture.dispose();
        carrot.dispose();
        spikesTexture.dispose();
        spikes.dispose();
    }
}

