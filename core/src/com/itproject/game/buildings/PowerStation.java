package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.City;
import com.itproject.game.GameScreen;
import com.itproject.game.Hud;

public class PowerStation extends Building{

	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int POWER_STATION_OK = 0;
	public static final int POWER_STATION_ON_FIRE = 1;
	public static final int POWER_STATION_SELECTED = 2;
	public static final int POWER_STATION_UNSELECTED = 3;
	public static final int POWER_STATION_DESTROYED = 4;
	public static final int POWER_STATION_HEIGHT = 3;
	public static final int POWER_STATION_WIDTH = 3;

	public static final int POWER_RADIUS = 20;
	public static final int POWER_CONSUMERS_LIMIT = 12;

	TiledMapTileLayer.Cell[] cell;
	boolean isPowered;
	boolean isWatered;
	int state;
	private int col, row;
	private Polygon shape;
	//List<Citizen> worker;
	TiledMapTileLayer layer;
	int zIndex;
	
	public PowerStation(int row, int col) {
		super(500000, 500);
		
		state = 0;
		zIndex = 100 - col + row;
		isPowered = true;
		this.col = col;
		this.row = row;
		cell = new TiledMapTileLayer.Cell[6];
		employees = new ArrayList<Citizen>(10); 
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
		buildings = new ArrayList<Building>(POWER_CONSUMERS_LIMIT);
		initializePowerConsumers();
	}
	
	public void update() {
		if (City.time.getDay() == 1) {
			calculateBIll();
		}
	}

	@Override
	public void setElectricityBill(int electricityBill) {
		//not used for power station
	}

	@Override
	public void setWaterBill(int waterBill) {
		//not used for power station
	}

	public void updateSelected() {
		if(state == POWER_STATION_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.powerStationSelectedCell7));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.powerStationSelectedCell8));
			layer.getCell(row + 2, col).setTile(new StaticTiledMapTile(Assets.powerStationSelectedCell9));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.powerStationSelectedCell4));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.powerStationSelectedCell5));
			layer.getCell(row + 2, col + 1).setTile(new StaticTiledMapTile(Assets.powerStationSelectedCell6));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.powerStationSelectedCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.powerStationSelectedCell2));
			layer.getCell(row + 2, col + 2).setTile(new StaticTiledMapTile(Assets.powerStationSelectedCell3));
		} else if(state == POWER_STATION_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.powerStationCell7));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.powerStationCell8));
			layer.getCell(row + 2, col).setTile(new StaticTiledMapTile(Assets.powerStationCell9));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.powerStationCell4));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.powerStationCell5));
			layer.getCell(row + 2, col + 1).setTile(new StaticTiledMapTile(Assets.powerStationCell6));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.powerStationCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.powerStationCell2));
			layer.getCell(row + 2, col + 2).setTile(new StaticTiledMapTile(Assets.powerStationCell3));
			state = POWER_STATION_OK;
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

	public void initializePowerConsumers() {
		for(Building building : City.buildings) {
			if(((building.getCol() >= this.col - POWER_RADIUS && building.getCol() <= this.col + POWER_RADIUS + this.getHeight() - 1 && building.getRow() >= this.row - POWER_RADIUS && building.getRow() <= this.row + POWER_RADIUS + this.getWidth() - 1)
			   || (building.getCol() + building.getHeight() - 1 >= this.col - POWER_RADIUS && building.getCol() + building.getHeight() - 1 <= this.col + POWER_RADIUS + this.getHeight() - 1
			   && building.getRow() + building.getWidth() - 1 >= this.row - POWER_RADIUS && building.getRow() + building.getWidth() - 1 <= this.row + POWER_RADIUS + this.getWidth() - 1 ))
			   && building.isPowered() == false) {
				buildings.add(building);
				building.setPowered(true);
				System.out.println(building.getClass().getName() + "\n");
			}
		}
	}


	@Override
	public boolean isPowered() {
		return isPowered;
	}

	@Override
	public void setPowered(boolean isPowered) {
		this.isPowered = isPowered;
	}

	public void addPowerConsumer(Building building) {
		buildings.add(building);
	}

	public List<Building> getPowerConsumers() {
		return buildings;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return POWER_STATION_HEIGHT;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return POWER_STATION_WIDTH;
	}
	

	public final byte buildingsLimit = 12;
	public final byte employeeLimitForBlock = 10;
	public final short employeeSalary = 4800;
	public final short monthlyExpenses = 12000;
	public final short dailyExpenses = 1200;
	public final short baseProfitRate = 1200;

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
			employee.occupation = Citizen.Occupation.POWERSTATIONWORKER;
		} else {
			return false;
		}

		return true;
	}

	public void calculateBIll() {
		int electricityBill;
		if(buildings.size() > 0) {
			calculateMarkup();
			electricityBill = Math.round((City.time.days[City.time.getMonth() - 1] * dailyExpenses + monthlyExpenses
					+ employees.size() * employeeSalary + currentProfit) / buildings.size());
	
			System.out.println("Power station calculated electricity bill: " + electricityBill);
			System.out.println("Daily expenses: " + City.time.days[City.time.getMonth() - 1] * dailyExpenses);
			System.out.println("Monthly expenses: " + monthlyExpenses);
			System.out.println("Employee salary: " + employees.size() * employeeSalary);
			System.out.println("Profit: " + currentProfit);
			System.out.println("");
	
			City.budget.changeBudget(currentProfit);
	
			employees.forEach(employee -> employee.getSalary());
			buildings.forEach(building -> building.setElectricityBill(electricityBill));
		}
	}

	private void calculateMarkup() {
		currentProfit = (short) ((City.PRNG.nextInt(400) + baseProfitRate) * buildings.size());
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
