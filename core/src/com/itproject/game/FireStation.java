package com.itproject.game;

import java.util.ArrayList;
import java.util.List;

public class FireStation extends Building{
	public static final int FIRE_STATION_OK = 0;
	public static final int FIRE_STATION_ON_FIRE = 1;
	
	int state;
	List<Citizen> firefighters;
	
	public FireStation() {
		cost = 5000;
		serviceCost = 300;
		firefighters = new ArrayList<Citizen>(10); // default 10 firefighters at start
	}
	
	public void sendCrew() {
		// Send closest team of firefighters to prevent fire
	}
	
	
	
}
