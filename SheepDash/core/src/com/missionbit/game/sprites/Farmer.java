package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by missionbit on 6/29/17.
 */

public class Farmer {
    private Texture farmer;
    private Animation farmerAnimation;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle boundsFarmer;
    private Vector2 posFarmer;
    private int movement = 250;

    public Farmer(int x, int y){
        farmer = new Texture("farmerOnATractor.png");
        farmerAnimation = new Animation(new TextureRegion(farmer),8,0.5f);
        posFarmer = new Vector2(0,45);
        velocity = new Vector3(0,0,0);
        position = new Vector3(posFarmer.x,posFarmer.y,0);
        boundsFarmer = new Rectangle(posFarmer.x,posFarmer.y,70,45);

    }

    public void update(float dt) {
        farmerAnimation.update(dt);
        velocity.scl(dt);
        position.add(movement * dt, 0,0);
        velocity.scl(1/dt);
        boundsFarmer.setPosition(position.x,position.y);
        //System.out.println("Farmer's speed:" + movement);
    }

    public void killedSheep() {
        movement = 0;
    }


    public Vector3 getPosition() {
        return position;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsFarmer);
    }

    public TextureRegion getFarmer() {
        return farmerAnimation.getFrame();
    }

    public Rectangle getBoundsFarmer() {
        return boundsFarmer;
    }

    public void setBoundsFarmer(int x, int y){
        boundsFarmer.width = x;
        boundsFarmer.height = y;
    }

    public void dispose(){
        farmer.dispose();
    }

}
