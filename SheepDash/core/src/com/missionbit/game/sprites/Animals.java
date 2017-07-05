package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by missionbit on 6/26/17.
 */

public class Animals {
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private static final int GRAVITY = -9;
    private static final int MOVEMENT = 50;

    public Animals(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);

    }

    public void update(float dt){
        if (position.y >0){
            velocity.add(0,GRAVITY,0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, 0,0);
        velocity.scl(1/dt);

    }

    public void jump(){
        velocity.y = 100;
    }

    public Vector3 getPosition() {
        return position;
    }
}
