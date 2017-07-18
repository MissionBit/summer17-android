package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.missionbit.game.NoObjectionGame;


/**
 * Created by missionbit on 6/26/17.
 */

public class Door extends InteractiveTileObject {

    public Door(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(NoObjectionGame.DOOR_BIT);
    }

    @Override
    public int onCollisionDetected() {
        Gdx.app.log("Door", "Door Hit");
        return NoObjectionGame.DOOR;
    }

}
