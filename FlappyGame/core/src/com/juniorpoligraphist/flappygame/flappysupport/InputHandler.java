package com.juniorpoligraphist.flappygame.flappysupport;

import com.badlogic.gdx.InputProcessor;
import com.juniorpoligraphist.flappygame.gameobjects.Gyrocopter;

/**
 * Created by Junior Poligraphist on 08.06.2016.
 */
public class InputHandler implements InputProcessor {
    private Gyrocopter myFlappyGyrocopter;

    public InputHandler(Gyrocopter gyrocopter) {
        this.myFlappyGyrocopter = gyrocopter;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        myFlappyGyrocopter.onClick();
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
