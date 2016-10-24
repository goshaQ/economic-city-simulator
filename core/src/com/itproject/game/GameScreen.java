package com.itproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.itproject.game.City.CityListener;

public class GameScreen extends ScreenAdapter {
	
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_OVER = 3;
	
	EconomicCitySimulator game;
	
	int state;
	
	City city;
	CityListener cityListener;
	CityRenderer renderer;
	
	public GameScreen(EconomicCitySimulator game) {
		
		this.game = game;
		state = GAME_READY;
		cityListener = new CityListener() {
			// will be added later
		};
		
		city = new City(cityListener);
		renderer = new CityRenderer(city);
	}
	
	
/*	public void update(float deltaTime) {
	
		handleInput();
		
	}*/
	
	public void render(float delta) {
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		city.update(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);		
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
		renderer.render();
		
	}
	/*	
	public void updateRunning(float deltaTime) {
		if(Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			// handle pause buttons or some other
		}
		
		ApplicationType appType = Gdx.app.getType();
		
		if (appType == ApplicationType.Android || appType == ApplicationType.iOS) {
			city.update();
		} else {
			float accel = 0;
			if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) accel = 5f;
			if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) accel = -5f;
			city.update(deltaTime);
		}
		
		if(city.state == City.CITY_STATE_GAME_OVER) {
			state = GAME_OVER;
		}
	}*/
	

/*	
	@Override
	public void resize(int width, int height) {
		 guiCam.viewportWidth = width;
        guiCam.viewportHeight = height;
		guiCam.update();
	}
	*/
	@Override
	public void pause() {
		if (state == GAME_RUNNING) {
			state = GAME_PAUSED;
		}
	}
}
