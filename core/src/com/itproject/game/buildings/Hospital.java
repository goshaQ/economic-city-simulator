package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.City;

public class Hospital extends Building{

	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int HOSPITAL_OK = 0;
	public static final int HOSPITAL_ON_FIRE = 1;
	public static final int HOSPITAL_SELECTED = 2;
	public static final int HOSPITAL_UNSELECTED = 3;
	public static final int HOSPITAL_DESTROYED = 4;
	public static final int HOSPITAL_HEIGHT = 3;
	public static final int HOSPITAL_WIDTH = 2;

	boolean isPowered;
	int state;
	private int col, row;
	private Polygon shape;
	public List<Citizen> doctors;
	TiledMapTileLayer layer;
	int zIndex;
	
	public Hospital(int row, int col) {
		super(5000, 300);
		state = 0;
		zIndex = 100 - col + row;
		this.col = col;
		this.row = row;
		doctors = new ArrayList<Citizen>(10); 
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
	}
	
	public void update() {
		updateSelected();
		if (City.time.getDay() == 1) {
			payForFunctioning();
		}
	}
	
	public void updateSelected() {
		if(state == HOSPITAL_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.hospitalSelectedCell5));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.hospitalSelectedCell6));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.hospitalSelectedCell3));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.hospitalSelectedCell4));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.hospitalSelectedCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.hospitalSelectedCell2));
		} else if(state == HOSPITAL_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.hospitalCell5));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.hospitalCell6));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.hospitalCell3));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.hospitalCell4));
			layer.getCell(row, col + 2).setTile(new StaticTiledMapTile(Assets.hospitalCell1));
			layer.getCell(row + 1, col + 2).setTile(new StaticTiledMapTile(Assets.hospitalCell2));
			state = HOSPITAL_OK;
		}
	}
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[12];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 64; vertices[3] = screeny - 32;
	    vertices[4] = screenx + 160; vertices[5] = screeny + 32;
	    vertices[6] = screenx + 160; vertices[7] = screeny + 32 + 64;
	    vertices[8] = screenx + 160 - 64; vertices[9] = screeny + 16 + 96;
	    vertices[10] = screenx; vertices[11] = screeny + 64;
		shape = new Polygon(vertices);
	}
	
	@Override
	public void setWaterBill(short waterBill) {
		this.electricityBill = waterBill;
	}

	final byte doctorsLimit = 16;
	final short monthlyPatientLimit = 800;
	final float baseTreatmentBill = 0.25f;
	final short serviceBill = 12000;
	final short employeeSalary = 9200;

	short curedPatients;

	short treatmentBill;
	int currentProfit;
	short collectedMoney;

	public boolean hireEmployee(Citizen employee) {
		if (doctors.size() <= doctorsLimit) {
			doctors.add(employee);

			employee.salary = employeeSalary;
			employee.isSalaryChanged = true;
			employee.occupation = Citizen.Occupation.DOCTOR;
		} else {
			return false;
		}

		return true;
	}

	public int visitHospital(Citizen patient) {
		if (curedPatients <= monthlyPatientLimit) {
			calculateTreatmentBill(patient);
			if (!(patient.moneySavings < treatmentBill)) {
				return 0;
			}

			curedPatients++;

			collectedMoney += treatmentBill;
			return treatmentBill;
		}

		return 0;
	}

	public void payForFunctioning() {
		currentProfit = collectedMoney - waterBill - electricityBill - serviceBill - doctors.size() * employeeSalary;
		City.budget.changeBudget(currentProfit);

		collectedMoney = 0;
	}

	public void calculateTreatmentBill(Citizen patient) {
		treatmentBill = (short) Math.round((City.PRNG.nextFloat() * 0.15 + baseTreatmentBill) * patient.salary);

		float[] seriousProblem = new float[2];
		seriousProblem[0] = (float) 0.95;
		seriousProblem[1] = (float) 0.05;

		if (City.BPRNG.nextByte(seriousProblem, (short) 100) == 1) {
			treatmentBill += (short) (City.PRNG.nextFloat() * 0.3 * patient.salary);
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
		System.out.println("It is a Hospital!!");
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
	public void setElectricityBill(short electricityBill) {
		this.electricityBill = electricityBill;
	}
	
	@Override
	public int getHeight() {
		return HOSPITAL_HEIGHT;
	}

	@Override
	public int getWidth() {
		return HOSPITAL_WIDTH;
	}

}
