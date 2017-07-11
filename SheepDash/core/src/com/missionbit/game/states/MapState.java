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


public class MapState extends State {

    private Texture bg;

    private Stage stage;

    private Texture myTexture1;
    private TextureRegion myTextureRegion1;
    private TextureRegionDrawable myTexRegionDrawable1;
    private ImageButton button1;

    //button 2
    private Texture myTexture2;
    private TextureRegion myTextureRegion2;
    private TextureRegionDrawable myTexRegionDrawable2;
    private ImageButton button2;

    //button 3
    private Texture myTexture3;
    private TextureRegion myTextureRegion3;
    private TextureRegionDrawable myTexRegionDrawable3;
    private ImageButton button3;

    //button 4
    private Texture myTexture4;
    private TextureRegion myTextureRegion4;
    private TextureRegionDrawable myTexRegionDrawable4;
    private ImageButton button4;

    private Texture myTexture5;
    private TextureRegion myTextureRegion5;
    private TextureRegionDrawable myTexRegionDrawable5;
    private ImageButton button5;


//    private Texture l1;
//    private Texture l2;
//    private Texture l3;
//    private Texture l4;
//    private Texture l5;


    public MapState(final GameStateManager gsm) {
        super(gsm);
        bg = new Texture("LevelBg.png");
//        l1 = new Texture("Lvl1.png");
//        l2 = new Texture("Lvl2.png");
//        l3 = new Texture("Lvl3.png");
//        l4 = new Texture("Lvl4.png");
//        l5 = new Texture("Lvl5.png");

        myTexture1 = new Texture(Gdx.files.internal("Lvl1.png"));
        myTextureRegion1 = new TextureRegion(myTexture1);
        myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
        button1 = new ImageButton(myTexRegionDrawable1);
        stage = new Stage(new StretchViewport(GameTutorial.WIDTH, GameTutorial.HEIGHT));
        stage.addActor(button1);
        Gdx.input.setInputProcessor(stage);
        button1.setBounds(124,322,64,47);
        button1.getImageCell().expand().fill();

        button1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected Level 1");
                gsm.push(new Level1(gsm));
            }
        });

        myTexture2 = new Texture(Gdx.files.internal("Lvl2.png"));
        myTextureRegion2 = new TextureRegion(myTexture2);
        myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
        button2 = new ImageButton(myTexRegionDrawable2);
        stage.addActor(button2);
        Gdx.input.setInputProcessor(stage);
        button2.setBounds(205,136,64,47);
        button2.getImageCell().expand().fill();

        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected Level 2");
                gsm.push(new Level2(gsm));
            }
        });

        myTexture3 = new Texture(Gdx.files.internal("Lvl3.png"));
        myTextureRegion3 = new TextureRegion(myTexture3);
        myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);
        button3 = new ImageButton(myTexRegionDrawable3);
        stage.addActor(button3);
        Gdx.input.setInputProcessor(stage);
        button3.setBounds(377,260,64,47);
        button3.getImageCell().expand().fill();

        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected Level 3");
                gsm.push(new Level3(gsm));
            }
        });

        myTexture4 = new Texture(Gdx.files.internal("Lvl4.png"));
        myTextureRegion4 = new TextureRegion(myTexture4);
        myTexRegionDrawable4 = new TextureRegionDrawable(myTextureRegion4);
        button4 = new ImageButton(myTexRegionDrawable4);
        stage.addActor(button4);
        Gdx.input.setInputProcessor(stage);
        button4.setBounds(526,354,64,47);
        button4.getImageCell().expand().fill();

        button4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected Level 4");
                gsm.push(new Level4(gsm));
            }
        });


        myTexture5 = new Texture(Gdx.files.internal("Lvl5.png"));
        myTextureRegion5 = new TextureRegion(myTexture5);
        myTexRegionDrawable5 = new TextureRegionDrawable(myTextureRegion5);
        button5 = new ImageButton(myTexRegionDrawable5);
        stage.addActor(button5);
        Gdx.input.setInputProcessor(stage);
        button5.setBounds(526,107,64,47);
        button5.getImageCell().expand().fill();

        button5.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Selected Level 5");
                gsm.push(new Level5(gsm));
            }
        });

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

        sb.begin();
        sb.draw(bg, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
//        sb.draw(l1,117,322,64,47);
//        sb.draw(l2,200,138,64,47);
//        sb.draw(l3,369,251,64,47);
//        sb.draw(l4,526,354,64,47);
//        sb.draw(l5,522,107,64,47);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw();

    }


    @Override
    public void dispose() {
        bg.dispose();
        stage.dispose();
        System.out.println("Disposing of Menu State");
    }
}

