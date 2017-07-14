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
    private float timer;
    private boolean isTimerStarted = false;
    private static final float PENALTY_TIMER =2;
    private Sound jump;

    public Sheep(int x, int y) {
        super(x, y);
        sheep = new Texture("sheeprunning.png");
        sheepDead = new Texture("sheepsquish.png");
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

        bounds1.setPosition(position.x,position.y);
        //System.out.println("Sheep's speed:" + MOVEMENT);
    }

    public void jump() {
        velocity.y = 500;
    }

    public void updateTimer(float elaspedTime) {
        if (isTimerStarted) {
            timer = timer + elaspedTime;
        }
    }

    public void startTimer() {
        timer = 0;
        isTimerStarted = true;
    }

    public boolean isTimerDone() {
        if (timer > PENALTY_TIMER) {
            return true;
        }
        return false;
    }
  
    public void reduceSpd(){
        MOVEMENT = 300;
    }

    public void resetSpd() {
        MOVEMENT = 440;
    }

    public void noSpd() {
        MOVEMENT = 0;
    }

    public void increaseSpd(){
        //MOVEMENT = MOVEMENT + 20;
        MOVEMENT = 500;
    }

    public void goBackwards() {
        MOVEMENT = -200;
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
        return sheep2Animation.getLastFrame();
    }

    public void setDead(boolean dead) {
        sheep2Animation.dead = false;
    }


    @Override
    public void dispose() {
        sheep.dispose();
    }

}
