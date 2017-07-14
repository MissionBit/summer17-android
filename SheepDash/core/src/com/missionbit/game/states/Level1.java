package com.missionbit.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Obstacle;

import java.util.Random;

/**
 * Created by missionbit on 7/6/17.
 */

public class Level1 extends State {

    private Texture bg;
    private Texture haybaleTexture;
    private Obstacle haybale;
    private Texture appleTexture;
    private Obstacle apple;
    private boolean appleIsTouched;

    public Level1(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("FarmBg1.png");

        haybaleTexture = new Texture("haybale.png");
        haybale = new Obstacle(haybaleTexture, 600, 50, 1, 0.5f);
        appleTexture = new Texture("Apple.png");
        apple = new Obstacle(appleTexture, 1000, 50, 2, 0.5f);
        appleIsTouched = false;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void create() {

    }

    @Override
    public void update(float dt) {
        apple.update(dt);
        updateHaybale();
        updateApple();
        collisionCheck();
    }

    public void collisionCheck() {
//        if (haybale.collides(sheep.getBounds1())) {
//            sheep.reduceSpd();
//        }
//        if (apple.collides(sheep.getBounds1())) {
//          sheep.increaseSpd();
//          appleIsTouched = true;
//        }
    }

    public void updateHaybale() {
        if (cam.position.x - cam.viewportWidth / 2 > haybale.getPosObs().x + haybale.getWidth()) {
            Random rand = new Random();
            float fluctuation = rand.nextFloat();
            float distance = (fluctuation * 600) + GameTutorial.WIDTH;
            haybale.reposition(haybale.getPosObs().x + distance, 58);
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

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(haybale.getObstacle(), haybale.getPosObs().x, haybale.getPosObs().y);
        if (appleIsTouched == false) {
            sb.draw(apple.getObsAnimation(), apple.getPosObs().x, apple.getPosObs().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        haybaleTexture.dispose();
        haybale.dispose();
        appleTexture.dispose();
        apple.dispose();
    }
}
