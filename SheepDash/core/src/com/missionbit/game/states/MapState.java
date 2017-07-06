package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.missionbit.game.GameTutorial;
import com.missionbit.game.sprites.Level1;

/**
 * Created by missionbit on 6/26/17.
 */

public class MapState extends State {
    private Texture bg;
    private Texture l1;
    private Texture l2;
    private Texture l3;
    private Texture l4;
    private Texture l5;
    private Circle circle;


    public MapState(GameStateManager gsm) {
            super(gsm);
            bg = new Texture("LevelBg.png");
            l1 = new Texture("Lvl1.png");
            l2 = new Texture("Lvl2.png");
            l3 = new Texture("Lvl3.png");
            l4 = new Texture("Lvl4.png");
            l5 = new Texture("Lvl5.png");

        }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {

            System.out.println("logging coords: " + Gdx.input.getX() + " , " + Gdx.input.getY());

            if (Gdx.input.getX() > 125 && Gdx.input.getX() < 177 && // This is the top-left corner of the button
                    Gdx.input.getY() > 115 && Gdx.input.getY() < 156) { // This is the bottom-right corner of the button

                gsm.set(new Level2(gsm));


            } else if (Gdx.input.getX() > 215 && Gdx.input.getX() < 271 && // This is the top-left corner of the button
                    Gdx.input.getY() > 298 && Gdx.input.getY() < 339) { // This is the bottom-right corner of the button

                gsm.set(new Level2(gsm));
                System.out.println("logging coords: " + Gdx.input.getX() + " , " + Gdx.input.getY());

            } else if (Gdx.input.getX() > 483 && Gdx.input.getX() < 665 && // This is the top-left corner of the button
                    Gdx.input.getY() < 665 && Gdx.input.getY() > 519) { // This is the bottom-right corner of the button

                gsm.set(new Level3(gsm));

                Gdx.app.log("LOG", "Coords: " + Gdx.input.getX() + " , " + Gdx.input.getY());
                Gdx.app.debug("MyTag", "my debug message");
                System.out.println("logging coords: " + Gdx.input.getX() + " , " + Gdx.input.getY());

            } else if (Gdx.input.getX() > 483 && Gdx.input.getX() < 665 && // This is the top-left corner of the button
                    Gdx.input.getY() < 665 && Gdx.input.getY() > 519) { // This is the bottom-right corner of the button

                gsm.set(new Level4(gsm));
                System.out.println("logging coords: " + Gdx.input.getX() + " , " + Gdx.input.getY());

            } else if (Gdx.input.getX() > 483 && Gdx.input.getX() < 665 && // This is the top-left corner of the button
                    Gdx.input.getY() < 665 && Gdx.input.getY() > 519) { // This is the bottom-right corner of the button

                gsm.set(new Level5(gsm));
                System.out.println("logging coords: " + Gdx.input.getX() + " , " + Gdx.input.getY());

            }
            else;



        }

    }

        @Override
        public void update(float dt) {
            handleInput();
        }


        @Override
        public void render(SpriteBatch sb) {
            //sb.setProjectionMatrix(cam.combined);
            sb.begin();
            sb.draw(bg, 0, 0, GameTutorial.WIDTH, GameTutorial.HEIGHT);
            sb.draw(l1,117,322,64,47); //(x,y,w,h)
            sb.draw(l2,209,138,64,47);
            sb.draw(l3,369,251,64,47);
            sb.draw(l4,533,354,64,47);
            sb.draw(l5,522,107,64,47);
            sb.end();

            }


        @Override
        public void dispose() {
            bg.dispose();
            l1.dispose();
            l2.dispose();
            l3.dispose();
            l4.dispose();
            l5.dispose();

            System.out.println("Disposing of Menu State");
        }
    }

