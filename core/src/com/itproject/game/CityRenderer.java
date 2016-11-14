
package com.itproject.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;
import com.itproject.game.buildings.Bank;
import com.itproject.game.buildings.Building;
import com.itproject.game.buildings.CityHall;
import com.itproject.game.buildings.FireStation;
import com.itproject.game.buildings.Hospital;
import com.itproject.game.buildings.House;
import com.itproject.game.buildings.PoliceStation;
import com.itproject.game.buildings.PowerStation;
import com.itproject.game.buildings.WaterStation;

public class CityRenderer {
	
	public static final int TILE_WIDTH_HALF = 32;
	public static final int TILE_HEIGHT_HALF = 16;
	
	City city;
	OrthographicCamera cam;
	
	public int boundary = 10;
	public int speed = 20;
	
	TiledMapTileLayer.Cell temporaryCell;
	Building lastSelected;
	StaticTiledMapTile[] tempTiles = new StaticTiledMapTile[10];
	int lastCol = -10000, lastRow = -10000;

	public CityRenderer(City city) {
		Assets.load();
		this.city = city;
		this.cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.cam.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
	
	}
	
	public void render(float delta) {
		cam.update();
	    Assets.tiledMapRenderer.setView(cam);
	    Assets.tiledMapRenderer.render();
	    
	    GameScreen.multi.addProcessor(inputProcOne);
	    Gdx.input.setInputProcessor(GameScreen.multi);
		handleInput(delta);
	}
	
