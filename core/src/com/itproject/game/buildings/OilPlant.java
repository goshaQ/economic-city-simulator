package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.Hud;

public class OilPlant extends Building {
	
	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int OIL_PLANT_OK = 0;
	public static final int OIL_PLANT_ON_FIRE = 1;
	public static final int OIL_PLANT_SELECTED = 2;
	public static final int OIL_PLANT_UNSELECTED = 3;
	public static final int OIL_PLANT_DESTROYED = 4;
	public static final int OIL_PLANT_HEIGHT = 3;
	public static final int OIL_PLANT_WIDTH = 3;
	
	boolean isPowered;
	int state;
	private int col, row;
	private Polygon shape;
	List<Citizen> lords; 
	TiledMapTileLayer layer;
	int zIndex;
	
	public OilPlant(int row, int col) {
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
		if(state == OIL_PLANT_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell7));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell8));
			layer.getCell(row + 2, col).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell9));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell4));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell5));
			layer.getCell(row + 2, col + 1).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell6));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell2));
			layer.getCell(row + 2, col + 2).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell3));
		} else if(state == OIL_PLANT_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.oilPlantCell7));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.oilPlantCell8));
			layer.getCell(row + 2, col).setTile(new StaticTiledMapTile(Assets.oilPlantCell9));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.oilPlantCell4));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.oilPlantCell5));
			layer.getCell(row + 2, col + 1).setTile(new StaticTiledMapTile(Assets.oilPlantCell6));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.oilPlantCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.oilPlantCell2));
			layer.getCell(row + 2, col + 2).setTile(new StaticTiledMapTile(Assets.oilPlantCell3));
			state = OIL_PLANT_OK;
		}
	}
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[16];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 96; vertices[3] = screeny - 47;
	    vertices[4] = screenx + 192; vertices[5] = screeny;
	    vertices[6] = screenx + 160; vertices[7] = screeny + 81;
	    vertices[8] = screenx + 141; vertices[9] = screeny + 74;
	    vertices[10] = screenx + 107; vertices[11] = screeny + 106;
	    vertices[12] = screenx + 95; vertices[13] = screeny + 96;
	    vertices[14] = screenx; vertices[15] = screeny + 64;
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

		System.out.println("It is a OIL_PLANT!!");
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
		return OIL_PLANT_HEIGHT;
	}

	@Override
	public int getWidth() {
		return OIL_PLANT_WIDTH;
	}

}
