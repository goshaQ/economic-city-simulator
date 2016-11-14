package com.itproject.game;

import com.itproject.game.buildings.Building;
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

	float gameTime;
	public static Time time;

	static final Random DEFAULT_PRNG = new Random();
	public static Random PRNG;
	public static BiasedRandom BPRNG;

	static float[] birthStatistics = new float[2];
	static float[] procreateStatistics = new float[2];
	static float[] deathStatistics = new float[117];
	static float[] expensesStatistics = new float[4];

	CityListener listener;
	List<Citizen> citizens;
	public static List<Building> buildings;
	List<Road> road;

	Worldview worldview;
	public static Budget budget;

	int state;

	public City(CityListener listener) {
		loadStatistics();

		this.citizens = new ArrayList<Citizen>();
		this.buildings = new ArrayList<Building>();
		this.road = new ArrayList<Road>();
		this.listener = listener;
		this.PRNG = DEFAULT_PRNG;
		this.BPRNG = new BiasedRandom(this);
		this.worldview = new Worldview(this);
		this.budget = new Budget(this);
		this.time = new Time();

		// Create map generation later
		// generateMap();

        // Init. population
		citizens.add(new Citizen(Worldview.WorldviewType.WORLDVIEW1, new Interval((byte) 0, (byte)0, (byte)18), (short) 3600));
		citizens.add(new Citizen(Worldview.WorldviewType.WORLDVIEW2, new Interval((byte) 0, (byte)0, (byte)18), (short) 3600));
		citizens.add(new Citizen(Worldview.WorldviewType.WORLDVIEW3, new Interval((byte) 0, (byte)0, (byte)18), (short) 3600));

		for (int i = 0; i < 97; i++) {
			citizens.add(new Citizen(worldview.determineType(), new Interval((byte) 0, (byte)0, (byte)18), (short) 3600));
		}
	}
	
	/*private void generateMap() {
		
	}*/
	
	public void update(float deltaTime) {
		gameTime += deltaTime;
		if (gameTime >= 1/200f) {
			time.nextDay();

            updateCitizens();
			updatePopulation();
            updateTime();

            gameTime -= 1/200f;
		}

        updateBuildings();
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
			statistics[3] = expensesStatistics;

			for (int i = 0; i < childNodes.getLength(); i++) {
				node = childNodes.item(i);
				if (node.getTextContent().trim().equals("")) {
					node.getParentNode().removeChild(node);
					i--;
				} else {
					NodeList statisticsData = node.getChildNodes();
					for (int j = 0; j < statisticsData.getLength(); j++) {
						node = statisticsData.item(j);
						if (node.getTextContent().trim().equals("")) {
							node.getParentNode().removeChild(node);
							j--;
						} else {
							statistics[i][j] = Float.parseFloat(node.getTextContent());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	private void updatePopulation() {
		List<Citizen> newCitizens = new ArrayList<>();

        Citizen firstParent = null;
		Citizen secondParent = null;

		int throwDie;
		short moneyForChild = 0;
		float happinessContribution = 0;

		Iterator<Citizen> citizen = citizens.iterator();
		while (citizen.hasNext()) {
			if (citizen.hasNext()) {
				firstParent = citizen.next();
			}

			if (citizen.hasNext()) {
				secondParent = citizen.next();
			}

			if ( (firstParent != null && secondParent != null) &&
					(firstParent.isReadyToProcreate && secondParent.isReadyToProcreate)) {
                if (firstParent.happinessLevel > 60 && secondParent.happinessLevel > 60) {
					happinessContribution = ((firstParent.happinessLevel - 60) + (secondParent.happinessLevel - 60)) / 10000;
					procreateStatistics[1] += happinessContribution;
				} else if (firstParent.happinessLevel < 45 && secondParent.happinessLevel < 45) {
					happinessContribution = ((45 - firstParent.happinessLevel) + (45 - secondParent.happinessLevel)) / -10000;
					procreateStatistics[1] += happinessContribution;
				}

				throwDie = BPRNG.nextByte(procreateStatistics, (short)1000);
                if (throwDie == 1) {
					throwDie = BPRNG.nextByte(birthStatistics, (short)100);
					if (throwDie == 0) {
						if (firstParent.moneySavings - 1200 >= 0) {
							firstParent.moneySavings -= 1200;
							moneyForChild += 1200;
						} else {
							moneyForChild += (short) firstParent.moneySavings;
							firstParent.moneySavings = 0;
						}

						if (secondParent.moneySavings - 1200 >= 0) {
							secondParent.moneySavings -= 1200;
							moneyForChild += 1200;
						} else {
							moneyForChild += (short) secondParent.moneySavings;
							secondParent.moneySavings = 0;
						}

						newCitizens.add(new Citizen(worldview.determineType(), moneyForChild));
					} else {
						if (firstParent.moneySavings - 2400 >= 0) {
							firstParent.moneySavings -= 2400;
							moneyForChild += 1200;
						} else {
							moneyForChild += (short) (firstParent.moneySavings >> 1);
							firstParent.moneySavings = 0;
						}

						if (secondParent.moneySavings - 2400 >= 0) {
							secondParent.moneySavings -= 2400;
							moneyForChild += 1200;
						} else {
							moneyForChild += (short) (secondParent.moneySavings >> 1);
							secondParent.moneySavings = 0;
						}

						newCitizens.add(new Citizen(worldview.determineType(), moneyForChild));
						newCitizens.add(new Citizen(worldview.determineType(), moneyForChild));
					}

					firstParent.ageOfLastProcreation.concatenateWith(firstParent.age.subtractInterval(firstParent.ageOfLastProcreation));
                    secondParent.ageOfLastProcreation.concatenateWith(secondParent.age.subtractInterval(secondParent.ageOfLastProcreation));
					secondParent.isReadyToProcreate = firstParent.isReadyToProcreate = false;
					moneyForChild = 0;
				}

				if (happinessContribution != 0) {
					procreateStatistics[1] = 0.006f;
					happinessContribution = 0;
				}
			}

            firstParent = secondParent = null;
		}

		citizens.addAll(newCitizens);
    }

	private void updateCitizens() {
        List<Citizen> deadCitizens = new ArrayList<>();

		int previousYear;
        for (Citizen c : citizens) {
			previousYear = c.age.getYear();
			c.update();

			if (c.age.getYear() > previousYear || (c.age.getYear() == 0 && c.age.getMonth() == 0 && c.age.getDay() == 7)) {
                if(!c.isAlive()) {
					deadCitizens.add(c);
                }
            }
		}

		citizens.removeAll(deadCitizens);
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
		buildings.forEach(Building::update);
	}

	private void checkGameOver() {

	}
}
