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
 * Created by missionbit on 6/26/17.
 */

public class CharacterState extends State {
    private Stage stage;
    private Texture bg;

    //Sheep button
    private Texture sheep;
    private TextureRegion sheepRegion;
    private TextureRegionDrawable sheepDrawable;
    private ImageButton sheepButton;

    //Cow button
    private Texture cow;
    private TextureRegion cowRegion;
    private TextureRegionDrawable cowDrawable;
    private ImageButton cowButton;

    //Pig button
    private Texture pig;
    private TextureRegion pigRegion;
    private TextureRegionDrawable pigDrawable;
    private ImageButton pigButton;

    //Chick button
    private Texture chick;
    private TextureRegion chickRegion;
    private TextureRegionDrawable chickDrawable;
    private ImageButton chickButton;

    //Bunny button
    private Texture bunny;
    private TextureRegion bunnyRegion;
    private TextureRegionDrawable bunnyDrawable;
    private ImageButton bunnyButton;

    public CharacterState(final GameStateManager gsm) {
        super(gsm);
        bg = new Texture("CharScreen.png");

        sheep = new Texture(Gdx.files.internal("SheepButton.png"));
        sheepRegion = new TextureRegion(sheep);
        sheepDrawable = new TextureRegionDrawable(sheepRegion);
        sheepButton = new ImageButton(sheepDrawable);
        stage = new Stage(new StretchViewport(GameTutorial.WIDTH, GameTutorial.HEIGHT));
        stage.addActor(sheepButton);
        Gdx.input.setInputProcessor(stage);
        sheepButton.setBounds(100, 230, 100, 100);
        sheepButton.getImageCell().expand().fill();
        sheepButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new MapState(gsm));
            }
        });

        cow = new Texture(Gdx.files.internal("CowButton.png"));
        cowRegion = new TextureRegion(cow);
        cowDrawable = new TextureRegionDrawable(cowRegion);
        cowButton = new ImageButton(cowDrawable);
        stage.addActor(cowButton);
        cowButton.setBounds(250, 230, 100, 100);
        cowButton.getImageCell().expand().fill();
        cowButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new MapState(gsm));
            }
        });

        pig = new Texture(Gdx.files.internal("PigButton.png"));
        pigRegion = new TextureRegion(pig);
        pigDrawable = new TextureRegionDrawable(pigRegion);
        pigButton = new ImageButton(pigDrawable);
        stage.addActor(pigButton);
        Gdx.input.setInputProcessor(stage);
        pigButton.setBounds(400, 230, 100, 100);
        pigButton.getImageCell().expand().fill();
        pigButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new MapState(gsm));
            }
        });

        chick = new Texture(Gdx.files.internal("ChickButton.png"));
        chickRegion = new TextureRegion(chick);
        chickDrawable = new TextureRegionDrawable(chickRegion);
        chickButton = new ImageButton(chickDrawable);
        stage.addActor(chickButton);
        chickButton.setBounds(175, 80, 100, 100);
        chickButton.getImageCell().expand().fill();
        chickButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new MapState(gsm));
            }
        });

        bunny = new Texture(Gdx.files.internal("BunnyButton.png"));
        bunnyRegion = new TextureRegion(bunny);
        bunnyDrawable = new TextureRegionDrawable(bunnyRegion);
        bunnyButton = new ImageButton(bunnyDrawable);
        stage.addActor(bunnyButton);
        bunnyButton.setBounds(325, 80, 100, 100);
        bunnyButton.getImageCell().expand().fill();
        bunnyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new MapState(gsm));
            }
        });
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void create() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        bg.dispose();
        sheep.dispose();
        cow.dispose();
        pig.dispose();
        chick.dispose();
        bunny.dispose();
        stage.dispose();
    }

}
