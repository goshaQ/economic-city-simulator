package com.itproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;

public class CityRenderer {

	public static final int TILE_WIDTH_HALF = 32;
	public static final int TILE_HEIGHT_HALF = 16;
	
	City city;
	OrthographicCamera cam;
	
	TiledMapRenderer tiledMapRenderer;
	TiledMap tiledMap;

	public CityRenderer(City city) {
		Assets.load();
		this.city = city;
		this.cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.cam.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		
		this.tiledMap = Assets.tiledMap;
		this.tiledMapRenderer = Assets.tiledMapRenderer;	
	}
	
	public void render() {
		cam.update();
	    tiledMapRenderer.setView(cam);
	    tiledMapRenderer.render();
	    
		handleInput();
		Gdx.input.setInputProcessor(new InputAdapter() {
				
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		        Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
		        Vector3 position = cam.unproject(clickCoordinates);
		        
		        TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
				int col, row;
				col = Math.round( ((position.x / TILE_WIDTH_HALF) + (position.y / TILE_HEIGHT_HALF)) / 2) - 1;
		        row = Math.round( ((position.x / TILE_WIDTH_HALF) - (position.y / TILE_HEIGHT_HALF)) / 2);
		        
		        if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	
		        	TiledMapTileLayer.Cell cell = layer.getCell(row, col);
					StaticTiledMapTile tile = new StaticTiledMapTile(Assets.demoBlock);
					
					cell.setTile(tile);
		        }
		            
				return true;
		   }
		});
	}
	
	
	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-10, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(10, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -10, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 10, 0);
        }
        
	}
}
