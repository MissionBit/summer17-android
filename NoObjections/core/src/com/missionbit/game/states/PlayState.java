//package com.missionbit.game.states;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.missionbit.game.NoObjectionGame;
//import com.missionbit.game.sprites.Hero;
//
///**
// * Created by missionbit on 6/26/17.
// */
//
//public class PlayState extends State{
//    private Hero hero;
//    private Texture bg;
//
//    public PlayState(GameStateManager gsm) {
//        super(gsm);
//        hero = new Hero(50, 100);
//        bg = new Texture("main_background.png");
//        cam.setToOrtho(false, NoObjectionGame.WIDTH, NoObjectionGame.HEIGHT);
//
//
//    }
//
//    @Override
//    public void handleInput() {
//        //makes hero jump
//        if(Gdx.input.justTouched()){
//            hero.jump();
//        }
//    }
//
//    @Override
//    public void update(float dt) {
//        handleInput();
//        hero.update(dt);
//        cam.position.x = hero.getPosition().x + 80;
//        cam.position.y = hero.getPosition().y + 150;
//        cam.update();
//    }
//
//    @Override
//    public void render(SpriteBatch sb) {
//        sb.setProjectionMatrix(cam.combined);
//        sb.begin();
//        sb.draw(bg, 0, 0, NoObjectionGame.WIDTH, NoObjectionGame.HEIGHT);
//        sb.draw(hero.getTexture(), hero.getPosition().x, hero.getPosition().y);
//        sb.end();
//    }
//
//    @Override
//    public void dispose() {
//        bg.dispose();
//        hero.dispose();
//    }
//}
>>>>>>> hero moves with circle
