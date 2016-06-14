package com.juniorpoligraphist.flappygame.gameobjects;

/**
 * Created by Junior Poligraphist on 14.06.2016.
 */
public class ScrollHandler {
    private Grass frontGrass;
    private Grass backGrass;
    private Pipe pipe1;
    private Pipe pipe2;
    private Pipe pipe3;
    public static final int SCROLL_SPEED = -59;
    public static final int PIPE_GAP = 49;

    public ScrollHandler(float yPos) {
        this.frontGrass = new Grass(0.0F, yPos, 143, 11, -59.0F);
        this.backGrass = new Grass(this.frontGrass.getTailX(), yPos, 143, 11, -59.0F);
        this.pipe1 = new Pipe(210.0F, 0.0F, 22, 60, -59.0F, yPos);
        this.pipe2 = new Pipe(this.pipe1.getTailX() + 49.0F, 0.0F, 22, 70, -59.0F, yPos);
        this.pipe3 = new Pipe(this.pipe2.getTailX() + 49.0F, 0.0F, 22, 60, -59.0F, yPos);
    }

    public void update(float delta) {
        this.frontGrass.update(delta);
        this.backGrass.update(delta);
        this.pipe1.update(delta);
        this.pipe2.update(delta);
        this.pipe3.update(delta);
        if (this.pipe1.isScrolledLeft()) {
            this.pipe1.reset(this.pipe3.getTailX() + 49.0F);
        } else if (this.pipe2.isScrolledLeft()) {
            this.pipe2.reset(this.pipe1.getTailX() + 49.0F);
        } else if (this.pipe3.isScrolledLeft()) {
            this.pipe3.reset(this.pipe2.getTailX() + 49.0F);
        }

        if (this.frontGrass.isScrolledLeft()) {
            this.frontGrass.reset(this.backGrass.getTailX());
        } else if (this.backGrass.isScrolledLeft()) {
            this.backGrass.reset(this.frontGrass.getTailX());
        }

    }

    public void stop() {
        this.frontGrass.stop();
        this.backGrass.stop();
        this.pipe1.stop();
        this.pipe2.stop();
        this.pipe3.stop();
    }

    public boolean collides(Gyrocopter gyrocopter) {
        return this.pipe1.collides(gyrocopter) || this.pipe2.collides(gyrocopter) || this.pipe3.collides(gyrocopter);
    }

    public Grass getFrontGrass() {
        return this.frontGrass;
    }

    public Grass getBackGrass() {
        return this.backGrass;
    }

    public Pipe getPipe1() {
        return this.pipe1;
    }

    public Pipe getPipe2() {
        return this.pipe2;
    }

    public Pipe getPipe3() {
        return this.pipe3;
    }
}
