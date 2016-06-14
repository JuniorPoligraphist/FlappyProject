package com.juniorpoligraphist.flappygame.gameworld;


import com.juniorpoligraphist.flappygame.gameobjects.Gyrocopter;
import com.juniorpoligraphist.flappygame.gameobjects.ScrollHandler;

/**
 * Created by Junior Poligraphist on 08.06.2016.
 */
public class GameWorld {
    private Gyrocopter gyrocopter;
    private ScrollHandler scroller;

    public GameWorld(int midPointY) {
        this.gyrocopter = new Gyrocopter(33.0F, (float) (midPointY - 5), 17, 12);
        this.scroller = new ScrollHandler((float) (midPointY + 66));
    }

    public void update(float delta) {
        this.gyrocopter.update(delta);
        this.scroller.update(delta);
    }

    public Gyrocopter getGyrocopter() {
        return this.gyrocopter;
    }

    public ScrollHandler getScroller() {
        return this.scroller;
    }
}