package com.missionbit.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.missionbit.game.NoObjectionGame;
import com.missionbit.game.sprites.Hero;
import com.missionbit.game.sprites.InteractiveTileObject;

/**
 * Created by missionbit on 7/13/17.
 */

public class WorldContactListener implements ContactListener {

    public int isObjectTouched = NoObjectionGame.DEFAULT;

    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() == "left" || fixB.getUserData() == "left") {
            Fixture left = fixA.getUserData() == "left" ? fixA : fixB;
            Fixture object = left == fixA ? fixB : fixA;

            //checks if object is InteractiveTileObject (aka ladder or door)
            if (object.getUserData() != null &&
                    InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                isObjectTouched = (((InteractiveTileObject) object.getUserData()).onCollisionDetected());
            }
        }

        if (fixA.getUserData() == "right" || fixB.getUserData() == "right") {
            Fixture right = fixA.getUserData() == "right" ? fixA : fixB;
            Fixture object = right == fixA ? fixB : fixA;

            //checks if object is InteractiveTileObject (aka ladder or door)
            if (object.getUserData() != null &&
                    InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                isObjectTouched = ((InteractiveTileObject) object.getUserData()).onCollisionDetected();

            }
        }
    }

    @Override
    public void endContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        if (fixA.getUserData() == "bottom" || fixB.getUserData() == "bottom") {
            Fixture bottom = fixA.getUserData() == "bottom" ? fixA : fixB;
            Fixture object = bottom == fixA ? fixB : fixA;

            //resets object touched
            if (object.getUserData() != null &&
                    InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                isObjectTouched = NoObjectionGame.DEFAULT;
            }
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    //Getter method for object touch code
    public int getIsObjectTouched() {
        return isObjectTouched;
    }
}
