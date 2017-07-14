package com.missionbit.game.sprites.animals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.sprites.Animation;

/**
 * Created by missionbit on 7/10/17.
 */

public class Pig extends Animals {
    private Texture pig;
    private Texture pigDead;
    private Animation pigAnimation;
    private Animation pig1Animation;
    private Rectangle pigBounds;
    private float timer;
    private boolean isTimerStarted = false;
    private static final float PENALTY_TIMER=1;

    public Pig(int x, int y) {
        super(x, y);
        pig = new Texture("PigRun.png");
        pigDead = new Texture("PigDead.png");
        pigAnimation = new Animation(new TextureRegion(pig),4,0.5f);
        pig1Animation = new Animation(new TextureRegion(pigDead),27,0.5f);
        pigBounds = new Rectangle(x,y,70,45);
    }

    @Override
    public void update(float dt) {
        pigAnimation.update(dt);
        pig1Animation.update(dt);
        if (position.y >0){
            velocity.add(0,GRAVITY,0);
        }
        velocity.scl(dt);
        position.add(movement * dt,velocity.y,0);
        velocity.scl(1/dt);
        if(position.y < 60){
            position.y = 60;
        }
        if(position.y>300){
            position.y=60;
        }
        pigBounds.setPosition(position.x,position.y);


    }

    public void jump() {
        if (position.y <= 150){
            velocity.y = 500;
        }

    }

    public void updateTimer(float elaspedTime){
        if (isTimerStarted){
            timer = timer + elaspedTime;
        }
    }

    public void startTimer(){
        timer = 0;
        isTimerStarted = true;
    }

    public boolean isTimerDone(){
        if (timer > PENALTY_TIMER ){
            return true;
        }
        return false;
    }

    public void reduceSpd(){
        movement = 110;
    }

    public void resetSpd(){
        movement = 200;

    }

    public void stopSpd(){
        movement = 0;
    }

    public void increaseSpd(){
        movement = 250;
    }


    @Override
    public void dispose() {
        pig.dispose();

    }

    public TextureRegion getPig() {
        return pigAnimation.getFrame();
    }

    public TextureRegion getPigDead() {
        return pig1Animation.getFrame();
    }

    public Rectangle getPigBounds() {
        return pigBounds;
    }

    public Vector3 getPosition() {
        return position;
    }
}
