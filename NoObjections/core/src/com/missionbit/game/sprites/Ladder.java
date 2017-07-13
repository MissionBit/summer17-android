package com.missionbit.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.missionbit.game.NoObjectionGame;

/**
 * Created by missionbit on 6/26/17.
 */

public class Ladder extends InteractiveTileObject{
    public Ladder(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
<<<<<<< HEAD

    }

=======
        setCategoryFilter(NoObjectionGame.LADDER_BIT);

    }

    @Override
    public void onCollisionDetected() {
        Gdx.app.log("Ladder", "Ladder Hit");
    }

>>>>>>> 267b56d174bdb653d74bcdd8b80bfe125c12d685
}
