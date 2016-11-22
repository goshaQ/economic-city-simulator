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
	boolean isWatered;
	int state;
	private int col, row;
	private Polygon shape;
	public List<Citizen> salespeople;
	public List<Citizen> traders;
	TiledMapTileLayer layer;
	int zIndex;

	public WorldTradeCenter(int row, int col) {
		super(800000, 500);
		
		state = 0;
		this.zIndex = 100 - col + row;
		this.col = col;
		this.row = row;
		salespeople = new ArrayList<>();
		traders = new ArrayList<>();
		capital = 40000;
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
	}
	
	public void update() {
		tradeOnStockExchange();
		if (City.time.getDay() == 1) {
			calculateProfit();
		}
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

	public final float baseExpenseRate = (float) 0.12;
	public final short serviceBill = 18000;
	public final short sellerSalary = 2800;
	public final short traderSalary = 6600;
	public final short sellersLimit = 90;
	public final short bankersLimit = 60;

	public short purchasePrice;
	public int capital;
	public int bet = 2800;

	int[] averageCheck = new int[2];
	public int expensesLastMonth;
	public int currentProfit;
	public int taxes;

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

		salespeople.forEach(Citizen::getSalary);
		traders.forEach(Citizen::getSalary);

		expensesLastMonth = taxes +
				(electricityBill + waterBill + serviceBill + salespeople.size() * sellerSalary + traders.size() * traderSalary);
		capital += currentProfit - expensesLastMonth;

		int desiredProfit = (int) Math.round(capital * 0.01);
		int profitPerDay = ((desiredProfit - averageCheck[0]) + expensesLastMonth) / City.time.days[City.time.getMonth() - 1];
		bet = Math.round(profitPerDay / (0.2f * traders.size()));

		System.out.println("Desired profit in next month: " + desiredProfit);
		System.out.println("Needed profit per day: " + profitPerDay);
		System.out.println("Expenses could be: " + expensesLastMonth / City.time.days[City.time.getMonth() - 1]);

		System.out.println("WTC has payed taxes: " + taxes);
		System.out.println("Number of sellers in WTC: " + salespeople.size());
		System.out.println("Number of traders in WTC: " + traders.size());
		System.out.println("Current capital of WTC: " + capital);
		System.out.println("Last month WTC earned: " + currentProfit);
		System.out.println("Bet in this month: " + bet);
		System.out.println("Average check for customers was: " + averageCheck[0] / ++averageCheck[1]);
		System.out.println("");

		currentProfit = averageCheck[0] = averageCheck[1] = 0;
	}

	public short visitWTC(Citizen citizen) {
		calculateExpenses(citizen);
		if (citizen.moneySavings >= purchasePrice) {
			currentProfit += purchasePrice;

			averageCheck[0] += purchasePrice;
			averageCheck[1]++;

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


		for (Citizen trader : traders) {
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

	@Override
	public boolean isWatered() {
		return isWatered;
	}

	@Override
	public void setWatered(boolean isWatered) {
		this.isWatered = isWatered;
	}

}
