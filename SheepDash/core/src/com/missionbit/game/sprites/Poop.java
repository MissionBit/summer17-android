package com.missionbit.game.sprites;

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
    private static final int POOP_MIN_X = 200;
    private Vector2 posPoop;
    private Vector2 posPoop2;
    private Rectangle boundsPoop2;
    private Rectangle boundsPoop;
    private Random rand;

    public Poop(int x, int y){
        poop = new Texture("poop.png");
        rand = new Random();
        posPoop = new Vector2(rand.nextInt(FLUCTUATION)+POOP_MIN_X,60);
        posPoop2 = new Vector2(rand.nextInt(FLUCTUATION)+POOP_MIN_X+posPoop.x,60);
        boundsPoop = new Rectangle(posPoop.x,posPoop.y,poop.getWidth(),poop.getHeight());
        boundsPoop2 = new Rectangle(posPoop2.x,posPoop2.y,poop.getWidth(),poop.getHeight());

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

    public void reposition(float x){
        posPoop.set(rand.nextInt(FLUCTUATION)+POOP_MIN_X,60);
        posPoop2.set(rand.nextInt(FLUCTUATION)+POOP_MIN_X+posPoop.x,60);
        boundsPoop.setPosition(posPoop.x,posPoop.y);
        boundsPoop2.setPosition(posPoop2.x,posPoop2.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsPoop) || player.overlaps(boundsPoop2);
    }

    public void dispose(){
        poop.dispose();
    }

    public Vector2 getPosPoop2() {
        return posPoop2;
    }

    public Rectangle getBoundsPoop2() {
        return boundsPoop2;
    }
}
