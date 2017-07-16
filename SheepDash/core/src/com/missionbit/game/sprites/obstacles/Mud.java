package com.missionbit.game.sprites.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by missionbit on 7/13/17.
 */

public class Mud {
    private Texture mud;
    private static final int FLUCTUATION = 300;
    private static final int MUD_MIN_X = 400;
    private Vector2 posMud;
    private Rectangle boundsMud;
    private Random rand;
    private boolean collided;

    public Mud(int x, int y){
        mud = new Texture("mud.png");
        rand = new Random();
        posMud = new Vector2(rand.nextInt(FLUCTUATION)+MUD_MIN_X,37);
        boundsMud = new Rectangle(posMud.x,posMud.y,30,30);
        collided = false;

    }

    public Texture getMud() {
        return mud;
    }

    public Rectangle getBoundsMud() {
        return boundsMud;
    }

    public Vector2 getPosMud() {
        return posMud;
    }


    public boolean collides(Rectangle player){
        if ((player.overlaps(boundsMud))) {
            collided = true;
            return true;
        }
        else{
            return false;
        }
    }

    public void dispose(){
        mud.dispose();
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }
}

