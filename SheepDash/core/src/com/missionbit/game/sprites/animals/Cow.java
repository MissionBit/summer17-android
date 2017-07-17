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
        cow1Animation = new Animation(new TextureRegion(cowDead),24,0.5f);
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
        if(position.y>300){
            position.y=60;
        }
        cowBounds.setPosition(position.x,position.y);

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
        cow.dispose();

    }


    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getCow() {
        return cowAnimation.getFrame();
    }

    public TextureRegion getCowDead() {
        return cow1Animation.getFrame();
    }

    public Rectangle getCowBounds() {
        return cowBounds;
    }
}
