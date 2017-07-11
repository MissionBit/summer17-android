package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by missionbit on 6/26/17.
 */

public abstract class Animals {
    protected Vector3 position;
    protected Vector3 velocity;
    protected static final int GRAVITY = -23;
    protected int MOVEMENT = 90;

    protected Animals(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);

    }

    public abstract void update(float dt);
    public abstract void dispose();

}
