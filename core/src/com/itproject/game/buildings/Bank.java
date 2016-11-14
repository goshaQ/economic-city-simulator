package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;
import com.itproject.game.City;
import com.itproject.game.Interval;

public class Bank extends Building{

	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int BANK_OK = 0;
	public static final int BANK_ON_FIRE = 1;
	public static final int BANK_SELECTED = 2;
	public static final int BANK_UNSELECTED = 3;
	public static final int BANK_DESTROYED = 4;
	public static final int BANK_HEIGHT = 2;
	public static final int BANK_WIDTH = 2;


	boolean isPowered;

	TiledMapTileLayer.Cell[] cell;
	int state;
	private int col, row;
	private Polygon shape;
	List<Citizen> bankers;
	List<Citizen> clerks;
	TiledMapTileLayer layer;
	  
	public Bank(int row, int col) {
		super(5000, 300);
		
		state = 0;
		
		this.col = col;
		this.row = row;
		cell = new TiledMapTileLayer.Cell[6];
		bankers = new ArrayList<Citizen>(10); // default 10 firefighters at start
		clerks = new ArrayList<Citizen>(10);
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get("mainLayer");
	}
	
	public void sendCrew() {
		// Send closest team of firefighters to prevent fire
	}
	
	public void update() {
		updateSelected();
	}

	@Override
	public void setElectricityBill(short electricityBill) {
		this.electricityBill = electricityBill;
	}

	@Override
	public void setWaterBill(short waterBill) {
		this.waterBill = waterBill;
	}

	public void updateSelected() {
		if(state == BANK_SELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.bankSelectedCell3));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.bankSelectedCell4));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.bankSelectedCell1));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.bankSelectedCell2));
		} else if(state == BANK_UNSELECTED) {
			layer.getCell(row, col).setTile(new StaticTiledMapTile(Assets.bankCell3));
			layer.getCell(row + 1, col).setTile(new StaticTiledMapTile(Assets.bankCell4));
			layer.getCell(row, col + 1).setTile(new StaticTiledMapTile(Assets.bankCell1));
			layer.getCell(row + 1, col + 1).setTile(new StaticTiledMapTile(Assets.bankCell2));
			state = BANK_OK;
		}
	}
	
	public void createShape() {
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[22];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 64; vertices[3] = screeny - 32;
	    vertices[4] = screenx + 128; vertices[5] = screeny;
	    vertices[6] = screenx + 128 - 13; vertices[7] = screeny + 6;
	    vertices[8] = screenx + 128 - 13; vertices[9] = screeny + 6 + 40;
	    vertices[10] = screenx + 128 - 7; vertices[11] = screeny + 49;
	    vertices[12] = screenx + 65; vertices[13] = screeny + 79;
	    vertices[14] = screenx + 31; vertices[15] = screeny + 79;
	    vertices[16] = screenx; vertices[17] = screeny + 47;
	    vertices[18] = screenx + 3; vertices[19] = screeny + 41;
	    vertices[20] = screenx + 3; vertices[21] = screeny + 3;
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
		System.out.println("It is a BANK!!");
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
		return BANK_HEIGHT;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return BANK_WIDTH;
	}

	final byte clercksLimit = 120;
	final byte bankersLimit = 60;

	final short clerckSalary = 2400;
	final short bankersSalary = 4800;

	final short serviceBill = 18000;

	float percent;
	boolean isLoanTaken;
	short period;
	int monthlyPayment;

	public boolean hireEmployee(Citizen employee) {
		if (clerks.size() <= clercksLimit) {
			clerks.add(employee);

			employee.salary = clerckSalary;
			employee.isSalaryChanged = true;
			employee.occupation = Citizen.Occupation.CLERCK;
		} else if(bankers.size() <= bankersLimit) {
			bankers.add(employee);

			employee.salary = bankersSalary;
			employee.isSalaryChanged = true;
			employee.occupation = Citizen.Occupation.BANKER;
		} else {
			return false;
		}

		return true;
	}

	public void loan(int amountMoney) {
		isLoanTaken = true;
		monthlyPayment = Math.round(amountMoney * (1 + percent) / period);

		City.budget.changeBudget(amountMoney);
	}

	public void calculatePercent(Interval interval, int amountMoney) {
		period = (byte) (interval.getYear() * 12 + interval.getMonth());
		if (electricityBill == 0 && waterBill == 0) {

		} else {
			percent = (electricityBill + waterBill + serviceBill) * period / amountMoney +
					City.PRNG.nextFloat() * 6;
		}
	}
}
