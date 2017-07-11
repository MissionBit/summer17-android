package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.missionbit.game.NoObjectionGame;


/**
 * Created by missionbit on 6/26/17.
 */

public class Hero extends Sprite {
//    private Vector2 position;
//    private Vector2 velocity;
//    private Texture hero;
//    private static final int GRAVITY = -12;
//    private static final int MOVEMENT = 48;
//    private Animation heroAnimation;
//    private Animation heroClimbingAni;
//
//    private int screenHeight = Gdx.graphics.getWidth();
//    private int screenWidth = Gdx.graphics.getWidth();
////    private Texture heroClimbing;
//    //Flipping directions
//
//
//    public Hero(int x, int y) {
//        position = new Vector2(x, y);
//        velocity = new Vector2(0, 0);
//        hero = new Texture("dudeRun.png");
//       // heroClimbing = new Texture("charClimbing2.png");
//        heroAnimation = new Animation(new TextureRegion(hero), 5, 0.8f);
//        //heroClimbingAni = new Animation(new TextureRegion(heroClimbing), 4, 0.8f);
//
//    }
//
//    public void update(float dt) {
//        heroAnimation.update(dt);
//        //adds gravity
//        if(position.y > 0) {
//            velocity.add(0, GRAVITY);
//        }
//        velocity.scl(dt);
//        if(position.x + 127.5 < NoObjectionGame.WIDTH) {
//            position.add(MOVEMENT * dt, velocity.y);
//        }
//
//        if(position.y < 0) {
//            position.y = 0;
//        }
//        velocity.scl(1/dt);
//    }
//
//    public Vector2 getPosition() {
//        return position;
//    }
//
//    public TextureRegion getTexture() {
////        if (climbing()) {
////            return heroClimbingAni.getFrame();
////        }
//        return heroAnimation.getFrame();
//    }
//
//    public void jump() {
//        velocity.y = 800;
//    }
//
//
//    public void fall() {
//        velocity.y = -100;
//    }
//
//    public void dispose() {
//        hero.dispose();
//    }
//
//    public void left() {
//        velocity.x  = MOVEMENT * -1;
//    }
//
//    public void right() {
//        velocity.x  = MOVEMENT;
//    }
//
////    public boolean climbing() {
////        return true;
////    }
    public World world;
    public Body b2body;

    public Hero(World world){
        this.world = world;
        defineHero();
    }

    public void defineHero(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ NoObjectionGame.PPM,32/ NoObjectionGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ NoObjectionGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

}





