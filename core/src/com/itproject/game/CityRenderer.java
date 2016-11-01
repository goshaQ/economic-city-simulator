package com.itproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;
import com.itproject.game.buildings.Building;
import com.itproject.game.buildings.FireStation;
import com.itproject.game.buildings.House;

public class CityRenderer {
	
	public static final int TILE_WIDTH_HALF = 32;
	public static final int TILE_HEIGHT_HALF = 16;
	
	City city;
	OrthographicCamera cam;

	TiledMapTileLayer.Cell temporaryCell;
	Building lastSelected;
	StaticTiledMapTile temporaryTile;
	int lastCol = -10000, lastRow = -10000;
	
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
			public boolean mouseMoved(int screenX, int screenY) {
				Vector3 moveCoordinates = new Vector3(screenX,screenY,0);
			    Vector3 position = cam.unproject(moveCoordinates);
			    
			    TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
			    int col, row;
			    col = Math.round( ((position.x / TILE_WIDTH_HALF) + (position.y / TILE_HEIGHT_HALF)) / 2) - 1;
		        row = Math.round( ((position.x / TILE_WIDTH_HALF) - (position.y / TILE_HEIGHT_HALF)) / 2);
		        
				if(GameScreen.state == GameScreen.GAME_REDACTOR_MODE) {
					if(GameScreen.redactorState == GameScreen.BUILD_BLOCK) {
						if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	
								if(lastRow != -10000 && lastCol != -10000) {
									if(Math.abs(lastRow - row) != 0 || Math.abs(lastCol - col) != 0) {
										layer.getCell(lastRow, lastCol).setTile(temporaryTile);
										temporaryTile = (StaticTiledMapTile) layer.getCell(row, col).getTile();
									}
								}
								if(temporaryTile == null)
									temporaryTile = (StaticTiledMapTile) layer.getCell(row, col).getTile();
								
								lastRow = row; lastCol = col;
								layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.greenDemoBlock));
						
							
						}
					}
				}
				
				if(GameScreen.state == GameScreen.GAME_RUNNING) {
					lastRow = -10000;
					lastCol = -10000;
				}
				
				return true;
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		        Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
		        Vector3 position = cam.unproject(clickCoordinates);
		        TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
				int col, row;
				col = Math.round( ((position.x / TILE_WIDTH_HALF) + (position.y / TILE_HEIGHT_HALF)) / 2) - 1;
		        row = Math.round( ((position.x / TILE_WIDTH_HALF) - (position.y / TILE_HEIGHT_HALF)) / 2);
		        
		        if(GameScreen.state == GameScreen.GAME_REDACTOR_MODE ) {
		        	if(GameScreen.redactorState == GameScreen.BUILD_BLOCK) 
		        		buildHouse(row, col, layer);
		        	
		        	if(GameScreen.redactorState == GameScreen.BUILD_FIRESTATION)
		        		buildFireStation(row, col, layer);
		        	
		        	GameScreen.state = GameScreen.GAME_RUNNING;
		        } else {
		        	for(Building station : city.getBuildingList()) {
		        		
		        		if(station.getShape().contains(position.x, position.y) && station instanceof FireStation) {
		        			if(lastSelected != null) {
		        				lastSelected.setState(FireStation.FIRE_STATION_UNSELECTED);
		        					
		        			} 
			        			
		        			lastSelected = station;
			        		station.setState(FireStation.FIRE_STATION_SELECTED);
			        		station.showInfo();
		        		
		        		}
		        		
		        		if(station.getShape().contains(position.x, position.y) && station instanceof House) {
		        			if(lastSelected != null) {
		        				lastSelected.setState(House.HOUSE_UNSELECTED);
		        			}
		        			lastSelected = station;
		        			station.setState(House.HOUSE_SELECTED);
		        			station.showInfo();
		        		}
		        	}
		        }
		            
				return true;
		   }
		});
	}
	
	private void buildHouse(int row, int col, TiledMapTileLayer layer) {
		 if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	
	        	TiledMapTileLayer.Cell cell = layer.getCell(row, col);
				StaticTiledMapTile tile = new StaticTiledMapTile(Assets.demoBlock);
				cell.setTile(tile);
				city.getBuildingList().add(new House(row, col));
				city.getBuildingList().get(city.getBuildingList().size()-1).createShape(row, col);
				city.getBuildingList().get(city.getBuildingList().size()-1).createCollisionShape(row, col);
	        }
		 return;
	}
	
	
	private void buildHospital(int row, int col, TiledMapTileLayer layer) {
		 if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	
	        	TiledMapTileLayer.Cell cell1 = layer.getCell(row, col);
	        	TiledMapTileLayer.Cell cell2 = layer.getCell(row+1, col);
	        	TiledMapTileLayer.Cell cell3 = layer.getCell(row, col+1);
	        	TiledMapTileLayer.Cell cell4 = layer.getCell(row+1, col+1);
	        	TiledMapTileLayer.Cell cell5 = layer.getCell(row, col+2);
	        	TiledMapTileLayer.Cell cell6 = layer.getCell(row+1, col+2);
	        	
				StaticTiledMapTile tile1 = new StaticTiledMapTile(Assets.fireStationCell5);
				StaticTiledMapTile tile2 = new StaticTiledMapTile(Assets.fireStationCell6);
				StaticTiledMapTile tile3 = new StaticTiledMapTile(Assets.fireStationCell3);
				StaticTiledMapTile tile4 = new StaticTiledMapTile(Assets.fireStationCell4);
				StaticTiledMapTile tile5 = new StaticTiledMapTile(Assets.fireStationCell1);
				StaticTiledMapTile tile6 = new StaticTiledMapTile(Assets.fireStationCell2);
				
				cell1.setTile(tile1);
				cell2.setTile(tile2);
				cell3.setTile(tile3);
				cell4.setTile(tile4);
				cell5.setTile(tile5);
				cell6.setTile(tile6);
				
				city.getBuildingList().add(new FireStation(row, col));
				city.getBuildingList().get(city.getBuildingList().size()-1).createShape(row, col);
	        }
		 return;
	}
	
	private void buildFireStation(int row, int col, TiledMapTileLayer layer) {
		 if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	
	        	TiledMapTileLayer.Cell cell1 = layer.getCell(row, col);
	        	TiledMapTileLayer.Cell cell2 = layer.getCell(row+1, col);
	        	TiledMapTileLayer.Cell cell3 = layer.getCell(row, col+1);
	        	TiledMapTileLayer.Cell cell4 = layer.getCell(row+1, col+1);
	        	TiledMapTileLayer.Cell cell5 = layer.getCell(row, col+2);
	        	TiledMapTileLayer.Cell cell6 = layer.getCell(row+1, col+2);
	        	
				StaticTiledMapTile tile1 = new StaticTiledMapTile(Assets.fireStationCell5);
				StaticTiledMapTile tile2 = new StaticTiledMapTile(Assets.fireStationCell6);
				StaticTiledMapTile tile3 = new StaticTiledMapTile(Assets.fireStationCell3);
				StaticTiledMapTile tile4 = new StaticTiledMapTile(Assets.fireStationCell4);
				StaticTiledMapTile tile5 = new StaticTiledMapTile(Assets.fireStationCell1);
				StaticTiledMapTile tile6 = new StaticTiledMapTile(Assets.fireStationCell2);
				
				cell1.setTile(tile1);
				cell2.setTile(tile2);
				cell3.setTile(tile3);
				cell4.setTile(tile4);
				cell5.setTile(tile5);
				cell6.setTile(tile6);
				
				city.getBuildingList().add(new FireStation(row, col));
				city.getBuildingList().get(city.getBuildingList().size()-1).createShape(row, col);
	        }
		 return;
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
