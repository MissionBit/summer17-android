package com.missionbit.game.states;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Obstacle;

import java.util.Random;

/**
 * Created by missionbit on 6/28/17.
 */

public class Level2 extends State {

    private Texture barrelTexture;
    private Obstacle barrel;
    private Texture cherryTexture;
    private Obstacle cherry;
    private Texture mushroomTexture;
    private Obstacle mushroom;
    private boolean cherryIsTouched;
    private boolean mushroomIsTouched;

    public Level2(GameStateManager gsm) {
        super(gsm);
        barrelTexture = new Texture("Barrel.png");
        barrel = new Obstacle(barrelTexture, 400, 50, 1, 0.5f);
        cherryTexture = new Texture("Cherry2_0.35.png");
        cherry = new Obstacle(cherryTexture, 200, 50, 2, 0.35f);
        cherryIsTouched = false;
        mushroomTexture = new Texture("Mushroom.png");
        mushroom = new Obstacle(mushroomTexture, 700, 50, 2, 0.3f);
        mushroomIsTouched = false;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        cherry.update(dt);
        mushroom.update(dt);
        updateBarrel();
        updateCherry();
        updateMushroom();
        collisionCheck();
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
        sb.draw(barrel.getObstacle(), barrel.getPosObs().x, barrel.getPosObs().y);
        if (cherryIsTouched == false) {
            sb.draw(cherry.getObsAnimation(), cherry.getPosObs().x, cherry.getPosObs().y);
        }
        if (mushroomIsTouched == false) {
            sb.draw(mushroom.getObsAnimation(), mushroom.getPosObs().x, mushroom.getPosObs().y);
        }
        sb.end();
    }

    public void collisionCheck() {
//        if (barrel.collides(sheep.getBounds1())) {
//            sheep.reduceSpd();
//        }
//        if (mushroom.collides(sheep.getBounds1())) {
//            sheep.goBackwards();
//            mushroomIsTouched = true;
//        }
//        if (cherry.collides(sheep.getBounds1())) {
//            sheep.increaseSpd();
//            cherryIsTouched = true;
//        }
    }

    @Override
    public void dispose() {
        barrelTexture.dispose();
        barrel.dispose();
        cherryTexture.dispose();
        cherry.dispose();
        mushroomTexture.dispose();
        mushroom.dispose();
    }
}