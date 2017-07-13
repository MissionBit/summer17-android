package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by missionbit on 6/29/17.
 */

//floor
public class Floor extends InteractiveTileObject{
    public Floor(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);

    }

    @Override
    public void onCollisionDetected() {
        Gdx.app.log("Floor", "Floor Hit");
    }

//    @Override
//    public void onLadderHit() {
//        Gdx.app.log("Floor Ladder", "Ladder Hit");
//    }
//
//    @Override
//    public void onDoorHit() {
//        Gdx.app.log("Floor Door", "Ladder Hit");
//    }
}
