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
import com.missionbit.game.sprites.obstacles.Barrel;
import com.missionbit.game.sprites.obstacles.Mud;
import com.missionbit.game.sprites.animals.Sheep;
import com.missionbit.game.sprites.obstacles.Obstacle;

import java.util.Random;

/**
 * Created by missionbit on 6/28/17.
 */

public class Level2 extends State {

    //CHARACTERS
    private Sheep sheep;
    private Bunny bunny;
    private Chick chick;
    private Cow cow;
    private Pig pig;
    //others
    private Farmer farmer;
    private Texture background;
    private Texture ground;
    private Vector2 bgPos1, bgPos2, bgPos3;
    private Vector2 groundPos1, groundPos2;
    //OBSTACLES
    private Texture mushroomTexture;
    private Obstacle mushroom;
    private boolean mushroomIsTouched;
    private Texture appleTexture;
    private Obstacle apple;
    private boolean appleIsTouched;
    private Mud mud;
    private Barrel barrel;
    private static final int GROUND_Y_OFFSET = -80;
    private static final int bg_width = 1300;
    private static final int ground_width = 800;
    private static final int MUD_SPACING = 250;
    private static final int MUD_WIDTH = 30;
    private static final int BARREL_WIDTH = 30;
    private int a;
    long startTime;

