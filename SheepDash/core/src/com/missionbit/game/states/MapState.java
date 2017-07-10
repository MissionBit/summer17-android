package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.missionbit.game.GameTutorial;


/**
 * Created by missionbit on 6/26/17.
 */

public class MapState extends State {

    private Texture bg;

    private Stage stage1;
    private Texture myTexture1;
    private TextureRegion myTextureRegion1;
    private TextureRegionDrawable myTexRegionDrawable1;
    private ImageButton button1;

    private Stage stage2;
    private Texture myTexture2;
    private TextureRegion myTextureRegion2;
    private TextureRegionDrawable myTexRegionDrawable2;
    private ImageButton button2;

    private Stage stage3;
    private Texture myTexture3;
    private TextureRegion myTextureRegion3;
    private TextureRegionDrawable myTexRegionDrawable3;
    private ImageButton button3;

    private Stage stage4;
    private Texture myTexture4;
    private TextureRegion myTextureRegion4;
    private TextureRegionDrawable myTexRegionDrawable4;
    private ImageButton button4;

    private Stage stage5;
    private Texture myTexture5;
    private TextureRegion myTextureRegion5;
    private TextureRegionDrawable myTexRegionDrawable5;
    private ImageButton button5;

    private Stage stage;
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;

    private Texture l1;
    private Texture l2;
    private Texture l3;
    private Texture l4;
    private Texture l5;


    public MapState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("LevelBg.png");
        l1 = new Texture("Lvl1.png");
        l2 = new Texture("Lvl2.png");
        l3 = new Texture("Lvl3.png");
        l4 = new Texture("Lvl4.png");
        l5 = new Texture("Lvl5.png");

        myTexture1 = new Texture(Gdx.files.internal("Lvl1.png"));
        myTextureRegion1 = new TextureRegion(myTexture1);
        myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
        button1 = new ImageButton(myTexRegionDrawable1);
        stage1 = new Stage(new StretchViewport(GameTutorial.WIDTH, GameTutorial.HEIGHT));
        stage1.addActor(button1);
        Gdx.input.setInputProcessor(stage1);
        button1.setBounds(117,322,64,47);

        myTexture2 = new Texture(Gdx.files.internal("Lvl2.png"));
        myTextureRegion2 = new TextureRegion(myTexture2);
        myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
        button2 = new ImageButton(myTexRegionDrawable2);
        stage2 = new Stage(new StretchViewport(GameTutorial.WIDTH, GameTutorial.HEIGHT));
        stage2.addActor(button2);
        Gdx.input.setInputProcessor(stage2);
        button2.setBounds(209,138,64,47);

        myTexture3 = new Texture(Gdx.files.internal("Lvl3.png"));
        myTextureRegion3 = new TextureRegion(myTexture3);
        myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);
        button3 = new ImageButton(myTexRegionDrawable3);
        stage3 = new Stage(new StretchViewport(GameTutorial.WIDTH, GameTutorial.HEIGHT));
        stage3.addActor(button3);
        Gdx.input.setInputProcessor(stage3);
        button3.setBounds(369,251,64,47);

        myTexture4 = new Texture(Gdx.files.internal("Lvl4.png"));
        myTextureRegion4 = new TextureRegion(myTexture4);
        myTexRegionDrawable4 = new TextureRegionDrawable(myTextureRegion4);
        button4 = new ImageButton(myTexRegionDrawable4);
        stage4 = new Stage(new StretchViewport(GameTutorial.WIDTH, GameTutorial.HEIGHT));
        stage4.addActor(button4);
        Gdx.input.setInputProcessor(stage4);
        button4.setBounds(533,354,64,47);


    }

    @Override
    protected void handleInput() {

    }


    @Override
    public void update(float dt) {
        handleInput();
    }


    @Override
    public void render(SpriteBatch sb) {

        stage1.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage1.draw();
        button1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected Level 1");
                gsm.push(new Level1(gsm));
            }
        });

        stage2.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage2.draw();
        button2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected Level 2");
                gsm.push(new Level2(gsm));
            }
        });

        stage3.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage3.draw();
        button3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected Level 3");
                gsm.push(new Level3(gsm));
            }
        });

        stage4.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage4.draw();
        button4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected Level 4");
                gsm.push(new Level4(gsm));
            }
        });

        sb.begin();
        sb.draw(bg, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        sb.draw(l1,117,322,64,47);
        sb.draw(l2,209,138,64,47);
        sb.draw(l3,369,251,64,47);
        sb.draw(l4,533,354,64,47);
        sb.draw(l5,522,107,64,47);
        sb.end();

    }



    @Override
    public void dispose() {
        bg.dispose();
        stage1.dispose();
        System.out.println("Disposing of Menu State");
    }
}






