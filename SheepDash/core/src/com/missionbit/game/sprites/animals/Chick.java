package com.missionbit.game.sprites.animals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.sprites.Animation;

/**
 * Created by missionbit on 7/10/17.
 */

public class Chick extends Animals {
    private Texture chick;
    private Texture chickDead;
    private Rectangle chickBounds;
    private Animation chickAnimation;
    private Animation chick2Animation;
    private float timer;
    private boolean isTimerStarted = false;
    private static final float PENALTY_TIMER=1;

    public Chick(int x, int y) {
        super(x, y);
        chick = new Texture("chickRun.png");
        chickDead = new Texture("ducksquish.png");
        chickAnimation = new Animation(new TextureRegion(chick),20,0.5f);
        chick2Animation = new Animation(new TextureRegion(chickDead),19,0.5f);
        chickBounds = new Rectangle(x,y,70,45);
    }

    @Override
    public void update(float dt) {
        chickAnimation.update(dt);
        chick2Animation.update(dt);
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
        chickBounds.setPosition(position.x,position.y);

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
        chick.dispose();

    }

    public TextureRegion getChick() {
        return chickAnimation.getFrame();
    }

    public TextureRegion getChickDead() {
        return chick2Animation.getFrame();
    }

    public Rectangle getChickBounds() {
        return chickBounds;
    }

    public Vector3 getPosition() {
        return position;
    }
}
