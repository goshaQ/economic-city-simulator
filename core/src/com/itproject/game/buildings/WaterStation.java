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

public class WaterStation extends Building {

	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int WATER_STATION_OK = 0;
	public static final int WATER_STATION_ON_FIRE = 1;
	public static final int WATER_STATION_SELECTED = 2;
	public static final int WATER_STATION_UNSELECTED = 3;
	public static final int WATER_STATION_DESTROYED = 4;
	public static final int WATER_STATION_HEIGHT = 1;
	public static final int WATER_STATION_WIDTH = 1;
	
	public static final int WATER_RADIUS = 12;
	public static final int WATER_CONSUMER_LIMIT = 8;
	
	boolean isPowered;
	boolean isWatered;
	
	int state;
	private int col, row;
	private Polygon shape;
	TiledMapTileLayer layer;
	TiledMapTileLayer statusLayer;
	int zIndex;

	public WaterStation(int row, int col) {
		super(50000, 500);
		state = 0;
		zIndex = 100 - col + row;
		isWatered = true;
		this.col = col;
		this.row = row;
		buildings = new ArrayList<Building>(WATER_CONSUMER_LIMIT);
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
		statusLayer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("statusLayer");
		employees = new ArrayList<Citizen>(10);
		initializeWaterConsumers();
	}
	
	public void update() {
		if (City.time.getDay() == 1) {
			calculateBIll();
		}
	}

	@Override
	public void setElectricityBill(int electricityBill) {
		//not used for WaterStation
	}

	@Override
	public void setWaterBill(int waterBill) {
		//not used for WaterStation
	}
	

	public void initializeWaterConsumers() {
		for(Building building : City.buildings) {
			if(((building.getCol() >= this.col - WATER_RADIUS && building.getCol() <= this.col + WATER_RADIUS + this.getHeight() - 1 && building.getRow() >= this.row - WATER_RADIUS && building.getRow() <= this.row + WATER_RADIUS + this.getWidth() - 1)
			   || (building.getCol() + building.getHeight() - 1 >= this.col - WATER_RADIUS && building.getCol() + building.getHeight() - 1 <= this.col + WATER_RADIUS + this.getHeight() - 1
			   && building.getRow() + building.getWidth() - 1 >= this.row - WATER_RADIUS && building.getRow() + building.getWidth() - 1 <= this.row + WATER_RADIUS + this.getWidth() - 1 ))
			   && building.isWatered() == false) {
				buildings.add(building);
				building.setWatered(true);
				System.out.println(building.getClass().getName() + " Watered\n");
			}
		}
	}
	
	public void updateSelected() {
		if(state == WATER_STATION_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.waterStationSelectedCell));
			//for(int i = col - 13; i <= col  + 12; i++) {
			//	for(int j = row - 13; j <= row + 12; j++) {
			//		statusLayer.setCell(i, j, new TiledMapTileLayer.Cell());
			//		statusLayer.getCell(i, j).setTile(Assets.waterDropTile);
			//	}
			//}
		} else if(state == WATER_STATION_UNSELECTED) {
			//for(int i = col - 13; i <= col  + 12; i++) {
			//	for(int j = row - 13; j <= row + 12; j++) {
			//		statusLayer.getCell(i, j).setTile(null);
			//	}
			//}
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.waterStationRegion));
			state = WATER_STATION_OK;
		}
	}
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[16];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 32; vertices[3] = screeny - 16;
	    vertices[4] = screenx + 64; vertices[5] = screeny;
	    vertices[6] = screenx + 64; vertices[7] = screeny + 72;
	    vertices[8] = screenx + 64 - 20; vertices[9] = screeny + 72 + 8;
	    vertices[10] = screenx + 64 - 38; vertices[11] = screeny + 72 + 8;
	    vertices[12] = screenx + 4; vertices[13] = screeny + 72;
	    vertices[14] = screenx; vertices[15] = screeny + 64;
		shape = new Polygon(vertices);
	}

	public final byte buildingsLimit = 8;
	public final byte employeeLimitForBlock = 4;
	public final short employeeSalary = 3200;
	public final short monthlyExpenses = 4000;
	public final short dailyExpenses = 400;
	public final short baseProfitRate = 800;

	public int currentProfit;
	public short taxes;

	public List<Building> buildings;
	public List<Citizen> employees;

	public boolean attachBuilding(Building building) {
		if (buildings.size() <= buildingsLimit) {
			buildings.add(building);

			// TODO properties in building
		} else {
			return false;
		}

		return true;
	}

	public boolean hireEmployee(Citizen employee) {
		if (employees.size() <= Math.ceil(buildings.size() / 2) * employeeLimitForBlock) {
			employees.add(employee);

			employee.salary = employeeSalary;
			employee.isSalaryChanged = true;
			employee.occupation = Citizen.Occupation.WATERSTATIONWORKER;
		} else {
			return false;
		}

		return true;
	}

	public void calculateBIll() {
		if (!buildings.isEmpty()) {
			int waterBill;

			calculateMarkup();
			waterBill = Math.round((City.time.days[City.time.getMonth() - 1] * dailyExpenses + monthlyExpenses
					+ employees.size() * employeeSalary + currentProfit) / buildings.size());

			System.out.println("Water station calculated water bill: " + waterBill);
			System.out.println("Daily expenses: " + City.time.days[City.time.getMonth() - 1] * dailyExpenses);
			System.out.println("Monthly expenses: " + monthlyExpenses);
			System.out.println("Employee salary: " + employees.size() * employeeSalary);
			System.out.println("Profit: " + currentProfit);
			System.out.println("");

			City.budget.changeBudget(currentProfit);

			if (!employees.isEmpty()) {
				employees.forEach(employee -> employee.getSalary());
			}
			buildings.forEach(building -> building.setWaterBill(waterBill));
		}
	}

	private void calculateMarkup() {
		currentProfit = (short) ((City.PRNG.nextInt(400) + baseProfitRate) * buildings.size());
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

		System.out.println("It is a Power Station!!");
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
		return WATER_STATION_HEIGHT;
	}

	@Override
	public int getWidth() {
		return WATER_STATION_WIDTH;
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
