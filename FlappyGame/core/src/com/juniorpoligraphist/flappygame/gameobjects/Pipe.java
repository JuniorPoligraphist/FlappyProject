package com.juniorpoligraphist.flappygame.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by Junior Poligraphist on 14.06.2016.
 */
public class Pipe extends Scrollable {
    private Random r = new Random();
    private Rectangle skullUp = new Rectangle();
    private Rectangle skullDown = new Rectangle();
    private Rectangle barUp = new Rectangle();
    private Rectangle barDown = new Rectangle();
    public static final int VERTICAL_GAP = 45;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;
    private float groundY;

    public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
        this.groundY = groundY;
    }

    public void update(float delta) {
        super.update(delta);
        this.barUp.set(this.position.x, this.position.y, (float) this.width, (float) this.height);
        this.barDown.set(this.position.x, this.position.y + (float) this.height + 45.0F, (float) this.width, this.groundY - (this.position.y + (float) this.height + 45.0F));
        this.skullUp.set(this.position.x - (float) ((24 - this.width) / 2), this.position.y + (float) this.height - 11.0F, 24.0F, 11.0F);
        this.skullDown.set(this.position.x - (float) ((24 - this.width) / 2), this.barDown.y, 24.0F, 11.0F);
    }

    public void reset(float newX) {
        super.reset(newX);
        this.height = this.r.nextInt(90) + 15;
    }

    public Rectangle getSkullUp() {
        return this.skullUp;
    }

    public Rectangle getSkullDown() {
        return this.skullDown;
    }

    public Rectangle getBarUp() {
        return this.barUp;
    }

    public Rectangle getBarDown() {
        return this.barDown;
    }

    public boolean collides(Gyrocopter gyrocopter) {
        return this.position.x < gyrocopter.getX() + gyrocopter.getWidth() ? Intersector.overlaps(gyrocopter.getBoundingCircle(),
                this.barUp) || Intersector.overlaps(gyrocopter.getBoundingCircle(),
                this.barDown) || Intersector.overlaps(gyrocopter.getBoundingCircle(),
                this.skullUp) || Intersector.overlaps(gyrocopter.getBoundingCircle(),
                this.skullDown) : false;
    }
}