package com.missionbit.game.sprites.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by missionbit on 7/12/17.
 */

public class Barrel {
    private Texture barrel;
    private Rectangle barrelBounds;
    //private Rectangle barrel2Bounds;
    private Vector2 posBarrel;
    //private Vector2 pos2Barrel;
    private Random rand;
    private boolean collided;
    private static final int FLUCTUATION = 200;
    private static final int BARREL_MIN_X = 400;

    public Barrel(int x, int y) {
        barrel = new Texture("Barrel.png");
        rand = new Random();
        posBarrel = new Vector2(rand.nextInt(FLUCTUATION) + BARREL_MIN_X, y);
        //pos2Barrel = new Vector2(rand.nextInt(FLUCTUATION)+BARREL_MIN_X+posBarrel.x,60);
        barrelBounds = new Rectangle(posBarrel.x, posBarrel.y, barrel.getWidth(), barrel.getHeight());
        //barrel2Bounds = new Rectangle(pos2Barrel.x,pos2Barrel.y,barrel.getWidth(),barrel.getHeight());
        collided = false;
    }

    public void reposition(float x, int y) {
        posBarrel.set(x, y);
        barrelBounds.setPosition(posBarrel.x, posBarrel.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(barrelBounds);
    }

    public Texture getBarrel() {
        return barrel;
    }

    public Rectangle getBarrelBounds() {
        return barrelBounds;
    }

    public Vector2 getPosBarrel() {
        return posBarrel;
    }

    public void dispose() {
        barrel.dispose();
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

//    public Rectangle getBarrel2Bounds() {
//        return barrel2Bounds;
//    }

//    public Vector2 getPos2Barrel() {
//        return pos2Barrel;
//    }
}
