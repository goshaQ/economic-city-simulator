package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.Hud;

public class PoliceStation extends Building{

	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int POLICE_STATION_OK = 0;
	public static final int POLICE_STATION_ON_FIRE = 1;
	public static final int POLICE_STATION_SELECTED = 2;
	public static final int POLICE_STATION_UNSELECTED = 3;
	public static final int POLICE_STATION_DESTROYED = 4;
	public static final int POLICE_STATION_HEIGHT = 2;
	public static final int POLICE_STATION_WIDTH = 2;

	boolean isPowered;
	boolean isWatered;
	int state;
	private int col, row;
	private Polygon shape;
	List<Citizen> policemen;
	TiledMapTileLayer layer;
	int zIndex;
	
	public PoliceStation(int row, int col) {
		super(5000, 300);
		state = 0;
		zIndex = 100 - col + row;
		this.col = col;
		this.row = row;
		policemen = new ArrayList<Citizen>(10); 
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
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
		if(state == POLICE_STATION_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.policeStationSelectedCell3));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.policeStationSelectedCell4));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.policeStationSelectedCell1));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.policeStationSelectedCell2));

		} else if(state == POLICE_STATION_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.policeStationCell3));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.policeStationCell4));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.policeStationCell1));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.policeStationCell2));
			state = POLICE_STATION_OK;
		}
	}

	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[12];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 64; vertices[3] = screeny - 32;
	    vertices[4] = screenx + 128; vertices[5] = screeny;
	    vertices[6] = screenx + 128; vertices[7] = screeny + 32;
	    vertices[8] = screenx + 64; vertices[9] = screeny - 32 + 128;
	    vertices[10] = screenx; vertices[11] = screeny + 32;
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

	@Override
	public void createCollisionShape() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Polygon getCollisionShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showInfo(float screenX, float screenY) {
		// TODO Auto-generated method stub
		if(Hud.infoActor != null) {
    		Hud.infoActor.remove();
    	}
		Hud.setInformationScreen(this, screenX, screenY);

	}

	@Override
	public int getZIndex() {
		return zIndex;
	}

	@Override
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	@Override
	public boolean isPowered() {
		// TODO Auto-generated method stub
		return isPowered;
	}

	@Override
	public void setPowered(boolean isPowered) {
		this.isPowered = isPowered;
	}
	
	@Override
	public int getHeight() {
		return POLICE_STATION_HEIGHT;
	}

	@Override
	public int getWidth() {
		return POLICE_STATION_WIDTH;
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
