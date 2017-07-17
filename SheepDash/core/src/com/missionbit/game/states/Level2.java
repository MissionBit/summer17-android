package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.obstacles.Barrel;
import com.missionbit.game.sprites.obstacles.Mud;
import com.missionbit.game.sprites.animals.Sheep;
import com.missionbit.game.sprites.obstacles.Obstacle;

import java.util.Random;

/**
 * Created by missionbit on 6/28/17.
 */

public class Level2 extends State {
    private Sheep sheep;
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


    public Level2(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, GameTutorial.WIDTH / 2, GameTutorial.HEIGHT / 2);
        sheep = new Sheep(150, 60);
        farmer = new Farmer(-50, 60);
        ground = new Texture("CornFieldGround.png");
        background = new Texture("CornField.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(ground.getWidth() + groundPos1.x, GROUND_Y_OFFSET);
        bgPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        bgPos2 = new Vector2(background.getWidth() + bgPos1.x, 0);
        bgPos3 = new Vector2(background.getWidth() + bgPos2.x, 0);
        //OBSTACLES
        mushroomTexture = new Texture("Mushroom.png");
        mushroom = new Obstacle(mushroomTexture, 700, 50, 2, 0.3f);
        mushroomIsTouched = false;
        mud = new Mud(260, 37);
        appleTexture = new Texture("Apple.png");
        apple = new Obstacle(appleTexture, 1000, 50, 2, 0.5f);
        appleIsTouched = false;
        barrel = new Barrel(300, 60);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            sheep.jump();
        }
    }

    @Override
    public void create() {

    }

    public void update(float dt) {
        handleInput();
        sheep.update(dt);
        mushroom.update(dt);
        updateMushroom();
        apple.update(dt);
        updateApple();
        updateMud();
        updateBarrel();
        cam.position.x = sheep.getPosition().x + 80;
        farmer.update(dt);
        updateBg();
        updateGround();
        collisionCheck();
        timerCheck(dt);
        changeLevels();
        cam.update();
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
        if (apple.collides(sheep.getBounds1())) {
            appleIsTouched = true;
            sheep.increaseSpd();
            sheep.startTimer();
        }
        if (barrel.collides(sheep.getBounds1())) {
            sheep.reduceSpd();
            sheep.startTimer();
        }
        if (mushroom.collides(sheep.getBounds1())) {
            mushroomIsTouched = true;
            sheep.goBackwards();
            sheep.startTimer();
        }
    }

    public void timerCheck(float timePassed) {
        sheep.updateTimer(timePassed);
        if (sheep.isTimerDone()) {
            sheep.resetSpd();
        }
    }

    public void updateBg() {
        if (bgPos1.x + background.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            bgPos1.add(3 * background.getWidth(), 0);
        }
        if (bgPos2.x + background.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            bgPos2.add(3 * background.getWidth(), 0);
        }
        if (bgPos3.x + background.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            bgPos3.add(3 * background.getWidth(), 0);
        }
    }

    public void updateGround() {
        if (groundPos1.x + ground.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            groundPos1.add(2 * ground.getWidth(), 0);
        }
        if (groundPos2.x + ground.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            groundPos2.add(2 * ground.getWidth(), 0);
        }

    }

    public void changeLevels() {
        if (sheep.getPosition().x > 3000) {
            gsm.set(new Level3(gsm));
        }
    }

    public void updateMushroom() {
        if (cam.position.x - cam.viewportWidth / 2 > mushroom.getPosObs().x + mushroom.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 2500) + GameTutorial.WIDTH;
            mushroom.reposition(mushroom.getPosObs().x + distance, 58);
            mushroomIsTouched = false;
        }
    }

    public void updateApple() {
        if (cam.position.x - cam.viewportWidth / 2 > apple.getPosObs().x + apple.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 800) + GameTutorial.WIDTH;
            apple.reposition(apple.getPosObs().x + distance, 58);
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
        sb.draw(barrel.getBarrel(), barrel.getPosBarrel().x, barrel.getPosBarrel().y);
        if (appleIsTouched == false) {
            sb.draw(apple.getObsAnimation(), apple.getPosObs().x, apple.getPosObs().y);
        }
        if (mushroomIsTouched == false) {
            sb.draw(mushroom.getObsAnimation(), mushroom.getPosObs().x, mushroom.getPosObs().y);
        }
        if (farmer.collides(sheep.getBounds1())) {
            sb.draw(sheep.getSheepDead(), sheep.getPosition().x, sheep.getPosition().y, 70, 45);
        } else {
            sb.draw(sheep.getSheep(), sheep.getPosition().x, sheep.getPosition().y, 70, 45);
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
        farmer.dispose();
        ground.dispose();
        background.dispose();
        mud.dispose();
    }
}