	private void addToTiledMap(int row, int col, int width, int height, StaticTiledMapTile[] tiles, TiledMapTileLayer layer, Building newBuilding) {
			int tileCounter = 0;
			
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					layer.getCell(row + j, col + i).setTile(tiles[tileCounter]);
					tileCounter++;
				}
			}	
			
			for(Building building : City.buildings) {
				if(building instanceof PowerStation) {
					if(((col >= building.getCol() - PowerStation.POWER_RADIUS && col <= building.getCol() + PowerStation.POWER_RADIUS + PowerStation.POWER_STATION_HEIGHT - 1 && row >= building.getRow() - PowerStation.POWER_RADIUS && row <= building.getRow() + PowerStation.POWER_RADIUS + PowerStation.POWER_STATION_HEIGHT - 1)
					   || (col + newBuilding.getWidth() - 1 >= building.getCol() - PowerStation.POWER_RADIUS && col + newBuilding.getWidth() - 1 <= building.getCol() + PowerStation.POWER_RADIUS + PowerStation.POWER_STATION_WIDTH - 1&& row + newBuilding.getWidth() - 1 >= building.getRow() - PowerStation.POWER_RADIUS && row + newBuilding.getWidth() - 1 <= building.getRow() + PowerStation.POWER_RADIUS + PowerStation.POWER_STATION_WIDTH - 1)) 
					   && newBuilding.isPowered() == false) {
						((PowerStation)building).addPowerConsumer(newBuilding);
						newBuilding.setPowered(true);
						System.out.println(newBuilding.getClass().getName());
						break;
					}
				}
			}
	}
	
	private StaticTiledMapTile[] buildGreen(int row, int col, int oldRow, int oldCol, int width, int height, StaticTiledMapTile[] tempTiles, StaticTiledMapTile[] greenTiles, TiledMapTileLayer layer) {
		int tileCounter = 0;
		if(oldRow != -10000 && oldCol != -10000) {
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					layer.getCell(oldRow + j, oldCol + i).setTile(tempTiles[tileCounter]);	
					tileCounter++;
				}
			}
		}
		
		tileCounter = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
			
					tempTiles[tileCounter] = (StaticTiledMapTile) layer.getCell(row + j, col + i).getTile();
					layer.getCell(row + j, col + i).setTile(greenTiles[tileCounter]);
					tileCounter++;
				
			}
		}
		
		return tempTiles;
	}
	private void handleInput(float delta) {
		
		if(Gdx.input.getX() > Gdx.graphics.getWidth() - boundary) {
			cam.translate(speed, 0, 0);
		}
		if(Gdx.input.getX() < 0 + boundary) {
			cam.translate(-speed, 0, 0);
		}
		if(Gdx.input.getY() > Gdx.graphics.getHeight() - boundary) {
			cam.translate(0, -speed, 0);
		}
		if(Gdx.input.getY() < 0 + boundary) {
			cam.translate(0, speed, 0);
		}
		
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
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
	
	public int maxZIndex(List<Building> buildings) {
		int maxIndex = 0;
		Building maxBuilding = buildings.get(maxIndex);
		
		for(int i = 1; i < buildings.size(); i++) {
			if(buildings.get(i).getZIndex() > maxBuilding.getZIndex()) {
				maxIndex = i;
				maxBuilding = buildings.get(i);
			}
		}
		
		return maxIndex;
	}
	
	public InputProcessor inputProcOne = new InputAdapter() {
		
		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			Vector3 moveCoordinates = new Vector3(screenX,screenY,0);
		    Vector3 position = cam.unproject(moveCoordinates);
		    
		    TiledMapTileLayer layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
		    int col, row;
		    col = Math.round( ((position.x / TILE_WIDTH_HALF) + (position.y / TILE_HEIGHT_HALF)) / 2) - 1;
	        row = Math.round( ((position.x / TILE_WIDTH_HALF) - (position.y / TILE_HEIGHT_HALF)) / 2);
	        
			if(GameScreen.state == GameScreen.GAME_REDACTOR_MODE) {
				if(GameScreen.redactorState == GameScreen.BUILD_FIRESTATION) {
					if( (col >= 0 && col < 99) && (row >= 0 && row < 99) ) {	
						if(lastRow != -10000 && lastCol != -10000) {
							if(Math.abs(lastRow - row) != 0 || Math.abs(lastCol - col) != 0) {
									tempTiles = buildGreen(row, col, lastRow, lastCol, FireStation.FIRE_STATION_WIDTH, FireStation.FIRE_STATION_HEIGHT, tempTiles, Assets.greenTiles, layer);
							}
						} else {	
							tempTiles = buildGreen(row, col, lastRow, lastCol, FireStation.FIRE_STATION_WIDTH, FireStation.FIRE_STATION_HEIGHT, tempTiles, Assets.greenTiles, layer);
						}				
						lastRow = row; lastCol = col;
					} 
				}
				
				if(GameScreen.redactorState == GameScreen.BUILD_BANK) {
					if( (col >= 0 && col < 99) && (row >= 0 && row < 99) ) {	
						if(lastRow != -10000 && lastCol != -10000) {
							if(Math.abs(lastRow - row) != 0 || Math.abs(lastCol - col) != 0) {
									tempTiles = buildGreen(row, col, lastRow, lastCol, Bank.BANK_WIDTH, Bank.BANK_HEIGHT, tempTiles, Assets.bankGreenTiles, layer);
							}
						} else {	
							tempTiles = buildGreen(row, col, lastRow, lastCol, Bank.BANK_WIDTH, Bank.BANK_HEIGHT, tempTiles, Assets.bankGreenTiles, layer);
						}				
						lastRow = row; lastCol = col;
					} 
				}
				
				if(GameScreen.redactorState == GameScreen.BUILD_HOUSE) {
					StaticTiledMapTile[] greenTiles = {
							new StaticTiledMapTile(Assets.greenDemoBlock)
					};
					if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	
						if(lastRow != -10000 && lastCol != -10000) {
							if(Math.abs(lastRow - row) != 0 || Math.abs(lastCol - col) != 0) {
								tempTiles = buildGreen(row, col, lastRow, lastCol, House.HOUSE_WIDTH, House.HOUSE_HEIGHT, tempTiles, greenTiles, layer);
							}
						}
						if(tempTiles[0] == null) {
							tempTiles[0] = (StaticTiledMapTile) layer.getCell(row, col).getTile();
						}
						lastRow = row; lastCol = col;
						tempTiles = buildGreen(row, col, lastRow, lastCol,  House.HOUSE_WIDTH, House.HOUSE_HEIGHT, tempTiles, greenTiles, layer);				
						
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
	        TiledMapTileLayer layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
			int col, row;
			col = Math.round( ((position.x / TILE_WIDTH_HALF) + (position.y / TILE_HEIGHT_HALF)) / 2) - 1;
	        row = Math.round( ((position.x / TILE_WIDTH_HALF) - (position.y / TILE_HEIGHT_HALF)) / 2);
	        
	        if(GameScreen.state == GameScreen.GAME_REDACTOR_MODE ) {
	        	if(GameScreen.redactorState == GameScreen.BUILD_HOUSE) {
		        	if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	

	        		int houseWidth = 1, houseHeight = 1;
	        		StaticTiledMapTile[] tiles = new StaticTiledMapTile[houseWidth * houseHeight];
	        		tiles[0] = new StaticTiledMapTile(Assets.demoBlock);
	        		
	        		House house = new House(row, col);
	        		addToTiledMap(row, col, houseWidth, houseHeight, tiles, layer, house);

	        		house.createShape();
	        		house.createCollisionShape();
		        	city.buildings.add(house);
		        	}
	        	}
	        				        	
	        	if(GameScreen.redactorState == GameScreen.BUILD_FIRESTATION) {
		        	if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	

	        		 int fireStationWidth = 2, fireStationLength = 2;
	        	      StaticTiledMapTile[] tiles = new StaticTiledMapTile[4];
	        	      tiles[0] = new StaticTiledMapTile(Assets.fireStationCell3);
	        	      tiles[1] = new StaticTiledMapTile(Assets.fireStationCell4);
	        	      tiles[2] = new StaticTiledMapTile(Assets.fireStationCell1);
	        	      tiles[3] = new StaticTiledMapTile(Assets.fireStationCell2);
	        	      FireStation fireStation = null;
	        	      if (row == 99 && col == 99) {
		        	    	fireStation = new FireStation(row - 1, col - 1);
		        	    	addToTiledMap(row - 1, col - 1, fireStationWidth, fireStationLength, tiles, layer, fireStation);
		        	    	
	        	      } else if (row == 99) {
	        	    	fireStation = new FireStation(row - 1, col);
	        	    	addToTiledMap(row - 1, col, fireStationWidth, fireStationLength, tiles, layer, fireStation);
	        	    	
	        	    } else if (col == 99) {
	        	    	fireStation = new FireStation(row, col - 1);
	        	    	addToTiledMap(row, col - 1, fireStationWidth, fireStationLength, tiles, layer, fireStation);
	        	   
	        	    } else {
	        	    	fireStation = new FireStation(row, col);
	        	    	addToTiledMap(row, col, fireStationWidth, fireStationLength, tiles, layer, fireStation);
	        	    }
	        	    
		        	fireStation.createShape();
		        	fireStation.createCollisionShape();
		        	city.buildings.add(fireStation);
		        	
		        	}
	        	}
	        	
	        	
				if(GameScreen.redactorState == GameScreen.BUILD_POLICESTATION) {
			    	if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	
			
					 int policeStationWidth = 2, policeStationLength = 2;
				      StaticTiledMapTile[] tiles = new StaticTiledMapTile[4];
				      tiles[0] = new StaticTiledMapTile(Assets.policeStationCell3);
				      tiles[1] = new StaticTiledMapTile(Assets.policeStationCell4);
				      tiles[2] = new StaticTiledMapTile(Assets.policeStationCell1);
				      tiles[3] = new StaticTiledMapTile(Assets.policeStationCell2);
				      PoliceStation policeStation = null;
				      if (row == 99 && col == 99) {
			    	    	policeStation = new PoliceStation(row - 1, col - 1);
			    	    	addToTiledMap(row - 1, col - 1, policeStationWidth, policeStationLength, tiles, layer, policeStation);

				      } else if (row == 99) {
				    	  policeStation = new PoliceStation(row - 1, col);
				    	  addToTiledMap(row - 1, col, policeStationWidth, policeStationLength, tiles, layer, policeStation);
				    	
				    } else if (col == 99) {
				    	policeStation = new PoliceStation(row, col - 1);
				    	addToTiledMap(row, col - 1, policeStationWidth, policeStationLength, tiles, layer, policeStation);
				   
				    } else {
				    	policeStation = new PoliceStation(row, col);
				    	addToTiledMap(row, col, policeStationWidth, policeStationLength, tiles, layer, policeStation);
				    }
				    
			    	policeStation.createShape();
			    	policeStation.createCollisionShape();
			    	city.buildings.add(policeStation);
			    	}
				}
	        	
				if(GameScreen.redactorState == GameScreen.BUILD_POWERSTATION) {
			    	if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	
			
					 int powerStationWidth = 3, powerStationLength = 3;
				      StaticTiledMapTile[] tiles = new StaticTiledMapTile[9];
				      tiles[0] = new StaticTiledMapTile(Assets.powerStationCell7);
				      tiles[1] = new StaticTiledMapTile(Assets.powerStationCell8);
				      tiles[2] = new StaticTiledMapTile(Assets.powerStationCell9);
				      tiles[3] = new StaticTiledMapTile(Assets.powerStationCell4);
				      tiles[4] = new StaticTiledMapTile(Assets.powerStationCell5);
				      tiles[5] = new StaticTiledMapTile(Assets.powerStationCell6);
				      tiles[6] = new StaticTiledMapTile(Assets.powerStationCell1);
				      tiles[7] = new StaticTiledMapTile(Assets.powerStationCell2);
				      tiles[8] = new StaticTiledMapTile(Assets.powerStationCell3);
				      PowerStation powerStation = null;
				      if (row == 99 && col == 99) {
			    	    	powerStation = new PowerStation(row - 1, col - 1);
			    	    	addToTiledMap(row - 1, col - 1, powerStationWidth, powerStationLength, tiles, layer, powerStation);
			    	    	
				      } else if (row == 99) {
					    	powerStation = new PowerStation(row - 1, col);
				    	addToTiledMap(row - 1, col, powerStationWidth, powerStationLength, tiles, layer, powerStation);
				    	
				    } else if (col == 99) {
				    	powerStation = new PowerStation(row, col - 1);
				    	addToTiledMap(row, col - 1, powerStationWidth, powerStationLength, tiles, layer, powerStation);
				   
				    } else {
				    	powerStation = new PowerStation(row, col);
				    	addToTiledMap(row, col, powerStationWidth, powerStationLength, tiles, layer, powerStation);
				    }
				    
			    	powerStation.createShape();
			    	powerStation.createCollisionShape();
			    	city.buildings.add(powerStation);
			    	}
				}
				
				if(GameScreen.redactorState == GameScreen.BUILD_HOSPITAL) {
		        	if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	

	        		 int hospitalWidth = 2, hospitalLength = 3;
	        	      StaticTiledMapTile[] tiles = new StaticTiledMapTile[6];
	        	      tiles[0] = new StaticTiledMapTile(Assets.hospitalCell5);
	        	      tiles[1] = new StaticTiledMapTile(Assets.hospitalCell6);
	        	      tiles[2] = new StaticTiledMapTile(Assets.hospitalCell3);
	        	      tiles[3] = new StaticTiledMapTile(Assets.hospitalCell4);
	        	      tiles[4] = new StaticTiledMapTile(Assets.hospitalCell1);
	        	      tiles[5] = new StaticTiledMapTile(Assets.hospitalCell2);
	        	      Hospital hospital = null;
	        	      if (row == 99 && col == 99) {
		        	    	hospital = new Hospital(row - 1, col - 1);
		        	    	addToTiledMap(row - 1, col - 1, hospitalWidth, hospitalLength, tiles, layer, hospital);
		        	    	
	        	      } else if (row == 99) {
		        	    hospital = new Hospital(row - 1, col);
	        	    	addToTiledMap(row - 1, col, hospitalWidth, hospitalLength, tiles, layer, hospital);
	        	    	
	        	    } else if (col == 99) {
	        	    	hospital = new Hospital(row, col - 1);
	        	    	addToTiledMap(row, col - 1, hospitalWidth, hospitalLength, tiles, layer, hospital);
	        	   
	        	    } else {
	        	    	hospital = new Hospital(row, col);
	        	    	addToTiledMap(row, col, hospitalWidth, hospitalLength, tiles, layer, hospital);
	        	    }
	        	    
	        	      hospital.createShape();
	        	      hospital.createCollisionShape();
		        	city.buildings.add(hospital);
		        	}
	        	}
				
				if(GameScreen.redactorState == GameScreen.BUILD_BANK) {
		        	if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	

	        		 int bankWidth = 2,bankLength = 2;
	        	      StaticTiledMapTile[] tiles = new StaticTiledMapTile[4];
	        	      tiles[0] = new StaticTiledMapTile(Assets.bankCell3);
	        	      tiles[1] = new StaticTiledMapTile(Assets.bankCell4);
	        	      tiles[2] = new StaticTiledMapTile(Assets.bankCell1);
	        	      tiles[3] = new StaticTiledMapTile(Assets.bankCell2);
	        	      Bank bank = null;
	        	      if (row == 99 && col == 99) {
		        	    	bank = new Bank(row - 1, col - 1);
		        	    	addToTiledMap(row - 1, col - 1, bankWidth, bankLength, tiles, layer, bank);
		        	    	
	        	      } else if (row == 99) {
		        	    	bank = new Bank(row - 1, col);
	        	    	addToTiledMap(row - 1, col, bankWidth, bankLength, tiles, layer, bank);
	        	    	
	        	    } else if (col == 99) {
	        	    	bank = new Bank(row, col - 1);
	        	    	addToTiledMap(row, col - 1, bankWidth, bankLength, tiles, layer, bank);
	        	   
	        	    } else {
	        	    	bank = new Bank(row, col);
	        	    	addToTiledMap(row, col, bankWidth, bankLength, tiles, layer, bank);
	        	    }
	        	    
	        	      bank.createShape();
	        	      bank.createCollisionShape();
	        	      city.buildings.add(bank);
		        	}
	        	}
	        	
				if(GameScreen.redactorState == GameScreen.BUILD_WATERSTATION) {
		        	if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	

	        		int waterStationWidth = 1, waterStationHeight = 1;
	        		StaticTiledMapTile[] tiles = new StaticTiledMapTile[waterStationWidth * waterStationHeight];
	        		tiles[0] = new StaticTiledMapTile(Assets.waterStationRegion);
	        		WaterStation waterStation = new WaterStation(row, col);

	        		addToTiledMap(row, col, waterStationWidth, waterStationHeight, tiles, layer, waterStation);
	        		waterStation.createShape();
	        		waterStation.createCollisionShape();
		        	city.buildings.add(waterStation);
		        	}
	        	}
				
				if(GameScreen.redactorState == GameScreen.BUILD_CITYHALL) {
		        	if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	

	        		 int cityHallWidth = 2, cityHallLength = 2;
	        	      StaticTiledMapTile[] tiles = new StaticTiledMapTile[6];
	        	      tiles[0] = new StaticTiledMapTile(Assets.cityHallCell5);
	        	      tiles[1] = new StaticTiledMapTile(Assets.cityHallCell6);
	        	      tiles[2] = new StaticTiledMapTile(Assets.cityHallCell3);
	        	      tiles[3] = new StaticTiledMapTile(Assets.cityHallCell4);
	        	      tiles[4] = new StaticTiledMapTile(Assets.cityHallCell1);
	        	      tiles[5] = new StaticTiledMapTile(Assets.cityHallCell2);
	        	      CityHall cityHall = null;
	        	      if (row == 99 && col == 99) {
		        	    	cityHall = new CityHall(row - 1, col - 1);
		        	    	addToTiledMap(row - 1, col - 1, cityHallWidth, cityHallLength, tiles, layer, cityHall);
		        	    	
	        	      } else if (row == 99) {
		        	    	cityHall = new CityHall(row - 1, col);
	        	    	addToTiledMap(row - 1, col, cityHallWidth, cityHallLength, tiles, layer, cityHall);
	        	    	
	        	    } else if (col == 99) {
	        	    	cityHall = new CityHall(row, col - 1);
	        	    	addToTiledMap(row, col - 1, cityHallWidth, cityHallLength, tiles, layer, cityHall);
	        	   
	        	    } else {
	        	    	cityHall = new CityHall(row, col);
	        	    	addToTiledMap(row, col, cityHallWidth, cityHallLength, tiles, layer, cityHall);
	        	    }
	        	    
	        	      cityHall.createShape();
	        	      cityHall.createCollisionShape();
		        	city.buildings.add(cityHall);
		        	}
	        	}
				
			
				
	        	if( (col >= 0 && col < 100) && (row >= 0 && row < 100) ) {	
	        		GameScreen.state = GameScreen.GAME_RUNNING;
	        	}
	        } else {
	        	if(Hud.infoActor != null) {
	        		Hud.infoActor.remove();
	        	}
	        		
	        	List<Building> intersectingBuildings = new ArrayList<Building>();
	        	for(Building station : city.buildings) {
	        		
	        	
	        		if(station.getShape().contains(position.x, position.y)) {
	        			intersectingBuildings.add(station);
	        			
	        			if(intersectingBuildings.size() >= 2) {
	        				int index = maxZIndex(intersectingBuildings);
	        					if(lastSelected != null) {
			        				lastSelected.setState(FireStation.FIRE_STATION_UNSELECTED);
			        					
			        			} 
				        			
			        			lastSelected = intersectingBuildings.get(index);
			        			intersectingBuildings.get(index).setState(FireStation.FIRE_STATION_SELECTED);
				        		System.out.println(" " + station.getShape().area() + "\n");
				        		station.showInfo(position.x, position.y);
	        			} else {
	        				if(lastSelected != null) {
		        				lastSelected.setState(FireStation.FIRE_STATION_UNSELECTED);	
		        			} 
			        			
		        			lastSelected = station;
			        		station.setState(FireStation.FIRE_STATION_SELECTED);
			        		System.out.println(" " + station.getShape().area() + "\n");
			        		station.showInfo(position.x, position.y);
	        			}
	        		}	
	        	}
	        }            
			return true;
	   }
	};
	

}
