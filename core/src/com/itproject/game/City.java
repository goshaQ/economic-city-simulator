package com.itproject.game;

import java.util.ArrayList;
import java.util.List;

import com.itproject.game.buildings.Building;

public class City {
	public interface CityListener {
		// To do
	}
	
	public static final float CITY_WIDTH = 10; // to change
	public static final float CITY_HEIGHT = 15 * 20; // to change
	
	public static final int CITY_STATE_RUNNING = 0;
	public static final int CITY_STATE_GAME_OVER = 1;
	
	private float gameTime;
	public static Time time;
	public int globalTime;
	
	CityListener listener;
	List<Citizen> citizens;
	List<Building> buildings;
	List<Road> road;
	
	int state;
	long cityBudget; // long long
	
	public City(CityListener listener) {
		time = new Time();
		
		this.citizens = new ArrayList<Citizen>();
		this.buildings = new ArrayList<Building>(); // Building
		this.road = new ArrayList<Road>();
		this.listener = listener;
		
		// Create map generation later
		// generateMap();
		
		this.cityBudget = 10000; // default City budget
	}
	
	/*private void generateMap() {
		
	}*/
	
	public void update(float deltaTime) {
		gameTime += deltaTime;
		//updateCitizens(deltaTime);
		//updateBuildings(deltaTime);
		//updateRoad(deltaTime);
		if(gameTime >= 1f) {
			updateTime();
			gameTime -= 1f;
		}
		
		updateBuildings();
		checkGameOver();
	}
	
	public void updateTime() {
		time.nextDay();
	//	System.out.println(City.time.toString());	
	}
	
	public void updateBuildings() {
		for(Building station : buildings) {
			station.update();
		}
	}	
	
	private void checkGameOver() {
		
	}
	
	public List<Building> getBuildingList() {
		return buildings;
	}
}
