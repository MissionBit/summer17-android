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

/**
 * Created by missionbit on 7/12/17.
 */

public class GameOver extends State{

    public Texture black;
    public Texture gameOverTxt;

    private Stage stage;

    private Texture myTexture1;
    private TextureRegion myTextureRegion1;
    private TextureRegionDrawable myTexRegionDrawable1;
    private ImageButton back;

    private Texture myTexture2;
    private TextureRegion myTextureRegion2;
    private TextureRegionDrawable myTexRegionDrawable2;
    private ImageButton restart;

    public GameOver(final GameStateManager gsm) {

        super(gsm);

        black = new Texture("blackness.jpg");
        gameOverTxt = new Texture("GameOver.png");

        myTexture1 = new Texture(Gdx.files.internal("back.png"));
        myTextureRegion1 = new TextureRegion(myTexture1);
        myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
        back = new ImageButton(myTexRegionDrawable1);
        stage = new Stage(new StretchViewport(GameTutorial.WIDTH, GameTutorial.HEIGHT));
        stage.addActor(back);
        Gdx.input.setInputProcessor(stage);
        back.setBounds(GameTutorial.WIDTH/2 - 50,230,64,47);
        back.getImageCell().expand().fill();

        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exiting to Menu");
                gsm.push(new MenuState(gsm));
            }
        });

        myTexture2 = new Texture(Gdx.files.internal("restart.png"));
        myTextureRegion2 = new TextureRegion(myTexture2);
        myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
        restart = new ImageButton(myTexRegionDrawable2);
        stage.addActor(restart);
        Gdx.input.setInputProcessor(stage);
        restart.setBounds(GameTutorial.WIDTH/2 + 50,230,64,47);
        restart.getImageCell().expand().fill();

        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Level restarting");
                gsm.push(new Level5(gsm));
            }
        });
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void create() {

    }

    @Override
    public void update(float dt) {handleInput();}

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(black, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        sb.draw(gameOverTxt,GameTutorial.WIDTH/2,300);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {

        black.dispose();
        gameOverTxt.dispose();
        stage.dispose();



    }
}
