package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
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
	
	TiledMapTileLayer.Cell[] cell;
	int state;
	private int col, row;
	private Polygon shape;
	List<Citizen> doctors;
	TiledMapTileLayer layer;
	  
	public Hospital(int row, int col) {
		super(5000, 300);
		
		state = 0;
		
		this.col = col;
		this.row = row;
		cell = new TiledMapTileLayer.Cell[6];
		doctors = new ArrayList<Citizen>(10); // default 10 firefighters at start
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get(0);
	}
	
	public void update() {
		updateSelected();
	}

	public void updateSelected() {
		/*if(state == HOSPITAL_SELECTED) {
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
		} else if(state == HOSPITAL_UNSELECTED) {
	
			cell[0].setTile(new StaticTiledMapTile(Assets.fireStationCell5));
			cell[1].setTile(new StaticTiledMapTile(Assets.fireStationCell6));
			cell[2].setTile(new StaticTiledMapTile(Assets.fireStationCell3));
			cell[3].setTile(new StaticTiledMapTile(Assets.fireStationCell4));
			cell[4].setTile(new StaticTiledMapTile(Assets.fireStationCell1));
			cell[5].setTile(new StaticTiledMapTile(Assets.fireStationCell2));
			
			state = HOSPITAL_OK;
		}*/
	}
	
	public void createShape(int row, int col) {
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
	
	public void showInfo() {
		// to implement
		System.out.println("It is a Hospital!!");
	}

	@Override
	public void createCollisionShape(int row, int col) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Polygon getCollisionShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setElectricityBill(short electricityBill) {
		this.electricityBill = electricityBill;
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
			curedPatients++;

			calculateTreatmentBill(patient);
			if (!(patient.moneySavings < treatmentBill)) {
				return 0;
			}

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
	}
}
