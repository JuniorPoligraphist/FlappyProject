package com.juniorpoligraphist.flappygame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.juniorpoligraphist.flappygame.flappysupport.AssetLoader;
import com.juniorpoligraphist.flappygame.screens.GameScreen;

public class FlappyGame extends Game {

    public FlappyGame() {
    }

    @Override
    public void create() {
        Gdx.app.log("FlappyGame", "created");
        AssetLoader.load();
        this.setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
