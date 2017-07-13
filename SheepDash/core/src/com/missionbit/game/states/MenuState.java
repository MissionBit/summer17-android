package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.animals.Bunny;
import com.missionbit.game.sprites.animals.Chick;
import com.missionbit.game.sprites.animals.Cow;
import com.missionbit.game.sprites.Farmer;
import com.missionbit.game.sprites.animals.Pig;
import com.missionbit.game.sprites.animals.Sheep;

/**
 * Created by MissionBit on 6/22/17.
 */

public class MenuState extends State {


    private Texture background;
    private Texture playBtn;
    private Sheep sheep;
    private Chick chick;
    private Pig pig;
    private Bunny bunny;
    private Cow cow;
    private Farmer farmer;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("MenuScreen.png");
        playBtn = new Texture("PlayBtn.png");
        sheep = new Sheep(150,50);
        chick = new Chick(200,50);
        farmer = new Farmer(20,60);
        cow = new Cow(250,50);
        pig = new Pig(300,50);
        bunny = new Bunny(350,50);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new Level1(gsm));

        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        sheep.update(dt);
        farmer.update(dt);
        chick.update(dt);
        pig.update(dt);
        cow.update(dt);
        bunny.update(dt);
        if (sheep.getPosition().x > 750){
            sheep = new Sheep(20,60);
        }
        if (farmer.getPosition().x > 750){
            farmer = new Farmer(20,60);
        }
        if (chick.getPosition().x > 750){
            chick = new Chick(20,60);
        }
        if (pig.getPosition().x > 750){
            pig = new Pig(20,60);
        }
        if (cow.getPosition().x > 750){
            cow = new Cow(20,60);
        }
        if (bunny.getPosition().x > 750){
            bunny = new Bunny(20,60);
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        sb.draw(playBtn,(GameTutorial.WIDTH - playBtn.getWidth())/2, (GameTutorial.HEIGHT - playBtn.getHeight())/2);
        sb.draw(sheep.getSheep(),sheep.getPosition().x,sheep.getPosition().y,70,50);
        sb.draw(farmer.getFarmer(),farmer.getPosition().x,farmer.getPosition().y,90,100);
        sb.draw(chick.getChick(),chick.getPosition().x,chick.getPosition().y,50,50);
        sb.draw(bunny.getBunny(),bunny.getPosition().x,bunny.getPosition().y,70,50);
        sb.draw(pig.getPig(),pig.getPosition().x,pig.getPosition().y,70,50);
        sb.draw(cow.getCow(),cow.getPosition().x,cow.getPosition().y,70,50);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        sheep.dispose();
        farmer.dispose();
        chick.dispose();
        pig.dispose();
        cow.dispose();
        bunny.dispose();
    }
}
