package com.juniorpoligraphist.flappygame.gameobjects;

import java.util.Random;

/**
 * Created by Junior Poligraphist on 14.06.2016.
 */
public class Pipe extends Scrollable {
    private Random r = new Random();

    public Pipe(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }

    public void reset(float newX) {
        super.reset(newX);
        this.height = this.r.nextInt(90) + 15;
    }
}