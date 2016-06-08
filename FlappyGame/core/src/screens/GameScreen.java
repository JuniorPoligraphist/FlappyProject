package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import com.juniorpoligraphist.flappygame.gameworld.GameRenderer;
import com.juniorpoligraphist.flappygame.gameworld.GameWorld;

/**
 * Created by Junior Poligraphist on 08.06.2016.
 */
public class GameScreen implements Screen {

    private GameWorld gameWorld;
    private GameRenderer gameRenderer;


    public GameScreen() {
        Gdx.app.log("GameScreen", "Attached");

        gameWorld = new GameWorld();
        gameRenderer = new GameRenderer(gameWorld);

    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(10 / 255.0f, 15 / 255.0f, 230 / 255.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.app.log("GameScreen FPS", (1 / delta) + "");

        gameWorld.update(delta);
        gameRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");

    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");

    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");

    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");

    }

    @Override
    public void dispose() {

    }
}
