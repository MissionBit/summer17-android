package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.missionbit.game.NoObjectionGame;


/**
 * Created by missionbit on 6/26/17.
 */

public class Mushroom extends InteractiveTileObject {

    public Mushroom(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(NoObjectionGame.MUSHROOM_BIT);
    }

    @Override
    public int onCollisionDetected() {
        Gdx.app.log("Mushroom Hit", "Mushroom Hit");
        getCell().setTile(null);
        setCategoryFilter(NoObjectionGame.DESTROYED_BIT);

        return NoObjectionGame.MUSHROOM;
    }

}
