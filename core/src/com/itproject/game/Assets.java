package com.itproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class Assets {

	// Down below will be all resources, textures & stuff
	
	public static Texture items;
	public static Texture block;
	public static Texture greenBlock;
	public static Texture grass;
	public static TextureRegion demoBlock;
	public static TextureRegion greenDemoBlock;
	public static TextureRegion demoGrass;
	
	// FIRESTATION
	public static Texture fireStation;
	public static TextureRegion fireStationCell1;
	public static TextureRegion fireStationCell2;
	public static TextureRegion fireStationCell3;
	public static TextureRegion fireStationCell4;
	public static Texture fireStationSelected;
	public static TextureRegion selectedFireStationCell1;
	public static TextureRegion selectedFireStationCell2;
	public static TextureRegion selectedFireStationCell3;
	public static TextureRegion selectedFireStationCell4;
	public static Texture fireStationGreen;
	public static TextureRegion fireStationGreenCell1;
	public static TextureRegion fireStationGreenCell2;
	public static TextureRegion fireStationGreenCell3;
	public static TextureRegion fireStationGreenCell4;
	public static Texture fireStationRedactor;
	public static TextureRegion fireStationRedactorRegion;
	// POLICESTATION
	public static Texture policeStation;
	public static TextureRegion policeStationCell1;
	public static TextureRegion policeStationCell2;
	public static TextureRegion policeStationCell3;
	public static TextureRegion policeStationCell4;
	public static Texture policeStationSelected;
	public static TextureRegion policeStationSelectedCell1;
	public static TextureRegion policeStationSelectedCell2;
	public static TextureRegion policeStationSelectedCell3;
	public static TextureRegion policeStationSelectedCell4;
	public static Texture policeStationGreen;
	public static TextureRegion policeStationGreenCell1;
	public static TextureRegion policeStationGreenCell2;
	public static TextureRegion policeStationGreenCell3;
	public static TextureRegion policeStationGreenCell4;
	public static Texture policeStationRedactor;
	public static TextureRegion policeStationRedactorRegion;
	// HOSPITAL
	public static Texture hospital;
	public static TextureRegion hospitalCell1;
	public static TextureRegion hospitalCell2;
	public static TextureRegion hospitalCell3;
	public static TextureRegion hospitalCell4;
	public static TextureRegion hospitalCell5;
	public static TextureRegion hospitalCell6;
	public static Texture hospitalSelected;
	public static TextureRegion hospitalSelectedCell1;
	public static TextureRegion hospitalSelectedCell2;
	public static TextureRegion hospitalSelectedCell3;
	public static TextureRegion hospitalSelectedCell4;
	public static TextureRegion hospitalSelectedCell5;
	public static TextureRegion hospitalSelectedCell6;
	public static Texture hospitalRedactor;
	public static TextureRegion hospitalRedactorRegion;
	// BANK
	public static Texture bank;
	public static TextureRegion bankCell1;
	public static TextureRegion bankCell2;
	public static TextureRegion bankCell3;
	public static TextureRegion bankCell4;
	public static Texture bankSelected;
	public static TextureRegion bankSelectedCell1;
	public static TextureRegion bankSelectedCell2;
	public static TextureRegion bankSelectedCell3;
	public static TextureRegion bankSelectedCell4;
	public static Texture bankGreen;
	public static TextureRegion bankGreenCell1;
	public static TextureRegion bankGreenCell2;
	public static TextureRegion bankGreenCell3;
	public static TextureRegion bankGreenCell4;
	public static Texture bankRedactor;
	public static TextureRegion bankRedactorRegion;
	// GROCERY SHOP
	public static Texture groceryShop;
	public static TextureRegion groceryShopCell1;
	public static TextureRegion groceryShopCell2;
	public static Texture groceryShopSelected;
	public static TextureRegion groceryShopSelectedCell1;
	public static TextureRegion groceryShopSelectedCell2;
	public static Texture groceryShopGreen;
	public static TextureRegion groceryShopGreenCell1;
	public static TextureRegion groceryShopGreenCell2;
	public static Texture groceryShopRedactor;
	public static TextureRegion groceryShopRedactorRegion;
	// BAR
	public static Texture bar;
	public static TextureRegion barCell1;
	public static TextureRegion barCell2;
	public static TextureRegion barCell3;
	public static TextureRegion barCell4;
	public static Texture barSelected;
	public static TextureRegion barSelectedCell1;
	public static TextureRegion barSelectedCell2;
	public static TextureRegion barSelectedCell3;
	public static TextureRegion barSelectedCell4;
	public static Texture barGreen;
	public static TextureRegion barGreenCell1;
	public static TextureRegion barGreenCell2;
	public static TextureRegion barGreenCell3;
	public static TextureRegion barGreenCell4;
	public static Texture barRedactor;
	public static TextureRegion barRedactorRegion;
	// POWER STATION
	public static Texture powerStation;
	public static TextureRegion powerStationCell1;
	public static TextureRegion powerStationCell2;
	public static TextureRegion powerStationCell3;
	public static TextureRegion powerStationCell4;
	public static TextureRegion powerStationCell5;
	public static TextureRegion powerStationCell6;
	public static TextureRegion powerStationCell7;
	public static TextureRegion powerStationCell8;
	public static TextureRegion powerStationCell9;
	public static Texture powerStationSelected;
	public static TextureRegion powerStationSelectedCell1;
	public static TextureRegion powerStationSelectedCell2;
	public static TextureRegion powerStationSelectedCell3;
	public static TextureRegion powerStationSelectedCell4;
	public static TextureRegion powerStationSelectedCell5;
	public static TextureRegion powerStationSelectedCell6;
	public static TextureRegion powerStationSelectedCell7;
	public static TextureRegion powerStationSelectedCell8;
	public static TextureRegion powerStationSelectedCell9;
	public static Texture powerStationGreen;
	public static TextureRegion powerStationGreenCell1;
	public static TextureRegion powerStationGreenCell2;
	public static TextureRegion powerStationGreenCell3;
	public static TextureRegion powerStationGreenCell4;
	public static TextureRegion powerStationGreenCell5;
	public static TextureRegion powerStationGreenCell6;
	public static TextureRegion powerStationGreenCell7;
	public static TextureRegion powerStationGreenCell8;
	public static TextureRegion powerStationGreenCell9;
	public static Texture powerStationRedactor;
	public static TextureRegion powerStationRedactorRegion;
	// WATER STATION
	public static Texture waterStation;
	public static TextureRegion waterStationRegion;
	public static Texture waterStationSelected;
	public static TextureRegion waterStationSelectedCell;
	public static Texture waterStationGreen;
	public static TextureRegion waterStationGreenCell;

	public static Texture cityHall;
	public static TextureRegion cityHallCell1;
	public static TextureRegion cityHallCell2;
	public static TextureRegion cityHallCell3;
	public static TextureRegion cityHallCell4;
	public static TextureRegion cityHallCell5;
	public static TextureRegion cityHallCell6;
	public static Texture cityHallRedactor;
	public static TextureRegion cityHallRedactorRegion;
	

	public static Texture redactorMenu;
	public static TextureRegion demoRedactorMenu;
	
	public static TiledMap tiledMap;
	public static TiledMapRenderer tiledMapRenderer;
	
	public static Texture selectedBlock;
	public static TextureRegion selectedDemoBlock;
	
	public static TextureRegion mainMenuPlayButton;

	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}

	static StaticTiledMapTile[] greenTiles;
	static StaticTiledMapTile[] bankGreenTiles;
	static StaticTiledMapTile[] groceryShopGreenTiles;
	static StaticTiledMapTile[] barGreenTiles;
	static StaticTiledMapTile[] powerStationGreenTiles;
	static StaticTiledMapTile[] policeStationGreenTiles;
	
	
	public static void load() {
		// For now just a few examples
		mainMenuPlayButton = new TextureRegion(new Texture("data/mainMenuPlayButton_demo.png"));


		tiledMap = new TmxMapLoader().load("data/test_map.tmx");
		tiledMapRenderer = new IsometricTiledMapRenderer(Assets.tiledMap);


		block = loadTexture("data/block.png");
		demoBlock = new TextureRegion(block);
		
		selectedBlock = loadTexture("data/selected_block.png");
		selectedDemoBlock = new TextureRegion(selectedBlock);
		
		greenBlock = loadTexture("data/build_block_green.png");
		greenDemoBlock = new TextureRegion(greenBlock);
		
		grass = loadTexture("data/grass_demo1.png");
		demoGrass = new TextureRegion(grass);
		
		redactorMenu = loadTexture("data/redactorMenu_demo.png");
		demoRedactorMenu = new TextureRegion(redactorMenu);
		
		cityHallRedactor = loadTexture("data/cityHall_redactor.png");
		cityHallRedactorRegion = new TextureRegion(cityHallRedactor);
		
		cityHall = loadTexture("data/house_demo.png");
		cityHallCell1 = new TextureRegion(cityHall, 0, 0, 64, 64);
		cityHallCell2 = new TextureRegion(cityHall, 64, 0, 64, 64);
		cityHallCell3 = new TextureRegion(cityHall, 0, 64, 64, 64);
		cityHallCell4 = new TextureRegion(cityHall, 64, 64, 64, 64);
		cityHallCell5 = new TextureRegion(cityHall, 0, 128, 64, 64);
		cityHallCell6 = new TextureRegion(cityHall, 64, 128, 64, 64);


		bank = loadTexture("data/bank.png");
		bankCell1 = new TextureRegion(bank,0 ,0, 64, 96);
		bankCell2 = new TextureRegion(bank,64, 0, 64, 96);
		bankCell3 = new TextureRegion(bank,0, 96, 64, 96);
		bankCell4 = new TextureRegion(bank,64, 96, 64, 96);
		bankSelected = loadTexture("data/bankSelected.png");
		bankSelectedCell1 = new TextureRegion(bankSelected,0 ,0, 64, 96);
		bankSelectedCell2 = new TextureRegion(bankSelected,64, 0, 64, 96);
		bankSelectedCell3 = new TextureRegion(bankSelected,0, 96, 64, 96);
		bankSelectedCell4 = new TextureRegion(bankSelected,64, 96, 64, 96);
		bankGreen = loadTexture("data/bankGreen.png");
		bankGreenCell1 = new TextureRegion(bankGreen,0 ,0, 64, 96);
		bankGreenCell2 = new TextureRegion(bankGreen,64, 0, 64, 96);
		bankGreenCell3 = new TextureRegion(bankGreen,0, 96, 64, 96);
		bankGreenCell4 = new TextureRegion(bankGreen,64, 96, 64, 96);
		bankGreenTiles = new StaticTiledMapTile[4];
		bankGreenTiles[0] = new StaticTiledMapTile(Assets.bankGreenCell3);
		bankGreenTiles[1] = new StaticTiledMapTile(Assets.bankGreenCell4);
		bankGreenTiles[2] = new StaticTiledMapTile(Assets.bankGreenCell1);
		bankGreenTiles[3] = new StaticTiledMapTile(Assets.bankGreenCell2);
		bankRedactor = loadTexture("data/bank_redactor.png");
		bankRedactorRegion = new TextureRegion(bankRedactor);

		
		bar = loadTexture("data/bar.png");
		barCell1 = new TextureRegion(bar,0 ,0, 64, 96);
		barCell2 = new TextureRegion(bar,64, 0, 64, 96);
		barCell3 = new TextureRegion(bar,0, 96, 64, 96);
		barCell4 = new TextureRegion(bar,64, 96, 64, 96);
		barSelected = loadTexture("data/barSelected.png");
		barSelectedCell1 = new TextureRegion(barSelected,0 ,0, 64, 96);
		barSelectedCell2 = new TextureRegion(barSelected,64, 0, 64, 96);
		barSelectedCell3 = new TextureRegion(barSelected,0, 96, 64, 96);
		barSelectedCell4 = new TextureRegion(barSelected,64, 96, 64, 96);
		barGreen = loadTexture("data/barGreen.png");
		barGreenCell1 = new TextureRegion(barGreen,0 ,0, 64, 96);
		barGreenCell2 = new TextureRegion(barGreen,64, 0, 64, 96);
		barGreenCell3 = new TextureRegion(barGreen,0, 96, 64, 96);
		barGreenCell4 = new TextureRegion(barGreen,64, 96, 64, 96);
		barGreenTiles = new StaticTiledMapTile[4];
		barGreenTiles[0] = new StaticTiledMapTile(Assets.barGreenCell3);
		barGreenTiles[1] = new StaticTiledMapTile(Assets.barGreenCell4);
		barGreenTiles[2] = new StaticTiledMapTile(Assets.barGreenCell1);
		barGreenTiles[3] = new StaticTiledMapTile(Assets.barGreenCell2);
		barRedactor = loadTexture("data/barRedactor.png");
		barRedactorRegion = new TextureRegion(barRedactor);
		
		groceryShop = loadTexture("data/groceryShop.png");
		groceryShopCell2 = new TextureRegion(groceryShop,0 ,0, 64, 96);
		groceryShopCell1 = new TextureRegion(groceryShop,0, 96	, 64, 96);
		groceryShopSelected = loadTexture("data/groceryShopSelected.png");
		groceryShopSelectedCell2 = new TextureRegion(groceryShopSelected,0 ,0, 64, 96);
		groceryShopSelectedCell1 = new TextureRegion(groceryShopSelected,0, 96, 64, 96);
		groceryShopGreen = loadTexture("data/groceryShopGreen.png");
		groceryShopGreenCell2 = new TextureRegion(groceryShopGreen,0 ,0, 64, 96);
		groceryShopGreenCell1 = new TextureRegion(groceryShopGreen,0, 96, 64, 96);
		groceryShopGreenTiles = new StaticTiledMapTile[2];
		groceryShopGreenTiles[0] = new StaticTiledMapTile(Assets.groceryShopGreenCell1);
		groceryShopGreenTiles[1] = new StaticTiledMapTile(Assets.groceryShopGreenCell2);
		groceryShopRedactor = loadTexture("data/groceryShopRedactor.png");
		groceryShopRedactorRegion = new TextureRegion(groceryShopRedactor);
		

		powerStation = loadTexture("data/powerStation.png");
		powerStationCell1 = new TextureRegion(powerStation, 0, 0, 64, 96);
		powerStationCell2 = new TextureRegion(powerStation, 64, 0, 64, 96);
		powerStationCell3 = new TextureRegion(powerStation, 128, 0, 64, 96);
		powerStationCell4 = new TextureRegion(powerStation, 0, 96, 64, 96);
		powerStationCell5 = new TextureRegion(powerStation, 64, 96, 64, 96);
		powerStationCell6 = new TextureRegion(powerStation, 128, 96, 64, 96);
		powerStationCell7 = new TextureRegion(powerStation, 0, 192, 64, 96);
		powerStationCell8 = new TextureRegion(powerStation, 64, 192, 64, 96);
		powerStationCell9 = new TextureRegion(powerStation, 128, 192, 64, 96);
		powerStationSelected = loadTexture("data/powerStationSelected.png");
		powerStationSelectedCell1 = new TextureRegion(powerStationSelected, 0, 0, 64, 96);
		powerStationSelectedCell2 = new TextureRegion(powerStationSelected, 64, 0, 64, 96);
		powerStationSelectedCell3 = new TextureRegion(powerStationSelected, 128, 0, 64, 96);
		powerStationSelectedCell4 = new TextureRegion(powerStationSelected, 0, 96, 64, 96);
		powerStationSelectedCell5 = new TextureRegion(powerStationSelected, 64, 96, 64, 96);
		powerStationSelectedCell6 = new TextureRegion(powerStationSelected, 128, 96, 64, 96);
		powerStationSelectedCell7 = new TextureRegion(powerStationSelected, 0, 192, 64, 96);
		powerStationSelectedCell8 = new TextureRegion(powerStationSelected, 64, 192, 64, 96);
		powerStationSelectedCell9 = new TextureRegion(powerStationSelected, 128, 192, 64, 96);
		powerStationGreen = loadTexture("data/powerStationGreen.png");
		powerStationGreenCell1 = new TextureRegion(powerStationGreen, 0, 0, 64, 96);
		powerStationGreenCell2 = new TextureRegion(powerStationGreen, 64, 0, 64, 96);
		powerStationGreenCell3 = new TextureRegion(powerStationGreen, 128, 0, 64, 96);
		powerStationGreenCell4 = new TextureRegion(powerStationGreen, 0, 96, 64, 96);
		powerStationGreenCell5 = new TextureRegion(powerStationGreen, 64, 96, 64, 96);
		powerStationGreenCell6 = new TextureRegion(powerStationGreen, 128, 96, 64, 96);
		powerStationGreenCell7 = new TextureRegion(powerStationGreen, 0, 192, 64, 96);
		powerStationGreenCell8 = new TextureRegion(powerStationGreen, 64, 192, 64, 96);
		powerStationGreenCell9 = new TextureRegion(powerStationGreen, 128, 192, 64, 96);
		powerStationRedactor = loadTexture("data/powerStationRedactor.png");
		powerStationRedactorRegion = new TextureRegion(powerStationRedactor);
		powerStationGreenTiles = new StaticTiledMapTile[9];
		powerStationGreenTiles[0] = new StaticTiledMapTile(Assets.powerStationGreenCell7);
		powerStationGreenTiles[1] = new StaticTiledMapTile(Assets.powerStationGreenCell8);
		powerStationGreenTiles[2] = new StaticTiledMapTile(Assets.powerStationGreenCell9);
		powerStationGreenTiles[3] = new StaticTiledMapTile(Assets.powerStationGreenCell4);
		powerStationGreenTiles[4] = new StaticTiledMapTile(Assets.powerStationGreenCell5);
		powerStationGreenTiles[5] = new StaticTiledMapTile(Assets.powerStationGreenCell6);
		powerStationGreenTiles[6] = new StaticTiledMapTile(Assets.powerStationGreenCell1);
		powerStationGreenTiles[7] = new StaticTiledMapTile(Assets.powerStationGreenCell2);
		powerStationGreenTiles[8] = new StaticTiledMapTile(Assets.powerStationGreenCell3);

		
		waterStation = loadTexture("data/waterStation.png");
		waterStationRegion = new TextureRegion(waterStation);

		policeStationRedactor = loadTexture("data/policeStationRedactor.png");
		policeStationRedactorRegion = new TextureRegion(policeStationRedactor);
		policeStation = loadTexture("data/policeStation.png");
		policeStationCell1 = new TextureRegion(policeStation, 0, 0, 64, 96);
		policeStationCell2 = new TextureRegion(policeStation, 64, 0, 64, 96);
		policeStationCell3 = new TextureRegion(policeStation, 0, 96, 64, 96);
		policeStationCell4 = new TextureRegion(policeStation, 64, 96, 64, 96);
		policeStationSelected = loadTexture("data/policeStationSelected.png");
		policeStationSelectedCell1 = new TextureRegion(policeStationSelected, 0, 0, 64, 96);
		policeStationSelectedCell2 = new TextureRegion(policeStationSelected, 64, 0, 64, 96);
		policeStationSelectedCell3 = new TextureRegion(policeStationSelected, 0, 96, 64, 96);
		policeStationSelectedCell4 = new TextureRegion(policeStationSelected, 64, 96, 64, 96);
		policeStationGreen = loadTexture("data/policeStationGreen.png");
		policeStationGreenCell1 = new TextureRegion(policeStationGreen, 0, 0, 64, 96);
		policeStationGreenCell2 = new TextureRegion(policeStationGreen, 64, 0, 64, 96);
		policeStationGreenCell3 = new TextureRegion(policeStationGreen, 0, 96, 64, 96);
		policeStationGreenCell4 = new TextureRegion(policeStationGreen, 64, 96, 64, 96);
		policeStationGreenTiles = new StaticTiledMapTile[4];
		policeStationGreenTiles[0] = new StaticTiledMapTile(policeStationGreenCell3);
		policeStationGreenTiles[1] = new StaticTiledMapTile(policeStationGreenCell4);
		policeStationGreenTiles[2] = new StaticTiledMapTile(policeStationGreenCell1);
		policeStationGreenTiles[3] = new StaticTiledMapTile(policeStationGreenCell2);
		
		fireStationRedactor = loadTexture("data/fireStation_redactor.png");
		fireStationRedactorRegion = new TextureRegion(fireStationRedactor);
		fireStation = loadTexture("data/fireStation.png");
		fireStationCell1 = new TextureRegion(fireStation, 0, 0, 64, 96);
		fireStationCell2 = new TextureRegion(fireStation, 64, 0, 64, 96);
		fireStationCell3 = new TextureRegion(fireStation, 0, 96, 64, 96);
		fireStationCell4 = new TextureRegion(fireStation, 64, 96, 64, 96);
		fireStationSelected = loadTexture("data/fireStationSelected.png");
		selectedFireStationCell1 = new TextureRegion(fireStationSelected, 0, 0, 64, 96);
		selectedFireStationCell2 = new TextureRegion(fireStationSelected, 64, 0, 64, 96);
		selectedFireStationCell3 = new TextureRegion(fireStationSelected, 0, 96, 64, 96);
		selectedFireStationCell4 = new TextureRegion(fireStationSelected, 64, 96, 64, 96);
		fireStationGreen = loadTexture("data/fireStationGreen.png");
		fireStationGreenCell1 = new TextureRegion(fireStationGreen, 0, 0, 64, 96);
		fireStationGreenCell2 = new TextureRegion(fireStationGreen, 64, 0, 64, 96);
		fireStationGreenCell3 = new TextureRegion(fireStationGreen, 0, 96, 64, 96);
		fireStationGreenCell4 = new TextureRegion(fireStationGreen, 64, 96, 64, 96);

		greenTiles = new StaticTiledMapTile[4];
		greenTiles[0] = new StaticTiledMapTile(Assets.fireStationGreenCell3);
		greenTiles[1] = new StaticTiledMapTile(Assets.fireStationGreenCell4);
		greenTiles[2] = new StaticTiledMapTile(Assets.fireStationGreenCell1);
		greenTiles[3] = new StaticTiledMapTile(Assets.fireStationGreenCell2);

		
		hospital= loadTexture("data/hospital.png");
		hospitalCell1 = new TextureRegion(hospital, 0, 0, 64, 96);
		hospitalCell2 = new TextureRegion(hospital, 64, 0, 64, 96);
		hospitalCell3 = new TextureRegion(hospital, 0, 96, 64, 96);
		hospitalCell4 = new TextureRegion(hospital, 64, 96, 64, 96);
		hospitalCell5 = new TextureRegion(hospital, 0, 192, 64, 96);
		hospitalCell6 = new TextureRegion(hospital, 64, 192, 64, 96);
		hospitalRedactor = loadTexture("data/hospital_redactor.png");
		hospitalRedactorRegion = new TextureRegion(hospitalRedactor);

		hospitalSelected = loadTexture("data/hospitalSelected.png");
		hospitalSelectedCell1 = new TextureRegion(hospital, 0, 0, 64, 96);
		hospitalSelectedCell2 = new TextureRegion(hospital, 64, 0, 64, 96);
		hospitalSelectedCell3 = new TextureRegion(hospital, 0, 96, 64, 96);
		hospitalSelectedCell4 = new TextureRegion(hospital, 64, 96, 64, 96);
		hospitalSelectedCell5 = new TextureRegion(hospital, 0, 192, 64, 96);
		hospitalSelectedCell6 = new TextureRegion(hospital, 64, 192, 64, 96);
		
		

		
	}

	public StaticTiledMapTile[] getGreenTiles() {
		return greenTiles;
	}
}
