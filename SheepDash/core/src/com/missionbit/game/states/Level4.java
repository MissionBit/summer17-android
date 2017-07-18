package com.missionbit.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.animals.Bunny;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.animals.Chick;
import com.missionbit.game.sprites.animals.Cow;
import com.missionbit.game.sprites.animals.Pig;
import com.missionbit.game.sprites.obstacles.Poop;
import com.missionbit.game.sprites.animals.Sheep;
import com.missionbit.game.sprites.obstacles.Obstacle;

import java.util.Random;

/**
 * Created by missionbit on 6/28/17.
 */

public class Level4 extends State {
    private Texture sky;
    private Farmer farmer;
    private Texture cars;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Vector2 skyPos, skyPos2;
    private Vector2 carsPos, carsPos2, carsPos3;
    private static final int POOP_WIDTH = 30;
    //CHARACTERS
    private Sheep sheep;
    private Bunny bunny;
    private Chick chick;
    private Cow cow;
    private Pig pig;
    //OBSTACLES
    private Texture greenCarTexture;
    private Obstacle greenCar;
    private Texture appleTexture;
    private Obstacle apple;
    private boolean appleIsTouched;
    private Obstacle poop;
    private Texture poopTexture;
    private boolean poopIsTouched;
    long startTime;
    private static final int CAR_WIDTH = 270;
    private static final int SKY_WIDTH = 800;
    private static final int GROUND_WIDTH = 790;
    //
    private int a;
    SpriteBatch batch;
    BitmapFont font;

