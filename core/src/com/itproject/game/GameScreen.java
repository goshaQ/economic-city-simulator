package com.itproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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
	static final int BUILD_HOUSE = 0;
	static final int BUILD_HOSPITAL = 1;
	static final int BUILD_FIRESTATION = 2;
	static final int BUILD_POLICESTATION = 3;
	static final int BUILD_POWERSTATION = 4;
	static final int BUILD_BANK = 5;
	static final int BUILD_CITYHALL = 6;
	static final int BUILD_WATERSTATION= 7;
	static final int BUILD_OFF = 8;
	
	
	
	public static City city;
	CityListener cityListener;
	CityRenderer renderer;
	
	OrthographicCamera guiCam;
	private Viewport guiPort;
	
	Rectangle redactorWindow;
	Rectangle buildHouse;
	Rectangle buildBlock;
	Rectangle buildFireStation;
	public static InputMultiplexer multi = new InputMultiplexer();
	
	Vector3 touchPoint;
	
	private Hud hud;
	
	public GameScreen(EconomicCitySimulator game) {
		
		this.game = game;
		state = GAME_READY;
		redactorState = BUILD_OFF;
		cityListener = new CityListener() {
			// will be added later
		};
		
		city = new City(cityListener);
		renderer = new CityRenderer(city);

		this.guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//this.guiCam.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		guiPort = new ScreenViewport(/*Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), */ guiCam);
		touchPoint = new Vector3();
		hud = new Hud(game.batcher, guiCam);
		
		
		//
	}
	
	
	public void update(float deltaTime) {
		updateRunning(deltaTime);

	}
	
	public void render(float delta) {
		//delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		update(delta);
		
		multi.addProcessor(Hud.stage);

		//Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
		
		Gdx.gl.glClearColor(0.5f, 0.75f, 1, 1);		
		
		  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	   
	    
		renderer.render(delta);
		
	
		guiCam.update();
		game.batcher.setProjectionMatrix(Hud.stage.getCamera().combined);
		Hud.stage.act(Gdx.graphics.getDeltaTime());
		Hud.stage.draw();
	/*	game.batcher.setProjectionMatrix(guiCam.combined);
		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.demoRedactorMenu, 1920 - 300, 1080 - 200, 300, 200);
		game.batcher.draw(Assets.demoBlock, 1920 - 80 - 64 - 5, 1080 - 25 - 64, 64, 64);
		game.batcher.draw(Assets.demoHouseRedactor, 1920 - 64 - 11 - 5, 1080 - 25 - 64, 64, 64);
		game.batcher.draw(Assets.fireStationRedactorRegion, 1920 - 80 - 64 - 10 - 64, 1080 - 25 - 64, 64, 64);
		game.batcher.end();*/
			
		//  Gdx.gl.glEnable(GL20.GL_BLEND);
		//  Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		
	}
		
	public void updateRunning(float deltaTime) {
		
		city.update(deltaTime);
	}


	
	@Override
	public void resize(int width, int height) {
		guiPort.update(width, height);
	}

	@Override
	public void pause() {
		if (state == GAME_RUNNING) {
			state = GAME_PAUSED;
		}
	}
}
