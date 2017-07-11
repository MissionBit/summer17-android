package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.Sheep;

/**
 * Created by MissionBit on 7/7/17.
 */

public class Instruction extends State {

    private Sheep sheep;
    private Farmer farmer;
    private Texture background;
    //all the animations
    //but first the textures ya knead
    private Texture apple;
    private Texture cherry;
    private Texture carrot;
    private Animation appleAni;
    private Animation cherryAni;



    public Instructions(GameStateManager gsm) {
        super(gsm);
        background = new Texture("INST.png");
        sheep = new Sheep (150,280);
        sheep.MOVEMENT = 0;
        farmer = new Farmer (400, 280);
        farmer.MOVEMENT = 0;
        cam.setToOrtho(false, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        //animaitons guysss kill me now im so sleep deprived
        //BUT FIRST THE TEXTURES
        apple = new Texture("apple.png");
        cherry = new Texture("cherry.png");
        carrot = new Texture("carrot.png");
        //MAKE 'EM MOVE
        // appleAni = new Animation(new TextureRegion(apple),2,0.5f);
        ///cherryAni = new Animation(new TextureRegion(cherry),2,0.5f);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.push(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        sheep.update(dt);
        farmer.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        sb.draw(sheep.getSheep(), 100, 220, 140, 90);
        sb.draw(farmer.getFarmer(), 400, 220, 180, 180);
        sb.draw(apple, 50, 50);
        sb.draw(cherry, 100, 50, 100, 100);
        sb.draw(carrot, 300, 50);
        sb.end();
    }

    @Override
    public void dispose() {
        sheep.dispose();
        farmer.dispose();
    }
}