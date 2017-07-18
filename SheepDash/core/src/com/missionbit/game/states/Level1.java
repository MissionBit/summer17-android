package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.animals.Sheep;
import com.missionbit.game.sprites.obstacles.Cherry;
import com.missionbit.game.sprites.obstacles.Poop;
import com.missionbit.game.sprites.obstacles.Obstacle;

import java.util.Random;


public class Level1 extends State {
    private Texture bg;
    private Sheep sheep;
    private Farmer farmer;
    private Texture ground;
    private Texture trees;
    private Texture trees2;
    private Texture trees3;
    private Texture barn;
    private Texture shed;
    private Texture background;
    private Vector2 groundPos1;
    private Vector2 groundPos2;
    private Vector2 treePos1;
    private Vector2 treePos2;
    private Vector2 treePos3;
    private Vector2 barnPos;
    private Vector2 shedPos;
    private Vector2 bgPos1;
    private Vector2 bgPos2;
    SpriteBatch batch;
    BitmapFont font;

    //OBSTACLES
    private Texture haybaleTexture;
    private Obstacle haybale;
    private Cherry cherry;
    private boolean cherryIsTouched;
    private Poop poop;
    private static final int bg_width = 1000;
    private static final int GROUND_Y_OFFSET = -80;
    private static final int ground_width = 800;
    private static final int CHERRY_WIDTH = 30;
    private static final int CHERRY_SPACING = 300;
    private static final int POOP_WIDTH = 30;
    long startTime;

    public Level1(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, GameTutorial.WIDTH / 2, GameTutorial.HEIGHT / 2);
        bg = new Texture("FarmBg1.png");
        sheep = new Sheep(150, 60);
        farmer = new Farmer(-50, 60);
        ground = new Texture("FarmBG6.png");
        trees = new Texture("FarmBG3.png");
        trees2 = new Texture("FarmBG4.png");
        trees3 = new Texture("FarmBG5.png");
        barn = new Texture("FarmBG1.png");
        shed = new Texture("FarmBG2.png");
        background = new Texture("FarmBG7.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(ground.getWidth() + groundPos1.x, GROUND_Y_OFFSET);
        treePos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        treePos2 = new Vector2(trees.getWidth() + treePos1.x, 0);
        treePos3 = new Vector2(trees.getWidth() + treePos2.x, 0);
        barnPos = new Vector2(50, 0);
        shedPos = new Vector2(350, 0);
        bgPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        bgPos2 = new Vector2(background.getWidth() + bgPos1.x, 0);
        batch = new SpriteBatch();
        font = new BitmapFont();

        //OBSTACLES
        haybaleTexture = new Texture("haybale3.png");
        haybale = new Obstacle(haybaleTexture, 1300, 50, 1, 0.5f);
        poop = new Poop(260, 60);
        cherry = new Cherry(500, 150);
        cherryIsTouched = false;
        startTime = System.currentTimeMillis();
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

    public void update(float dt) {
        handleInput();
        updateHaybale();
        sheep.update(dt);
        cherry.update(dt);
        cam.position.x = sheep.getPosition().x + 80;
        farmer.update(dt);
        updateBg();
        updateGround();
        updateTrees();
        updateCherries();
        updatePoops();
        timerCheck(dt);
        changeLevels();
        collisionCheck();
        cam.update();

    }

    public void collisionCheck() {
        if (farmer.collides(sheep.getBounds1())) {
            sheep.getSheepDead();
            sheep.sheepDied();
            farmer.killedSheep();
            gameOver(1);
        }
        if (poop.collides(sheep.getBounds1())) {
            sheep.reduceSpd();
            sheep.startTimer();
        }
        if (cherry.collides(sheep.getBounds1())) {
            cherryIsTouched = true;
            sheep.increaseSpd();
            sheep.startTimer();
        }
        if (haybale.collides(sheep.getBounds1())) {
            sheep.reduceSpd();
            sheep.startTimer();
        }
    }

    public void timerCheck(float timePassed) {
        sheep.updateTimer(timePassed);
        if (sheep.isTimerDone()) {
            sheep.resetSpd();
        }
    }


    public void updatePoops() {
        if (poop.getPosPoop().x + POOP_WIDTH <= cam.position.x-cam.viewportWidth/2){
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 600) + GameTutorial.WIDTH;
            poop.reposition(poop.getPosPoop().x+ distance, 58);
            poop.setCollided(false);
        }
    }

