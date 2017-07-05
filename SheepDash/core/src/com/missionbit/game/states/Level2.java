package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.Sheep;

/**
 * Created by missionbit on 6/28/17.
 */

public class Level2 extends State {
    private Texture sky;
    private Sheep sheep;
    private Farmer farmer;
    private Texture buildings;
    private Texture ground;
    private Vector2 groundPos1;
    private Vector2 groundPos2;
    private Vector2 skyPos;
    private Vector2 buildingsPos;
    private Vector2 skyPos2;
    private Vector2 buildingsPos2;
    private static final int GROUND_Y_OFFSET = -80;

    public Level2(GameStateManager gsm) {
        super(gsm);
        sheep = new Sheep(50,60);
        sky = new Texture("CitySky.png");
        farmer = new Farmer(-50,60);
        buildings = new Texture("CityBuildings.png");
        ground = new Texture("CityGround.png");
        cam.setToOrtho(false, GameTutorial.WIDTH / 2, GameTutorial.HEIGHT / 2);
        groundPos1 = new Vector2(cam.position.x-cam.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2 = new Vector2(ground.getWidth() + groundPos1.x,GROUND_Y_OFFSET);
        skyPos = new Vector2(cam.position.x - cam.viewportWidth/2,0);
        skyPos2 = new Vector2(sky.getWidth()+skyPos.x,0);
        buildingsPos = new Vector2(cam.position.x-cam.viewportWidth/2,0);
        buildingsPos2 = new Vector2(buildings.getWidth()+buildingsPos.x,0);
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
        updateBuildings();
        collisionCheck();
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

    public void updateBuildings(){
        if(buildingsPos.x+buildings.getWidth() <= cam.position.x-cam.viewportWidth/2){
            buildingsPos.add(2*buildings.getWidth(),0);
        }
        if(buildingsPos2.x+buildings.getWidth() <= cam.position.x-cam.viewportWidth/2){
            buildingsPos2.add(2*buildings.getWidth(),0);
        }
    }


    public void collisionCheck() {
        if (farmer.collides(sheep.getBounds1())){
            sheep.getSheepDead();
        }
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(sky,skyPos.x,0,800,400);
        sb.draw(sky,skyPos2.x,0,800,400);
        sb.draw(buildings,buildingsPos.x,0,350,200);
        sb.draw(buildings,buildingsPos2.x,0,400,200);
        sb.draw(ground,groundPos1.x,0,790,350);
        sb.draw(ground,groundPos2.x,0,790,350);
        if (farmer.collides(sheep.getBounds1())) {
            sb.draw(sheep.getSheepDead(), sheep.getPosition().x, sheep.getPosition().y,70,45);
        }
        else {
            sb.draw(sheep.getSheep(), sheep.getPosition().x, sheep.getPosition().y, 70,45);
        }
        sb.draw(farmer.getFarmer(),farmer.getPosition().x,farmer.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {
        sky.dispose();
        buildings.dispose();
        ground.dispose();
        sheep.dispose();
        farmer.dispose();

    }
}