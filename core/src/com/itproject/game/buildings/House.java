package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.City;
import com.itproject.game.Hud;


public class House extends Building {
	
	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int HOUSE_OK = 0;
	public static final int HOUSE_ON_FIRE = 1;
	public static final int HOUSE_SELECTED = 2;
	public static final int HOUSE_UNSELECTED = 3;
	public static final int HOUSE_DESTROYED = 4;
	public static final int HOUSE_HEIGHT = 2;
	public static final int HOUSE_WIDTH = 2;

	boolean isPowered;
	int state;
	private int col, row;
	private Polygon shape;
	private Polygon collisionShape;
	List<Citizen> residents;
	TiledMapTileLayer layer;
	int zIndex;
	//final byte residentsLimit = 75;
	
	public House(int row, int col) {
		super(1000, 100);
		this.col = col;
		this.row = row;
		
		zIndex = 100 - col + row;
		cost = 5000;
		serviceCost = 300;
		residents = new ArrayList<>();
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
		isUtilityBillUpdated = false;
	}
	
	public void update() {
		if (City.time.getDay() == 1) {
			isUtilityBillUpdated = false;
			payProviderOfPowerAndWater();
		}
	}
	

	public void updateSelected() {
		if(state == HOUSE_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.houseSelectedCell3));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.houseSelectedCell4));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.houseSelectedCell1));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.houseSelectedCell2));
		} else if(state == HOUSE_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.houseCell3));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.houseCell4));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.houseCell1));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.houseCell2));
			state = HOUSE_OK;
		}
	}
	
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[12];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 64; vertices[3] = screeny - 32;
	    vertices[4] = screenx + 128; vertices[5] = screeny;
	    vertices[6] = screenx + 128; vertices[7] = screeny + 64;
	    vertices[8] = screenx + 64; vertices[9] = screeny + 96;
	    vertices[10] = screenx; vertices[11] = screeny + 64;
		shape = new Polygon(vertices);
	}

	public void createCollisionShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[12];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 64; vertices[3] = screeny - 32;
	    vertices[4] = screenx + 128; vertices[5] = screeny;
	    vertices[6] = screenx + 128; vertices[7] = screeny + 64;
	    vertices[8] = screenx + 64; vertices[9] = screeny + 96;
	    vertices[10] = screenx; vertices[11] = screeny + 64;
	    shape = new Polygon(vertices);
	}
	
	final short serviceBill = 16000;
	final short baseProfitRate = 100;
	final byte residentsLimit = 75;

	boolean isUtilityBillUpdated;
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
		if(!residents.isEmpty()) {
			calculateMarkup();
			utilityBill = (short) ((electricityBill + waterBill + serviceBill + currentProfit) / residents.size());

			isUtilityBillUpdated = true;
		}
	}

	public short payUtility(Citizen resident) {
		if (!isUtilityBillUpdated) {
			calculateUtilityBill();
		}

		if (resident.moneySavings - utilityBill >= 0) {
			collectedMoney += utilityBill;
			return utilityBill;
		} else {
			return 0;
		}
	}

	public boolean settleResident(Citizen resident) {
		if (residents.size() < residentsLimit) {
			resident.house = this;
			residents.add(resident);
		} else {
			return false;
		}

		return true;
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
		Hud.setInformationScreen(this, screenX, screenY);
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
		return HOUSE_HEIGHT;
	}

	@Override
	public int getWidth() {
		return HOUSE_WIDTH;
	}

}
