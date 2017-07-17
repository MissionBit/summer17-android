package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.animals.Sheep;
import com.missionbit.game.sprites.obstacles.Obstacle;


/**
 * Created by MissionBit on 7/7/17.
 */

public class Instruction extends State {

    private Sheep sheep;
    private Sheep theOneThatDies;
    private Farmer farmer;
    private Texture background;
    //Texture for thingies
    private Texture cherryTxt;
    private Texture appleTxt;
    private Texture carrotTxt;
    //things
    private Obstacle cherry;
    private Texture barrel;
    private Obstacle apple;
    private Obstacle carrot;
    private Texture car;
    private Texture hayBale;

    public Instruction(GameStateManager gsm) {
        super(gsm);
        background = new Texture("INST.png");
        sheep = new Sheep (50,280);
        theOneThatDies = new Sheep(650, 280);
        farmer = new Farmer (400, 280);
        farmer.getPosition().x = 400;
        farmer.setBoundsFarmer(180, 180);
        //thingies
        //remember textures first guys
        cherryTxt = new Texture("Cherry2_0.35.png");
        appleTxt = new Texture("Apple.png");
        carrotTxt = new Texture("Carrott.png");
        //now make the obstacles
        cherry = new Obstacle(cherryTxt, 30, 15, 2, 0.5f);
        apple = new Obstacle(appleTxt, 200, 100, 2, 0.5f);
        carrot = new Obstacle(carrotTxt, 300, 100, 2, 0.5f);
        //smthn wrong im gonna try and reposition the things
        cherry.reposition(-40, 15);
        apple.reposition(150, 50);
        carrot.reposition(250, 65);
        //these are just Textures but oh well;
        hayBale = new Texture("haybale.png");
        barrel = new Texture("Barrel.png");
        car = new Texture("CarRed.png");
        cam.setToOrtho(false, GameTutorial.WIDTH, GameTutorial.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void create() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        sheep.update(dt);
        theOneThatDies.update(dt);
        farmer.update(dt);
        apple.update(dt);
        cherry.update(dt);
        carrot.update(dt);
        collisionCheck();
        //ifs and butts
        if (sheep.getPosition().y  ==  280) {
            sheep.jump();
        }
        if (sheep.getPosition().x > 250) {
            sheep.getPosition().x = 50;
        }
        if (farmer.getPosition().x > 650){
            farmer.getPosition().x = 400;
        }
    }

    public void collisionCheck() {
        if (farmer.collides(theOneThatDies.getBounds1())){
            theOneThatDies.getSheepDead();
        } else {
            theOneThatDies.setDead(false);
        }
    }

    @Override
    public void render(final SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        sb.draw(apple.getObsAnimation(), apple.getPosObs().x, apple.getPosObs().y, 100, 100);
        sb.draw(cherry.getObsAnimation(), cherry.getPosObs().x, cherry.getPosObs().y, 250, 160);
        sb.draw(carrot.getObsAnimation(), carrot.getPosObs().x, carrot.getPosObs().y, 150, 91);
        sb.draw(barrel, 580, 60, 80, 80);
        sb.draw(car, 400, 60, 150, 80);
        sb.draw(hayBale, 660, 45, 100, 100);
        sb.draw(sheep.getSheep(), sheep.getPosition().x, 220, 140, 90);
        if (farmer.collides(theOneThatDies.getBounds1())) {
            sb.draw(theOneThatDies.getSheepDead(), theOneThatDies.getPosition().x, 220, 140, 90);
            theOneThatDies.noSpd();
        }
        else {
            sb.draw(theOneThatDies.getSheep(), theOneThatDies.getPosition().x, 220, 140,90);
            theOneThatDies.noSpd();
        }
        sb.draw(farmer.getFarmer(), farmer. getPosition().x, 220, 180, 180);
        sb.end();
    }

    @Override
    public void dispose() {
        sheep.dispose();
        farmer.dispose();
        theOneThatDies.dispose();
        background.dispose();
    }
}