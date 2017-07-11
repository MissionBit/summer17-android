package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.missionbit.game.NoObjectionGame;


/**
 * Created by missionbit on 6/26/17.
 */

public class EndState extends State {
    private Texture background;
    private Texture playBtn;
    private Texture instructBtn;
    private TextButton tb;
    private TextButton.TextButtonStyle textButtonStyle;
    private Stage stage;
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;


    public EndState(final GameStateManager gsm) {
        super(gsm);
        background = new Texture("pixil-layer-Background.png");
        //textButtonStyle = new TextButton.TextButtonStyle();
       // tb = new TextButton("Restart", textButtonStyle);

        myTexture = new Texture(Gdx.files.internal("Restart_button.png")); //put the image you want for your button here
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable); //Set the button up
        stage = new Stage(new StretchViewport(NoObjectionGame.WIDTH, NoObjectionGame.HEIGHT));
        stage.addActor(button); //Add the button to the stage to perform rendering and take input.

        button.setBounds(275, 150 , 250, 200); //x, y, width (of button), height (of button) //x, y, width (of button), height (of button)

        Gdx.input.setInputProcessor(stage); //Start taking input from the ui
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.push(new MenuState(gsm));
            }
        });


    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
        }
//        tb.addListener(new ChangeListener() {
//            @Override
//            public void  changed(ChangeEvent event, Actor actor) {
//                gsm.set(new MenuState(gsm));
//            }
//        });
    }


    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
       // sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw(); //Draw the ui

        sb.end();
    }

    public void dispose() {

        background.dispose();
        stage.dispose();
    }

}
