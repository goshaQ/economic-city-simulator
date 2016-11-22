package com.itproject.game.buildings;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

abstract public class Building {
	
	// need to create an actor
	
	public int cost;
	public int serviceCost;
	public int electricityBill;
	public int waterBill;

	public Building(int cost, int serviceCost) {
		this.cost = cost;
		this.serviceCost = serviceCost;
	}
	
	abstract public void createShape();
	abstract public void createCollisionShape();
	abstract public Polygon getShape();
	abstract public Polygon getCollisionShape();
	abstract public int getCol();
	abstract public int getRow();
	abstract public void showInfo(float screenX, float screenY);
	abstract public void setState(int state);
	abstract public void update();
	abstract public void updateSelected();
	abstract public void setElectricityBill(int electricityBill);
	abstract public void setWaterBill(int waterBill);
	abstract public int getZIndex();
	abstract public void setZIndex(int zIndex);
	abstract public boolean isPowered();
	abstract public void setPowered(boolean isPowered);
	abstract public boolean isWatered();
	abstract public void setWatered(boolean isWatered);
	abstract public int getHeight();
	abstract public int getWidth();

}
