package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.City;

public class House extends Building {
	
	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int HOUSE_OK = 0;
	public static final int HOUSE_ON_FIRE = 1;
	public static final int HOUSE_SELECTED = 2;
	public static final int HOUSE_UNSELECTED = 3;
	public static final int HOUSE_DESTROYED = 4;
	public static final int HOUSE_HEIGHT = 1;
	public static final int HOUSE_WIDTH = 1;

	boolean isPowered;
	int state;
	private int col, row;
	private Polygon shape;
	private Polygon collisionShape;
	
	List<Citizen> residents;
	TiledMapTileLayer.Cell cell;
	TiledMapTileLayer layer;
	int zIndex;

	public House(int row, int col) {
		super(1000, 100);
		this.col = col;
		this.row = row;
		
		zIndex = 100 - col + row;
		cost = 5000;
		serviceCost = 300;
		residents = new ArrayList<Citizen>();
		cell = new TiledMapTileLayer.Cell();
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
	}
	
	public void update() {
		updateSelected();
	}
	

	public void updateSelected() {
		if(state == HOUSE_SELECTED) {
			cell = layer.getCell(row, col);
			cell.setTile(new StaticTiledMapTile(Assets.selectedDemoBlock));

		} else if(state == HOUSE_UNSELECTED){
			cell.setTile(new StaticTiledMapTile(Assets.demoBlock));
			state = HOUSE_OK;
		}
	}
	
	
	public void createShape() {
		this.col = col; 
		this.row = row;
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[12];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 32; vertices[3] = screeny - 16;
	    vertices[4] = screenx + 64; vertices[5] = screeny;
	    vertices[6] = screenx + 64; vertices[7] = screeny + 32;
	    vertices[8] = screenx + 32; vertices[9] = screeny - 16 + 64;
	    vertices[10] = screenx; vertices[11] = screeny + 32;
		shape = new Polygon(vertices);
	}

	public void createCollisionShape() {
		this.col = col; 
		this.row = row;
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[8];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 32; vertices[3] = screeny - 16;
	    vertices[4] = screenx + 64; vertices[5] = screeny;
	    vertices[6] = screenx + 32; vertices[7] = screeny + 16;
	}
	
	public Polygon getCollisionShape() {
		return collisionShape;
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
		System.out.println("It is a house!");
	}

	
	public void setState(int state) {
		this.state = state;
	}

	@Override
	public int getZIndex() {
		return zIndex;
	}

	@Override
	public void setZIndex(int zIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPeopleSize() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return HOUSE_HEIGHT;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return HOUSE_WIDTH;
	}

	final short serviceBill = 16000;
	final short baseProfitRate = 100;

	short collectedMoney;
	public short utilityBill;
	short currentProfit;

	@Override
	public void setElectricityBill(short electricityBill) {
		this.electricityBill = electricityBill;
	}

	@Override
	public void setWaterBill(short waterBill) {
		this.waterBill = waterBill;
	}

	public void calculateUtilityBill() {
		calculateMarkup();
		utilityBill = (short) ((electricityBill + waterBill + serviceBill + currentProfit) / residents.size());
	}

	public short payUtility(Citizen resident) {
		if (resident.moneySavings - utilityBill >= 0) {
			collectedMoney += utilityBill;
			return utilityBill;
		} else {
			return 0;
		}
	}

	public void moveOut(Citizen resident) {
		residents.remove(resident);
	}

	public void payProviderOfPowerAndWater() {
		if (collectedMoney - serviceBill >= electricityBill + waterBill) {
			City.budget.changeBudget(currentProfit);
		} else {
			City.budget.changeBudget(-(electricityBill + waterBill + serviceBill - collectedMoney));
		}
	}

	private void calculateMarkup() {
		currentProfit = (short) ((City.PRNG.nextInt(120) + baseProfitRate) * residents.size());
	}
}
