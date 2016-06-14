package com.juniorpoligraphist.flappygame.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.juniorpoligraphist.flappygame.flappysupport.InputHandler;
import com.juniorpoligraphist.flappygame.gameworld.GameRenderer;
import com.juniorpoligraphist.flappygame.gameworld.GameWorld;

/**
 * Created by Junior Poligraphist on 08.06.2016.
 */
public class GameScreen implements Screen {
    private GameWorld gameWorld;
    private GameRenderer gameRenderer;
    private float runTime;


    public GameScreen() {
        float screenWidth = (float) Gdx.graphics.getWidth();
        float screenHeight = (float) Gdx.graphics.getHeight();
        float gameWidth = 136.0F;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int midPointY = (int) (gameHeight / 2.0F);

        this.gameWorld = new GameWorld(midPointY);
        this.gameRenderer = new GameRenderer(this.gameWorld, (int) gameHeight, midPointY);
        Gdx.input.setInputProcessor(new InputHandler(this.gameWorld.getGyrocopter()));
    }

    @Override
    public void render(float delta) {
        Gdx.app.log("GameScreen FPS", (1 / delta) + "");

        this.runTime += delta;
        this.gameWorld.update(delta);
        this.gameRenderer.render(this.runTime);
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void dispose() {
    }
}
