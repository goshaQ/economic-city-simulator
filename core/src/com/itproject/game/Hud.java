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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.itproject.game.BuildingRedactorWindow.PowerStationRedactor;
import com.itproject.game.buildings.Building;


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
			budget = new Label("Budget", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			budgetValue = new Label(String.format("$%03d", City.budget.amountMoney) ,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			population = new Label("Population", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			populationValue = new Label(String.format("%03d", City.citizens.size()) ,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
			//setFillParent(true);
			debug();
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
		redactorMenuContent.add(new Label(String.format("$%d", 100000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
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
		redactorMenuContent.add(new Label(String.format("$%d", 150000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
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
		redactorMenuContent.add(new Label(String.format("$%d", 150000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
		redactorMenuContent.add(new Label(String.format("$%d", 300000), new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
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
		Table statisticsPlotFunctionContainer = new Table();
		statisticsPlotFunctionContainer.debug();
		
		statisticsMenu.setFillParent(true);
		statisticsMenu.add(statisticsMenuContainer).width(800f).height(400f);
		statisticsMenuContainer.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("data/buildingInformationWindow.png"))));
		statisticsMenuContainer.add(statisticsPlotFunctionContainer).width(780f).height(380).center();
		
	
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
		hudTop.setPosition(0, Gdx.graphics.getHeight() - 80);
		stage.addActor(hudTop);
		stage.addActor(hudTopContent);
		stage.addActor(budgetHudTop);
		stage.addActor(bottomHud);
		
	}
	
	public static void setInformationScreen(Building building, float screenX, float screenY) {
		Table buildingInfo = new Table();
		buildingInfo.setFillParent(true);
		buildingInfo.right().bottom();
		//buildingInfo.debug();
		
		Label people = new Label("People:" , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//Label peopleCount = new Label(String.format("%d", building.getPeopleSize()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		//Image actor = new Image(new Texture("data/buildingInformationWindow.png"));
		Table content = new Table();
		buildingInfo.add(content).width(420f).height(400f);
		//content.setFillParent(true);
		content.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("data/buildingInformationWindow.png"))));
		content.add().expandX();
		content.add().expandX();
		content.row();
		content.add(people).expandX();
		//content.add(peopleCount).expandX();
		content.debug();
		content.setOrigin(screenX, screenY);
		buildingInfo.setTouchable(Touchable.enabled);
		stage.addActor(buildingInfo);
		//stage.addActor(actor);
		infoActor = stage.getActors().get(stage.getActors().size - 1);
		
	}
	
	public void renderShapes() {
		shape.begin(ShapeType.Point);
		shape.setColor(1, 1, 1, 1);
		//shape.
		for(int x = 0; x < 100; x++)
		shape.point(x, City.citizens.size(), 0);
		
		shape.end();
	}
}
