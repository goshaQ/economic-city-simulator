package com.itproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.itproject.game.City.CityListener;

public class GameScreen extends ScreenAdapter {
	
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_REDACTOR_MODE = 3; // new
	static final int GAME_OVER = 4;
	
	EconomicCitySimulator game;
	
	static int state;
	static int redactorState;
	static final int BUILD_BLOCK = 0;
	static final int BUILD_HOUSE = 1;
	static final int BUILD_FIRESTATION = 2;
	static final int BUILD_OFF = 3;
	
	
	
	City city;
	CityListener cityListener;
	CityRenderer renderer;
	
	OrthographicCamera guiCam;
	Rectangle redactorWindow;
	Rectangle buildHouse;
	Rectangle buildBlock;
	Rectangle buildFireStation;
	
	Vector3 touchPoint;
	
	public GameScreen(EconomicCitySimulator game) {
		
		this.game = game;
		state = GAME_READY;
		redactorState = BUILD_OFF;
		cityListener = new CityListener() {
			// will be added later
		};
		
		city = new City(cityListener);
		renderer = new CityRenderer(city);
		
		redactorWindow = new Rectangle(1920 - 300, 1080 - 200, 300, 200);
		buildBlock = new Rectangle(1920 - 80 - 64 - 5, 1080 - 25 - 64, 64, 64);
		buildHouse = new Rectangle(1920 - 64 - 11 - 5, 1080 - 25 - 64, 64, 64);
		buildFireStation = new Rectangle(1920 - 80 - 64 - 10 - 64, 1080 - 25 - 64, 64, 64);
		
		this.guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.guiCam.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		touchPoint = new Vector3();
	}
	
	
	public void update(float deltaTime) {
		updateRunning(deltaTime);

	}
	
	public void render(float delta) {
		//delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		update(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);		
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	   
	    
		renderer.render();
		
		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);
		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.demoRedactorMenu, 1920 - 300, 1080 - 200, 300, 200);
		game.batcher.draw(Assets.demoBlock, 1920 - 80 - 64 - 5, 1080 - 25 - 64, 64, 64);
		game.batcher.draw(Assets.demoHouseRedactor, 1920 - 64 - 11 - 5, 1080 - 25 - 64, 64, 64);
		game.batcher.draw(Assets.fireStationRedactorRegion, 1920 - 80 - 64 - 10 - 64, 1080 - 25 - 64, 64, 64);
		game.batcher.end();
		
		if(Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			if (redactorWindow.contains(touchPoint.x, touchPoint.y)) {
				state = GAME_REDACTOR_MODE;
				if(buildBlock.contains(touchPoint.x, touchPoint.y)) {
					redactorState = BUILD_BLOCK;
				} else if(buildHouse.contains(touchPoint.x, touchPoint.y)) {
					redactorState = BUILD_HOUSE;
				} else if(buildFireStation.contains(touchPoint.x, touchPoint.y)) {
					redactorState = BUILD_FIRESTATION;
				}
				
				return;
			}
		}
		
	}
		
	public void updateRunning(float deltaTime) {
		
		city.update(deltaTime);
	}


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
