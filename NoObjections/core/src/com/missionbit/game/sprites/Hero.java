package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by missionbit on 6/26/17.
 */

public class Hero {
    private Vector2 position;
    private Vector2 velocity;
    private Texture hero;
    private static final int GRAVITY =-15;
    public Hero (int x, int y){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        hero = new Texture("charRunning.png");
    }

    public void update(float dt){
        velocity.add(0, GRAVITY);
        velocity.scl(dt);
        position.add(0, velocity.y);
        velocity.scl(1/ dt);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return hero;
    }



    }





