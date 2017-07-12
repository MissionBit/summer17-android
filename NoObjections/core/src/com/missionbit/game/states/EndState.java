package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.missionbit.game.NoObjectionGame;




/**
 * Created by missionbit on 6/26/17.
 */


public class EndState extends State {
    private Texture menu;


    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private Stage stage;
    private Texture myTexture1;
    private TextureRegion myTextureRegion1;
    private TextureRegionDrawable myTexRegionDrawable1;
    private ImageButton playButton;



    public EndState(final GameStateManager gsm) {
        super(gsm);
        menu = new Texture("pixil-layer-Background.png");

        myTexture1 = new Texture(Gdx.files.internal("black_bg.png")); //put the image you want for your button here
        myTextureRegion1 = new TextureRegion(myTexture1);
        myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
        playButton = new ImageButton(myTexRegionDrawable1); //Set the button up
        stage = new Stage(new StretchViewport(NoObjectionGame.WIDTH, NoObjectionGame.HEIGHT)); //Set up a stage for the ui, the two parameters for the StretchViewport will be your game width and height variables.

        stage.addActor(playButton); //Add the button to the stage to perform rendering and take input.
        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

        playButton.setBounds(200, 100 , 300, 300); //x, y, width (of button), height (of button)
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.push(new MenuState(gsm));
            }
        });

    }

    @Override
    public void handleInput() {

    }


    public void create() {

    }


    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(menu, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw(); //Draw the ui
    }


    public void dispose() {
        menu.dispose();
        stage.dispose();
        myTexture1.dispose();
    }
}
