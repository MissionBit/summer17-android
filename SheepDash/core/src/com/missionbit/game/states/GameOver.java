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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.missionbit.game.GameTutorial;

import java.lang.reflect.Constructor;

/**
 * Created by missionbit on 7/12/17.
 */

public class GameOver extends State{
    private int level;
    private int character;
    private Texture black;
    private Stage stage;

    private Texture myTexture1;
    private TextureRegion myTextureRegion1;
    private TextureRegionDrawable myTexRegionDrawable1;
    private ImageButton back;

    private Texture myTexture2;
    private TextureRegion myTextureRegion2;
    private TextureRegionDrawable myTexRegionDrawable2;
    private ImageButton restart;

    private Texture myTexture3;
    private TextureRegion myTextureRegion3;
    private TextureRegionDrawable myTexRegionDrawable3;
    private ImageButton Gtext;


    public GameOver(final GameStateManager gsm, int level, final int character) {

        super(gsm);
        this.level = level;
        this.character = character;
        black = new Texture("blackness.jpg");


        myTexture1 = new Texture(Gdx.files.internal("back.png"));
        myTextureRegion1 = new TextureRegion(myTexture1);
        myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
        back = new ImageButton(myTexRegionDrawable1);
        stage = new Stage(new StretchViewport(GameTutorial.WIDTH, GameTutorial.HEIGHT));
        stage.addActor(back);
        Gdx.input.setInputProcessor(stage);
        back.setBounds(GameTutorial.WIDTH/2 - 130,200,80,80);
        back.getImageCell().expand().fill();

        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exiting to Menu");
                gsm.set(new MapState(gsm, character));
            }
        });


        myTexture2 = new Texture(Gdx.files.internal("restart.png"));
        myTextureRegion2 = new TextureRegion(myTexture2);
        myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
        restart = new ImageButton(myTexRegionDrawable2);
        stage.addActor(restart);
        Gdx.input.setInputProcessor(stage);
        restart.setBounds(GameTutorial.WIDTH/2 + 41,200,80,80);
        restart.getImageCell().expand().fill();

        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level restarting");
                restart();
            }
        });


        myTexture3 = new Texture(Gdx.files.internal("GameOver.png"));
        myTextureRegion3 = new TextureRegion(myTexture3);
        myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);
        Gtext = new ImageButton(myTexRegionDrawable3);
        stage.addActor(Gtext);
        Gdx.input.setInputProcessor(stage);
        Gtext.setBounds(200,270,400,90);
        Gtext.getImageCell().expand().fill();


        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level restarting");
            }
        });

    }

    @Override
    public void handleInput() {

    }

    public void restart() {
        if(level == 1) {
            gsm.set(new Level1(gsm, character));
        } else if (level == 2) {
            gsm.set(new Level2(gsm, character));
        } else if (level == 3) {
            gsm.set(new Level3(gsm, character));
        } else if (level == 4) {
            gsm.set(new Level4(gsm, character));
        } else if (level == 5) {
            gsm.set(new Level5(gsm, character));
        } else {
            gsm.set(new MapState(gsm, character));
        }
    }

    @Override
    public void create() {

    }

    @Override
    public void update(float dt) {handleInput();}

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(black, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        black.dispose();
        stage.dispose();
        myTexture1.dispose();
        myTexture2.dispose();
        myTexture3.dispose();
    }
}
