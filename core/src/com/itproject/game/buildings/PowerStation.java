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
	int state;
	private int col, row;
	private Polygon shape;
	List<Citizen> worker;
	TiledMapTileLayer layer;

	List<Building> powerConsumers;

	public PowerStation(int row, int col) {
		super(10000, 500);
		
		state = 0;
		
		isPowered = true;
		this.col = col;
		this.row = row;
		cell = new TiledMapTileLayer.Cell[6];
		worker = new ArrayList<Citizen>(10); // default 10 firefighters at start
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
		powerConsumers = new ArrayList<Building>(POWER_CONSUMERS_LIMIT);
		initializePowerConsumers();
	}
	
	public void update() {
		updateSelected();
	}

	@Override
	public void setElectricityBill(short electricityBill) {
		//not used for power station
	}

	@Override
	public void setWaterBill(short waterBill) {
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
	    float[] vertices = new float[12];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 96; vertices[3] = screeny - 47;
	    vertices[4] = screenx + 192; vertices[5] = screeny;
	    vertices[6] = screenx + 192; vertices[7] = screeny + 64;
	    vertices[8] = screenx + 96; vertices[9] = screeny - 47 + 192;
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
		// TODO Auto-generated method stub
		return 0;
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

	public void initializePowerConsumers() {
		for(Building building : City.buildings) {
			if(((building.getCol() >= this.col - POWER_RADIUS && building.getCol() <= this.col + POWER_RADIUS + this.getHeight() - 1 && building.getRow() >= this.row - POWER_RADIUS && building.getRow() <= this.row + POWER_RADIUS + this.getWidth() - 1)
			   || (building.getCol() + building.getHeight() - 1 >= this.col - POWER_RADIUS && building.getCol() + building.getHeight() - 1 <= this.col + POWER_RADIUS + this.getHeight() - 1
			   && building.getRow() + building.getWidth() - 1 >= this.row - POWER_RADIUS && building.getRow() + building.getWidth() - 1 <= this.row + POWER_RADIUS + this.getWidth() - 1 ))
			   && building.isPowered() == false) {
				powerConsumers.add(building);
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
		powerConsumers.add(building);
	}

	public List<Building> getPowerConsumers() {
		return powerConsumers;
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
	

	final byte buildingsLimit = 12;
	final byte employeeLimitForBlock = 10;
	final short employeeSalary = 4800;
	final short monthlyExpenses = 10000;
	final short dailyExpenses = 2000;
	final short baseProfitRate = 800;

	public int currentProfit;
	public short taxes;

	List<Building> buildings;
	List<Citizen> employees;

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
		short electricityBill;

		calculateMarkup();
		electricityBill = (short) Math.round((City.time.days[City.time.getMonth() - 1] * dailyExpenses + monthlyExpenses
				+ employees.size() * employeeSalary + currentProfit) / buildings.size());

		City.budget.changeBudget(currentProfit);

		employees.forEach(employee -> employee.getSalary());
		buildings.forEach(building -> building.setElectricityBill(electricityBill));
	}

	private void calculateMarkup() {
		currentProfit = (short) ((City.PRNG.nextInt(400) + baseProfitRate) * buildings.size());
	}
}
