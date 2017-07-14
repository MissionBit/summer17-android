package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Logger;
import com.missionbit.game.NoObjectionGame;




/**
 * Created by missionbit on 6/26/17.
 */


public class MenuState extends State {
    private Texture menu;

    //Logger log;
//    private Texture playBtn;
//    private Texture instructBtn;
    Stage stage;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    Button button;



    public MenuState(GameStateManager gsm) {
        super(gsm);
        menu = new Texture("coolbg3.png");
        //cam.setToOrtho(false, NoObjectionGame.WIDTH, NoObjectionGame.HEIGHT);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() > 0 && Gdx.input.getX() < 600) {
                if (Gdx.input.getY() > 200 && Gdx.input.getY() < 500) {
                    // gsm.set(new PlayState(gsm));
                }
            }


//        if (Gdx.input.justTouched()) {
//            gsm.set(new PlayState(gsm));
//        }
        }
    }

    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        //font = new BitmapFont();
        //skin = new Skin();
        //buttonAtlas = new TextureAtlas(Gdx.files.internal());
        //skin.addRegions(buttonAtlas);

        button = new Button();
        button.setStyle(new Button.ButtonStyle());

        button.addCaptureListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                Gdx.app.log("LOG", "event " + event);
                return false;
            }
        });
        stage.addActor(button);
    }


    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(menu, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.draw();
        sb.end();
//        if (Gdx.input.isTouched()) {
//
//
//            Gdx.app.log("LOG", "Coords: " + Gdx.input.getX()+  " , " +  Gdx.input.getY() );
//            Gdx.app.debug("MyTag", "my debug message");
//            System.out.println("logging coords: " + Gdx.input.getX()+  " , " +  Gdx.input.getY());
//
//            if (Gdx.input.getX() > 114 && Gdx.input.getX() < 205 && // This is the top-left corner of the button
//                    Gdx.input.getY() < 147 && Gdx.input.getY() > 187) { // This is the bottom-right corner of the button
//
//                // DO STUFF HERE WHEN BUTTON 1 PRESSED!!
//                handleInput();
//                Gdx.app.log("LOG", "play pressed");
//
//
//            } else if (Gdx.input.getX() > 193  && Gdx.input.getX() < 510 && // This is the top-left corner of the button
//                    Gdx.input.getY() < 441 && Gdx.input.getY() > 498) { // This is the bottom-right corner of the button
//
//                // DO STUFF HERE WHEN BUTTON 2 PRESSED!!
//                System.out.println("Options pressed");
//
//            }



    }


    public void dispose() {
        menu.dispose();
    }


}

