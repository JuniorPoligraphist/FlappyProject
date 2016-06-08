package com.juniorpoligraphist.flappygame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import screens.GameScreen;

public class FlappyGame extends Game{


	@Override
	public void create() {
		Gdx.app.log("FlappyGame", "created");
        setScreen(new GameScreen());

	}
}
