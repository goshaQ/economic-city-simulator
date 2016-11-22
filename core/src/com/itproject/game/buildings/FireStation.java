package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.Hud;
import com.itproject.game.BuildingRedactorWindow;

public class FireStation extends Building {
	
	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int FIRE_STATION_OK = 0;
	public static final int FIRE_STATION_ON_FIRE = 1;
	public static final int FIRE_STATION_SELECTED = 2;
	public static final int FIRE_STATION_UNSELECTED = 3;
	public static final int FIRE_STATION_DESTROYED = 4;
	public static final int FIRE_STATION_HEIGHT = 2;
	public static final int FIRE_STATION_WIDTH = 2;
	
	boolean isPowered;
	boolean isWatered;
	int state;
	private int col, row;
	private Polygon shape;
	private Polygon collisionShape;
	List<Citizen> firefighters;
	TiledMapTileLayer layer;
	int zIndex;
	
	public FireStation(int row, int col) {
		super(5000, 300);
		
		state = 0;
		zIndex = 100 - col + row;
		this.col = col;
		this.row = row;
		firefighters = new ArrayList<Citizen>(10);
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
	}
	
	public void sendCrew() {
		// Send closest team of firefighters to prevent fire
	}
	
	public void update() {
		updateSelected();
	}

	@Override
	public void setElectricityBill(int electricityBill) {
		this.electricityBill = electricityBill;
	}

	@Override
	public void setWaterBill(int waterBill) {
		this.waterBill = waterBill;
	}

	public void updateSelected() {
		if(state == FIRE_STATION_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.selectedFireStationCell3));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.selectedFireStationCell4));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.selectedFireStationCell1));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.selectedFireStationCell2));
		} else if(state == FIRE_STATION_UNSELECTED) {
	
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.fireStationCell3));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.fireStationCell4));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.fireStationCell1));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.fireStationCell2));
			state = FIRE_STATION_OK;
		}
	}
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = {
    		screenx,	screeny,
    	    screenx + 64, screeny - 32,
    	    screenx + 128, screeny,
    	    screenx + 128, screeny + 32,
    	    screenx + 128 - 33, screeny + 64,
    	    screenx + 64, screeny - 32 + 96,
    	    screenx, screeny + 32
	    };
	    
		shape = new Polygon(vertices);
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public Polygon getShape() {
		return shape;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public void showInfo(float screenX, float screenY) {
		// to implement
		//Hud.stage.addActor(new BuildingInformationWindow());
		//this.firefighters.add(new Citizen());
		if(Hud.infoActor != null) {
    		Hud.infoActor.remove();
    	}
		Hud.setInformationScreen(this, screenX, screenY);
		System.out.println("It is a FIRESTATION!!");
	}

	@Override
	public void createCollisionShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = {
    		screenx,	screeny,
    	    screenx + 64, screeny - 32,
    	    screenx + 128, screeny,
    	    screenx + 64, screeny + 32
	    };
	    
		collisionShape = new Polygon(vertices);
	}

	@Override
	public Polygon getCollisionShape() {
		return collisionShape;
	}
	
	@Override
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}
	
	@Override
	public int getZIndex() {
		return this.zIndex;
	}
	
	@Override
	public boolean isPowered() {
		return isPowered;
	}

	@Override
	public void setPowered(boolean isPowered) {
		this.isPowered = isPowered;
	}
	
	@Override
	public int getHeight() {
		return FIRE_STATION_HEIGHT;
	}

	@Override
	public int getWidth() {
		return FIRE_STATION_WIDTH;
	}

	@Override
	public boolean isWatered() {
		return isWatered;
	}

	@Override
	public void setWatered(boolean isWatered) {
		this.isWatered = isWatered;
	}

}
