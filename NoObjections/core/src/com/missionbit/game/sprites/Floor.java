package com.missionbit.game.sprites;

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
}
