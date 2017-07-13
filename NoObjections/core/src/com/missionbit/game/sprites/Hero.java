package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
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
    public enum State { FALLING, JUMPING, STANDING, RUNNING, DEAD};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion heroStand;
    private Animation heroRun;
    private Animation heroClimb;
    private float stateTimer;
    private boolean runningRight;
   private static final float y_deathposition=-100;

    public Hero(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("dudeRun2"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i <5; i++) {
            frames.add(new TextureRegion(getTexture(), i * 46, 0, 35, 45));
        }
        heroRun = new Animation(0.1f, frames);

        //TODO: add climbing images to image pack

        frames.clear();

        defineHero();

        heroStand = new TextureRegion(getTexture(), 0, 0, 40, 45);
        setBounds(0, 0, 40 / NoObjectionGame.PPM, 45/ NoObjectionGame.PPM);
        setRegion(heroStand);

    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
        System.out.println(b2body.getPosition().x + ","+ b2body.getPosition().y);
        if(b2body.getPosition().y < y_deathposition){
            currentState=State.DEAD;
            System.out.println("hero is dead");
        }
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch(currentState){
            //TODO: case climbing
            case RUNNING:
                region = (TextureRegion) heroRun.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
                default:
                    region = heroStand;
                    break;
        }

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
//        if(b2body.getLinearVelocity().y > 0){
//            return State.JUMPING;
//        }

        //TODO: need to code climbing
        if(b2body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }
        else if(b2body.getPosition().y < y_deathposition){
            System.out.println("hero is dead");
            return State.DEAD;
        }
        else{
            return State.STANDING;
        }
    }

    public void defineHero(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ NoObjectionGame.PPM,32/ NoObjectionGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        //fixture1
        FixtureDef fdef1 = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5 / NoObjectionGame.PPM, 18 / NoObjectionGame.PPM);

        fdef1.filter.categoryBits = NoObjectionGame.HERO_BIT;
        fdef1.filter.maskBits = NoObjectionGame.DEFAULT_BIT | NoObjectionGame.DOOR_BIT | NoObjectionGame.LADDER_BIT;

        fdef1.shape = shape;
        b2body.createFixture(fdef1);

        EdgeShape right = new EdgeShape();
        right.set(new Vector2(6/NoObjectionGame.PPM, -10/NoObjectionGame.PPM),new Vector2(6/NoObjectionGame.PPM, 10/NoObjectionGame.PPM));
        fdef1.shape = right;
        fdef1.isSensor = true;


        //fixture2

        FixtureDef fdef2 = new FixtureDef();

        fdef2.filter.categoryBits = NoObjectionGame.HERO_BIT;
        fdef2.filter.maskBits = NoObjectionGame.DEFAULT_BIT | NoObjectionGame.DOOR_BIT | NoObjectionGame.LADDER_BIT;


        fdef2.shape = shape;
        b2body.createFixture(fdef2);

        EdgeShape left = new EdgeShape();
        left.set(new Vector2(-6/NoObjectionGame.PPM, -10/NoObjectionGame.PPM),new Vector2(-6/NoObjectionGame.PPM, 10/NoObjectionGame.PPM));
        fdef2.shape = left;
        fdef2.isSensor = true;


        b2body.createFixture(fdef1).setUserData("right");
        b2body.createFixture(fdef2).setUserData("left");


    }

}