    public Level4(GameStateManager gsm, int c) {
        super(gsm);
        a = c;
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
        //--//
        sky = new Texture("sunCloudsForHighway.png");
        farmer = new Farmer(-50, 60);
        cars = new Texture("carsForHighway.png");
        ground = new Texture("highwayBackground.png");
        cam.setToOrtho(false, GameTutorial.WIDTH / 2, GameTutorial.HEIGHT / 2);
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        groundPos2 = new Vector2(ground.getWidth() + groundPos1.x, 0);
        skyPos = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        skyPos2 = new Vector2(sky.getWidth() + skyPos.x, 0);
        carsPos = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        carsPos2 = new Vector2(cars.getWidth() + carsPos.x, 0);
        carsPos3 = new Vector2(cars.getWidth() + carsPos2.x, 0);
        //OBSTACLES
        greenCarTexture = new Texture("CarGreen.png");
        greenCar = new Obstacle(greenCarTexture, 1000, 35, 1, 0.5f);
        appleTexture = new Texture("Apple2.png");
        apple = new Obstacle(appleTexture, 700, 150, 2, 0.5f);
        appleIsTouched = false;
        poopTexture = new Texture("poop.png");
        poop = new Obstacle(poopTexture, 800, 60, 1, 0.5f);
        poopIsTouched = false;
        startTime = System.currentTimeMillis();
        batch = new SpriteBatch();
        font = new BitmapFont();

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
                gsm.set(new Level5(gsm, a));
            }
        }
        if (a == 2){
            cow.update(dt);
            cam.position.x = cow.getPosition().x + 80;
            if(System.currentTimeMillis() - startTime > 45000 && !farmer.collides(cow.getCowBounds())) {
                gsm.set(new Level5(gsm, a));
            }
        }
        if (a == 3){
            pig.update(dt);
            cam.position.x = pig.getPosition().x + 80;
            if(System.currentTimeMillis() - startTime > 45000 && !farmer.collides(pig.getPigBounds())) {
                gsm.set(new Level5(gsm, a));
            }
        }
        if (a == 4){
            bunny.update(dt);
            cam.position.x = bunny.getPosition().x + 80;
            if(System.currentTimeMillis() - startTime > 45000 && !farmer.collides(bunny.getBoundsBunny())) {
                gsm.set(new Level5(gsm, a));
            }
        }
        if (a == 5){
            chick.update(dt);
            cam.position.x = chick.getPosition().x + 80;
            if(System.currentTimeMillis() - startTime > 45000 && !farmer.collides(chick.getChickBounds())) {
                gsm.set(new Level5(gsm, a));
            }
        }
        //--//
        farmer.update(dt);
        updateGround();
        updateSky();
        updateCars();
        timerCheck(dt);
        apple.update(dt);
        updateApple();
        updatePoops();
        updateGreen();
        collisionCheck();
        cam.update();
    }


    public void updateGreen() {
        if (cam.position.x - cam.viewportWidth / 2 > greenCar.getPosObs().x + greenCar.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 1500) + GameTutorial.WIDTH;
            greenCar.reposition(greenCar.getPosObs().x + distance, 35);
        }
    }

    public void updatePoops(){
        if (cam.position.x - cam.viewportWidth / 2 > poop.getPosObs().x + poop.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 1500) + GameTutorial.WIDTH;
            poop.reposition(poop.getPosObs().x + distance, 60);
            poopIsTouched = false;
        }
    }

    public void updateApple() {
        if (cam.position.x - cam.viewportWidth / 2 > apple.getPosObs().x + apple.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 700) + GameTutorial.WIDTH;
            apple.reposition(apple.getPosObs().x + distance, 150);
            appleIsTouched = false;
        }
    }


    public void updateGround(){
        if(groundPos1.x+ground.getWidth() <= cam.position.x-cam.viewportWidth/2){
            groundPos1.add(2*ground.getWidth(),0);
        }
        if(groundPos2.x+ground.getWidth() <= cam.position.x-cam.viewportWidth/2){
            groundPos2.add(2*ground.getWidth(),0);
        }

    }

    public void updateSky(){
        if(skyPos.x+sky.getWidth() <= cam.position.x-cam.viewportWidth/2){
            skyPos.add(2*sky.getWidth(),0);
        }
        if(skyPos2.x+sky.getWidth() <= cam.position.x-cam.viewportWidth/2){
            skyPos2.add(2*sky.getWidth(),0);
        }

    }

    public void updateCars(){
        if(carsPos.x+cars.getWidth() <= cam.position.x-cam.viewportWidth/2){
            carsPos.add(3*cars.getWidth(),0);
        }
        if(carsPos2.x+cars.getWidth() <= cam.position.x-cam.viewportWidth/2){
            carsPos2.add(3*cars.getWidth(),0);
        }

        if(carsPos3.x+cars.getWidth()<= cam.position.x-cam.viewportWidth/2){
            carsPos3.add(3*cars.getWidth(),0);
        }

    }

    public void collisionCheck() {

        //for sheep
        if (a == 1) {
            if (farmer.collides(sheep.getBounds1())){
                sheep.getSheepDead();
                sheep.sheepDied();
                farmer.killed();
                gameOver(4, a);
            }
            if (poop.collides(sheep.getBounds1())||greenCar.collides(sheep.getBounds1())) {
                sheep.reduceSpd();
                sheep.startTimer();
                poopIsTouched = true;
            }
            if (apple.collides(sheep.getBounds1())) {
                appleIsTouched = true;
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
                gameOver(4, a);
            }
            if (poop.collides(cow.getCowBounds())||greenCar.collides(cow.getCowBounds())){
                cow.reduceSpd();
                cow.startTimer();
                poopIsTouched = true;
            }
            if (apple.collides(cow.getCowBounds())) {
                appleIsTouched = true;
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
                gameOver(4, a);
            }
            if (poop.collides(pig.getPigBounds())||greenCar.collides(pig.getPigBounds())){
                pig.reduceSpd();
                pig.startTimer();
                poopIsTouched = true;
            }
            if (apple.collides(pig.getPigBounds())) {
                appleIsTouched = true;
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
                gameOver(4, a);
            }
            if (poop.collides(bunny.getBoundsBunny())||greenCar.collides(bunny.getBoundsBunny())) {
                bunny.reduceSpd();
                bunny.startTimer();
                poopIsTouched = true;
            }
            if (apple.collides(bunny.getBoundsBunny())) {
                appleIsTouched = true;
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
                gameOver(4, a);
            }
            if (poop.collides(chick.getChickBounds()) || greenCar.collides(chick.getChickBounds())) {
                chick.reduceSpd();
                chick.startTimer();
                poopIsTouched = true;
            }
            if (apple.collides(chick.getChickBounds())) {
                appleIsTouched = true;
                chick.increaseSpd();
                chick.startTimer();
            }
        }
    }

    public void changeLevels(){
       // if (sheep.getPosition().x > 3000){
        //    gsm.set(new Level5(gsm));

        if (sheep.getPosition().x > 4500){
            gsm.set(new Level5(gsm, a));
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
        sb.draw(sky,skyPos.x,0,SKY_WIDTH,400);
        sb.draw(sky,skyPos2.x,0,SKY_WIDTH,400);
        sb.draw(ground,groundPos1.x,0,GROUND_WIDTH,350);
        sb.draw(ground,groundPos2.x,0,GROUND_WIDTH,350);
        sb.draw(cars,carsPos.x,20,CAR_WIDTH,300);
        sb.draw(cars,carsPos2.x,20,CAR_WIDTH,300);
        sb.draw(cars,carsPos3.x,20,CAR_WIDTH,300);
        if (poopIsTouched== false) {
            sb.draw(poop.getObsAnimation(), poop.getPosObs().x, poop.getPosObs().y,30,30);
        }
        sb.draw(greenCar.getObstacle(), greenCar.getPosObs().x, greenCar.getPosObs().y);
        if (appleIsTouched == false) {
            sb.draw(apple.getObsAnimation(), apple.getPosObs().x, apple.getPosObs().y);
        }
        //ANIMALLLS
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
        //--//
        sb.draw(farmer.getFarmer(),farmer.getPosition().x,farmer.getPosition().y,120,110);
        sb.end();

        batch.begin();
        font.setColor(Color.WHITE);
        font.getData().setScale(2, 2);
        font.draw(batch, ((46000 - (System.currentTimeMillis() - startTime)) / 1000) + " ", GameTutorial.WIDTH / 2, GameTutorial.HEIGHT);
        batch.end();
    }

    @Override
    public void dispose() {
        sky.dispose();
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
        cars.dispose();
        ground.dispose();
        poop.dispose();
        greenCarTexture.dispose();
        greenCar.dispose();
        appleTexture.dispose();
        apple.dispose();
    }
}
