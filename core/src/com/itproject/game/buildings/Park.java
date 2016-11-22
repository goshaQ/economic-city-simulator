package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.Hud;

public class Park extends Building {

	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int PARK_OK = 0;
	public static final int PARK_ON_FIRE = 1;
	public static final int PARK_SELECTED = 2;
	public static final int PARK_UNSELECTED = 3;
	public static final int PARK_DESTROYED = 4;
	public static final int PARK_HEIGHT = 3;
	public static final int PARK_WIDTH = 3;
	
	int state;
	private int col, row;
	private Polygon shape;
	public List<Citizen> camper;
	TiledMapTileLayer layer;
	int zIndex;
	boolean isPowered;
	boolean isWatered;
	
	public Park(int row, int col) {
		super(300000, 500);
		
		state = 0;
		zIndex = 100 - col + row;
		this.col = col;
		this.row = row;
		isPowered = false;
		camper = new ArrayList<Citizen>(10);
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
	}
	
	public void update() {
		updateSelected();
	}

	@Override
	public void setElectricityBill(int electricityBill) {
		//not used for park
	}

	@Override
	public void setWaterBill(int waterBill) {
		//not used for park
	}

	public void updateSelected() {
		if(state == PARK_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.parkSelectedCell7));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.parkSelectedCell8));
			layer.getCell(row + 2, col).setTile(new StaticTiledMapTile(Assets.parkSelectedCell9));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.parkSelectedCell4));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.parkSelectedCell5));
			layer.getCell(row + 2, col + 1).setTile(new StaticTiledMapTile(Assets.parkSelectedCell6));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.parkSelectedCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.parkSelectedCell2));
			layer.getCell(row + 2, col + 2).setTile(new StaticTiledMapTile(Assets.parkSelectedCell3));
		} else if(state == PARK_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.parkCell7));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.parkCell8));
			layer.getCell(row + 2, col).setTile(new StaticTiledMapTile(Assets.parkCell9));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.parkCell4));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.parkCell5));
			layer.getCell(row + 2, col + 1).setTile(new StaticTiledMapTile(Assets.parkCell6));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.parkCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.parkCell2));
			layer.getCell(row + 2, col + 2).setTile(new StaticTiledMapTile(Assets.parkCell3));
			state = PARK_OK;
		}
	}
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[12];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 96; vertices[3] = screeny - 47;
	    vertices[4] = screenx + 192; vertices[5] = screeny;
	    vertices[6] = screenx + 192; vertices[7] = screeny + 10;
	    vertices[8] = screenx + 96; vertices[9] = screeny + 64 + 8;
	    vertices[10] = screenx; vertices[11] = screeny + 8;
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

		System.out.println("It is a Park!!");
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
		return false;
	}

	@Override
	public void setPowered(boolean isPowered) {
		this.isPowered = isPowered;
	}

	@Override
	public int getHeight() {
		return PARK_HEIGHT;
	}

	@Override
	public int getWidth() {
		return PARK_WIDTH;
	}

	@Override
	public boolean isWatered() {
		return isWatered;
	}

	@Override
	public void setWatered(boolean isWatered) {
		this.isWatered = isWatered;
	}
	
	public int numberOfVisits;

	public void visitPark(Citizen citizen) {
		citizen.happinessLevel++;
		numberOfVisits++;
	}
	
}
