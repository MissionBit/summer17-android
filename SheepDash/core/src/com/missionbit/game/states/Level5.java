package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.animals.Bunny;
import com.missionbit.game.sprites.animals.Chick;
import com.missionbit.game.sprites.animals.Cow;
import com.missionbit.game.sprites.animals.Pig;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.animals.Sheep;
import com.missionbit.game.sprites.obstacles.Obstacle;

import java.util.Random;

/**
 * Created by missionbit on 7/5/17.
 */

public class Level5 extends State {
    private Texture sky;
    private Sheep sheep;
    private Bunny bunny;
    private Chick chick;
    private Cow cow;
    private Pig pig;

    private Farmer farmer;
    private Texture buildings;
    private Texture ground;
    private Vector2 groundPos1, groundPos2, groundPos3;
    private Vector2 skyPos, skyPos2;
    private Vector2 buildingsPos, buildingsPos2, buildingsPos3, buildingsPos4, buildingsPos5;

    //Obstacles
    private Texture greyTexture;
    private Obstacle greyCar;
    private Texture mushroomTexture;
    private Obstacle mushroom;
    private boolean mushroomIsTouched;
    private Texture cherryTexture;
    private Obstacle cherry;
    private boolean cherryIsTouched;
    private Texture spikeTexture;
    private Obstacle spikes;
    private static final int BUILDINGS_WIDTH = 193;
    private static final int GROUND_WIDTH = 550;
    private static final int SKY_WIDTH = 800;
    long startTime;
    SpriteBatch batch;
    BitmapFont font;
    private int a;

    public Level5(GameStateManager gsm, int c) {
        super(gsm);
        a = c;
        // INITIALIZING ANIMALS
        if (a == 1) {
            sheep = new Sheep(150, 60);
        }
        if (a == 2) {
            cow = new Cow(150, 60);
        }
        if (a == 3) {
            pig = new Pig(150, 60);
        }
        if (a == 4) {
            bunny = new Bunny(150, 60);
        }
        if (a == 5) {
            chick = new Chick(150, 60);
        }

        sky = new Texture("CitySky.png");
        farmer = new Farmer(-60,60);
        buildings = new Texture("UpdatedCityBuildings.png");
        ground = new Texture("CityGround.png");
        cam.setToOrtho(false, GameTutorial.WIDTH / 2, GameTutorial.HEIGHT / 2);
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2 - ground.getWidth(), 0);
        groundPos2 = new Vector2(ground.getWidth() + groundPos1.x, 0);
        groundPos3 = new Vector2(ground.getWidth() + groundPos2.x, 0);
        skyPos = new Vector2(cam.position.x - cam.viewportWidth / 2 - sky.getWidth(), 0);
        skyPos2 = new Vector2(sky.getWidth() + skyPos.x, 0);
        buildingsPos = new Vector2(cam.position.x - cam.viewportWidth / 2 - BUILDINGS_WIDTH, 0);
        buildingsPos2 = new Vector2(buildingsPos.x + BUILDINGS_WIDTH, 0);
        buildingsPos3 = new Vector2(2 * BUILDINGS_WIDTH + buildingsPos.x, 0);
        buildingsPos4 = new Vector2(3 * BUILDINGS_WIDTH + buildingsPos.x, 0);
        buildingsPos5 = new Vector2(4 * BUILDINGS_WIDTH + buildingsPos.x, 0);

