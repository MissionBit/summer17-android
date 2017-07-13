package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.animals.Bunny;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.obstacles.Poop;
import com.missionbit.game.sprites.animals.Sheep;

/**
 * Created by missionbit on 6/28/17.
 */

public class Level4 extends State {
    private Texture sky;
    private Sheep sheep;
    private Farmer farmer;
    private Texture cars;
    private Texture ground;
    private Vector2 groundPos1;
    private Vector2 groundPos2;
    private Vector2 skyPos;
    private Vector2 skyPos2;
    private Vector2 carsPos;
    private Vector2 carsPos2;
    private Vector2 carsPos3;
    private static final int GROUND_Y_OFFSET = -80;
    private static final int car_Width=270;
    private static final int sky_width = 800;
    private static final int ground_width = 790;

    public Level4(GameStateManager gsm) {
        super(gsm);
        sheep = new Sheep(150,60);
        sky = new Texture("sunCloudsForHighway.png");
        farmer = new Farmer(-50,60);
        cars = new Texture("carsForHighway.png");
        ground = new Texture("highwayBackground.png");
        cam.setToOrtho(false, GameTutorial.WIDTH / 2, GameTutorial.HEIGHT / 2);
        groundPos1 = new Vector2(cam.position.x-cam.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2 = new Vector2(ground.getWidth() + groundPos1.x,GROUND_Y_OFFSET);
        skyPos = new Vector2(cam.position.x - cam.viewportWidth/2,0);
        skyPos2 = new Vector2(sky.getWidth()+skyPos.x,0);
        carsPos = new Vector2(cam.position.x-cam.viewportWidth/2,0);
        carsPos2 = new Vector2(cars.getWidth()+carsPos.x,0);
        carsPos3 = new Vector2(cars.getWidth()+carsPos2.x,0);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
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
        updateCars();
        collisionCheck2();
        timerCheck(dt);
        changeLevels();
        cam.update();

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



    public void changeLevels(){
        if (sheep.getPosition().x > 3000){
            gsm.set(new Level5(gsm));
        }
    }

    public void collisionCheck2(){
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
        sb.draw(sky,skyPos.x,0,sky_width,400);
        sb.draw(sky,skyPos2.x,0,sky_width,400);
        sb.draw(ground,groundPos1.x,0,ground_width,350);
        sb.draw(ground,groundPos2.x,0,ground_width,350);
        sb.draw(cars,carsPos.x,20,car_Width,300);
        sb.draw(cars,carsPos2.x,20,car_Width,300);
        sb.draw(cars,carsPos3.x,20,car_Width,300);
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
        sheep.dispose();
        farmer.dispose();
        cars.dispose();
        ground.dispose();
    }
}