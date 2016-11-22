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

public class GroceryShop extends Building {

	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int GROCERY_SHOP_OK = 0;
	public static final int GROCERY_SHOP_ON_FIRE = 1;
	public static final int GROCERY_SHOP_SELECTED = 2;
	public static final int GROCERY_SHOP_UNSELECTED = 3;
	public static final int GROCERY_SHOP_DESTROYED = 4;
	public static final int GROCERY_SHOP_HEIGHT = 2;
	public static final int GROCERY_SHOP_WIDTH = 1;
	
	boolean isPowered;
	boolean isWatered;
	int state;
	private int col, row;
	private Polygon shape;
	int zIndex;
	List<Citizen> worker;
	TiledMapTileLayer layer;
	  
	public GroceryShop(int row, int col) {
		super(150000, 500);
		
		state = 0;
		zIndex = 100 - col + row;
		this.col = col;
		this.row = row;
		salespeople = new ArrayList<>(); // default 10 firefighters at start
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
	}
	
	public void update() {
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
		if(state == GROCERY_SHOP_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.groceryShopSelectedCell1));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.groceryShopSelectedCell2));
		} else if(state == GROCERY_SHOP_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.groceryShopCell1));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.groceryShopCell2));
			state = GROCERY_SHOP_OK;
		}
	}
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[14];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 32; vertices[3] = screeny - 16;
	    vertices[4] = screenx + 64 + 32; vertices[5] = screeny + 16;
	    vertices[6] = screenx + 64 + 32 - 8; vertices[7] = screeny + 16 + 5;
	    vertices[8] = screenx + 64 + 32 - 8; vertices[9] = screeny + 64;
	    vertices[10] = screenx + 64 - 32; vertices[11] = screeny + 64;
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
		return GROCERY_SHOP_HEIGHT;
	}

	@Override
	public int getWidth() {
		return GROCERY_SHOP_WIDTH;
	}

	public List<Citizen> salespeople;

	public final float baseExpenseRate = (float) 0.24;
	public final short serviceBill = 2000;
	public final short sellerSalary = 2800;
	public final short sellersLimit = 20;

	public short purchasePrice;
	public int capital;

	public int numberOfVisits;
	public int expensesLastMonth;

	public int currentProfit;
	public int taxes;

	public boolean hireEmployee(Citizen employee) {
		if (salespeople.size() < sellersLimit) {
			salespeople.add(employee);

			employee.salary = sellerSalary;
			employee.isSalaryChanged = true;
			employee.occupation = Citizen.Occupation.SELLER;
		} else {
			return false;
		}

		return true;
	}

	public void calculateProfit() {
		City.budget.recalculateTax(this);
		City.budget.changeBudget(taxes);

		salespeople.forEach(Citizen::getSalary);

		expensesLastMonth = taxes +
				(electricityBill + waterBill + serviceBill + salespeople.size() * sellerSalary);
		capital += currentProfit - expensesLastMonth;

		System.out.println("Grocery shop has payed taxes: " + taxes);
		System.out.println("Number of sellers in grocery shop: " + salespeople.size());
		System.out.println("Current capital of grocery shop: " + capital);
		System.out.println("Last month WTC grocery shop: " + currentProfit);
		System.out.println("Average check for customers was: " + currentProfit / ++numberOfVisits);
		System.out.println("");

		currentProfit = numberOfVisits = 0;
	}

	public short visitGroceryShop(Citizen citizen) {
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

	@Override
	public boolean isWatered() {
		return isWatered;
	}

	@Override
	public void setWatered(boolean isWatered) {
		this.isWatered = isWatered;
	}
}
