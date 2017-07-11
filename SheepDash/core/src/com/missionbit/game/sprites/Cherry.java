package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by MissionBit on 7/11/17.
 */

public class Cherry {
    private Texture cherry;
    private static final int FLUCTUATION = 300;
    private static final int POOP_MIN_X = 200;
    private Animation cherryAnimation;
    private Vector2 posCherry;
    //private Vector2 posCherry2;
    //private Rectangle boundsCherry2;
    private Rectangle boundsCherry;
    private Random rand;

    public Cherry(int x, int y){
        cherry = new Texture("Cherry2_0.35.png");
        rand = new Random();
        cherryAnimation = new Animation(new TextureRegion(cherry),2,0.5f);
        posCherry = new Vector2(rand.nextInt(FLUCTUATION)+POOP_MIN_X,60);
        //posPoop2 = new Vector2(rand.nextInt(FLUCTUATION)+POOP_MIN_X+posPoop.x,60);
        boundsCherry = new Rectangle(posCherry.x,posCherry.y,cherry.getWidth(),cherry.getHeight());
        //boundsPoop2 = new Rectangle(posPoop2.x,posPoop2.y,poop.getWidth(),poop.getHeight());

    }

    public void update(float dt) {
        cherryAnimation.update(dt);
    }

    public Texture getChery() {
        return cherry;
    }

    public Rectangle getBoundsCherry() {
        return boundsCherry;
    }

    public Vector2 getPosCherry() {
        return posCherry;
    }

    public void reposition(float x){
        //posCherry.set(rand.nextInt(FLUCTUATION)+POOP_MIN_X,60);
        //posPoop2.set(rand.nextInt(FLUCTUATION)+POOP_MIN_X+posPoop.x,60);
        //boundsPoop.setPosition(posPoop.x,posPoop.y);
        //boundsPoop2.setPosition(posPoop2.x,posPoop2.y);
    }

    /*public boolean collides(Rectangle player){
       // return player.overlaps(boundsCherry) || player.overlaps(boundsCherry2);
    }*/

    public void dispose(){
        cherry.dispose();
    }

   /* public Vector2 getPosPoop2() {
        return posCherry2;
    }

    public Rectangle getBoundsPoop2() {
        return boundsPoop2;
    }*/
}
