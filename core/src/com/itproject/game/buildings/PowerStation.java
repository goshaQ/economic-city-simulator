package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.City;
import com.itproject.game.GameScreen;

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
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get(0);
		powerConsumers = new ArrayList<Building>(POWER_CONSUMERS_LIMIT);
		initializePowerConsumers();
	}
	
	public void update() {
		updateSelected();
	}
	
	public void updateSelected() {
		/*if(state == POWER_STATION_SELECTED) {
			cell[0] = layer.getCell(row, col);
			cell[1] = layer.getCell(row + 1, col);
			cell[2] = layer.getCell(row, col + 1);
			cell[3] = layer.getCell(row + 1, col + 1);
			cell[4] = layer.getCell(row, col + 2);
			cell[5] = layer.getCell(row + 1, col + 2);
			
			cell[0].setTile(new StaticTiledMapTile(Assets.selectedFireStationCell5));
			cell[1].setTile(new StaticTiledMapTile(Assets.selectedFireStationCell6));
			cell[2].setTile(new StaticTiledMapTile(Assets.selectedFireStationCell3));
			cell[3].setTile(new StaticTiledMapTile(Assets.selectedFireStationCell4));
			cell[4].setTile(new StaticTiledMapTile(Assets.selectedFireStationCell1));
			cell[5].setTile(new StaticTiledMapTile(Assets.selectedFireStationCell2));
		} else if(state == POWER_STATION_UNSELECTED) {
	
			cell[0].setTile(new StaticTiledMapTile(Assets.fireStationCell5));
			cell[1].setTile(new StaticTiledMapTile(Assets.fireStationCell6));
			cell[2].setTile(new StaticTiledMapTile(Assets.fireStationCell3));
			cell[3].setTile(new StaticTiledMapTile(Assets.fireStationCell4));
			cell[4].setTile(new StaticTiledMapTile(Assets.fireStationCell1));
			cell[5].setTile(new StaticTiledMapTile(Assets.fireStationCell2));
			
			state = POWER_STATION_OK;
		}*/
	}
	
	public void createShape() {
		this.col = col; 
		this.row = row;
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
		for(Building building : GameScreen.city.getBuildingList()) {
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
