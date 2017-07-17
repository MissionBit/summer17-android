package com.missionbit.game.sprites.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.sprites.Animation;

import java.util.Random;

/**
 * Created by missionbit on 7/10/17.
 */

public class Cherry {
    private Texture cherry;
    private Rectangle cherryBounds;
    private Animation cherryAnimation;
    private Vector2 posCherry;
    private Random rand;
    private static final int FLUCTUATION = 300;
    private static final int CHERRY_MIN_X = 250;
    private boolean collided;

    public Cherry(int x, int y){
        cherry = new Texture("Cherry2_0.35.png");
        cherryAnimation = new Animation(new TextureRegion(cherry),2,0.5f);
        rand = new Random();
        posCherry = new Vector2(rand.nextInt(FLUCTUATION)+CHERRY_MIN_X,60);
        cherryBounds = new Rectangle(posCherry.x,posCherry.y,cherry.getWidth(),cherry.getHeight());
        collided = false;

    }

    public boolean collides(Rectangle player){
        if (((player.overlaps(cherryBounds)))&& !collided) {
            collided = true;
            return true;
        }
        else{
            return false;
        }
    }

    public void update(float dt){
        cherryAnimation.update(dt);
    }

    public TextureRegion getCherry() {
        return cherryAnimation.getFrame();
    }

    public Rectangle getCherryBounds() {
        return cherryBounds;
    }

    public Vector2 getPosCherry() {
        return posCherry;
    }

    public void dispose(){
        cherry.dispose();
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }
}
