package com.missionbit.game.sprites.animals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.sprites.Animation;

/**
 * Created by missionbit on 7/6/17.
 */

public class Bunny extends Animals {
    private Texture bunny;
    private Texture bunnyJump;
    private Texture bunnyDead;
    private Rectangle boundsBunny;
    private Animation bunnyAnimation;
    private Animation bunny2Animation;
    private Animation bunny3Animation;
    private float timer;
    private boolean isTimerStarted = false;
    private static final float PENALTY_TIMER=1;

    public Bunny(int x, int y) {

        super(x, y);
        bunny = new Texture("bunnyrunning7_0.35.png");
        bunnyJump = new Texture("bunnyjumpinplace12_0.4.png");
        bunnyDead = new Texture("bunnydeath17_1.png");
        bunnyAnimation = new Animation(new TextureRegion(bunny),7,0.5f);
        bunny2Animation = new Animation(new TextureRegion(bunnyJump),12,0.5f);
        bunny3Animation = new Animation(new TextureRegion(bunnyDead),17,0.5f);
        boundsBunny = new Rectangle(x,y,70,45);
    }



    @Override
    public void update(float dt) {
        bunnyAnimation.update(dt);
        bunny2Animation.update(dt);
        bunny3Animation.update(dt);
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
        boundsBunny.setPosition(position.x,position.y);

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
        MOVEMENT = 110;
    }

    public void resetSpd(){
        MOVEMENT = 200;

    }

    public void stopSpd(){
        MOVEMENT = 0;
    }

    public void increaseSpd(){
        MOVEMENT = 250;
    }

    @Override
    public void dispose() {
        bunny.dispose();

    }

    public Rectangle getBoundsBunny() {
        return boundsBunny;
    }

    public TextureRegion getBunny() {
        return bunnyAnimation.getFrame();
    }

    public TextureRegion getBunnyJump() {
        return bunny2Animation.getFrame();
    }

    public TextureRegion getBunnyDead() {
        return bunny3Animation.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }
}
