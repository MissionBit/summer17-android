package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Sheep;

/**
 * Created by missionbit on 6/28/17.
 */

public class Level3 extends State {
    private Texture sky;
    private Sheep sheep;
    //private Farmer farmer;
    private Texture hills, ground;
    private Vector2 groundPos1, groundPos2;
    private Vector2 skyPos, skyPos2;
    private Vector2 hillsPos, hillsPos2;
    private static final int GROUND_Y_OFFSET = -90;

    public Level3(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, GameTutorial.WIDTH / 2, GameTutorial.HEIGHT / 2);
        sheep = new Sheep(50, 60);
        sky = new Texture("pixil-layer-Background.png");
        //farmer = new Farmer(-50,60);
        hills = new Texture("pixil-layer-Hills.png");
        ground = new Texture("pixil-layer-Ground.png");
        groundPos1 = new Vector2(cam.position.x - (cam.viewportWidth / 2), GROUND_Y_OFFSET);
        groundPos2 = new Vector2(ground.getWidth() + groundPos1.x, GROUND_Y_OFFSET);
        skyPos = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        skyPos2 = new Vector2(sky.getWidth() + skyPos.x, 0);
        hillsPos = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        //hillsPos2 = new Vector2(hills.getWidth() + hillsPos.x, 0);

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            sheep.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        sheep.update(dt);
        cam.position.x = sheep.getPosition().x + 80;
        //farmer.update(dt);
        updateGround();
        updateSky();
        updateHills();
        //collisionCheck();
        cam.update();

    }

    public void updateGround() {
        if (groundPos1.x + ground.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            groundPos1.add(2 * ground.getWidth(), 0);
        }
        if (groundPos2.x + ground.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            groundPos2.add(2 * ground.getWidth(), 0);
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
            hillsPos.add(2 * hills.getWidth(), 0);
        }
        /*
        if (hillsPos2.x + hills.getWidth() <= cam.position.x - cam.viewportWidth / 2) {
            hillsPos2.add(2 * hills.getWidth(), 0);
        }
        */
    }

    /*
    public void collisionCheck() {
        if (farmer.collides(sheep.getBounds1())){
            sheep.getSheepDead();
        }
    }
    */

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(sky, skyPos.x, 0);
        sb.draw(sky, skyPos2.x, 0);
        sb.draw(hills, hillsPos.x, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        //sb.draw(hills, hillsPos2.x, 0, 400, 200);
        sb.draw(ground, groundPos1.x, 0);
        sb.draw(ground, groundPos2.x, 0);
        //if (farmer.collides(sheep.getBounds1())) {
        //    sb.draw(sheep.getSheepDead(), sheep.getPosition().x, sheep.getPosition().y,70,45);
        //}
        //else {
        sb.draw(sheep.getSheep(), sheep.getPosition().x, sheep.getPosition().y, 70, 45);
        //}
        //sb.draw(farmer.getFarmer(),farmer.getPosition().x,farmer.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {
        sky.dispose();
        hills.dispose();
        ground.dispose();
        sheep.dispose();
        //farmer.dispose();
    }
}
