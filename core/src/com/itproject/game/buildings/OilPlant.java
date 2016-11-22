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

public class OilPlant extends Building {
	
	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int OIL_PLANT_OK = 0;
	public static final int OIL_PLANT_ON_FIRE = 1;
	public static final int OIL_PLANT_SELECTED = 2;
	public static final int OIL_PLANT_UNSELECTED = 3;
	public static final int OIL_PLANT_DESTROYED = 4;
	public static final int OIL_PLANT_HEIGHT = 3;
	public static final int OIL_PLANT_WIDTH = 3;

	boolean isPowered;
	boolean isWatered;
	int state;
	private int col, row;
	private Polygon shape;
	TiledMapTileLayer layer;
	int zIndex;

	public OilPlant(int row, int col) {
		super(1000000, 500);
		state = 0;
		zIndex = 100 - col + row;
		this.col = col;
		this.row = row;
		workers = new ArrayList<>(); // default 10 firefighters at start
		volumeOfOilPerDay = 10;
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
		capital = 80000;
	}
	
	public void update() {
		produceResource();
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
		if(state == OIL_PLANT_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell7));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell8));
			layer.getCell(row + 2, col).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell9));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell4));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell5));
			layer.getCell(row + 2, col + 1).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell6));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell2));
			layer.getCell(row + 2, col + 2).setTile(new StaticTiledMapTile(Assets.oilPlantSelectedCell3));
		} else if(state == OIL_PLANT_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.oilPlantCell7));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.oilPlantCell8));
			layer.getCell(row + 2, col).setTile(new StaticTiledMapTile(Assets.oilPlantCell9));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.oilPlantCell4));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.oilPlantCell5));
			layer.getCell(row + 2, col + 1).setTile(new StaticTiledMapTile(Assets.oilPlantCell6));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.oilPlantCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.oilPlantCell2));
			layer.getCell(row + 2, col + 2).setTile(new StaticTiledMapTile(Assets.oilPlantCell3));
			state = OIL_PLANT_OK;
		}
	}
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[16];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 96; vertices[3] = screeny - 47;
	    vertices[4] = screenx + 192; vertices[5] = screeny;
	    vertices[6] = screenx + 160; vertices[7] = screeny + 81;
	    vertices[8] = screenx + 141; vertices[9] = screeny + 74;
	    vertices[10] = screenx + 107; vertices[11] = screeny + 106;
	    vertices[12] = screenx + 95; vertices[13] = screeny + 96;
	    vertices[14] = screenx; vertices[15] = screeny + 64;
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

		System.out.println("It is a OIL_PLANT!!");
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
		return OIL_PLANT_HEIGHT;
	}

	@Override
	public int getWidth() {
		return OIL_PLANT_WIDTH;
	}

	public List<Citizen> workers;

	public final short workersLimit = 120;
	public final short serviceBill = 24000;
	public final short workerSalary = 3800;
	public final short basePriceForOil = 20;

	public int expensesLastMonth;
	public int currentProfit;
	public int capital;
	public int taxes;

	public short volumeOfOil;
	public short volumeOfOilPerDay;
	public short priceForOil;

	public boolean hireEmployee(Citizen employee) {
		if (workers.size() < workersLimit) {
			workers.add(employee);

			employee.salary = workerSalary;
			employee.isSalaryChanged = true;
			employee.occupation = Citizen.Occupation.OILPLANTWORKER;
		} else {
			return false;
		}

		return true;
	}

	public void calculateProfit() {
		calculatePriceForResource();
		currentProfit = volumeOfOil * priceForOil;

		City.budget.recalculateTax(this);
		City.budget.changeBudget(taxes);

		workers.forEach(Citizen::getSalary);

		expensesLastMonth = taxes +
				(electricityBill + waterBill + serviceBill + workers.size() * workerSalary);
		capital += volumeOfOil * priceForOil - expensesLastMonth;

		int desiredProfit = (int) Math.round(capital * 0.015);
		int profitPerDay = (desiredProfit + expensesLastMonth) / City.time.days[City.time.getMonth() - 1];
		volumeOfOilPerDay = (short) ((profitPerDay / priceForOil) / workers.size());

		System.out.println("Desired volume of oil per day in next month: " + volumeOfOilPerDay);
		System.out.println("Needed profit per day: " + profitPerDay);
		System.out.println("Expenses could be: " + expensesLastMonth / City.time.days[City.time.getMonth() - 1]);

		System.out.println("Oil Plant has payed taxes: " + taxes);
		System.out.println("Number of workers in Oil Plant: " + workers.size());
		System.out.println("Current capital of Oil Plant: " + capital);
		System.out.println("Last month Oil Plant earned: " + currentProfit);
		System.out.println("Price for one unit of oil last month was: " + priceForOil);
		System.out.println("");

		volumeOfOil = 0;
	}

	public void calculatePriceForResource() {
		float[] priceProbability = new float[3];
		priceProbability[0] = 0.7f;
		priceProbability[1] = 0.15f;
		priceProbability[2] = 0.15f;

		switch (City.BPRNG.nextByte(priceProbability, (short) 100)) {
			case 0:
				priceForOil = (short) (City.PRNG.nextInt(10) + basePriceForOil);
				break;
			case 1:
				priceForOil = (short) (City.PRNG.nextInt(10) + City.PRNG.nextInt(8) + basePriceForOil);
				break;
			case 2:
				priceForOil = (short) (City.PRNG.nextInt(10) - City.PRNG.nextInt(8) + basePriceForOil);
				break;
		}
	}

	public void produceResource() {
		float[] produceAmountProbability = new float[4];
		produceAmountProbability[0] = 0.35f;
		produceAmountProbability[1] = 0.15f;
		produceAmountProbability[2] = 0.25f;
		produceAmountProbability[3] = 0.25f;


		for (Citizen worker : workers) {
			volumeOfOil += volumeOfOilPerDay;
			switch (City.BPRNG.nextByte(produceAmountProbability, (short) 100)) {
				case 0:
					volumeOfOil += volumeOfOilPerDay * 0.25f;
					break;
				case 1:
					volumeOfOil -= volumeOfOilPerDay * 0.6f;
					break;
				case 2:
					volumeOfOil += volumeOfOilPerDay * 0.4f;
					break;
				case 3:
					volumeOfOil -= volumeOfOilPerDay * 0.15f;
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
