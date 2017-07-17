package com.missionbit.game.sprites.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.sprites.Animation;

import java.util.Random;

/**
 * Created by missionbit on 6/29/17.
 */

public class Obstacle {
    private Texture obstacle;
    private Animation obstacleAnimation;
    private Vector2 posObs;
    private Rectangle boundsObs;
    public boolean hasCollided;

    //can change the obstacles and their position with each level
    public Obstacle(Texture obstacle, float x, float y, int frames, float time) {
        this.obstacle = obstacle;
        obstacleAnimation = new Animation(new TextureRegion(obstacle), frames, time);
        posObs = new Vector2(x, y);
        boundsObs = new Rectangle(posObs.x, posObs.y, obstacle.getWidth(), obstacle.getHeight());
        hasCollided = false;
    }

    public Texture getObstacle() {return obstacle; }

    public TextureRegion getObsAnimation() {
        return obstacleAnimation.getFrame();
    }

    public void update(float dt) {
        obstacleAnimation.update(dt);
    }

    public int getWidth() { return obstacle.getWidth(); }

    public Vector2 getPosObs() { return posObs; }

    //reposition the obstacle
    public void reposition (float x, int y) {
        hasCollided = false;
        posObs.set(x, y);
        boundsObs.setPosition(posObs.x, posObs.y);
    }

    //collision check
    public boolean collides(Rectangle player) {
        if (player.overlaps(boundsObs) && !hasCollided) {
            hasCollided = true;
            return true;
        }
        else {
            return false;
        }
    }

    //dispose
    public void dispose() {
        obstacle.dispose();
    }

    public Rectangle getBoundsObs() {
        return boundsObs;
    }
}
