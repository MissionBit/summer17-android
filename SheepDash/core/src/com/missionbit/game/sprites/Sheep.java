package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by MissionBit on 6/27/17.
 */

public class Sheep extends Animals{
    private Texture sheep;
    private Rectangle bounds1;
    private Animation sheepAnimation;
    private Texture sheepDead;
    private Animation sheep2Animation;
    private Sound jump;

    public Sheep(int x, int y) {
        super(x, y);
        sheep = new Texture("sheepClone1.png");
        sheepDead = new Texture("squishSheep1.png");
        sheepAnimation = new Animation(new TextureRegion(sheep),4,0.5f);
        sheep2Animation = new Animation(new TextureRegion(sheepDead),15,0.5f);
        bounds1 = new Rectangle(x,y,70,45);
        jump = Gdx.audio.newSound(Gdx.files.internal("jump_07.mp3"));
    }

    @Override
    public void update(float dt) {
        sheepAnimation.update(dt);
        sheep2Animation.update(dt);
        if (position.y >0){
            velocity.add(0,GRAVITY,0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);
        velocity.scl(1/dt);
        if(position.y < 60){
            position.y = 60;
        }
        if(position.y>300){
            position.y=60;
        }
        bounds1.setPosition(position.x,position.y);
    }

    public void jump() {
        velocity.y = 440;

    }

    public void reduceSpd(){
        if (Gdx.graphics.getDeltaTime() < 1){
            MOVEMENT = 100;
        }
    }

    public void increaseSpd(){
        if (Gdx.graphics.getDeltaTime() > 0.02){
            MOVEMENT = 210;
        }
    }

    public void sheepDied() {
        MOVEMENT = 0;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBounds1() {
        return bounds1;
    }

    public TextureRegion getSheep() {
        return sheepAnimation.getFrame();
    }

    public TextureRegion getSheepDead(){
        return sheep2Animation.getFrame();
    }

    @Override
    public void dispose() {
        sheep.dispose();
    }

}
