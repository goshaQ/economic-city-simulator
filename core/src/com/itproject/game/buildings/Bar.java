package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.Hud;

public class Bar extends Building {

	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int BAR_OK = 0;
	public static final int BAR_ON_FIRE = 1;
	public static final int BAR_SELECTED = 2;
	public static final int BAR_UNSELECTED = 3;
	public static final int BAR_DESTROYED = 4;
	public static final int BAR_HEIGHT = 2;
	public static final int BAR_WIDTH = 2;

	boolean isPowered;
	int state;
	int zIndex;
	private int col, row;
	private Polygon shape;
	List<Citizen> lords;
	TiledMapTileLayer layer;
	  
	public Bar(int row, int col) {
		super(10000, 500);
		state = 0;
		zIndex = 100 - col + row;
		this.col = col;
		this.row = row;
		lords = new ArrayList<Citizen>(10);
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
	}
	
	public void update() {
		updateSelected();
	}

	@Override
	public void setElectricityBill(short electricityBill) {
		this.electricityBill = electricityBill;
	}

	@Override
	public void setWaterBill(short waterBill) {
		this.waterBill = waterBill;
	}

	public void updateSelected() {
		if(state == BAR_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.barSelectedCell3));;
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.barSelectedCell4));;
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.barSelectedCell1));;
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.barSelectedCell2));;
		} else if(state == BAR_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.barCell3));;
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.barCell4));;
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.barCell1));;
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.barCell2));;
			state = BAR_OK;
		}
	}
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[14];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 64; vertices[3] = screeny - 32;
	    vertices[4] = screenx + 128; vertices[5] = screeny;
	    vertices[6] = screenx + 120; vertices[7] = screeny + 5;
	    vertices[8] = screenx + 120; vertices[9] = screeny + 31;
	    vertices[10] = screenx + 64; vertices[11] = screeny - 32 + 128;
	    vertices[12] = screenx; vertices[13] = screeny + 64;
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
		Hud.setInformationScreen(this, screenX, screenY);

		System.out.println("It is a Bar!!");
	}

	@Override
	public void createCollisionShape() {
		// TODO Auto-generated method stub
	}

	@Override
	public Polygon getCollisionShape() {
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
		return BAR_HEIGHT;
	}

	@Override
	public int getWidth() {
		return BAR_WIDTH;
	}

}
