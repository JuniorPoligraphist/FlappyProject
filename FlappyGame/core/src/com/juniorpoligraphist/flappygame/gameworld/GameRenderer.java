package com.juniorpoligraphist.flappygame.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import com.juniorpoligraphist.flappygame.flappysupport.AssetLoader;
import com.juniorpoligraphist.flappygame.gameobjects.Gyrocopter;

/**
 * Created by Junior Poligraphist on 08.06.2016.
 */
public class GameRenderer {

    private GameWorld myGameWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;
    private int midPointY;
    private int gameHeight;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        this.myGameWorld = world;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;
        this.cam = new OrthographicCamera();
        this.cam.setToOrtho(true, 136.0F, (float) gameHeight);
        this.batcher = new SpriteBatch();
        this.batcher.setProjectionMatrix(this.cam.combined);
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setProjectionMatrix(this.cam.combined);
    }

    public void render(float runTime) {
        Gyrocopter gyrocopter = this.myGameWorld.getGyrocopter();

        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.shapeRenderer.begin(ShapeType.Filled);
        this.shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        this.shapeRenderer.rect(0.0F, 0.0F, 136.0F, (float) (this.midPointY + 66));
        this.shapeRenderer.setColor(0.43529412F, 0.7294118F, 0.1764706F, 1.0F);
        this.shapeRenderer.rect(0.0F, (float) (this.midPointY + 66), 136.0F, 11.0F);
        this.shapeRenderer.setColor(0.5764706F, 0.3137255F, 0.105882354F, 1.0F);
        this.shapeRenderer.rect(0.0F, (float) (this.midPointY + 77), 136.0F, 52.0F);
        this.shapeRenderer.end();

        this.batcher.begin();
        this.batcher.disableBlending();
        this.batcher.draw(AssetLoader.bg, 0.0F, (float) (this.midPointY + 23), 136.0F, 43.0F);
        this.batcher.enableBlending();
        this.batcher.draw(AssetLoader.gyroAnimation.getKeyFrame(runTime), gyrocopter.getX(),
                gyrocopter.getY(), gyrocopter.getWidth(), gyrocopter.getHeight());

        this.batcher.end();
    }
}