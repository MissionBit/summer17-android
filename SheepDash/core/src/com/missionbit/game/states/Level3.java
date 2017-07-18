package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.animals.Bunny;
import com.missionbit.game.sprites.animals.Chick;
import com.missionbit.game.sprites.animals.Cow;
import com.missionbit.game.sprites.animals.Pig;
import com.missionbit.game.sprites.obstacles.Obstacle;
import com.missionbit.game.sprites.animals.Sheep;

import java.util.Random;


/**
 * Created by missionbit on 6/28/17.
 */

public class Level3 extends State {
    //CHARACTERS
    private Sheep sheep;
    private Bunny bunny;
    private Chick chick;
    private Cow cow;
    private Pig pig;
    //otherz(yeah, with a z, cuz it's cool that way)
    private Farmer farmer;
    private Texture ground;
    private Vector2 groundPos1, groundPos2, groundPos3;
    private Texture sky;
    private Vector2 skyPos, skyPos2;
    private Texture hills;
    private Vector2 hillsPos, hillsPos2, hillsPos3, hillsPos4, hillsPos5;
    //OBSTACLES
    private Texture mudTexture;
    private Obstacle mud;
    private Texture carrotTexture;
    private Obstacle carrot;
    private boolean carrotIsTouched;
    private Texture spikesTexture;
    private Obstacle spikes;
    private static final int hills_width = 1024;
    private static final int ground_width = 1024;
    long startTime;
    //
    private int a;

