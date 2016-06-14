package com.juniorpoligraphist.flappygame.gameobjects;

import com.badlogic.gdx.math.Circle;
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
    private Circle boundingCircle;

    public Gyrocopter(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0.0F, 0.0F);
        this.acceleration = new Vector2(0.0F, 460.0F);
        this.boundingCircle = new Circle();
    }

    public void update(float delta) {
        this.velocity.add(this.acceleration.cpy().scl(delta));
        if (this.velocity.y > 200.0F) {
            this.velocity.y = 200.0F;
        }

        this.position.add(this.velocity.cpy().scl(delta));
        this.boundingCircle.set(this.position.x + 9.0F, this.position.y + 6.0F, 6.5F);
        if (this.velocity.y < 0.0F) {
            this.rotation -= 600.0F * delta;
            if (this.rotation < -20.0F) {
                this.rotation = -20.0F;
            }
        }

        if (this.isFalling()) {
            this.rotation += 480.0F * delta;
            if (this.rotation > 90.0F) {
                this.rotation = 90.0F;
            }
        }
    }

    public boolean isFalling() {
        return this.velocity.y > 110.0F;
    }

    public boolean shouldntFlap() {
        return this.velocity.y > 70.0F;
    }

    public void onClick() {
        this.velocity.y = -140.0F;
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }

    public float getWidth() {
        return (float) this.width;
    }

    public float getHeight() {
        return (float) this.height;
    }

    public float getRotation() {
        return this.rotation;
    }

    public Circle getBoundingCircle() {
        return this.boundingCircle;
    }
}