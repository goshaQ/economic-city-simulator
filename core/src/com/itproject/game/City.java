package com.itproject.game;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class City {
	public interface CityListener {
		// To do
	}

	public static final float CITY_WIDTH = 10; // to change
	public static final float CITY_HEIGHT = 15 * 20; // to change
	
	public static final int CITY_STATE_RUNNING = 0;
	public static final int CITY_STATE_GAME_OVER = 1;

	static final Random DEFAULT_PRNG = new Random();
	Random PRNG;
	BiasedRandom BPRNG;

	static float[] birthStatistics = new float[2];
	static float[] procreateStatistics = new float[2];
	static float[] deathStatistics = new float[115];

	CityListener listener;
	List<Citizen> citizens;
	List<Building> buildings;
	List<Road> road;

	Worldview worldview;

	int state;
	long cityBudget; // long long

	public City(CityListener listener) {
		this.citizens = new ArrayList<Citizen>();
		this.buildings = new ArrayList<Building>();
		this.road = new ArrayList<Road>();
		this.listener = listener;
		this.PRNG = DEFAULT_PRNG;
		this.BPRNG = new BiasedRandom(this);
		this.worldview = new Worldview(this);

		// Create map generation later
		// generateMap();
		this.cityBudget = 10000; // default City budget
		BiasedRandom BPRNG = new BiasedRandom(this);
		float[] arr = new float[3];
		arr[0] = (float) 0.52;
		arr[1] = (float) 0.24;
		arr[2] = (float) 0.24;

		int s0 = 0, s1 = 0, s2 = 0;

		for (int i = 0; i < 1000; i++) {
			switch (BPRNG.nextByte(arr, (short) 100)) {
				case 0:
					s0++;
					break;
				case 1:
					s1++;
					break;
				case 2:
					s2++;
					break;
			}
		}
		System.out.println(s0 + " " + s1 + " " + s2);
	}
	
	/*private void generateMap() {
		
	}*/

	public void update(float deltaTime) {
		//updateCitizens(deltaTime);
		//updateBuildings(deltaTime);
		//updateRoad(deltaTime);
		updatePopulation();
		checkGameOver();
	}

	public void loadStatistics() {
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse("statistics.xml");

			Node root = document.getDocumentElement();
			NodeList childNodes = root.getChildNodes();

			Node node;

			float[][] statistics = new float[3][];
			statistics[0] = birthStatistics;
			statistics[1] = procreateStatistics;
			statistics[2] = deathStatistics;

			for (int i = 0; i < childNodes.getLength(); i++) {
				node = childNodes.item(i);
				if (node.getTextContent().trim().equals("")) {
					node.getParentNode().removeChild(node);
					i--;
				} else {
					NodeList statisticsData = childNodes.item(i).getChildNodes();
					for (int j = 0; j < statisticsData.getLength(); j++) {
						node = statisticsData.item(j);
						if (node.getTextContent().trim().equals("")) {
							node.getParentNode().removeChild(node);
							j--;
						} else {
							statistics[i][j] = Byte.parseByte(node.getTextContent());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	// add usage of biased random
	private void updatePopulation() {
		Citizen firstParent = null;
		Citizen secondParent = null;

		int throwDie;
		float happinessContribution = 0;

		Iterator<Citizen> citizen = citizens.iterator();
		while (citizen.hasNext()) {
			if (citizen.hasNext() && firstParent == null) {
				firstParent = citizen.next();
			}

			if (citizen.hasNext() && secondParent == null) {
				secondParent = citizen.next();
			}

			if ( (firstParent != null && secondParent != null) &&
					(firstParent.isReadyToProcreate && secondParent.isReadyToProcreate)) {
				if (firstParent.happinessLevel > 60 && secondParent.happinessLevel > 60) {
					happinessContribution = (firstParent.happinessLevel - 60) + (secondParent.happinessLevel - 60) / 10000;
					procreateStatistics[1] += happinessContribution;
				} else if (firstParent.happinessLevel < 45 && secondParent.happinessLevel < 45) {
					happinessContribution = (45 - firstParent.happinessLevel) + (45 - secondParent.happinessLevel) / -10000;
					procreateStatistics[1] += happinessContribution;
				}

				throwDie = BPRNG.nextByte(procreateStatistics, (short)100);
				if (throwDie == 1) {
					throwDie = BPRNG.nextByte(birthStatistics, (short)1000);
					if (throwDie == 0) {
						citizens.add(new Citizen(worldview.determineType(), this));
					} else {
						citizens.add(new Citizen(worldview.determineType(), this));
						citizens.add(new Citizen(worldview.determineType(), this));
					}

					firstParent.ageOfLastProcreation = firstParent.age;
					secondParent.ageOfLastProcreation = secondParent.age;
					secondParent.isReadyToProcreate = firstParent.isReadyToProcreate = false;
				}

				if (happinessContribution != 0) {
					procreateStatistics[1] = 0.006f;
					happinessContribution = 0;
				}
			}

			if (!firstParent.isReadyToProcreate) {
				firstParent = null;
			}

			if (!secondParent.isReadyToProcreate) {
				secondParent = null;
			}
		}

	}

	private void checkGameOver() {
		
	}
}
