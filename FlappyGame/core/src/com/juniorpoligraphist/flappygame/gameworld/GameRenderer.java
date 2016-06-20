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
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;
    private TextureRegion bg, grass;
    private Animation gyroAnimation;
    private TextureRegion gyroTexture, gyroDown, gyroUp;
    private TextureRegion skullUp, skullDown, bar;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        this.myGameWorld = world;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        // Call helper methods to initialize instance variables
        initGameObjects();
        initAssets();
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
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        batcher.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(bg, 0, midPointY + 23, 136, 43);

        drawGrass();
        drawPipes();
        batcher.enableBlending();
        drawSkulls();

        if (gyrocopter.shouldntFlap()) {
            batcher.draw(gyroTexture, gyrocopter.getX(), gyrocopter.getY(),
                    gyrocopter.getWidth() / 2.0f, gyrocopter.getHeight() / 2.0f,
                    gyrocopter.getWidth(), gyrocopter.getHeight(), 1, 1, gyrocopter.getRotation());

        } else {
            batcher.draw(gyroAnimation.getKeyFrame(runTime), gyrocopter.getX(),
                    gyrocopter.getY(), gyrocopter.getWidth() / 2.0f,
                    gyrocopter.getHeight() / 2.0f, gyrocopter.getWidth(), gyrocopter.getHeight(),
                    1, 1, gyrocopter.getRotation());
        }

   /*     // Convert integer into String
        String score = myGameWorld.getScore() + "";

        // Draw shadow first
        AssetLoader.shadow.draw(batcher, "" + myGameWorld.getScore(), (136 / 2)
                - (3 * score.length()), 12);
        // Draw text
        AssetLoader.font.draw(batcher, "" + myGameWorld.getScore(), (136 / 2)
                - (3 * score.length() - 1), 11);*/


        // ВРЕМЕННЫЙ КОД! Изменим позже:

        if (myGameWorld.isReady()) {
            // Отрисуем сначала тень
            AssetLoader.shadow.draw(batcher, "Touch me", (136 / 2)
                    - (42), 76);
            // Отрисуем сам текст
            AssetLoader.font.draw(batcher, "Touch me", (136 / 2)
                    - (42 - 1), 75);
        } else {

            if (myGameWorld.isGameOver()) {
                AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
                AssetLoader.font.draw(batcher, "Game Over", 24, 55);

                AssetLoader.shadow.draw(batcher, "Try again?", 23, 76);
                AssetLoader.font.draw(batcher, "Try again?", 24, 75);


            }

            String score = myGameWorld.getScore() + "";

            AssetLoader.shadow.draw(batcher, "" + myGameWorld.getScore(), (136 / 2)
                    - (3 * score.length()), 12);
            AssetLoader.font.draw(batcher, "" + myGameWorld.getScore(), (136 / 2)
                    - (3 * score.length() - 1), 11);
        }

        batcher.end();
    }
}