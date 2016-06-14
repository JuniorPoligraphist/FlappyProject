package com.juniorpoligraphist.flappygame.gameworld;


import com.juniorpoligraphist.flappygame.gameobjects.Gyrocopter;

/**
 * Created by Junior Poligraphist on 08.06.2016.
 */
public class GameWorld {
    private Gyrocopter gyrocopter;

    // Create the Constructor
    public GameWorld(int midPointY) {
        this.gyrocopter = new Gyrocopter(33, midPointY - 5, 17, 12);
    }

    public void update(float delta) {
        this.gyrocopter.update(delta);
    }

    public Gyrocopter getGyrocopter() {
        return this.gyrocopter;
    }
}