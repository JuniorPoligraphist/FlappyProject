package com.juniorpoligraphist.flappygame.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import com.juniorpoligraphist.flappygame.flappysupport.AssetLoader;
import com.juniorpoligraphist.flappygame.gameobjects.Grass;
import com.juniorpoligraphist.flappygame.gameobjects.Gyrocopter;
import com.juniorpoligraphist.flappygame.gameobjects.Pipe;
import com.juniorpoligraphist.flappygame.gameobjects.ScrollHandler;

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
    private Gyrocopter gyrocopter;
    private ScrollHandler scroller;
    private Grass frontGrass;
    private Grass backGrass;
    private Pipe pipe1;
    private Pipe pipe2;
    private Pipe pipe3;
    private TextureRegion bg;
    private TextureRegion grass;
    private Animation gyroAnimation;
    private TextureRegion gyroTexture;
    private TextureRegion gyroDown;
    private TextureRegion gyroUp;
    private TextureRegion skullUp;
    private TextureRegion skullDown;
    private TextureRegion bar;

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
        this.initGameObjects();
        this.initAssets();
    }

    private void initGameObjects() {
        this.gyrocopter = this.myGameWorld.getGyrocopter();
        this.scroller = this.myGameWorld.getScroller();
        this.frontGrass = this.scroller.getFrontGrass();
        this.backGrass = this.scroller.getBackGrass();
        this.pipe1 = this.scroller.getPipe1();
        this.pipe2 = this.scroller.getPipe2();
        this.pipe3 = this.scroller.getPipe3();
    }

    private void initAssets() {
        this.bg = AssetLoader.bg;
        this.grass = AssetLoader.grass;
        this.gyroAnimation = AssetLoader.gyroAnimation;
        this.gyroTexture = AssetLoader.gyroTexture;
        this.gyroDown = AssetLoader.gyroDown;
        this.gyroUp = AssetLoader.gyroUp;
        this.skullUp = AssetLoader.skullUp;
        this.skullDown = AssetLoader.skullDown;
        this.bar = AssetLoader.bar;
    }

    private void drawGrass() {
        this.batcher.draw(this.grass, this.frontGrass.getX(), this.frontGrass.getY(), (float) this.frontGrass.getWidth(), (float) this.frontGrass.getHeight());
        this.batcher.draw(this.grass, this.backGrass.getX(), this.backGrass.getY(), (float) this.backGrass.getWidth(), (float) this.backGrass.getHeight());
    }

    private void drawSkulls() {
        this.batcher.draw(this.skullUp, this.pipe1.getX() - 1.0F, this.pipe1.getY() + (float) this.pipe1.getHeight() - 14.0F, 24.0F, 14.0F);
        this.batcher.draw(this.skullDown, this.pipe1.getX() - 1.0F, this.pipe1.getY() + (float) this.pipe1.getHeight() + 45.0F, 24.0F, 14.0F);
        this.batcher.draw(this.skullUp, this.pipe2.getX() - 1.0F, this.pipe2.getY() + (float) this.pipe2.getHeight() - 14.0F, 24.0F, 14.0F);
        this.batcher.draw(this.skullDown, this.pipe2.getX() - 1.0F, this.pipe2.getY() + (float) this.pipe2.getHeight() + 45.0F, 24.0F, 14.0F);
        this.batcher.draw(this.skullUp, this.pipe3.getX() - 1.0F, this.pipe3.getY() + (float) this.pipe3.getHeight() - 14.0F, 24.0F, 14.0F);
        this.batcher.draw(this.skullDown, this.pipe3.getX() - 1.0F, this.pipe3.getY() + (float) this.pipe3.getHeight() + 45.0F, 24.0F, 14.0F);
    }

    private void drawPipes() {
        this.batcher.draw(this.bar, this.pipe1.getX(), this.pipe1.getY(), (float) this.pipe1.getWidth(), (float) this.pipe1.getHeight());
        this.batcher.draw(this.bar, this.pipe1.getX(), this.pipe1.getY() + (float) this.pipe1.getHeight() + 45.0F, (float) this.pipe1.getWidth(), (float) (this.midPointY + 66 - (this.pipe1.getHeight() + 45)));
        this.batcher.draw(this.bar, this.pipe2.getX(), this.pipe2.getY(), (float) this.pipe2.getWidth(), (float) this.pipe2.getHeight());
        this.batcher.draw(this.bar, this.pipe2.getX(), this.pipe2.getY() + (float) this.pipe2.getHeight() + 45.0F, (float) this.pipe2.getWidth(), (float) (this.midPointY + 66 - (this.pipe2.getHeight() + 45)));
        this.batcher.draw(this.bar, this.pipe3.getX(), this.pipe3.getY(), (float) this.pipe3.getWidth(), (float) this.pipe3.getHeight());
        this.batcher.draw(this.bar, this.pipe3.getX(), this.pipe3.getY() + (float) this.pipe3.getHeight() + 45.0F, (float) this.pipe3.getWidth(), (float) (this.midPointY + 66 - (this.pipe3.getHeight() + 45)));
    }

    public void render(float runTime) {

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
        this.batcher.draw(this.bg, 0.0F, (float) (this.midPointY + 23), 136.0F, 43.0F);
        this.drawGrass();
        this.drawPipes();
        this.batcher.enableBlending();
        this.drawSkulls();
        if (this.gyrocopter.shouldntFlap()) {
            this.batcher.draw(this.gyroTexture, this.gyrocopter.getX(), this.gyrocopter.getY(), this.gyrocopter.getWidth() / 2.0F, this.gyrocopter.getHeight() / 2.0F, this.gyrocopter.getWidth(), this.gyrocopter.getHeight(), 1.0F, 1.0F, this.gyrocopter.getRotation());
        } else {
            this.batcher.draw(this.gyroAnimation.getKeyFrame(runTime), this.gyrocopter.getX(), this.gyrocopter.getY(), this.gyrocopter.getWidth() / 2.0F, this.gyrocopter.getHeight() / 2.0F, this.gyrocopter.getWidth(), this.gyrocopter.getHeight(), 1.0F, 1.0F, this.gyrocopter.getRotation());
        }

        this.batcher.end();
    }
}