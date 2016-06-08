package com.juniorpoligraphist.flappygame.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Junior Poligraphist on 08.06.2016.
 */
public class GameRenderer {

    private GameWorld myGameWorld;
    private OrthographicCamera cam;


    public GameRenderer(GameWorld myGameWorld) {
        this.myGameWorld = myGameWorld;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, 204);

    }

    public void render() {
        Gdx.app.log("GameRenderer", "render");

    }
}
