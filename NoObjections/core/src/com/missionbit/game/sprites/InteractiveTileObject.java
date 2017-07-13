package com.missionbit.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
<<<<<<< HEAD
=======
import com.badlogic.gdx.physics.box2d.Filter;
>>>>>>> 267b56d174bdb653d74bcdd8b80bfe125c12d685
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.missionbit.game.NoObjectionGame;

/**
 * Created by missionbit on 7/11/17.
 */

public class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    protected Fixture fixture;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / NoObjectionGame.PPM, (bounds.getY() + bounds.getHeight() / 2 )/ NoObjectionGame.PPM);

        body = world.createBody(bdef);
        shape.setAsBox(bounds.getWidth() / 2 / NoObjectionGame.PPM, bounds.getHeight() / 2 / NoObjectionGame.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
<<<<<<< HEAD
=======
    }

    public abstract void onCollisionDetected();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
>>>>>>> 267b56d174bdb653d74bcdd8b80bfe125c12d685
    }

}
