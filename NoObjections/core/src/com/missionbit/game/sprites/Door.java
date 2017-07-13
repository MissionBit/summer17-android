package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by missionbit on 6/26/17.
 */

public class Door extends InteractiveTileObject {

    public Door(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);

    }

    @Override
    public void onCollisionDetected() {
        Gdx.app.log("Door", "Door Hit");
    }

//    @Override
//    public void onLadderHit() {
//        Gdx.app.log("Door Ladder", "Ladder Hit");
//    }
//
//    @Override
//    public void onDoorHit() {
//        Gdx.app.log("Door Door", "Ladder Hit");
//    }
}
