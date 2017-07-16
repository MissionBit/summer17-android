package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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
    //instructions button
    private Texture instBtn;
    private TextureRegion instTextureRegion;
    private TextureRegionDrawable instTextureRegionDrawable;
    private ImageButton buttonInst;
    //play button
    private Texture playBtn;
    private TextureRegion playTextureRegion;
    private TextureRegionDrawable playTextureRegionDrawable;
    private ImageButton buttonPlay;
    //everything else
    private Sheep sheep;
    private Chick chick;
    private Pig pig;
    private Bunny bunny;
    private Cow cow;
    private Farmer farmer;
    private Stage stage;

    public MenuState(final GameStateManager gsm) {
        super(gsm);
        background = new Texture("TITLE.png");
        playBtn = new Texture(Gdx.files.internal("PlayBtn.png"));
        playTextureRegion = new TextureRegion(playBtn);
        playTextureRegionDrawable = new TextureRegionDrawable(playTextureRegion);
        buttonPlay = new ImageButton((playTextureRegionDrawable));
        stage = new Stage(new ScreenViewport());
        instBtn = new Texture("instBtn.png");
        sheep = new Sheep(100,60);
        chick = new Chick(200,50);
        cow = new Cow(250,50);
        pig = new Pig(300,50);
        bunny = new Bunny(350,50);
        farmer = new Farmer(20,60);
        stage = new Stage();
        buttonPlay.setSize(playBtn.getWidth(), playBtn.getHeight());
        buttonPlay.setPosition((float)(GameTutorial.WIDTH - playBtn.getWidth())*1/5, (GameTutorial.HEIGHT - (playBtn.getHeight()))*1/5);
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                gsm.push(new CharacterState(gsm));
            }
        });
        instBtn = new Texture(Gdx.files.internal("InstBtn.png"));
        instTextureRegion = new TextureRegion((instBtn));
        instTextureRegionDrawable = new TextureRegionDrawable(instTextureRegion);
        buttonInst = new ImageButton((instTextureRegionDrawable));
        buttonInst.setSize(instBtn.getWidth(), instBtn.getHeight());
        buttonInst.setPosition((float)(GameTutorial.WIDTH - instBtn.getWidth())*4/5, (GameTutorial.HEIGHT - (instBtn.getHeight()))*1/5);
        buttonInst.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                gsm.push(new Instruction(gsm));
            }
        });
        stage.addActor(buttonPlay);
        stage.addActor(buttonInst);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void create() {

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
        sb.draw(sheep.getSheep(),sheep.getPosition().x,sheep.getPosition().y,70,50);
        sb.draw(farmer.getFarmer(),farmer.getPosition().x,farmer.getPosition().y,90,100);
        sb.draw(chick.getChick(),chick.getPosition().x,chick.getPosition().y,50,50);
        sb.draw(bunny.getBunny(),bunny.getPosition().x,bunny.getPosition().y,70,50);
        sb.draw(pig.getPig(),pig.getPosition().x,pig.getPosition().y,70,50);
        sb.draw(cow.getCow(),cow.getPosition().x,cow.getPosition().y,70,50);
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
        stage.dispose();
    }
}