        //Obstacles
        greyTexture = new Texture("CarGrey.png");
        greyCar = new Obstacle(greyTexture, 700, 48, 1, 0.5f);
        mushroomTexture = new Texture("Mushroom.png");
        mushroom = new Obstacle(mushroomTexture, 2000, 70, 2, 0.2f);
        mushroomIsTouched = false;
        cherryTexture = new Texture("Cherry2_0.35.png");
        cherry = new Obstacle(cherryTexture, 1000, 150, 2, 0.35f);
        cherryIsTouched = false;
        spikeTexture = new Texture("SPIKES2.0.18.png");
        spikes = new Obstacle(spikeTexture, 1700, 50, 2, 0.5f);
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
        //AGGGH
        if (a == 1) {
            sheep.update(dt);
            cam.position.x = sheep.getPosition().x + 80;
            if (System.currentTimeMillis() - startTime > 50000 && !farmer.collides(sheep.getBounds1())) {
                gsm.set(new YouWon(gsm,5,a));
            }
        }
        if (a == 2) {
            cow.update(dt);
            cam.position.x = cow.getPosition().x + 80;
            if (System.currentTimeMillis() - startTime > 50000 && !farmer.collides(cow.getCowBounds())) {
                gsm.set(new YouWon(gsm,5,a));
            }
        }
        if (a == 3) {
            pig.update(dt);
            cam.position.x = pig.getPosition().x + 80;
            if (System.currentTimeMillis() - startTime > 50000 && !farmer.collides(pig.getPigBounds())) {
                gsm.set(new YouWon(gsm,5,a));
            }
        }
        if (a == 4) {
            bunny.update(dt);
            cam.position.x = bunny.getPosition().x + 80;
            if (System.currentTimeMillis() - startTime > 50000 && !farmer.collides(bunny.getBoundsBunny())) {
                gsm.set(new YouWon(gsm,5,a));
            }
        }
        if (a == 5) {
            chick.update(dt);
            cam.position.x = chick.getPosition().x + 80;
            if (System.currentTimeMillis() - startTime > 50000 && !farmer.collides(chick.getChickBounds())) {
                gsm.set(new YouWon(gsm,5,a));
            }
        }
        farmer.update(dt);
        mushroom.update(dt);
        cherry.update(dt);
        spikes.update(dt);
        updateGround();
        updateSky();
        updateBuildings();
        updateGrey();
        updateMushroom();
        updateCherry();
        updateSpikes();
        timerCheck(dt);
        collisionCheck();
        cam.update();
    }

    public void timerCheck(float timePassed) {
        if (a == 1) {
            sheep.updateTimer(timePassed);
            if (sheep.isTimerDone()) {
                sheep.resetSpd();
            }
        }
        if (a == 2) {
            cow.updateTimer(timePassed);
            if (cow.isTimerDone()) {
                cow.resetSpd();
            }
        }
        if (a == 3) {
            pig.updateTimer(timePassed);
            if (pig.isTimerDone()) {
                pig.resetSpd();
            }
        }
        if (a == 4) {
            bunny.updateTimer(timePassed);
            if (bunny.isTimerDone()) {
                bunny.resetSpd();
            }
        }
        if (a == 5) {
            chick.updateTimer(timePassed);
            if (chick.isTimerDone()) {
                chick.resetSpd();
            }
        }
    }

    public void updateGround() {
        if (groundPos1.x + 2 * ground.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            groundPos1.add(3 * ground.getWidth(), 0);
        }
        if (groundPos2.x + 2 * ground.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            groundPos2.add(3 * ground.getWidth(), 0);
        }
        if (groundPos3.x + 2 * ground.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            groundPos3.add(3 * ground.getWidth(), 0);
        }

    }

    public void updateSky() {
        if (skyPos.x + 2 * sky.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            skyPos.add(2 * sky.getWidth(), 0);
        }
        if (skyPos2.x + 2 * sky.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            skyPos2.add(2 * sky.getWidth(), 0);
        }

    }

    public void updateBuildings() {
        if (buildingsPos.x + 2 * BUILDINGS_WIDTH <= cam.position.x - cam.viewportWidth / 2) {
            buildingsPos.add(5 * BUILDINGS_WIDTH, 0);
        }
        if (buildingsPos2.x + 2 * BUILDINGS_WIDTH <= cam.position.x - cam.viewportWidth / 2) {
            buildingsPos2.add(5 * BUILDINGS_WIDTH, 0);
        }
        if (buildingsPos3.x + 2 * BUILDINGS_WIDTH <= cam.position.x - cam.viewportWidth / 2) {
            buildingsPos3.add(5 * BUILDINGS_WIDTH, 0);
        }
        if (buildingsPos4.x + 2 * BUILDINGS_WIDTH <= cam.position.x - cam.viewportWidth / 2) {
            buildingsPos4.add(5 * BUILDINGS_WIDTH, 0);
        }
        if (buildingsPos5.x + 2 * BUILDINGS_WIDTH <= cam.position.x - cam.viewportWidth / 2) {
            buildingsPos5.add(5 * BUILDINGS_WIDTH, 0);
        }
    }

    public void updateGrey() {
        if (cam.position.x - cam.viewportWidth / 2 > greyCar.getPosObs().x + greyCar.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 600) + GameTutorial.WIDTH;
            greyCar.reposition(greyCar.getPosObs().x + distance, 48);
        }
    }

    public void updateMushroom() {
        if (cam.position.x - cam.viewportWidth / 2 > mushroom.getPosObs().x + mushroom.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 1300) + GameTutorial.WIDTH;
            mushroom.reposition(mushroom.getPosObs().x + distance, 70);
            mushroomIsTouched = false;
        }
    }

    public void updateCherry() {
        if (cam.position.x - cam.viewportWidth / 2 > cherry.getPosObs().x + cherry.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 850) + GameTutorial.WIDTH;
            cherry.reposition(cherry.getPosObs().x + distance, 150);
            cherryIsTouched = false;
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
            if (farmer.collides(sheep.getBounds1())) {
                sheep.getSheepDead();
                sheep.sheepDied();
                farmer.killed();
                gameOver(5, a);
            }
            if (spikes.collides(sheep.getBounds1()) || greyCar.collides(sheep.getBounds1())) {
                sheep.reduceSpd();
                sheep.startTimer();
            }
            if (cherry.collides(sheep.getBounds1())) {
                cherryIsTouched = true;
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
        if (a == 2) {
            if (farmer.collides(cow.getCowBounds())) {
                cow.getCowDead();
                cow.cowDied();
                farmer.killed();
                gameOver(5, a);
            }
            if (spikes.collides(cow.getCowBounds()) || greyCar.collides(cow.getCowBounds())) {
                cow.reduceSpd();
                cow.startTimer();
            }
            if (cherry.collides(cow.getCowBounds())) {
                cherryIsTouched = true;
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
        if (a == 3) {
            if (farmer.collides(pig.getPigBounds())) {
                pig.getPigDead();
                pig.pigDied();
                farmer.killed();
                gameOver(5, a);
            }
            if (spikes.collides(pig.getPigBounds()) || greyCar.collides(pig.getPigBounds())) {
                pig.reduceSpd();
                pig.startTimer();
            }
            if (cherry.collides(pig.getPigBounds())) {
                cherryIsTouched = true;
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
        if (a == 4) {
            if (farmer.collides(bunny.getBoundsBunny())) {
                bunny.getBunnyDead();
                bunny.bunnyDied();
                farmer.killed();
                gameOver(5, a);
            }
            if (spikes.collides(bunny.getBoundsBunny()) || greyCar.collides(bunny.getBoundsBunny())) {
                bunny.reduceSpd();
                bunny.startTimer();
            }
            if (cherry.collides(bunny.getBoundsBunny())) {
                cherryIsTouched = true;
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
                gameOver(5, a);
            }
            if (spikes.collides(chick.getChickBounds()) || greyCar.collides(chick.getChickBounds())) {
                chick.reduceSpd();
                chick.startTimer();
            }
            if (cherry.collides(chick.getChickBounds())) {
                cherryIsTouched = true;
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

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(sky, skyPos.x, 0, SKY_WIDTH, 400);
        sb.draw(sky, skyPos2.x, 0, SKY_WIDTH, 400);
        sb.draw(buildings, buildingsPos.x, 0, BUILDINGS_WIDTH, 200);
        sb.draw(buildings, buildingsPos2.x, 0, BUILDINGS_WIDTH, 200);
        sb.draw(buildings, buildingsPos3.x, 0, BUILDINGS_WIDTH, 200);
        sb.draw(buildings, buildingsPos4.x, 0, BUILDINGS_WIDTH, 200);
        sb.draw(buildings, buildingsPos5.x, 0, BUILDINGS_WIDTH, 200);
        sb.draw(ground, groundPos1.x, 0, GROUND_WIDTH, 350);
        sb.draw(ground, groundPos2.x, 0, GROUND_WIDTH, 350);
        sb.draw(ground, groundPos3.x, 0, GROUND_WIDTH, 350);
        sb.draw(spikes.getObsAnimation(), spikes.getPosObs().x, spikes.getPosObs().y);
        sb.draw(greyCar.getObstacle(), greyCar.getPosObs().x, greyCar.getPosObs().y);
        if (mushroomIsTouched == false) {
            sb.draw(mushroom.getObsAnimation(), mushroom.getPosObs().x, mushroom.getPosObs().y);
        }
        if (cherryIsTouched == false) {
            sb.draw(cherry.getObsAnimation(), cherry.getPosObs().x, cherry.getPosObs().y, 90, 50);
        }
        //my comments are so helpful and well written arent they guys
        if (a == 1) {
            if (farmer.collides(sheep.getBounds1())) {
                sb.draw(sheep.getSheepDead(), sheep.getPosition().x, sheep.getPosition().y, 70, 45);
            } else {
                sb.draw(sheep.getSheep(), sheep.getPosition().x, sheep.getPosition().y, 70, 45);
            }
        }
        if (a == 2) {
            if (farmer.collides(cow.getCowBounds())) {
                sb.draw(cow.getCowDead(), cow.getPosition().x, cow.getPosition().y, 70, 45);
            } else {
                sb.draw(cow.getCow(), cow.getPosition().x, cow.getPosition().y, 70, 45);
            }
        }
        if (a == 3) {
            if (farmer.collides(pig.getPigBounds())) {
                sb.draw(pig.getPigDead(), pig.getPosition().x, pig.getPosition().y, 70, 45);
            } else {
                sb.draw(pig.getPig(), pig.getPosition().x, pig.getPosition().y, 70, 45);
            }
        }
        if (a == 4) {
            if (farmer.collides(bunny.getBoundsBunny())) {
                sb.draw(bunny.getBunnyDead(), bunny.getPosition().x, bunny.getPosition().y, 50, 50);
            } else {
                sb.draw(bunny.getBunny(), bunny.getPosition().x, bunny.getPosition().y, 50, 50);
            }
        }
        if (a == 5) {
            if (farmer.collides(chick.getChickBounds())) {
                sb.draw(chick.getChickDead(), chick.getPosition().x, chick.getPosition().y, 32, 32);
            } else {
                sb.draw(chick.getChick(), chick.getPosition().x, chick.getPosition().y, 32, 32);
            }
        }
        sb.draw(farmer.getFarmer(), farmer.getPosition().x, farmer.getPosition().y, 120, 110);
        sb.end();

        //Text to display countdown timer!1!1!
        batch.begin();
        font.setColor(Color.WHITE);
        font.getData().setScale(2, 2);
        font.draw(batch, ((40000 - (System.currentTimeMillis() - startTime)) / 1000) + " ", GameTutorial.WIDTH / 2, GameTutorial.HEIGHT);
        batch.end();
    }

    @Override
    public void dispose() {
        sky.dispose();
        buildings.dispose();
        ground.dispose();
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
        greyTexture.dispose();
        greyCar.dispose();
        mushroomTexture.dispose();
        mushroom.dispose();
        cherryTexture.dispose();
        cherry.dispose();
        spikeTexture.dispose();
        spikes.dispose();
        batch.dispose();
        font.dispose();
    }
}
