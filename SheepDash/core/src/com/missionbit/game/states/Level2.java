package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.obstacles.Mud;
import com.missionbit.game.sprites.animals.Sheep;
import com.missionbit.game.sprites.obstacles.Obstacle;
import java.util.Random;

/**
 * Created by missionbit on 6/28/17.
 */

public class Level2 extends State{
    private Texture barrelTexture;
    private Obstacle barrel;
    private Texture cherryTexture;
    private Obstacle cherry;
    private Texture mushroomTexture;
    private Obstacle mushroom;
    private boolean cherryIsTouched;
    private boolean mushroomIsTouched;
    private Sheep sheep;
    private Farmer farmer;
    private Mud mud;
    private Texture background;
    private Texture ground;
    private Vector2 bgPos1;
    private Vector2 bgPos2;
    private Vector2 bgPos3;
    private Vector2 groundPos1;
    private Vector2 groundPos2;
    private static final int GROUND_Y_OFFSET = -80;
    private static final int bg_width = 1300;
    private static final int ground_width = 800;
    private static final int MUD_SPACING = 250;
    private static final int MUD_WIDTH = 30;


    public Level2(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, GameTutorial.WIDTH / 2, GameTutorial.HEIGHT / 2);
        barrelTexture = new Texture("Barrel.png");
        barrel = new Obstacle(barrelTexture, 400, 50, 1, 0.5f);
        cherryTexture = new Texture("Cherry2_0.35.png");
        cherry = new Obstacle(cherryTexture, 200, 50, 2, 0.35f);
        cherryIsTouched = false;
        mushroomTexture = new Texture("Mushroom.png");
        mushroom = new Obstacle(mushroomTexture, 700, 50, 2, 0.3f);
        mushroomIsTouched = false;
        mud = new Mud(260,60);
        sheep = new Sheep(150,60);
        farmer = new Farmer(-50,60);
        ground = new Texture("CornFieldGround.png");
        background = new Texture("CornField.png");
        groundPos1 = new Vector2(cam.position.x-cam.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2 = new Vector2(ground.getWidth() + groundPos1.x,GROUND_Y_OFFSET);
        bgPos1 = new Vector2(cam.position.x - cam.viewportWidth/2,0);
        bgPos2 = new Vector2(background.getWidth()+bgPos1.x,0);
        bgPos3 = new Vector2(background.getWidth()+bgPos2.x,0);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            sheep.jump();
        }
    }

    @Override
    public void create() {

    }

    public void update(float dt) {
        handleInput();
        cherry.update(dt);
        mushroom.update(dt);
        updateBarrel();
        updateCherry();
        updateMushroom();
        sheep.update(dt);
        cam.position.x = sheep.getPosition().x + 80;
        farmer.update(dt);
        updateBg();
        updateGround();
        updatePoops();
        collisionCheck2();
        timerCheck(dt);
        changeLevels();
        cam.update();

    }

    public void collisionCheck2() {
        if (mud.collides(sheep.getBounds1())) {
            sheep.reduceSpd();
            sheep.startTimer();
            System.out.println(sheep.getBounds1());
            System.out.println(mud.getBoundsMud());
        }
    }

    public void timerCheck(float timePassed) {
        sheep.updateTimer(timePassed);
        if (sheep.isTimerDone()) {
            sheep.resetSpd();
        }
    }

    public void updatePoops(){
        if (mud.getPosMud().x + MUD_WIDTH <= cam.position.x-cam.viewportWidth/2){
            mud.getPosMud().add(2*MUD_SPACING,0);
            mud.getBoundsMud().setPosition(mud.getPosMud().x,mud.getPosMud().y);
            mud.setCollided(false);
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

    public void updateGround(){
        if(groundPos1.x+ground.getWidth() <= cam.position.x-cam.viewportWidth/2){
            groundPos1.add(2*ground.getWidth(),0);
        }
        if(groundPos2.x+ground.getWidth() <= cam.position.x-cam.viewportWidth/2){
            groundPos2.add(2*ground.getWidth(),0);
        }

    }

    public void changeLevels(){
        if (sheep.getPosition().x > 3000) {
            gsm.set(new Level4(gsm));
        }
    }

    public void updateBarrel() {
        if (cam.position.x - cam.viewportWidth / 2 > barrel.getPosObs().x + barrel.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 500) + GameTutorial.WIDTH;
            barrel.reposition(barrel.getPosObs().x + distance, 58);
        }
    }

    public void updateCherry() {
        if (cam.position.x - cam.viewportWidth / 2 > cherry.getPosObs().x + cherry.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 700) + GameTutorial.WIDTH;
            cherry.reposition(cherry.getPosObs().x + distance, 58);
            cherryIsTouched = false;
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

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background,bgPos1.x,0,bg_width,400);
        sb.draw(background,bgPos2.x,0,bg_width,400);
        sb.draw(background,bgPos3.x,0,bg_width,400);
        sb.draw(ground,groundPos1.x,0,ground_width,260);
        sb.draw(ground,groundPos2.x,0,ground_width,260);
        sb.draw(mud.getMud(),mud.getPosMud().x,mud.getPosMud().y,40,30);
        sb.draw(barrel.getObstacle(), barrel.getPosObs().x, barrel.getPosObs().y);
        if (cherryIsTouched == false) {
            sb.draw(cherry.getObsAnimation(), cherry.getPosObs().x, cherry.getPosObs().y);
        }
        if (mushroomIsTouched == false) {
            sb.draw(mushroom.getObsAnimation(), mushroom.getPosObs().x, mushroom.getPosObs().y);
        }
        if (farmer.collides(sheep.getBounds1())) {
            sb.draw(sheep.getSheepDead(), sheep.getPosition().x, sheep.getPosition().y,70,45);
        }
        else {
            sb.draw(sheep.getSheep(), sheep.getPosition().x, sheep.getPosition().y, 70,45);
        }
        sb.draw(farmer.getFarmer(),farmer.getPosition().x,farmer.getPosition().y,120,110);
        sb.end();
    }

    @Override
    public void dispose() {
        barrelTexture.dispose();
        barrel.dispose();
        cherryTexture.dispose();
        cherry.dispose();
        mushroomTexture.dispose();
        mushroom.dispose();
        sheep.dispose();
        farmer.dispose();
        ground.dispose();
        background.dispose();
        mud.dispose();
    }
}