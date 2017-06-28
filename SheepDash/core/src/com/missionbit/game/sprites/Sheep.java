package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by MissionBit on 6/27/17.
 */

public class Sheep {
    public Vector3 position;
    private Vector3 velocity;
    private Texture sheep;
    public Rectangle bounds;

    public Sheep(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        sheep = new Texture("Sheep(1)");
    }

    public void update(float dt){
    }

    public Vector3 getPosition() { return position; }

    public Texture getSheep() { return sheep;}

    public void jump (){
        velocity.y = 250;
    }

    public void dispose(){
        sheep.dispose();
    }
}
