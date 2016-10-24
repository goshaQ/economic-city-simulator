package com.itproject.game;

import java.util.ArrayList;
import java.util.List;

public class City {
	public interface CityListener {
		// To do
	}
	
	public static final float CITY_WIDTH = 10; // to change
	public static final float CITY_HEIGHT = 15 * 20; // to change
	
	public static final int CITY_STATE_RUNNING = 0;
	public static final int CITY_STATE_GAME_OVER = 1;
	
	CityListener listener;
	List<Citizen> citizens;
	List<Building> buildings;
	List<Road> road;
	
	int state;
	long cityBudget; // long long
	
	public City(CityListener listener) {
		this.citizens = new ArrayList<Citizen>();
		this.buildings = new ArrayList<Building>();
		this.road = new ArrayList<Road>();
		this.listener = listener;
		
		// Create map generation later
		// generateMap();
		
		this.cityBudget = 10000; // default City budget
	}
	
	/*private void generateMap() {
		
	}*/
	
	public void update(float deltaTime) {
		//updateCitizens(deltaTime);
		//updateBuildings(deltaTime);
		//updateRoad(deltaTime);
		checkGameOver();
	}
	
	private void checkGameOver() {
		
	}
}
