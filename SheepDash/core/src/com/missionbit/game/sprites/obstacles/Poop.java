package com.missionbit.game.sprites.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by missionbit on 7/5/17.
 */

public class Poop {
    private Texture poop;
    private static final int FLUCTUATION = 300;
    private static final int POOP_MIN_X = 400;
    private Vector2 posPoop;
    private Rectangle boundsPoop;
    private Random rand;
    private boolean collided;

    public Poop(int x, int y){
        poop = new Texture("poop.png");
        rand = new Random();
        posPoop = new Vector2(rand.nextInt(FLUCTUATION)+POOP_MIN_X,60);
        boundsPoop = new Rectangle(posPoop.x,posPoop.y,30,30);
        collided = false;
    }

    public Texture getPoop() {
        return poop;
    }

    public Rectangle getBoundsPoop() {
        return boundsPoop;
    }

    public Vector2 getPosPoop() {
        return posPoop;
    }

    public void reposition(float x, int y){
        posPoop.set(x, y);
        boundsPoop.setPosition(posPoop.x,posPoop.y);
    }

    public boolean collides(Rectangle player){
        if ((player.overlaps(boundsPoop)&& !collided)) {
            collided = true;
            return true;
        }
        else{
            return false;
        }
    }

    public void dispose(){
        poop.dispose();
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }
}
