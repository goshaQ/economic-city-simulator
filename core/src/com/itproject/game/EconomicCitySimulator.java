package com.itproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EconomicCitySimulator extends Game {

	public SpriteBatch batcher;

	@Override
	public void create() {
		batcher = new SpriteBatch();
		Assets.load();
		setScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void render() {
		super.render();
	}
}