    public Level3(GameStateManager gsm, int c) {
        super(gsm);
        a = c;
        cam.setToOrtho(false, GameTutorial.WIDTH / 2, GameTutorial.HEIGHT / 2);
        // INITIALIZING ANIMALS
        if (a == 1){
            sheep = new Sheep(150, 60);
        }
        if (a == 2){
            cow = new Cow(150, 60);
        }
        if (a == 3){
            pig = new Pig(150, 60);
        }
        if (a == 4){
            bunny = new Bunny(150, 60);
        }
        if (a == 5){
            chick = new Chick(150, 60);
        }
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
        //OBSTACLES
        Texture spikeTexture = new Texture("SPIKES2.0.18.png");
        spikes = new Obstacle(spikeTexture, 400, 50, 2, 0.5f);
        startTime = System.currentTimeMillis();
        mudTexture = new Texture("mud.png");
        mud = new Obstacle(mudTexture, 700, 58, 1, 0.5f);
        carrotTexture = new Texture("Carrott.png");
        carrot = new Obstacle(carrotTexture, 500, 150, 2, 0.3f);
        spikesTexture = new Texture("SPIKES2.0.18.png");
        spikes = new Obstacle(spikesTexture, 1700, 50, 2, 0.5f);
        carrotIsTouched = false;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (a == 1) {
                if (sheep.getPosition().y == 60) {
                    sheep.jump();
                }
            }
            if (a == 2) {
                if (cow.getPosition().y == 60) {
                    cow.jump();
                }
            }
            if (a == 3) {
                if (pig.getPosition().y == 60) {
                    pig.jump();
                }
            }
            if (a == 4) {
                if (bunny.getPosition().y == 60) {
                    bunny.jump();
                }
            }
            if (a == 5) {
                if (chick.getPosition().y == 60) {
                    chick.jump();
                }
            }
        }
    }

    @Override
    public void create() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        //the characters (this is getting tiring and repetitive but I guess that's what programmimg is yay
        if (a == 1){
            sheep.update(dt);
            cam.position.x = sheep.getPosition().x + 80;
            if(System.currentTimeMillis() - startTime > 45000 && !farmer.collides(sheep.getBounds1())) {
                gsm.set(new Level4(gsm, a));
            }
        }
        if (a == 2){
            cow.update(dt);
            cam.position.x = cow.getPosition().x + 80;
            if(System.currentTimeMillis() - startTime > 45000 && !farmer.collides(cow.getCowBounds())) {
                gsm.set(new Level4(gsm, a));
            }
        }
        if (a == 3){
            pig.update(dt);
            cam.position.x = pig.getPosition().x + 80;
            if(System.currentTimeMillis() - startTime > 45000 && !farmer.collides(pig.getPigBounds())) {
                gsm.set(new Level4(gsm, a));
            }
        }
        if (a == 4){
            bunny.update(dt);
            cam.position.x = bunny.getPosition().x + 80;
            if(System.currentTimeMillis() - startTime > 45000 && !farmer.collides(bunny.getBoundsBunny())) {
                gsm.set(new Level4(gsm, a));
            }
        }
        if (a == 5){
            chick.update(dt);
            cam.position.x = chick.getPosition().x + 80;
            if(System.currentTimeMillis() - startTime > 45000 && !farmer.collides(chick.getChickBounds())) {
                gsm.set(new Level4(gsm, a));
            }
        }
        carrot.update(dt);
        spikes.update(dt);
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

        if(((System.currentTimeMillis() - startTime) > 45000 & farmer.collides(sheep.getBounds1()) == false)) {
            gsm.set(new YouWon(gsm,3,a));
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
            carrot.reposition(carrot.getPosObs().x + distance, 150);
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

        //for sheep
        if (a == 1) {
            if (farmer.collides(sheep.getBounds1())){
                sheep.getSheepDead();
                sheep.sheepDied();
                farmer.killed();
                gameOver(3, a);
            }
            if (spikes.collides(sheep.getBounds1())||mud.collides(sheep.getBounds1())) {
                sheep.reduceSpd();
                sheep.startTimer();
            }
            if (carrot.collides(sheep.getBounds1())) {
                carrotIsTouched = true;
                sheep.increaseSpd();
                sheep.startTimer();
            }
        }
//for cow
        if (a == 2){
            if (farmer.collides(cow.getCowBounds())){
                cow.getCowDead();
                cow.cowDied();
                farmer.killed();
                gameOver(3, a);
            }
            if (spikes.collides(cow.getCowBounds())||mud.collides(cow.getCowBounds())){
                cow.reduceSpd();
                cow.startTimer();
            }
            if (carrot.collides(cow.getCowBounds())) {
                carrotIsTouched = true;
                cow.increaseSpd();
                cow.startTimer();
            }
        }
//for pig
        if(a == 3){
            if (farmer.collides(pig.getPigBounds())){
                pig.getPigDead();
                pig.pigDied();
                farmer.killed();
                gameOver(3, a);
            }
            if (spikes.collides(pig.getPigBounds())||mud.collides(pig.getPigBounds())){
                pig.reduceSpd();
                pig.startTimer();
            }
            if (carrot.collides(pig.getPigBounds())) {
                carrotIsTouched = true;
                pig.increaseSpd();
                pig.startTimer();
            }
        }
//for bunny
        if (a == 4){
            if (farmer.collides(bunny.getBoundsBunny())){
                bunny.getBunnyDead();
                bunny.bunnyDied();
                farmer.killed();
                gameOver(3, a);
            }
            if (spikes.collides(bunny.getBoundsBunny())||mud.collides(bunny.getBoundsBunny())) {
                bunny.reduceSpd();
                bunny.startTimer();
            }
            if (carrot.collides(bunny.getBoundsBunny())) {
                carrotIsTouched = true;
                bunny.increaseSpd();
                bunny.startTimer();
            }
        }
//for chick
        if (a == 5) {
            if (farmer.collides(chick.getChickBounds())) {
                chick.getChickDead();
                chick.chickDied();
                farmer.killed();
                gameOver(3, a);
            }
            if (spikes.collides(chick.getChickBounds()) || mud.collides(chick.getChickBounds())) {
                chick.reduceSpd();
                chick.startTimer();
            }
            if (carrot.collides(chick.getChickBounds())) {
                carrotIsTouched = true;
                chick.increaseSpd();
                chick.startTimer();
            }
        }
    }

    public void timerCheck(float timePassed) {
        if (a == 1){
            sheep.updateTimer(timePassed);
            if (sheep.isTimerDone()) {
                sheep.resetSpd();
            }
        }
        if (a == 2){
            cow.updateTimer(timePassed);
            if (cow.isTimerDone()) {
                cow.resetSpd();
            }
        }
        if (a == 3){
            pig.updateTimer(timePassed);
            if (pig.isTimerDone()) {
                pig.resetSpd();
            }
        }
        if (a == 4){
            bunny.updateTimer(timePassed);
            if (bunny.isTimerDone()) {
                bunny.resetSpd();
            }
        }
        if (a == 5){
            chick.updateTimer(timePassed);
            if (chick.isTimerDone()) {
                chick.resetSpd();
            }
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
            sb.draw(carrot.getObsAnimation(), carrot.getPosObs().x, carrot.getPosObs().y,30,30);
        }
        sb.draw(spikes.getObsAnimation(), spikes.getPosObs().x, spikes.getPosObs().y);
        //CHARACTERS
        if (a == 1){
            if (farmer.collides(sheep.getBounds1())) {
                sb.draw(sheep.getSheepDead(), sheep.getPosition().x, sheep.getPosition().y, 70, 45);
            } else {
                sb.draw(sheep.getSheep(), sheep.getPosition().x, sheep.getPosition().y, 70, 45);
            }
        }
        if (a == 2){
            if (farmer.collides(cow.getCowBounds())) {
                sb.draw(cow.getCowDead(), cow.getPosition().x, cow.getPosition().y, 70, 45);
            } else {
                sb.draw(cow.getCow(), cow.getPosition().x, cow.getPosition().y, 70, 45);
            }
        }
        if (a == 3){
            if (farmer.collides(pig.getPigBounds())) {
                sb.draw(pig.getPigDead(), pig.getPosition().x, pig.getPosition().y, 70, 45);
            } else {
                sb.draw(pig.getPig(), pig.getPosition().x, pig.getPosition().y, 70, 45);
            }
        }
        if (a == 4){
            if (farmer.collides(bunny.getBoundsBunny())) {
                sb.draw(bunny.getBunnyDead(), bunny.getPosition().x, bunny.getPosition().y, 50,50);
            } else {
                sb.draw(bunny.getBunny(), bunny.getPosition().x, bunny.getPosition().y, 50, 50);
            }
        }
        if (a == 5){
            if (farmer.collides(chick.getChickBounds())) {
                sb.draw(chick.getChickDead(), chick.getPosition().x, chick.getPosition().y, 32, 32);
            } else {
                sb.draw(chick.getChick(), chick.getPosition().x, chick.getPosition().y, 32, 32);
            }
        }
        sb.draw(farmer.getFarmer(), farmer.getPosition().x, farmer.getPosition().y,120,110);
        sb.end();
    }

    @Override
    public void dispose() {
        if (a == 1){
            sheep.dispose();
        } else if (a == 2){
            cow.dispose();
        } else if (a == 3){
            pig.dispose();
        } else if (a == 4){
            bunny.dispose();
        } else if (a == 5){
            chick.dispose();
        }
        farmer.dispose();
        sky.dispose();
        hills.dispose();
        ground.dispose();
        farmer.dispose();
        mudTexture.dispose();
        mud.dispose();
        carrotTexture.dispose();
        carrot.dispose();
        spikesTexture.dispose();
        spikes.dispose();
    }
}

