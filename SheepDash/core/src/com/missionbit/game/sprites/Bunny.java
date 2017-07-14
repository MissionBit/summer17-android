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

public class Bunny extends Animals{
    private Texture bunny;
    private Rectangle bounds;
    private Animation bunnyAnimation;
    private Texture bunnyDead;
    private Animation deathAnimation;
    private Sound jump;

    public Bunny(int x, int y) {
        super(x, y);
        bunny = new Texture("bunnyrunnint7_0.35.png");
        bunnyDead = new Texture("bunnydeath17_1.png");
        bunnyAnimation = new Animation(new TextureRegion(bunny),4,0.5f);
        deathAnimation = new Animation(new TextureRegion(bunnyDead),15,0.5f);
        bounds = new Rectangle(x,y,70,45);
        jump = Gdx.audio.newSound(Gdx.files.internal("jump_07.mp3"));
    }

    @Override
    public void update(float dt) {
        bunnyAnimation.update(dt);
        deathAnimation.update(dt);
        if (position.y >0){
            velocity.add(0,GRAVITY,0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);
        velocity.scl(1/dt);
        if(position.y < 60){
            position.y = 60;
        }

        bounds.setPosition(position.x,position.y);
    }

    public void jump() {
        velocity.y = 500;

    }

    public void reduceSpd(){
        if (Gdx.graphics.getDeltaTime() < 1){
            MOVEMENT = 50;
        }
    }

    public void increaseSpd(){
        if (Gdx.graphics.getDeltaTime() > 0.02){
            MOVEMENT = 90;
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public TextureRegion getBunny() {
        return bunnyAnimation.getFrame();
    }

    public TextureRegion getBunnyDead(){
        return deathAnimation.getFrame();
    }

    @Override
    public void dispose() {
        bunny.dispose();
    }

}
