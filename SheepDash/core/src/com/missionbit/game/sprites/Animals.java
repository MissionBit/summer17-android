package com.missionbit.game.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by missionbit on 6/26/17.
 */


public abstract class Animals {

    private Texture sheep;
    private Texture duck;
    public Vector3 position;
    public Vector3 velocity;
    private Rectangle bounds;
    public int GRAVITY = -9;
    public int MOVEMENT = 50;

    protected Animals(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);

    }

    public abstract void update(float dt);
    public abstract void dispose();


    public void jump(){
        velocity.y = 100;
    }

    public Vector3 getPosition() {
        return position;
    }

}
