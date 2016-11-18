package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.Hud;

public class IronPlant extends Building {
	
	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int IRON_PLANT_OK = 0;
	public static final int IRON_PLANT_ON_FIRE = 1;
	public static final int IRON_PLANT_SELECTED = 2;
	public static final int IRON_PLANT_UNSELECTED = 3;
	public static final int IRON_PLANT_DESTROYED = 4;
	public static final int IRON_PLANT_HEIGHT = 3;
	public static final int IRON_PLANT_WIDTH = 3;
	
	boolean isPowered;
	int state;
	private int col, row;
	private Polygon shape;
	List<Citizen> lords;
	TiledMapTileLayer layer;
	int zIndex;
	
	public IronPlant(int row, int col) {
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
		if(state == IRON_PLANT_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell7));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell8));
			layer.getCell(row + 2, col).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell9));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell4));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell5));
			layer.getCell(row + 2, col + 1).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell6));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell2));
			layer.getCell(row + 2, col + 2).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell3));
		} else if(state == IRON_PLANT_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.ironPlantCell7));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.ironPlantCell8));
			layer.getCell(row + 2, col).setTile(new StaticTiledMapTile(Assets.ironPlantCell9));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.ironPlantCell4));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.ironPlantCell5));
			layer.getCell(row + 2, col + 1).setTile(new StaticTiledMapTile(Assets.ironPlantCell6));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.ironPlantCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.ironPlantCell2));
			layer.getCell(row + 2, col + 2).setTile(new StaticTiledMapTile(Assets.ironPlantCell3));
			state = IRON_PLANT_OK;
		}
	}
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[20];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 96; vertices[3] = screeny - 47;
	    vertices[4] = screenx + 192; vertices[5] = screeny;
	    vertices[6] = screenx + 160; vertices[7] = screeny + 81;
	    vertices[8] = screenx + 141; vertices[9] = screeny + 74;
	    vertices[10] = screenx + 107; vertices[11] = screeny + 106;
	    vertices[12] = screenx + 95; vertices[13] = screeny + 109;
	    vertices[14] = screenx + 16; vertices[15] = screeny + 72;
	    vertices[16] = screenx + 15; vertices[17] = screeny + 72 - 16;
	    vertices[18] = screenx; vertices[19] = screeny + 64;
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

		System.out.println("It is a IRON_PLANT!!");
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
		return IRON_PLANT_HEIGHT;
	}

	@Override
	public int getWidth() {
		return IRON_PLANT_WIDTH;
	}

}
