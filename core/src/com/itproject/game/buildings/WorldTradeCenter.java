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

public class WorldTradeCenter extends Building {

	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int WTC_OK = 0;
	public static final int WTC_ON_FIRE = 1;
	public static final int WTC_SELECTED = 2;
	public static final int WTC_UNSELECTED = 3;
	public static final int WTC_DESTROYED = 4;
	public static final int WTC_HEIGHT = 2;
	public static final int WTC_WIDTH = 2;

	boolean isPowered;
	int state;
	private int col, row;
	private Polygon shape;
	public List<Citizen> salespeople;
	public List<Citizen> traders;
	TiledMapTileLayer layer;
	int zIndex;
	
	public WorldTradeCenter(int row, int col) {
		super(10000, 500);
		
		state = 0;
		this.zIndex = 100 - col + row;
		this.col = col;
		this.row = row;
		salespeople = new ArrayList<>();
		traders = new ArrayList<>();
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
	}
	
	public void update() {
		updateSelected();
		tradeOnStockExchange();
		if (City.time.getDay() == 1) {
			calculateProfit();
		}
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
		if(state == WTC_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.wtcSelectedCell3));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.wtcSelectedCell4));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.wtcSelectedCell1));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.wtcSelectedCell2));
		} else if(state == WTC_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.wtcCell3));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.wtcCell4));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.wtcCell1));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.wtcCell2));
			state = WTC_OK;
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
	    vertices[10] = screenx; vertices[11] = screeny + 64;
		shape = new Polygon(vertices);
	}
	
	final float baseExpenseRate = (float) 0.16;
	final short serviceBill = 24000;
	final short sellerSalary = 2800;
	final short traderSalary = 6600;
	final short sellersLimit = 210;
	final short bankersLimit = 90;

	short purchasePrice;
	short basePurchasePrice;
	int capital;
	int numberOfVisits;
	public int currentProfit;
	public short taxes;

	public boolean hireEmployee(Citizen employee) {
		if (salespeople.size() < sellersLimit) {
			salespeople.add(employee);

			employee.salary = sellerSalary;
			employee.isSalaryChanged = true;
			employee.occupation = Citizen.Occupation.SELLER;
		} else if(traders.size() < bankersLimit) {
			traders.add(employee);

			employee.salary = traderSalary;
			employee.isSalaryChanged = true;
			employee.occupation = Citizen.Occupation.TRADER;
		} else {
			return false;
		}

		return true;
	}

	public void calculateProfit() {
		City.budget.recalculateTax(this);
		City.budget.changeBudget(taxes);

		capital += currentProfit - taxes -
				(electricityBill + waterBill + serviceBill + salespeople.size() * sellerSalary + traders.size() * traderSalary);

		currentProfit = numberOfVisits = 0;
	}

	public short visitWTC(Citizen citizen) {
		calculateExpenses(citizen);
		if (citizen.moneySavings >= purchasePrice) {
			currentProfit += purchasePrice;
			numberOfVisits++;

			return purchasePrice;
		}

		return 0;
	}

	private void calculateExpenses(Citizen citizen) {
		purchasePrice = (short) Math.round((City.PRNG.nextFloat() * 0.08 + baseExpenseRate) * citizen.salary);
	}

	public void tradeOnStockExchange() {
		float[] profitProbability = new float[3];
		profitProbability[0] = (float) 0.55;
		profitProbability[1] = (float) 0.1;
		profitProbability[2] = (float) 0.35;

		int bet = (int) Math.round(capital * 0.01);

		for (Citizen trader : traders) {
			for (int i = 0; i < 3; i++) {
				switch (City.BPRNG.nextByte(profitProbability, (short) 100)) {
					case 0:
						currentProfit += bet;
						break;
					case 1:
						break;
					case 2:
						currentProfit -= bet;
						break;
				}
			}
		}
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
		System.out.println("It is a WTC!!");
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
		// TODO Auto-generated method stub
		return isPowered;
	}

	@Override
	public void setPowered(boolean isPowered) {
		this.isPowered = isPowered;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return WTC_HEIGHT;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return WTC_WIDTH;
	}

}
