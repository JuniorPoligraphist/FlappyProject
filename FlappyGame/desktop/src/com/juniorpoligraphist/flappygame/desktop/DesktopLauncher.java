package com.juniorpoligraphist.flappygame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.juniorpoligraphist.flappygame.FlappyGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = 272;
        config.height = 408;
        config.title = "Flappy Project";
        new LwjglApplication(new FlappyGame(), config);


    }
}
