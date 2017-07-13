package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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


        public MapState(GameStateManager gsm) {
            super(gsm);
            bg = new Texture("CornField.png");
            l1 = new Texture("a");
            l2 = new Texture("");
            l3 = new Texture("");
            l4 = new Texture("");
            l5 = new Texture("");

        }

    @Override
    protected void handleInput() {

    }

        /*@Override
        public void handleInput() {
            if (Gdx.input.justTouched()) {
                gsm.set(new Level1(gsm));
            }
        }
        */

        @Override
        public void update(float dt) {
            handleInput();
        }

        @Override
        public void render(SpriteBatch sb) {
            sb.setProjectionMatrix(cam.combined);
            sb.begin();
            sb.draw(bg, 0, 0, 256,180);
            sb.draw(l1,100,400);
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

