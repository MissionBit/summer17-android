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

public class Sheep {
    public Vector3 position;
    private Vector3 velocity;
    private Texture sheep;
    public Rectangle bounds;
    private Animation sheepAnimation;
    private Sound jump;
    private static final int GRAVITY = -15;
    private static final int MOVMENT = 75;

    public Sheep(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        sheep = new Texture("Sheepdeath15.1.png");
        sheepAnimation = new Animation(new TextureRegion(sheep), 15, 1f);
        jump = Gdx.audio.newSound(Gdx.files.internal("jump_07.mp3"));
        bounds = new Rectangle(x, y, sheep.getWidth() / 3, sheep.getHeight());
    }

    public void update(float dt){
            sheepAnimation.update(dt);
            if (position.y > 0) {
                velocity.add(0, GRAVITY, 0);
            }
            velocity.scl(dt);
            position.add(MOVMENT*dt, velocity.y, 0);
            if (position.y < 0) {
                position.y = 0;
            }
            velocity.scl(1 / dt);
            bounds.setPosition(position.x, position.y);
        }

    public Vector3 getPosition() { return position; }

    public TextureRegion getSheep() { return sheepAnimation.getFrame();}

    public void jump (){
        velocity.y = 250;
    }

    public void dispose(){
        sheep.dispose();
    }
}
