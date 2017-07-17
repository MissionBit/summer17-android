package com.missionbit.game.sprites.animals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
    private Animation jumpAnimation;
    private Animation deathAnimation;
    private float timer;
    private boolean isTimerStarted = false;
    private static final float PENALTY_TIMER=1;
    private Sound jump;

    public Bunny(int x, int y) {
        super(x, y);
        bunny = new Texture("bunnyrunning.png");
        bunnyJump = new Texture("bunnyjumpinplace.png");
        bunnyDead = new Texture("bunnysquish.png");
        bunnyAnimation = new Animation(new TextureRegion(bunny),7,0.5f);
        jumpAnimation = new Animation(new TextureRegion(bunnyJump),12,0.5f);
        deathAnimation = new Animation(new TextureRegion(bunnyDead),17,0.5f);
        boundsBunny = new Rectangle(x,y,70,45);
        jump = Gdx.audio.newSound(Gdx.files.internal("jump_07.mp3"));
    }

    @Override
    public void update(float dt) {
        bunnyAnimation.update(dt);
        jumpAnimation.update(dt);
        deathAnimation.update(dt);
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
        return timer > PENALTY_TIMER;
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
        bunny.dispose();
        bunnyJump.dispose();
        bunnyDead.dispose();
    }

    public Rectangle getBoundsBunny() {
        return boundsBunny;
    }

    public TextureRegion getBunny() {
        return bunnyAnimation.getFrame();
    }

    public TextureRegion getBunnyJump() {
        return jumpAnimation.getFrame();
    }

    public TextureRegion getBunnyDead() {
        return deathAnimation.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }
}
