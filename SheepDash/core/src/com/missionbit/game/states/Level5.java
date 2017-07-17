package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.obstacles.Cherry;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.animals.Sheep;
import com.missionbit.game.sprites.obstacles.Obstacle;
import com.missionbit.game.sprites.animals.Sheep;

import java.util.Random;

/**
 * Created by missionbit on 7/5/17.
 */

public class Level5 extends State {
    private Texture sky;
    private Sheep sheep;
    private Farmer farmer;
    private Texture buildings;
    private Texture ground;
    private Vector2 groundPos1, groundPos2, groundPos3;
    private Vector2 skyPos, skyPos2;
    private Vector2 buildingsPos, buildingsPos2, buildingsPos3, buildingsPos4, buildingsPos5;
    //OBSTACLES
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

    public Level5(GameStateManager gsm) {
        super(gsm);
        sheep = new Sheep(150,60);
        sky = new Texture("CitySky.png");
        farmer = new Farmer(-50,60);
        buildings = new Texture("UpdatedCityBuildings.png");
        ground = new Texture("CityGround.png");
        cam.setToOrtho(false, GameTutorial.WIDTH / 2, GameTutorial.HEIGHT / 2);
        groundPos1 = new Vector2(cam.position.x-cam.viewportWidth/2,0);
        groundPos2 = new Vector2(ground.getWidth() + groundPos1.x,0);
        groundPos3 = new Vector2(ground.getWidth()+groundPos2.x,0);
        skyPos = new Vector2(cam.position.x - cam.viewportWidth/2,0);
        skyPos2 = new Vector2(sky.getWidth()+skyPos.x,0);
        buildingsPos = new Vector2(cam.position.x-cam.viewportWidth/2,0);
        buildingsPos2 = new Vector2(BUILDINGS_WIDTH+buildingsPos.x,0);
        buildingsPos3 = new Vector2(2*BUILDINGS_WIDTH+buildingsPos.x,0);
        buildingsPos4 = new Vector2(3*BUILDINGS_WIDTH+buildingsPos.x,0);
        buildingsPos5 = new Vector2(4*BUILDINGS_WIDTH+buildingsPos.x,0);
        //OBSTACLES
        greyTexture = new Texture("CarGrey.png");
        greyCar = new Obstacle(greyTexture, 700, 48, 1, 0.5f);
        mushroomTexture = new Texture("Mushroom.png");
        mushroom = new Obstacle(mushroomTexture, 2000, 60, 2, 0.2f);
        mushroomIsTouched = false;
        cherryTexture = new Texture("Cherry2_0.35.png");
        cherry = new Obstacle(cherryTexture, 1000, 50, 2, 0.35f);
        cherryIsTouched = false;
        spikeTexture = new Texture("SPIKES2.0.18.png");
        spikes = new Obstacle(spikeTexture, 1700, 50, 2, 0.5f);
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void handleInput() {
        if(sheep.getPosition().y == 60) {
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
        cam.position.x = sheep.getPosition().x + 80;
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
        timerCheck(dt);
        cam.update();
        if(((System.currentTimeMillis() - startTime) > 30000 & farmer.collides(sheep.getBounds1()) == false)) {
            gsm.set(new MenuState(gsm));
        }
    }

    public void timerCheck(float timePassed){
        sheep.updateTimer(timePassed);
        if (sheep.isTimerDone()){
            sheep.resetSpd();
        }
    }

    public void updateGround(){
        if(groundPos1.x+ground.getWidth() <= cam.position.x-cam.viewportWidth/2){
            groundPos1.add(3*ground.getWidth(),0);
        }
        if(groundPos2.x+ground.getWidth() <= cam.position.x-cam.viewportWidth/2){
            groundPos2.add(3*ground.getWidth(),0);
        }
        if (groundPos3.x+ground.getWidth() <= cam.position.x-cam.viewportWidth/2){
            groundPos3.add(3*ground.getWidth(),0);
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

    public void updateBuildings(){
        if(buildingsPos.x+buildings.getWidth() <= cam.position.x-cam.viewportWidth/2){
            buildingsPos.add(5*buildings.getWidth(),0);
        }
        if(buildingsPos2.x+buildings.getWidth() <= cam.position.x-cam.viewportWidth/2){
            buildingsPos2.add(5*buildings.getWidth(),0);
        }
        if(buildingsPos3.x+buildings.getWidth() <= cam.position.x-cam.viewportWidth/2){
            buildingsPos3.add(5*buildings.getWidth(),0);
        }
        if(buildingsPos4.x+buildings.getWidth() <= cam.position.x-cam.viewportWidth/2){
            buildingsPos4.add(5*buildings.getWidth(),0);
        }
        if(buildingsPos5.x+buildings.getWidth() <= cam.position.x-cam.viewportWidth/2){
            buildingsPos5.add(5*buildings.getWidth(),0);
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
            mushroom.reposition(mushroom.getPosObs().x + distance, 58);
            mushroomIsTouched = false;
        }
    }

    public void updateCherry() {
        if (cam.position.x - cam.viewportWidth / 2 > cherry.getPosObs().x + cherry.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 850) + GameTutorial.WIDTH;
            cherry.reposition(cherry.getPosObs().x + distance, 58);
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
        if (farmer.collides(sheep.getBounds1())){
            sheep.getSheepDead();
            sheep.sheepDied();
            farmer.killedSheep();
        }
        if (greyCar.collides(sheep.getBounds1())) {
            sheep.reduceSpd();
            sheep.startTimer();
        }
        if (cherry.collides(sheep.getBounds1())) {
            cherryIsTouched = true;
            sheep.increaseSpd();
            sheep.startTimer();
        }
        if (spikes.collides((sheep.getBounds1()))) {
            sheep.reduceSpd();
            sheep.startTimer();
        }
        if (mushroom.collides(sheep.getBounds1())) {
            mushroomIsTouched = true;
            sheep.goBackwards();
            sheep.startTimer();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(sky,skyPos.x,0,SKY_WIDTH,400);
        sb.draw(sky,skyPos2.x,0,SKY_WIDTH,400);
        sb.draw(buildings,buildingsPos.x,0,BUILDINGS_WIDTH,200);
        sb.draw(buildings,buildingsPos2.x,0,BUILDINGS_WIDTH,200);
        sb.draw(buildings,buildingsPos3.x,0,BUILDINGS_WIDTH,200);
        sb.draw(buildings,buildingsPos4.x,0,BUILDINGS_WIDTH,200);
        sb.draw(buildings,buildingsPos5.x,0,BUILDINGS_WIDTH,200);
        sb.draw(ground,groundPos1.x,0,GROUND_WIDTH,350);
        sb.draw(ground,groundPos2.x,0,GROUND_WIDTH,350);
        sb.draw(ground,groundPos3.x,0,GROUND_WIDTH,350);
        sb.draw(spikes.getObsAnimation(), spikes.getPosObs().x, spikes.getPosObs().y);
        sb.draw(greyCar.getObstacle(), greyCar.getPosObs().x, greyCar.getPosObs().y);
        if (mushroomIsTouched == false) {
            sb.draw(mushroom.getObsAnimation(), mushroom.getPosObs().x, mushroom.getPosObs().y);
        }
        if (cherryIsTouched == false) {
            sb.draw(cherry.getObsAnimation(), cherry.getPosObs().x, cherry.getPosObs().y);
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
        sky.dispose();
        buildings.dispose();
        ground.dispose();
        sheep.dispose();
        farmer.dispose();
        greyTexture.dispose();
        greyCar.dispose();
        mushroomTexture.dispose();
        mushroom.dispose();
        cherryTexture.dispose();
        cherry.dispose();
        spikeTexture.dispose();
        spikes.dispose();
    }
}