    public Level2(GameStateManager gsm, int c) {
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
        ground = new Texture("CornFieldGround.png");
        background = new Texture("CornField.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2 - ground.getWidth(), GROUND_Y_OFFSET);
        groundPos2 = new Vector2(ground.getWidth() + groundPos1.x, GROUND_Y_OFFSET);
        bgPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2 - bg_width, 0);
        bgPos2 = new Vector2(background.getWidth() + bgPos1.x, 0);
        bgPos3 = new Vector2(background.getWidth() + bgPos2.x, 0);
        //OBSTACLES
        mushroomTexture = new Texture("Mushroom.png");
        mushroom = new Obstacle(mushroomTexture, 700, 70, 2, 0.3f);
        mushroomIsTouched = false;
        mud = new Mud(260, 37);
        appleTexture = new Texture("Apple.png");
        apple = new Obstacle(appleTexture, 1000, 150, 2, 0.5f);
        appleIsTouched = false;
        barrel = new Barrel(300, 60);
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched()) {
            if ( a == 5){
                sheep.jump();
            }
            if (a == 2){
                cow.jump();
            }
            if (a == 3){
                pig.jump();
            }
            if (a == 4){
                bunny.jump();
            }
            if ( a == 5){
                chick.jump();
            }
        }
    }

    @Override
    public void create() {

    }

    public void update(float dt) {
        handleInput();
        //the characters (this is getting tiring and repetitive but I guess that's what programmimg is yay
        if (a == 1){
            sheep.update(dt);
            cam.position.x = sheep.getPosition().x + 80;
            if(((System.currentTimeMillis() - startTime) > 45000 & farmer.collides(sheep.getBounds1()) == false)) {
                gsm.set(new Level3(gsm, a));
            }
        }
        if (a == 2){
            cow.update(dt);
            cam.position.x = cow.getPosition().x + 80;
            if(((System.currentTimeMillis() - startTime) > 45000 & farmer.collides(cow.getCowBounds()) == false)) {
                gsm.set(new Level3(gsm, a));
            }
        }
        if (a == 3){
            pig.update(dt);
            cam.position.x = pig.getPosition().x + 80;
            if(((System.currentTimeMillis() - startTime) > 45000 & farmer.collides(pig.getPigBounds()) == false)) {
                gsm.set(new Level3(gsm, a));
            }
        }
        if (a == 4){
            bunny.update(dt);
            cam.position.x = bunny.getPosition().x + 80;
            if(((System.currentTimeMillis() - startTime) > 45000 & farmer.collides(bunny.getBoundsBunny()) == false)) {
                gsm.set(new Level3(gsm, a));
            }
        }
        if (a == 5){
            chick.update(dt);
            cam.position.x = chick.getPosition().x + 80;
            if(((System.currentTimeMillis() - startTime) > 45000 & farmer.collides(chick.getChickBounds()) == false)) {
                gsm.set(new Level3(gsm, a));
            }
        }
        mushroom.update(dt);
        updateMushroom();
        apple.update(dt);
        updateApple();
        updateMud();
        updateBarrel();
        farmer.update(dt);
        updateBg();
        updateGround();
        timerCheck(dt);
        changeLevels();
        collisionCheck();
        cam.update();
    }

    public void collisionCheck() {
        //for sheep
        if (a == 1) {
            if (farmer.collides(sheep.getBounds1())){
                sheep.getSheepDead();
                sheep.sheepDied();
                farmer.killed();
              gameOver(2);
            }
            if (mud.collides(sheep.getBounds1())||barrel.collides(sheep.getBounds1())) {
                sheep.reduceSpd();
                sheep.startTimer();
            }
            if (apple.collides(sheep.getBounds1())) {
                appleIsTouched = true;
                sheep.increaseSpd();
                sheep.startTimer();
            }
            if (mushroom.collides(sheep.getBounds1())) {
                mushroomIsTouched = true;
                sheep.goBackwards();
                sheep.startTimer();
            }
        }
//for cow
        if (a == 2){
            if (farmer.collides(cow.getCowBounds())){
                cow.getCowDead();
                cow.cowDied();
                farmer.killed();
              gameOver(2);
            }
            if (mud.collides(cow.getCowBounds())||barrel.collides(cow.getCowBounds())){
                cow.reduceSpd();
                cow.startTimer();
            }
            if (apple.collides(cow.getCowBounds())) {
                appleIsTouched = true;
                cow.increaseSpd();
                cow.startTimer();
            }
            if (mushroom.collides(cow.getCowBounds())) {
                mushroomIsTouched = true;
                cow.goBackwards();
                cow.startTimer();
            }
        }
//for pig
        if(a == 3){
            if (farmer.collides(pig.getPigBounds())){
                pig.getPigDead();
                pig.pigDied();
                farmer.killed();
              gameOver(2);
            }
            if (mud.collides(pig.getPigBounds())||barrel.collides(pig.getPigBounds())){
                pig.reduceSpd();
                pig.startTimer();
            }
            if (apple.collides(pig.getPigBounds())) {
                appleIsTouched = true;
                pig.increaseSpd();
                pig.startTimer();
            }
            if (mushroom.collides(pig.getPigBounds())) {
                mushroomIsTouched = true;
                pig.goBackwards();
                pig.startTimer();
            }
        }
//for bunny
        if (a == 4){
            if (farmer.collides(bunny.getBoundsBunny())){
                bunny.getBunnyDead();
                bunny.bunnyDied();
                farmer.killed();
              gameOver(2);
            }
            if (mud.collides(bunny.getBoundsBunny())||barrel.collides(bunny.getBoundsBunny())) {
                bunny.reduceSpd();
                bunny.startTimer();
            }
            if (apple.collides(bunny.getBoundsBunny())) {
                appleIsTouched = true;
                bunny.increaseSpd();
                bunny.startTimer();
            }
            if (mushroom.collides(bunny.getBoundsBunny())) {
                mushroomIsTouched = true;
                bunny.goBackwards();
                bunny.startTimer();
            }
        }
//for chick
        if (a == 5) {
            if (farmer.collides(chick.getChickBounds())) {
                chick.getChickDead();
                chick.chickDied();
                farmer.killed();
              gameOver(2);
            }
            if (mud.collides(chick.getChickBounds()) || barrel.collides(chick.getChickBounds())) {
                chick.reduceSpd();
                chick.startTimer();
            }
            if (apple.collides(chick.getChickBounds())) {
                appleIsTouched = true;
                chick.increaseSpd();
                chick.startTimer();
            }
            if (mushroom.collides(chick.getChickBounds())) {
                mushroomIsTouched = true;
                chick.goBackwards();
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

    public void updateBg() {
        if (bgPos1.x + 2*background.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            bgPos1.add(3 * background.getWidth(), 0);
        }
        if (bgPos2.x + 2*background.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            bgPos2.add(3 * background.getWidth(), 0);
        }
        if (bgPos3.x + 2*background.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            bgPos3.add(3 * background.getWidth(), 0);
        }
    }

    public void updateGround() {
        if (groundPos1.x + 2*ground.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            groundPos1.add(2 * ground.getWidth(), 0);
        }
        if (groundPos2.x + 2*ground.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            groundPos2.add(2 * ground.getWidth(), 0);
        }

    }

    public void changeLevels() {
        if(((System.currentTimeMillis() - startTime) > 30000 & farmer.collides(sheep.getBounds1()) == false)) {
            gsm.set(new Level3(gsm));
        }

    }

    public void updateMushroom() {
        if (cam.position.x - cam.viewportWidth / 2 > mushroom.getPosObs().x + mushroom.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 2500) + GameTutorial.WIDTH;
            mushroom.reposition(mushroom.getPosObs().x + distance, 70);
            mushroomIsTouched = false;
        }
    }

    public void updateApple() {
        if (cam.position.x - cam.viewportWidth / 2 > apple.getPosObs().x + apple.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 800) + GameTutorial.WIDTH;
            apple.reposition(apple.getPosObs().x + distance, 70);
            appleIsTouched = false;
        }
    }

    public void updateMud() {
        if (mud.getPosMud().x + MUD_WIDTH <= cam.position.x - cam.viewportWidth / 2) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 600) + GameTutorial.WIDTH;
            mud.reposition(mud.getPosMud().x + distance, 37);
            mud.setCollided(false);
        }
    }

    public void updateBarrel() {
        if (barrel.getPosBarrel().x + BARREL_WIDTH <= cam.position.x - cam.viewportWidth / 2) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 300) + GameTutorial.WIDTH;
            barrel.reposition(barrel.getPosBarrel().x + distance, 60);
            barrel.setCollided(false);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background, bgPos1.x, 0, bg_width, 400);
        sb.draw(background, bgPos2.x, 0, bg_width, 400);
        sb.draw(background, bgPos3.x, 0, bg_width, 400);
        sb.draw(ground, groundPos1.x, 0, ground_width, 260);
        sb.draw(ground, groundPos2.x, 0, ground_width, 260);
        sb.draw(mud.getMud(), mud.getPosMud().x, mud.getPosMud().y, 40, 30);
        sb.draw(barrel.getBarrel(), barrel.getPosBarrel().x, barrel.getPosBarrel().y,50,40);
        if (appleIsTouched == false) {
            sb.draw(apple.getObsAnimation(), apple.getPosObs().x, apple.getPosObs().y);
        }
        if (mushroomIsTouched == false) {
            sb.draw(mushroom.getObsAnimation(), mushroom.getPosObs().x, mushroom.getPosObs().y);
        }
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
                sb.draw(bunny.getBunnyDead(), bunny.getPosition().x, bunny.getPosition().y, 50, 50);
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
        sb.draw(farmer.getFarmer(), farmer.getPosition().x, farmer.getPosition().y, 120, 110);
        sb.end();
    }

    @Override
    public void dispose() {
        mushroomTexture.dispose();
        mushroom.dispose();
        appleTexture.dispose();
        apple.dispose();
        barrel.dispose();
        sheep.dispose();
        bunny.dispose();
        chick.dispose();
        cow.dispose();
        pig.dispose();
        farmer.dispose();
        ground.dispose();
        background.dispose();
        mud.dispose();
    }
}