    public void updateCherries() {
        if (cherry.getPosCherry().x + CHERRY_WIDTH <= cam.position.x - cam.viewportWidth / 2) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * CHERRY_SPACING) + GameTutorial.WIDTH;
            cherry.reposition(cherry.getPosCherry().x + distance, 150);
            cherry.setCollided(false);
            cherryIsTouched = false;
        }
    }

    public void updateHaybale() {
        if (cam.position.x - cam.viewportWidth / 2 > haybale.getPosObs().x + haybale.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 600) + GameTutorial.WIDTH;
            haybale.reposition(haybale.getPosObs().x + distance, 50);
        }
    }


    public void updateBg() {
        if (bgPos1.x + background.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            bgPos1.add(2 * background.getWidth(), 0);
        }
        if (bgPos2.x + background.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            bgPos2.add(2 * background.getWidth(), 0);
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
        if(((System.currentTimeMillis() - startTime) > 20000 & farmer.collides(sheep.getBounds1()) == false)) {
            gsm.set(new Level2(gsm));
        }
    }

    public void updateTrees() {
        if (treePos1.x + trees.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            treePos1.add(3 * trees.getWidth(), 0);
        }
        if (treePos2.x + trees2.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            treePos2.add(3 * trees2.getWidth(), 0);
        }
        if (treePos3.x + trees3.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            treePos3.add(3 * trees3.getWidth(), 0);
        }
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background, bgPos1.x, 0, bg_width, 800);
        sb.draw(background, bgPos2.x, 0, bg_width, 800);
        sb.draw(ground, groundPos1.x, 0, ground_width, 1200);
        sb.draw(ground, groundPos2.x, 0, ground_width, 1200);
        sb.draw(trees, treePos1.x, 50, 270, 250);
        sb.draw(trees2, treePos2.x, 50, 270, 250);
        sb.draw(trees3, treePos3.x, 50, 270, 250);
        sb.draw(barn, barnPos.x, 70);
        sb.draw(shed, shedPos.x, 60);
        if (cherryIsTouched == false) {
            sb.draw(cherry.getCherry(), cherry.getPosCherry().x, cherry.getPosCherry().y, 90, 50);
        }
        sb.draw(poop.getPoop(), poop.getPosPoop().x, poop.getPosPoop().y, 30, 30);
        sb.draw(haybale.getObstacle(), haybale.getPosObs().x, haybale.getPosObs().y);
        if (farmer.collides(sheep.getBounds1())) {
            sb.draw(sheep.getSheepDead(), sheep.getPosition().x, sheep.getPosition().y, 70, 45);
            gameOver(1);
        } else {
            sb.draw(sheep.getSheep(), sheep.getPosition().x, sheep.getPosition().y, 70, 45);
        }
        sb.draw(farmer.getFarmer(), farmer.getPosition().x, farmer.getPosition().y, 120, 110);
        sb.end();

        batch.begin();
        font.setColor(Color.WHITE);
        font.getData().setScale(2, 2);
        font.draw(batch, ((20000 - (System.currentTimeMillis() - startTime)) / 1000) + " ", GameTutorial.WIDTH / 2, GameTutorial.HEIGHT);
        batch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        haybaleTexture.dispose();
        haybale.dispose();
        sheep.dispose();
        barn.dispose();
        shed.dispose();
        farmer.dispose();
        trees.dispose();
        ground.dispose();
        background.dispose();
        cherry.dispose();
        poop.dispose();
        batch.dispose();
        font.dispose();
    }
}
