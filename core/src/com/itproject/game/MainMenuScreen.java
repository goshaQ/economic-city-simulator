package com.itproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen extends ScreenAdapter {

	EconomicCitySimulator game;
	OrthographicCamera guiCam;
	Rectangle playBounds;
	Vector3 touchPoint;
	
	
	public MainMenuScreen(EconomicCitySimulator game) {
		this.game = game;
		
		guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		guiCam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		playBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 - 100, 200, 100 );
		touchPoint = new Vector3();
		
	}
	
	public void update() {
		if(Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			if(playBounds.contains(touchPoint.x, touchPoint.y)) {
				game.setScreen(new GameScreen(game));
				return;
			}
			
		}
	}
	
	public void draw() {
		GL20 gl = Gdx.gl;
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);
		
	/*	game.batcher.disableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundRegion, 0, 0, 320, 480);
		game.batcher.end();
*/
		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.mainMenuPlayButton, Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 - 100, 200, 100 );
		game.batcher.end();	
		
	}
	
	@Override
	public void render(float delta) {
		update();
		draw();
	}
	
	@Override
	public void pause() {
		// Setting.save();
	}
}
