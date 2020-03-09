package com.missionbit.game.sprites.animals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.sprites.Animation;

/**
 * Created by missionbit on 7/10/17.
 */

public class Cow extends Animals {
    private Texture cow;
    private Texture cowDead;
    private Rectangle cowBounds;
    private Animation cowAnimation;
    private Animation cow1Animation;
    private float timer;
    private boolean isTimerStarted = false;
    private static final float PENALTY_TIMER=1;

    public Cow(int x, int y) {
        super(x, y);
        cow = new Texture("RunningCow1.png");
        cowDead = new Texture("cowsquish.png");
        cowAnimation = new Animation(new TextureRegion(cow),4,0.5f);
        cow1Animation = new Animation(new TextureRegion(cowDead),23,0.5f);
        cowBounds = new Rectangle(x,y,70,45);
    }

    @Override
    public void update(float dt) {
        cowAnimation.update(dt);
        cow1Animation.update(dt);
        if (position.y >0){
            velocity.add(0,GRAVITY,0);
        }
        velocity.scl(dt);
        position.add(movement * dt,velocity.y,0);
        velocity.scl(1/dt);
        if(position.y < 60){
            position.y = 60;
        }

        cowBounds.setPosition(position.x,position.y);

    }

    public void jump() {
        if (position.y == 60){
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
        movement = 230;
    }

    public void resetSpd(){
        movement = 250;

    }

    public void stopSpd(){
        movement = 0;
    }

    public void increaseSpd(){
        movement = 270;
    }


    @Override
    public void dispose() {
        cow.dispose();

    }

    public void cowDied() {
        movement = 0;
        velocity.y = 0;
    }

    public void goBackwards() {
        movement = -250;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getCow() {
        return cowAnimation.getFrame();
    }

    public TextureRegion getCowDead() {
        position.y = 60;
        return cow1Animation.getLastFrame();
    }

    public void setDead(boolean dead) {
        cow1Animation.setDead(false);
    }

    public Rectangle getCowBounds() {
        return cowBounds;
    }
}
