package com.itproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itproject.game.BuildingRedactorWindow.PowerStationRedactor;
import com.itproject.game.buildings.Building;
import com.itproject.game.buildings.*;


public class Hud {
	
	public class HudTimer extends Table {	
		float deltaTime = 0;
		Table timerContent = new Table();
		Label day; Label dayValue;
		Label month; Label monthValue;
		Label year; Label yearValue;
		
		public HudTimer() {
			day = new Label("dd", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			month = new Label("mm",  new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			year = new Label("yyyy",  new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayValue = new Label(String.format("%02d", City.time.getDay()) ,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthValue = new Label(String.format("%02d", City.time.getMonth()) ,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			yearValue = new Label(String.format("%d", City.time.getYear()) ,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			//setFillParent(true);
			//debug();
			add(day).expandX().padTop(10);
			add(month).expandX().padTop(10);
			add(year).expandX().padTop(10);
			row();
			add(dayValue).expandX();
			add(monthValue).expandX();
			add(yearValue).expandX();
			setTouchable(Touchable.enabled);
			
		}	
		@Override
		public void act(float delta) {
			deltaTime += delta;
			if(deltaTime >= 1 / 6f) {
				dayValue.setText(String.format("%02d", City.time.getDay()));
				monthValue.setText(String.format("%02d", City.time.getMonth()));
				yearValue.setText(String.format("%d", City.time.getYear()));
				deltaTime -= 1 / 6f;
			}
		}
	}
	
	public class HudBudget extends Table {	
		float deltaTime = 0;
		Table timerContent = new Table();
		Label budget; Label budgetValue;
		Label population; Label populationValue;
		
		public HudBudget() {
			padLeft(20f);
			budget = new Label("Budget", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			budgetValue = new Label(String.format("$%03d", City.budget.amountMoney) ,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			population = new Label("Population", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			populationValue = new Label(String.format("%03d", City.citizens.size()) ,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			//setFillParent(true);
			//debug();
			add(population).expandX().uniform();
			add(budget);
			row();
			add(populationValue);
			add(budgetValue);
			setTouchable(Touchable.enabled);
			
		}	
		@Override
		public void act(float delta) {
			deltaTime += delta;
			if(deltaTime >= 1 / 6f) {
				budgetValue.setText(String.format("$%03d", City.budget.amountMoney));
				populationValue.setText(String.format("%03d", City.citizens.size()));
				deltaTime -= 1f / 6f;
			}
		}
	}
	
	public class Jobs extends Table {	
		float deltaTime = 0;
		Table timerContent = new Table();
		Label people; Label peopleCount;
		Label people1; Label peopleCount1;
		Label people2; Label peopleCount2;
		Label people3; Label peopleCount3;
		Label people4; Label peopleCount4;
		Label people5; Label peopleCount5;
		Label people6; Label peopleCount6;
		Label people7; Label peopleCount7;
		Label people8; Label peopleCount8;
		Label people9; Label peopleCount9;
		Label people10; Label peopleCount10;
		Label people11; Label peopleCount11;
		Label people12; Label peopleCount12;
		
		
		public Jobs() {
			
			people = new Label("Iron Plant Jobs Taken: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people.setFontScale(2f);
			peopleCount = new Label(String.format("%3.0fs", City.jobDistribution[0] , "%"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount.setFontScale(2f);
			add(people).expandX();
			add(peopleCount).expandX();
			row();
			people1 = new Label("Oil Plant Jobs Taken: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people1.setFontScale(2f);
			peopleCount1 = new Label(String.format("%3.0f%s", City.jobDistribution[1], "%"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount1.setFontScale(2f);
			add(people1).expandX();
			add(peopleCount1).expandX();
			row();
			people2 = new Label("Trader Jobs Taken: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people2.setFontScale(2f);
			peopleCount2 = new Label(String.format("%3.0f%s", City.jobDistribution[2] , "%"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount2.setFontScale(2f);
			add(people2).expandX();
			add(peopleCount2).expandX();
			row();
			people3 = new Label("Seller Jobs Taken: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people3.setFontScale(2f);
			peopleCount3 = new Label(String.format("%3.0f%s", City.jobDistribution[3] , "%"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount3.setFontScale(2f);
			add(people3).expandX();
			add(peopleCount3).expandX();
			row();
			people4 = new Label("Banker Jobs Taken: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people4.setFontScale(2f);
			peopleCount4 = new Label(String.format("%3.0f%s", City.jobDistribution[4] , "%"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount4.setFontScale(2f);
			add(people4).expandX();
			add(peopleCount4).expandX();
			row();
			people5 = new Label("Clerk Jobs Taken: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people5.setFontScale(2f);
			peopleCount5 = new Label(String.format("%3.0f%s", City.jobDistribution[5], "%"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount5.setFontScale(2f);
			add(people5).expandX();
			add(peopleCount5).expandX();
			row();
			people6 = new Label("Doctor Jobs Taken: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people6.setFontScale(2f);
			peopleCount6 = new Label(String.format("%3.0f%s", City.jobDistribution[6] , "%"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount6.setFontScale(2f);
			add(people6).expandX();
			add(peopleCount6).expandX();
			row();
			people7 = new Label("Water Station Jobs Taken: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people7.setFontScale(2f);
			peopleCount7 = new Label(String.format("%3.0f%s", City.jobDistribution[7] , "%"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount7.setFontScale(2f);
			add(people7).expandX();
			add(peopleCount7).expandX();
			row();
			people8 = new Label("Power Station Jobs Taken: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people8.setFontScale(2f);
			peopleCount8 = new Label(String.format("%3.0f%s", City.jobDistribution[8] , "%"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount8.setFontScale(2f);
			add(people8).expandX();
			add(peopleCount8).expandX();
			row();
			people9 = new Label("Unemployed: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people9.setFontScale(2f);
			peopleCount9 = new Label(String.format("%f%s", City.jobDistribution[9] , "%"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount9.setFontScale(2f);
			add(people9).expandX();
			add(peopleCount9).expandX();
			row();//setFillParent(true);
			people10 = new Label("Happiness Level: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people10.setFontScale(2f);
			peopleCount10 = new Label(String.format("%1.2f%s", City.averageHappynessLevel, "%"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount10.setFontScale(2f);
			add(people10).expandX();
			add(peopleCount10).expandX();
			row();//setFillParent(true);
			//debug();
			people11 = new Label("Died This Month: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people11.setFontScale(2f);
			peopleCount11 = new Label(String.format("%d%s", City.deathCounter, " people"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount11.setFontScale(2f);
			add(people11).expandX();
			add(peopleCount11).expandX();
			row();
			people12 = new Label("Born This Month: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people12.setFontScale(2f);
			peopleCount12 = new Label(String.format("%d%s", City.birthCounter, " people"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount12.setFontScale(2f);
			add(people12).expandX();
			add(peopleCount12).expandX();
			row();
			setTouchable(Touchable.enabled);
			
		}	
		@Override
		public void act(float delta) {
			deltaTime += delta;
			if(deltaTime >= 1 / 6f) {
		
				peopleCount.setText(String.format("%1.2f%s", City.jobDistribution[0], "%"));
			
				
				peopleCount1.setText(String.format("%1.2f%s", City.jobDistribution[1] * 100.0, "%"));
				
				 peopleCount2.setText(String.format("%1.2f%s", City.jobDistribution[2] * 100.0, "%"));
				
				peopleCount3.setText(String.format("%1.2f%s", City.jobDistribution[3] * 100.0, "%"));
				
				peopleCount4.setText(String.format("%1.2f%s", City.jobDistribution[4] * 100.0, "%"));
				
				peopleCount5.setText(String.format("%1.2f%s", City.jobDistribution[5] * 100.0, "%"));
				
				peopleCount6.setText(String.format("%1.2f%s", City.jobDistribution[6] * 100.0, "%"));
				
				peopleCount7.setText(String.format("%1.2f%s", City.jobDistribution[7] * 100.0, "%"));
				
				peopleCount8.setText(String.format("%1.2f%s", City.jobDistribution[8] * 100.0, "%"));
				
				peopleCount9.setText(String.format("%1.2f%s", City.jobDistribution[9] * 100.0, "%"));
				
				peopleCount10.setText(String.format("%1.2f%s", City.averageHappynessLevel, "%"));
				peopleCount11.setText(String.format("%d%s", City.deathCounter, " people"));
				peopleCount12.setText(String.format("%d%s", City.birthCounter, " people"));
				
				deltaTime -= 1f / 6f;
			}
		}
	}
	
	public static int timer;
	
	public static Stage stage;
	private Viewport viewport;
	OrthographicCamera camera;

	private Integer worldTimer;
	private float timeCount;
	private Integer score;
	
	public static Actor statisticsActor;
	public static Actor redactorActor;
	public static Actor infoActor;
	public static int redactorStep = 0;
	public static int statisticsStep = 0;
	
	Label levelLabel;
	Label countdownLabel;
	Label timeLabel;
	Label worldLabel;
	Label marioLabel;
	Label scoreLabel;
	ShapeRenderer shape = new ShapeRenderer();

	public Hud(SpriteBatch sb, OrthographicCamera camera) {
		
		this.camera = camera;
		worldTimer = 300;
		timeCount = 0;
		score = 0;
		
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		stage = new Stage(viewport, sb);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		countdownLabel = new Label(String.format("%03d", timer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timeLabel =	new Label("TIME" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		levelLabel = new Label("1-1" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		worldLabel = new Label("WORLD" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		marioLabel = new Label("MARIO" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(marioLabel).expandX().padTop(10);
		table.add(worldLabel).expandX().padTop(10);
		table.add(timeLabel).expandX().padTop(10);
		table.row();
		table.add(scoreLabel).expandX();
		table.add(levelLabel).expandX();
		table.add(countdownLabel).expandX();
		
		BuildingRedactorWindow window = new BuildingRedactorWindow();
		BuildingRedactorWindow.FireStationRedactor fire = window.new FireStationRedactor();
		BuildingRedactorWindow.HouseRedactor house = window.new HouseRedactor();
		BuildingRedactorWindow.BankRedactor bank = window.new BankRedactor();
		BuildingRedactorWindow.CityHallRedactor cityHall = window.new CityHallRedactor();
		BuildingRedactorWindow.HospitalRedactor hospital = window.new HospitalRedactor();
		BuildingRedactorWindow.PoliceStationRedactor police = window.new PoliceStationRedactor();
		BuildingRedactorWindow.PowerStationRedactor power = window.new PowerStationRedactor();
		BuildingRedactorWindow.WaterStationRedactor water = window.new WaterStationRedactor();
		BuildingRedactorWindow.GroceryShopRedactor grocery = window.new GroceryShopRedactor();
		BuildingRedactorWindow.BarRedactor bar = window.new BarRedactor();
		BuildingRedactorWindow.OilPlantRedactor oil = window.new OilPlantRedactor();
		BuildingRedactorWindow.IronPlantRedactor iron = window.new IronPlantRedactor();
		BuildingRedactorWindow.ParkRedactor park = window.new ParkRedactor();
		BuildingRedactorWindow.WTCRedactor wtc = window.new WTCRedactor();

		final Table redactorMenu = new Table();
		redactorMenu.setFillParent(true);
		redactorMenu.right().bottom();
		Table innerRedactorMenu = new Table();
		redactorMenu.add(innerRedactorMenu).width(420f).height(400f);
		innerRedactorMenu.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("data/buildingInformationWindow.png"))));
		final Table redactorMenuContent = new Table();
		final ScrollPane scrollable = new ScrollPane(redactorMenuContent);
		scrollable.setScrollingDisabled(true, false);
		scrollable.setFlickScroll(false);
		innerRedactorMenu.add(scrollable).width(400f).height(375f).center();
		innerRedactorMenu.row();
		redactorMenuContent.right();
		redactorMenuContent.padRight(10);
		redactorMenuContent.add(new Label("Fire Station" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.add(new Label("Police Station" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.row();
		redactorMenuContent.add(fire).uniform();
		redactorMenuContent.add(police).uniform();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label(String.format("$%d", 150000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.add(new Label(String.format("$%d", 140000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.row().height(20f);
		redactorMenuContent.add();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label("Water Station" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.add(new Label("City Hall" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.row();
		redactorMenuContent.add(water).uniform();
		redactorMenuContent.add(cityHall).uniform();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label(String.format("$%d", 50000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.add(new Label(String.format("$%d", 1000000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.row().height(20f);
		redactorMenuContent.add();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label("Bank" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.add(new Label("Hospital" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.row();
		redactorMenuContent.add(bank).uniform();
		redactorMenuContent.add(hospital).uniform();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label(String.format("$%d", 300000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.add(new Label(String.format("$%d", 300000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.row().height(20f);
		redactorMenuContent.add();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label("Living House" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.add(new Label("Power Station" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.row();
		redactorMenuContent.add(house).uniform();
		redactorMenuContent.add(power).uniform();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label(String.format("$%d", 30000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.add(new Label(String.format("$%d", 500000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.row().height(20f);
		redactorMenuContent.add();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label("Grocery Shop" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.add(new Label("Bar" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.row();
		redactorMenuContent.add(grocery).uniform();
		redactorMenuContent.add(bar).uniform();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label(String.format("$%d", 150000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.add(new Label(String.format("$%d", 300000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.row().height(20f);
		redactorMenuContent.add();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label("World Trade Center" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.add(new Label("Park" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.row();
		redactorMenuContent.add(wtc).uniform();
		redactorMenuContent.add(park).uniform();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label(String.format("$%d", 800000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.add(new Label(String.format("$%d", 300000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.row().height(20f);
		redactorMenuContent.add();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label("Oil Plant" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.add(new Label("Iron Plant" , new Label.LabelStyle(new BitmapFont(), Color.BLACK)));
		redactorMenuContent.row();
		redactorMenuContent.add(oil).uniform();
		redactorMenuContent.add(iron).uniform();
		redactorMenuContent.row();
		redactorMenuContent.add(new Label(String.format("$%d", 1000000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.add(new Label(String.format("$%d", 1000000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.row().height(20f);
		redactorMenuContent.add();
		redactorMenuContent.row();
				
		Table bottomHud = new Table();
		bottomHud.left().bottom();
		bottomHud.setFillParent(true);
		
		Image miniMap = new Image(new Texture("data/miniMap.png"));
		final Image redactorButton = new Image(new Texture("data/redactorButton.png"));
		final Image roadButton = new Image(new Texture("data/roadButton.png"));
		final Image statisticsButton = new Image(new Texture("data/statisticsButton.png"));
		redactorButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				redactorButton.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("data/redactorButtonPressed.png"))));  
				if(redactorStep == 0) {

				
					stage.addActor(redactorMenu);
					redactorActor = stage.getActors().get(stage.getActors().size - 1);
					
					redactorStep = 1;
					
					
				} else {
			
					redactorActor.remove();
					redactorStep = 0;
				}
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				redactorButton.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("data/redactorButton.png"))));
				return;
			}	
		});
		roadButton.addListener(new InputListener() {
			
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				roadButton.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("data/roadButtonPressed.png"))));  
				// SET GameScreen.BUILD_ROAD
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				roadButton.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("data/roadButton.png"))));
				return;
			}	
		});
		
		Table statisticsMenu = new Table();
		Table statisticsMenuContainer = new Table();
		Table content = new Table();
		content.debug();
		Jobs jobs = new Jobs();
		statisticsMenu.setFillParent(true);
		statisticsMenu.add(statisticsMenuContainer).width(800f).height(550f);
		statisticsMenuContainer.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("data/buildingInformationWindow.png"))));
		statisticsMenuContainer.add(jobs).width(780f).height(380).center();
		
		//Slider budgetSlider = new Slider(12, 48, 2, false,  new Skin());
		
		//statisticsPlotFunctionContainer.add(budgetSlider);
		
		//statisticsPlotFunctionContainer.add(shape).center();
		
		
		statisticsButton.addListener(new InputListener() {
			
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				statisticsButton.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("data/statisticsButtonPressed.png"))));  
				if(statisticsStep == 0) {
					stage.addActor(statisticsMenu);
					statisticsActor = stage.getActors().get(stage.getActors().size - 1);
					statisticsStep = 1;
				} else {
					statisticsActor.remove();
					statisticsStep = 0;
				}
				return true;
			}
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				statisticsButton.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("data/statisticsButton.png"))));
				return;
			}	
		});

		//bottomHud.debug();
		bottomHud.add(miniMap).expandX().left();
		
		
		Table buttons = new Table();
		buttons.bottom();
		buttons.row().padRight(30f);
		buttons.add(redactorButton);
		buttons.add(roadButton);
		buttons.add(statisticsButton);
		
		bottomHud.add(buttons).expandX().left().bottom();
		bottomHud.add().expandX();

		Table hudTopContent = new Table();
		hudTopContent.setFillParent(true);
		hudTopContent.top();
		
		HudTimer timer = new HudTimer();
		HudBudget budget = new HudBudget();
		hudTopContent.add(timer).center();
		Table budgetHudTop = new Table();
		budgetHudTop.setFillParent(true);
		budgetHudTop.top();
		budgetHudTop.add(budget).right().expandX().padRight(20f);
		

	
		Image hudTop = new Image(new Texture("data/hutTopDemo.png"));
		hudTop.setPosition(-280, Gdx.graphics.getHeight() - 80);
		stage.addActor(hudTop);
		stage.addActor(hudTopContent);
		stage.addActor(budgetHudTop);
		stage.addActor(bottomHud);

	}
	
	public static void setInformationScreen(Building building, float screenX, float screenY) {
		Table buildingInfo = new Table();
		buildingInfo.setFillParent(true);
		buildingInfo.left().bottom();
		//buildingInfo.debug();
		
		Table content = new Table();
		buildingInfo.add(content).width(420f).height(400f);
		content.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("data/buildingInformationWindow.png"))));
		content.add().expandX();
		content.add().expandX();
		content.row();
		
		if(building instanceof House ) {
			Label people = new Label("Residents: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people.setFontScale(2f);
			Label peopleCount = new Label(String.format("%d / 75", ((House)building).residents.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount.setFontScale(2f);
			content.add(people).expandX();
			content.add(peopleCount).expandX();
			content.row();
			Label uitilityBill = new Label("Utility Bill/Resident: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBill.setFontScale(2f);
			Label uitilityBillCount = new Label(String.format("%d", ((House)building).utilityBill), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBillCount.setFontScale(2f);
			content.add(uitilityBill).expandX();
			content.add(uitilityBillCount).expandX();
			content.row();
			Label currentProfit = new Label("Current Profit: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfit.setFontScale(2f);
			Label currentProfitCount = new Label(String.format("%d", ((House)building).currentProfit), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfitCount.setFontScale(2f);
			content.add(currentProfit).expandX();
			content.add(currentProfitCount).expandX();
			content.row();
			Label isWatered = new Label("Water Supply: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			isWatered.setFontScale(2f);
			Label isWateredYes;
			if(((House)building).isWatered()){
				isWateredYes = new Label("Yes" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			} else {
				isWateredYes = new Label("No " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			}
			isWateredYes.setFontScale(2f);
			content.add(isWatered).expandX();
			content.add(isWateredYes).expandX();
			content.row();
			Label isPowered = new Label("Power Supply: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			isPowered.setFontScale(2f);
			Label isPoweredYes;
			if(((House)building).isPowered()) {
				isPoweredYes = new Label("Yes" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			} else {
				isPoweredYes = new Label("No" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			}
			isPoweredYes.setFontScale(2f);
			content.add(isPowered).expandX();
			content.add(isPoweredYes).expandX();
			content.row();

		} else if(building instanceof FireStation ) {
			// BLANK
		} else if(building instanceof PoliceStation ) {
			// BLANK
		} else if(building instanceof PowerStation ) {
			Label people = new Label("Employees: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people.setFontScale(2f);
			Label peopleCount = new Label(String.format("%d", ((PowerStation)building).employees.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount.setFontScale(2f);
			content.add(people).expandX();
			content.add(peopleCount).expandX();
			content.row();
			Label uitilityBill = new Label("Buildings Powered: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBill.setFontScale(2f);
			Label uitilityBillCount = new Label(String.format("%d / 12", ((PowerStation)building).buildings.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBillCount.setFontScale(2f);
			content.add(uitilityBill).expandX();
			content.add(uitilityBillCount).expandX();
			content.row();
			Label currentProfit = new Label("Current Profit: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfit.setFontScale(2f);
			Label currentProfitCount = new Label(String.format("%d", ((PowerStation)building).currentProfit), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfitCount.setFontScale(2f);
			content.add(currentProfit).expandX();
			content.add(currentProfitCount).expandX();
			content.row();
			Label sallary = new Label("Sallary/Employee: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallary.setFontScale(2f);
			Label sallaryCount = new Label(String.format("%d", ((PowerStation)building).employeeSalary), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryCount.setFontScale(2f);
			content.add(sallary).expandX();
			content.add(sallaryCount).expandX();
			content.row();
			Label monthExp = new Label("Monthly Expenses: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExp.setFontScale(2f);
			Label monthExpCount = new Label(String.format("%d", ((PowerStation)building).monthlyExpenses), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExpCount.setFontScale(2f);
			content.add(monthExp).expandX();
			content.add(monthExpCount).expandX();
			content.row();
			Label dayExp = new Label("Daily Expenses: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayExp.setFontScale(2f);
			Label dayExpCount = new Label(String.format("%d", ((PowerStation)building).dailyExpenses), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayExpCount.setFontScale(2f);
			content.add(dayExp).expandX();
			content.add(dayExpCount).expandX();
			content.row();
			Label taxes = new Label("Taxes: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxes.setFontScale(2f);
			Label taxesCount = new Label(String.format("%d", ((PowerStation)building).taxes), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxesCount.setFontScale(2f);
			content.add(taxes).expandX();
			content.add(taxesCount).expandX();
			content.row();
		} else if(building instanceof WaterStation ) {
			Label people = new Label("Employees: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people.setFontScale(2f);
			Label peopleCount = new Label(String.format("%d", ((WaterStation)building).employees.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount.setFontScale(2f);
			content.add(people).expandX();
			content.add(peopleCount).expandX();
			content.row();
			Label uitilityBill = new Label("Buildings Water Supplied: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBill.setFontScale(2f);
			Label uitilityBillCount = new Label(String.format("%d / 8", ((WaterStation)building).buildings.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBillCount.setFontScale(2f);
			content.add(uitilityBill).expandX();
			content.add(uitilityBillCount).expandX();
			content.row();
			Label currentProfit = new Label("Current Profit: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfit.setFontScale(2f);
			Label currentProfitCount = new Label(String.format("%d", ((WaterStation)building).currentProfit), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfitCount.setFontScale(2f);
			content.add(currentProfit).expandX();
			content.add(currentProfitCount).expandX();
			content.row();
			Label sallary = new Label("Sallary/Employee: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallary.setFontScale(2f);
			Label sallaryCount = new Label(String.format("%d", ((WaterStation)building).employeeSalary), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryCount.setFontScale(2f);
			content.add(sallary).expandX();
			content.add(sallaryCount).expandX();
			content.row();
			Label monthExp = new Label("Monthly Expenses: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExp.setFontScale(2f);
			Label monthExpCount = new Label(String.format("%d", ((WaterStation)building).monthlyExpenses), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExpCount.setFontScale(2f);
			content.add(monthExp).expandX();
			content.add(monthExpCount).expandX();
			content.row();
			Label dayExp = new Label("Daily Expenses: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayExp.setFontScale(2f);
			Label dayExpCount = new Label(String.format("%d", ((WaterStation)building).dailyExpenses), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayExpCount.setFontScale(2f);
			content.add(dayExp).expandX();
			content.add(dayExpCount).expandX();
			content.row();
			Label taxes = new Label("Taxes: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxes.setFontScale(2f);
			Label taxesCount = new Label(String.format("%d", ((WaterStation)building).taxes), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxesCount.setFontScale(2f);
			content.add(taxes).expandX();
			content.add(taxesCount).expandX();
			content.row();
		} else if(building instanceof WorldTradeCenter ) {
			Label people = new Label("Traders: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people.setFontScale(2f);
			Label peopleCount = new Label(String.format("%d / 60", ((WorldTradeCenter)building).traders.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount.setFontScale(2f);
			content.add(people).expandX();
			content.add(peopleCount).expandX();
			content.row();
			Label sallary = new Label("Sallary/Trader: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallary.setFontScale(2f);
			Label sallaryCount = new Label(String.format("%d", ((WorldTradeCenter)building).traderSalary), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryCount.setFontScale(2f);
			content.add(sallary).expandX();
			content.add(sallaryCount).expandX();
			content.row();
			Label sellers = new Label("Sellers: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellers.setFontScale(2f);
			Label sellersCount = new Label(String.format("%d / 90", ((WorldTradeCenter)building).salespeople.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellersCount.setFontScale(2f);
			content.add(sellers).expandX();
			content.add(sellersCount).expandX();
			content.row();
			Label sallarySale = new Label("Sallary/Saller: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallarySale.setFontScale(2f);
			Label sallarySaleCount = new Label(String.format("%d", ((WorldTradeCenter)building).traderSalary), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallarySaleCount.setFontScale(2f);
			content.add(sallarySale).expandX();
			content.add(sallarySaleCount).expandX();
			content.row();
			Label uitilityBill = new Label("Service Bill: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBill.setFontScale(2f);
			Label uitilityBillCount = new Label(String.format("%d", ((WorldTradeCenter)building).serviceBill), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBillCount.setFontScale(2f);
			content.add(uitilityBill).expandX();
			content.add(uitilityBillCount).expandX();
			content.row();
			Label currentProfit = new Label("Current Profit: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfit.setFontScale(2f);
			Label currentProfitCount = new Label(String.format("%d", ((WorldTradeCenter)building).currentProfit), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfitCount.setFontScale(2f);
			content.add(currentProfit).expandX();
			content.add(currentProfitCount).expandX();
			content.row();
			Label monthExp = new Label("Monthly Expenses: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExp.setFontScale(2f);
			Label monthExpCount = new Label(String.format("%d", ((WorldTradeCenter)building).expensesLastMonth), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExpCount.setFontScale(2f);
			content.add(monthExp).expandX();
			content.add(monthExpCount).expandX();
			content.row();
			Label dayExp = new Label("Capital: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayExp.setFontScale(2f);
			Label dayExpCount = new Label(String.format("%d", ((WorldTradeCenter)building).capital), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayExpCount.setFontScale(2f);
			content.add(dayExp).expandX();
			content.add(dayExpCount).expandX();
			content.row();
			Label taxes = new Label("Taxes: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxes.setFontScale(2f);
			Label taxesCount = new Label(String.format("%d", ((WorldTradeCenter)building).taxes), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxesCount.setFontScale(2f);
			content.add(taxes).expandX();
			content.add(taxesCount).expandX();
			content.row();
		} else if(building instanceof Park ) {
			
			Label sellers = new Label("Visits/Month: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellers.setFontScale(2f);
			Label sellersCount = new Label(String.format("%d", ((Park)building).numberOfVisits), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellersCount.setFontScale(2f);
			content.add(sellers).expandX();
			content.add(sellersCount).expandX();
			content.row();
			
		} else if(building instanceof OilPlant ) {
			Label sellers = new Label("Base Price For Oil: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellers.setFontScale(2f);
			Label sellersCount = new Label(String.format("%d", ((OilPlant)building).basePriceForOil), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellersCount.setFontScale(2f);
			content.add(sellers).expandX();
			content.add(sellersCount).expandX();
			content.row();
			Label sallarySale = new Label("Volume Of Oil: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallarySale.setFontScale(2f);
			Label sallarySaleCount = new Label(String.format("%d", ((OilPlant)building).volumeOfOil), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallarySaleCount.setFontScale(2f);
			content.add(sallarySale).expandX();
			content.add(sallarySaleCount).expandX();
			content.row();
			Label sallaryHeySale = new Label("Volume Of Oil/Day: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryHeySale.setFontScale(2f);
			Label sallaryHeySaleCount = new Label(String.format("%d", ((OilPlant)building).volumeOfOilPerDay), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryHeySaleCount.setFontScale(2f);
			content.add(sallaryHeySale).expandX();
			content.add(sallaryHeySaleCount).expandX();
			content.row();
			Label people = new Label("Employees: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people.setFontScale(2f);
			Label peopleCount = new Label(String.format("%d / 120", ((OilPlant)building).workers.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount.setFontScale(2f);
			content.add(people).expandX();
			content.add(peopleCount).expandX();
			content.row();
			Label sallary = new Label("Sallary/Employee: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallary.setFontScale(2f);
			Label sallaryCount = new Label(String.format("%d", ((OilPlant)building).workerSalary), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryCount.setFontScale(2f);
			content.add(sallary).expandX();
			content.add(sallaryCount).expandX();
			content.row();
			Label uitilityBill = new Label("Service Bill: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBill.setFontScale(2f);
			Label uitilityBillCount = new Label(String.format("%d", ((OilPlant)building).serviceBill), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBillCount.setFontScale(2f);
			content.add(uitilityBill).expandX();
			content.add(uitilityBillCount).expandX();
			content.row();
			Label currentProfit = new Label("Current Profit: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfit.setFontScale(2f);
			Label currentProfitCount = new Label(String.format("%d", ((OilPlant)building).currentProfit), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfitCount.setFontScale(2f);
			content.add(currentProfit).expandX();
			content.add(currentProfitCount).expandX();
			content.row();
			Label monthExp = new Label("Monthly Expenses: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExp.setFontScale(2f);
			Label monthExpCount = new Label(String.format("%d", ((OilPlant)building).expensesLastMonth), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExpCount.setFontScale(2f);
			content.add(monthExp).expandX();
			content.add(monthExpCount).expandX();
			content.row();
			Label dayExp = new Label("Capital: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayExp.setFontScale(2f);
			Label dayExpCount = new Label(String.format("%d", ((OilPlant)building).capital), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayExpCount.setFontScale(2f);
			content.add(dayExp).expandX();
			content.add(dayExpCount).expandX();
			content.row();
			Label taxes = new Label("Taxes: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxes.setFontScale(2f);
			Label taxesCount = new Label(String.format("%d", ((OilPlant)building).taxes), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxesCount.setFontScale(2f);
			content.add(taxes).expandX();
			content.add(taxesCount).expandX();
			content.row();
		} else if(building instanceof IronPlant ) {
			Label sellers = new Label("Base Price For Iron: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellers.setFontScale(2f);
			Label sellersCount = new Label(String.format("%d", ((IronPlant)building).basePriceForIron), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellersCount.setFontScale(2f);
			content.add(sellers).expandX();
			content.add(sellersCount).expandX();
			content.row();
			Label sallarySale = new Label("Volume Of Iron: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallarySale.setFontScale(2f);
			Label sallarySaleCount = new Label(String.format("%d", ((IronPlant)building).amountOfIron), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallarySaleCount.setFontScale(2f);
			content.add(sallarySale).expandX();
			content.add(sallarySaleCount).expandX();
			content.row();
			Label sallaryHeySale = new Label("Volume Of Iron/Day: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryHeySale.setFontScale(2f);
			Label sallaryHeySaleCount = new Label(String.format("%d", ((IronPlant)building).amountOfIronPerDay), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryHeySaleCount.setFontScale(2f);
			content.add(sallaryHeySale).expandX();
			content.add(sallaryHeySaleCount).expandX();
			content.row();
			Label people = new Label("Employees: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people.setFontScale(2f);
			Label peopleCount = new Label(String.format("%d / 120", ((IronPlant)building).workers.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount.setFontScale(2f);
			content.add(people).expandX();
			content.add(peopleCount).expandX();
			content.row();
			Label sallary = new Label("Sallary/Employee: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallary.setFontScale(2f);
			Label sallaryCount = new Label(String.format("%d", ((IronPlant)building).workerSalary), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryCount.setFontScale(2f);
			content.add(sallary).expandX();
			content.add(sallaryCount).expandX();
			content.row();
			Label uitilityBill = new Label("Service Bill: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBill.setFontScale(2f);
			Label uitilityBillCount = new Label(String.format("%d", ((IronPlant)building).serviceBill), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBillCount.setFontScale(2f);
			content.add(uitilityBill).expandX();
			content.add(uitilityBillCount).expandX();
			content.row();
			Label currentProfit = new Label("Current Profit: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfit.setFontScale(2f);
			Label currentProfitCount = new Label(String.format("%d", ((IronPlant)building).currentProfit), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfitCount.setFontScale(2f);
			content.add(currentProfit).expandX();
			content.add(currentProfitCount).expandX();
			content.row();
			Label monthExp = new Label("Monthly Expenses: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExp.setFontScale(2f);
			Label monthExpCount = new Label(String.format("%d", ((IronPlant)building).expensesLastMonth), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExpCount.setFontScale(2f);
			content.add(monthExp).expandX();
			content.add(monthExpCount).expandX();
			content.row();
			Label dayExp = new Label("Capital: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayExp.setFontScale(2f);
			Label dayExpCount = new Label(String.format("%d", ((IronPlant)building).capital), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayExpCount.setFontScale(2f);
			content.add(dayExp).expandX();
			content.add(dayExpCount).expandX();
			content.row();
			Label taxes = new Label("Taxes: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxes.setFontScale(2f);
			Label taxesCount = new Label(String.format("%d", ((IronPlant)building).taxes), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxesCount.setFontScale(2f);
			content.add(taxes).expandX();
			content.add(taxesCount).expandX();
			content.row();
		} else if(building instanceof CityHall ) {
			// BLANK
		} else if(building instanceof GroceryShop ) {
			Label people = new Label("Salesmen: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people.setFontScale(2f);
			Label peopleCount = new Label(String.format("%d / 60", ((GroceryShop)building).salespeople.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount.setFontScale(2f);
			content.add(people).expandX();
			content.add(peopleCount).expandX();
			content.row();
			Label sallary = new Label("Sallary/Salesman: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallary.setFontScale(2f);
			Label sallaryCount = new Label(String.format("%d", ((GroceryShop)building).sellerSalary), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryCount.setFontScale(2f);
			content.add(sallary).expandX();
			content.add(sallaryCount).expandX();
			content.row();
			Label sellers = new Label("Number of Visits/Month: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellers.setFontScale(2f);
			Label sellersCount = new Label(String.format("%d", ((GroceryShop)building).numberOfVisits), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellersCount.setFontScale(2f);
			content.add(sellers).expandX();
			content.add(sellersCount).expandX();
			content.row();

			Label uitilityBill = new Label("Service Bill: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBill.setFontScale(2f);
			Label uitilityBillCount = new Label(String.format("%d", ((GroceryShop)building).serviceBill), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBillCount.setFontScale(2f);
			content.add(uitilityBill).expandX();
			content.add(uitilityBillCount).expandX();
			content.row();
			Label currentProfit = new Label("Current Profit: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfit.setFontScale(2f);
			Label currentProfitCount = new Label(String.format("%d", ((GroceryShop)building).currentProfit), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfitCount.setFontScale(2f);
			content.add(currentProfit).expandX();
			content.add(currentProfitCount).expandX();
			content.row();
			Label monthExp = new Label("Monthly Expenses: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExp.setFontScale(2f);
			Label monthExpCount = new Label(String.format("%d", ((GroceryShop)building).expensesLastMonth), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExpCount.setFontScale(2f);
			content.add(monthExp).expandX();
			content.add(monthExpCount).expandX();
			content.row();
			Label dayExp = new Label("Capital: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayExp.setFontScale(2f);
			Label dayExpCount = new Label(String.format("%d", ((GroceryShop)building).capital), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			dayExpCount.setFontScale(2f);
			content.add(dayExp).expandX();
			content.add(dayExpCount).expandX();
			content.row();
			Label taxes = new Label("Taxes: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxes.setFontScale(2f);
			Label taxesCount = new Label(String.format("%d", ((GroceryShop)building).taxes), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxesCount.setFontScale(2f);
			content.add(taxes).expandX();
			content.add(taxesCount).expandX();
			content.row();
			
		} else if(building instanceof Hospital ) {
			Label people = new Label("Doctors: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people.setFontScale(2f);
			Label peopleCount = new Label(String.format("%d / 16", ((Hospital)building).doctors.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount.setFontScale(2f);
			content.add(people).expandX();
			content.add(peopleCount).expandX();
			content.row();
			Label sallary = new Label("Sallary/Doctor: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallary.setFontScale(2f);
			Label sallaryCount = new Label(String.format("%d", ((Hospital)building).employeeSalary), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryCount.setFontScale(2f);
			content.add(sallary).expandX();
			content.add(sallaryCount).expandX();
			content.row();
			Label sellers = new Label("Patients limit: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellers.setFontScale(2f);
			Label sellersCount = new Label(String.format("%d", ((Hospital)building).monthlyPatientLimit), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellersCount.setFontScale(2f);
			content.add(sellers).expandX();
			content.add(sellersCount).expandX();
			content.row();
			Label monthExp = new Label("Cured Patients / Month: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExp.setFontScale(2f);
			Label monthExpCount = new Label(String.format("%d", ((Hospital)building).curedPatients), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			monthExpCount.setFontScale(2f);
			content.add(monthExp).expandX();
			content.add(monthExpCount).expandX();
			content.row();
			Label uitilityBill = new Label("Service Bill: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBill.setFontScale(2f);
			Label uitilityBillCount = new Label(String.format("%d", ((Hospital)building).serviceBill), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBillCount.setFontScale(2f);
			content.add(uitilityBill).expandX();
			content.add(uitilityBillCount).expandX();
			content.row();
			Label currentSProfit = new Label("Current Profit: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentSProfit.setFontScale(2f);
			Label currentSProfitCount = new Label(String.format("%d", ((Hospital)building).currentProfit), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentSProfitCount.setFontScale(2f);
			content.add(currentSProfit).expandX();
			content.add(currentSProfitCount).expandX();
			content.row();
			Label currentProfit = new Label("Collected Money: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfit.setFontScale(2f);
			Label currentProfitCount = new Label(String.format("%d", ((Hospital)building).collectedMoney), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfitCount.setFontScale(2f);
			content.add(currentProfit).expandX();
			content.add(currentProfitCount).expandX();
			content.row();
		} else if(building instanceof Bar ) {
			Label people = new Label("Salespeople: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people.setFontScale(2f);
			Label peopleCount = new Label(String.format("%d / 20", ((Bar)building).salespeople.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount.setFontScale(2f);
			content.add(people).expandX();
			content.add(peopleCount).expandX();
			content.row();
			Label sallary = new Label("Sallary/Salesman: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallary.setFontScale(2f);
			Label sallaryCount = new Label(String.format("%d", ((Bar)building).sellerSalary), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryCount.setFontScale(2f);
			content.add(sallary).expandX();
			content.add(sallaryCount).expandX();
			content.row();
			Label sellers = new Label("Visits/Month: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellers.setFontScale(2f);
			Label sellersCount = new Label(String.format("%d", ((Bar)building).numberOfVisits), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellersCount.setFontScale(2f);
			content.add(sellers).expandX();
			content.add(sellersCount).expandX();
			content.row();
			Label uitilityBill = new Label("Service Bill: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBill.setFontScale(2f);
			Label uitilityBillCount = new Label(String.format("%d", ((Bar)building).serviceBill), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBillCount.setFontScale(2f);
			content.add(uitilityBill).expandX();
			content.add(uitilityBillCount).expandX();
			content.row();
			Label currentSProfit = new Label("Current Profit: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentSProfit.setFontScale(2f);
			Label currentSProfitCount = new Label(String.format("%d", ((Bar)building).currentProfit), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentSProfitCount.setFontScale(2f);
			content.add(currentSProfit).expandX();
			content.add(currentSProfitCount).expandX();
			content.row();
			Label currentProfit = new Label("Capital: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfit.setFontScale(2f);
			Label currentProfitCount = new Label(String.format("%d", ((Bar)building).capital), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			currentProfitCount.setFontScale(2f);
			content.add(currentProfit).expandX();
			content.add(currentProfitCount).expandX();
			content.row();
			Label taxes = new Label("Taxes: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxes.setFontScale(2f);
			Label taxesCount = new Label(String.format("%d", ((Bar)building).taxes), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			taxesCount.setFontScale(2f);
			content.add(taxes).expandX();
			content.add(taxesCount).expandX();
			content.row();
		} else if(building instanceof Bank ) {
			Label people = new Label("Bankers: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			people.setFontScale(2f);
			Label peopleCount = new Label(String.format("%d / 50", ((Bank)building).bankers.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			peopleCount.setFontScale(2f);
			content.add(people).expandX();
			content.add(peopleCount).expandX();
			content.row();
			Label sallary = new Label("Sallary/Bankers: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallary.setFontScale(2f);
			Label sallaryCount = new Label(String.format("%d", ((Bank)building).bankersSalary), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallaryCount.setFontScale(2f);
			content.add(sallary).expandX();
			content.add(sallaryCount).expandX();
			content.row();
			Label sellers = new Label("Clerks: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellers.setFontScale(2f);
			Label sellersCount = new Label(String.format("%d / 90", ((Bank)building).clerks.size()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sellersCount.setFontScale(2f);
			content.add(sellers).expandX();
			content.add(sellersCount).expandX();
			content.row();
			Label sallarySale = new Label("Sallary/Clerks: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallarySale.setFontScale(2f);
			Label sallarySaleCount = new Label(String.format("%d", ((Bank)building).clerckSalary), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			sallarySaleCount.setFontScale(2f);
			content.add(sallarySale).expandX();
			content.add(sallarySaleCount).expandX();
			content.row();
			Label uitilityBill = new Label("Service Bill: " , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBill.setFontScale(2f);
			Label uitilityBillCount = new Label(String.format("%d", ((Bank)building).serviceBill), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			uitilityBillCount.setFontScale(2f);
			content.add(uitilityBill).expandX();
			content.add(uitilityBillCount).expandX();
			content.row();
			
		} 
		
		//Label people = new Label("People:" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//Label peopleCount = new Label(String.format("%d", building.getPeopleSize()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//Image actor = new Image(new Texture("data/buildingInformationWindow.png"));
		//content.setFillParent(true);
		//content.debug();
		//content.setOrigin(screenX, screenY);
		buildingInfo.setTouchable(Touchable.enabled);
		stage.addActor(buildingInfo);
		//stage.addActor(actor);
		infoActor = stage.getActors().get(stage.getActors().size - 1);
		
	}

	public void renderShapes() {
		
	}
}
