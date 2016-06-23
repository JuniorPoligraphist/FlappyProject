package com.juniorpoligraphist.flappygame.gameworld;


import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.juniorpoligraphist.flappygame.flappysupport.AssetLoader;
import com.juniorpoligraphist.flappygame.gameobjects.Gyrocopter;
import com.juniorpoligraphist.flappygame.gameobjects.ScrollHandler;

/**
 * Created by Junior Poligraphist on 08.06.2016.
 */
public class GameWorld {
    private Gyrocopter gyrocopter;
    private ScrollHandler scroller;
    private Rectangle ground;
    private boolean isAlive = true;
    private int score = 0;
    private int midPointY;

    private GameState gameState;

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE;
    }

    public GameWorld(int midPointY) {

        gameState = GameState.READY;
        midPointY = midPointY;
        gyrocopter = new Gyrocopter(33, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 137, 11);
    }


    public void update(float delta) {
        switch (gameState) {
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
            default:
                break;
        }
    }

    public void updateReady(float delta) {

    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        gyrocopter.update(delta);
        scroller.update(delta);

        if (scroller.collides(gyrocopter) && gyrocopter.isAlive()) {
            scroller.stop();
            gyrocopter.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(gyrocopter.getBoundingCircle(), ground)) {
            scroller.stop();
            gyrocopter.die();
            gyrocopter.decelerate();
            gameState = GameState.GAMEOVER;
        }

        if (score > AssetLoader.getHighScore()) {
            AssetLoader.setHighScore(score);
            gameState = GameState.HIGHSCORE;
        }
    }

    public Gyrocopter getGyrocopter() {
        return this.gyrocopter;
    }

    public ScrollHandler getScroller() {
        return this.scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public boolean isReady() {
        return gameState == GameState.READY;
    }

    public void start() {
        gameState = GameState.RUNNING;
    }

    public void restart() {
        gameState = GameState.READY;
        score = 0;
        gyrocopter.onRestart(midPointY - 5);
        scroller.onRestart();
        gameState = GameState.READY;
    }

    public boolean isGameOver() {
        return gameState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return gameState == GameState.HIGHSCORE;
    }

}