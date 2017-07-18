package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by missionbit on 7/14/17.
 */

public class Darkness extends Sprite {
    private Texture darkness;
    private Vector2 posDarkness;

    public Darkness(){
        darkness = new Texture("blacknese.jpg");
        posDarkness = new Vector2(0,0);
    }
    public void update(float dt){
    }
    public void dispose(){
        darkness.dispose();
    }

}
