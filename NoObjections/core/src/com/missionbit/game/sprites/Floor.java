package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.missionbit.game.NoObjectionGame;

/**
 * Created by missionbit on 6/29/17.
 */

//floor
public class Floor extends InteractiveTileObject{
    public Floor(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        setCategoryFilter(NoObjectionGame.FLOOR_BIT);
    }

    @Override
    public int onCollisionDetected() {
        Gdx.app.log("Floor", "Floor Hit");
        return NoObjectionGame.FLOOR;
    }

}
