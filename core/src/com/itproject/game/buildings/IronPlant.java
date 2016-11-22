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

public class IronPlant extends Building {
	
	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int IRON_PLANT_OK = 0;
	public static final int IRON_PLANT_ON_FIRE = 1;
	public static final int IRON_PLANT_SELECTED = 2;
	public static final int IRON_PLANT_UNSELECTED = 3;
	public static final int IRON_PLANT_DESTROYED = 4;
	public static final int IRON_PLANT_HEIGHT = 3;
	public static final int IRON_PLANT_WIDTH = 3;

	boolean isPowered;
	boolean isWatered;
	int state;
	private int col, row;
	private Polygon shape;
	TiledMapTileLayer layer;
	int zIndex;

	public IronPlant(int row, int col) {
		super(1000000, 500);

		state = 0;
		zIndex = 100 - col + row;
		this.col = col;
		this.row = row;
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
		workers = new ArrayList<>(); // default 10 firefighters at start
		amountOfIronPerDay = 20;
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
		if(state == IRON_PLANT_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell7));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell8));
			layer.getCell(row + 2, col).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell9));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell4));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell5));
			layer.getCell(row + 2, col + 1).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell6));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell2));
			layer.getCell(row + 2, col + 2).setTile(new StaticTiledMapTile(Assets.ironPlantSelectedCell3));
		} else if(state == IRON_PLANT_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.ironPlantCell7));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.ironPlantCell8));
			layer.getCell(row + 2, col).setTile(new StaticTiledMapTile(Assets.ironPlantCell9));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.ironPlantCell4));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.ironPlantCell5));
			layer.getCell(row + 2, col + 1).setTile(new StaticTiledMapTile(Assets.ironPlantCell6));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.ironPlantCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.ironPlantCell2));
			layer.getCell(row + 2, col + 2).setTile(new StaticTiledMapTile(Assets.ironPlantCell3));
			state = IRON_PLANT_OK;
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

		System.out.println("It is a IRON_PLANT!!");
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
		return IRON_PLANT_HEIGHT;
	}

	@Override
	public int getWidth() {
		return IRON_PLANT_WIDTH;
	}

	public List<Citizen> workers;

	public final short workersLimit = 120;
	public final short serviceBill = 30000;
	public final short workerSalary = 4200;
	public final short basePriceForIron = 10; //unknown

	public int expensesLastMonth;
	public int currentProfit;
	public int capital;
	public int taxes;

	public short amountOfIron;
	public short amountOfIronPerDay;
	public short priceForIron;

	public boolean hireEmployee(Citizen employee) {
		if (workers.size() < workersLimit) {
			workers.add(employee);

			employee.salary = workerSalary;
			employee.isSalaryChanged = true;
			employee.occupation = Citizen.Occupation.IRONPLANTWORKER;
		} else {
			return false;
		}

		return true;
	}

	public void calculateProfit() {
		calculatePriceForResource();
		currentProfit = amountOfIron * priceForIron;

		City.budget.recalculateTax(this);
		City.budget.changeBudget(taxes);

		workers.forEach(Citizen::getSalary);

		expensesLastMonth = taxes +
				(electricityBill + waterBill + serviceBill + workers.size() * workerSalary);
		capital += currentProfit - expensesLastMonth;

		int desiredProfit = (int) Math.round(capital * 0.018);
		int profitPerDay = (desiredProfit + expensesLastMonth) / City.time.days[City.time.getMonth() - 1];
		amountOfIronPerDay = (short) ((profitPerDay / priceForIron) / workers.size());

		System.out.println("Desired volume of iron per day in next month: " + amountOfIronPerDay);
		System.out.println("Needed profit per day: " + profitPerDay);
		System.out.println("Expenses could be: " + expensesLastMonth / City.time.days[City.time.getMonth() - 1]);

		System.out.println("Iron Plant has payed taxes: " + taxes);
		System.out.println("Number of workers in Iron Plant: " + workers.size());
		System.out.println("Current capital of Iron Plant: " + capital);
		System.out.println("Last month Oil Plant earned: " + currentProfit);
		System.out.println("Price for one unit of iron last month was: " + priceForIron);
		System.out.println("");

		amountOfIron = 0;
	}

	public void calculatePriceForResource() {
		float[] priceProbability = new float[3];
		priceProbability[0] = 0.6f;
		priceProbability[1] = 0.2f;
		priceProbability[2] = 0.2f;

		switch (City.BPRNG.nextByte(priceProbability, (short) 100)) {
			case 0:
				priceForIron = (short) (City.PRNG.nextInt(8) + basePriceForIron);
				break;
			case 1:
				priceForIron = (short) (City.PRNG.nextInt(8) + City.PRNG.nextInt(5) + basePriceForIron);
				break;
			case 2:
				priceForIron = (short) (City.PRNG.nextInt(8) - City.PRNG.nextInt(5) + basePriceForIron);
				break;
		}
	}

	public void produceResource() {
		float[] produceAmountProbability = new float[4];
		produceAmountProbability[0] = 0.35f;
		produceAmountProbability[1] = 0.2f;
		produceAmountProbability[2] = 0.25f;
		produceAmountProbability[3] = 0.2f;


		for (Citizen worker : workers) {
			amountOfIron += amountOfIronPerDay;
			switch (City.BPRNG.nextByte(produceAmountProbability, (short) 100)) {
				case 0:
					amountOfIron += amountOfIronPerDay * 0.25f;
					break;
				case 1:
					amountOfIron -= amountOfIronPerDay * 0.6f;
					break;
				case 2:
					amountOfIron += amountOfIronPerDay * 0.45f;
					break;
				case 3:
					amountOfIron -= amountOfIronPerDay * 0.15f;
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
