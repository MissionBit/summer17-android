package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by missionbit on 6/26/17.
 */

public class Plank extends InteractiveTileObject{
    public Plank(World world, TiledMap tiledMap, Rectangle bounds){
        super(world, tiledMap, bounds);
    }

    @Override
    public boolean onCollisionDetected() {
        Gdx.app.log("Plank", "Plank Hit");
        return false;
    }
}
