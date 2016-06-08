package com.juniorpoligraphist.flappygame.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Junior Poligraphist on 08.06.2016.
 */
public class Gyrocopter {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private int height;

    public Gyrocopter(int width, int height, float x, float y) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 460);
        this.width = width;
        this.height = height;

    }

    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }

        position.add(velocity.cpy().scl(delta));

    }

    public void onClick() {
        velocity.y = -140;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

}