package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.missionbit.game.NoObjectionGame;
import com.missionbit.game.sprites.Hero;


/**
 * Created by missionbit on 6/26/17.
 */

public class PlayState extends State{
    private Hero hero;
    //tiled map
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        hero = new Hero(50, 100);
        cam.setToOrtho(false, NoObjectionGame.WIDTH / 2, NoObjectionGame.HEIGHT / 2);
        maploader = new TmxMapLoader();

        map = maploader.load("map1.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map);


        // bg = new Texture("suckybg_copy.png");
    }


    public void show() {

    }

    @Override
    public void handleInput() {
        //makes hero jump
        if(Gdx.input.justTouched()){
            hero.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        hero.update(dt);
        cam.position.x = hero.getPosition().x + 80;
        cam.position.y = hero.getPosition().y + 150;
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(hero.getTexture(), hero.getPosition().x, hero.getPosition().y);
        //sb.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        renderer.render();
        sb.end();
    }

    @Override
    public void dispose() {
        //bg.dispose();
        hero.dispose();
    }
}
