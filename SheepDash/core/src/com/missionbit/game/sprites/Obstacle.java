package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by missionbit on 6/29/17.
 */

public class Obstacle {
    private Texture obstacle;
    private Vector2 posObs;
    private Random rand;
    private Rectangle boundsObs;

    //can change the obstacles and their position with each level
    public Obstacle(Texture obstacle, float x, float y) {
        this.obstacle = obstacle;
        rand = new Random();
        posObs = new Vector2(x, y);
        boundsObs = new Rectangle(x, y, obstacle.getWidth(), obstacle.getHeight());
    }

    public Texture getObstacle() {return obstacle; }

    public Vector2 getPosObs() { return posObs; }

    //reposition the obstacle
    public void reposition (float x) {
        posObs.set(x, posObs.y);
        boundsObs.setPosition(posObs.x, posObs.y);
    }

    //collision check
    public boolean collides(Rectangle player) {
        return player.overlaps(boundsObs);
    }

    //dispose
    public void dispose() {
        obstacle.dispose();
    }
}
