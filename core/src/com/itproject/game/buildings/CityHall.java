package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.Hud;

public class CityHall extends Building {

	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int CITYHALL_OK = 0;
	public static final int CITYHALL_ON_FIRE = 1;
	public static final int CITYHALL_SELECTED = 2;
	public static final int CITYHALL_UNSELECTED = 3;
	public static final int CITYHALL_DESTROYED = 4;
	public static final int CITYHALL_HEIGHT = 3;
	public static final int CITYHALL_WIDTH = 2;

	boolean isPowered;
	boolean isWatered;
	int state;
	private int col, row;
	private Polygon shape;
	List<Citizen> lords;
	TiledMapTileLayer layer;
	int zIndex;
	
	public CityHall(int row, int col) {
		super(1000000, 500);
		state = 0;
		zIndex = 100 - col + row;
		this.col = col;
		this.row = row;
		lords = new ArrayList<Citizen>(10); // default 10 firefighters at start
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
		if(state == CITYHALL_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.cityHallSelectedCell5));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.cityHallSelectedCell6));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.cityHallSelectedCell3));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.cityHallSelectedCell4));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.cityHallSelectedCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.cityHallSelectedCell2));
		} else if(state == CITYHALL_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.cityHallCell5));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.cityHallCell6));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.cityHallCell3));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.cityHallCell4));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.cityHallCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.cityHallCell2));
			state = CITYHALL_OK;
		}
	}
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[20];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 64; vertices[3] = screeny - 32;
	    vertices[4] = screenx + 160; vertices[5] = screeny + 16;
	    vertices[6] = screenx + 160 - 13; vertices[7] = screeny + 22;
	    vertices[8] = screenx + 160 - 13; vertices[9] = screeny + 22 + 40;
	    vertices[10] = screenx + 160 - 7; vertices[11] = screeny + 16 + 49;
	    vertices[12] = screenx + 97; vertices[13] = screeny + 95;
	    vertices[14] = screenx + 63; vertices[15] = screeny + 95;
	    vertices[16] = screenx + 31; vertices[17] = screeny + 88;
	    vertices[18] = screenx; vertices[19] = screeny + 48;
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
		if(Hud.infoActor != null) {
    		Hud.infoActor.remove();
    	}
		Hud.setInformationScreen(this, screenX, screenY);
		System.out.println("It is a CityHall!!");
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
	public int getZIndex() {
		return zIndex;
	}

	@Override
	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
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
		return CITYHALL_HEIGHT;
	}

	@Override
	public int getWidth() {
		return CITYHALL_WIDTH;
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

