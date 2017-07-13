package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.missionbit.game.NoObjectionGame;
import com.missionbit.game.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Animation;


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
    public enum State { FALLING, JUMPING, STANDING, RUNNING, CLIMBING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion heroStand;
    private Animation heroRun;
    private Animation heroClimb;
    private float stateTimer;
    private boolean runningRight;

    public Hero(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("dudeRun4"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        //running animation
        for(int i = 1; i < 5; i++) {
            frames.add(new TextureRegion(getTexture(), i * 53, 2, 40, 60));
        }
        heroRun = new Animation(0.1f, frames);
        frames.clear();

        //climbing animation
        for(int i = 6; i < 9 ; i++){
            frames.add(new TextureRegion(getTexture(), i*53, 2, 40, 60));
        }
        heroClimb = new Animation(0.1f, frames);


        defineHero();

        //determines size and starting position of standing on spritesheet
        heroStand = new TextureRegion(getTexture(), 2, 2, 40, 60);
        setBounds(0, 0, 40 / NoObjectionGame.PPM, 60/ NoObjectionGame.PPM);
        setRegion(heroStand);

    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch(currentState){
            //TODO: case climbing
            case CLIMBING:
                region = (TextureRegion) heroClimb.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = (TextureRegion) heroRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = heroStand;
                break;
        }

        //flips the hero when he changes direction
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        }else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + dt :0;
        previousState = currentState;
        return region;
    }

    public State getState(){
        //TODO: need to code climbing instead jump
        if(b2body.getLinearVelocity().y > 0 ){
            return State.CLIMBING;

        } else if(b2body.getLinearVelocity().y < 0){
            return State.FALLING;
        } else if(b2body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }else{
            return State.STANDING;
        }
    }

    public void defineHero(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ NoObjectionGame.PPM,32/ NoObjectionGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5 / NoObjectionGame.PPM, 18 / NoObjectionGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/NoObjectionGame.PPM, 7/NoObjectionGame.PPM), new Vector2(2/NoObjectionGame.PPM, 5/NoObjectionGame.PPM));
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("head");

    }

}





