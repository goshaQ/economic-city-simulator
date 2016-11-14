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

	public static Texture policeStation;
	public static TextureRegion policeStationCell1;
	public static TextureRegion policeStationCell2;
	public static TextureRegion policeStationCell3;
	public static TextureRegion policeStationCell4;
	public static Texture policeStationRedactor;
	public static TextureRegion policeStationRedactorRegion;

	
	public static Texture hospital;
	public static TextureRegion hospitalCell1;
	public static TextureRegion hospitalCell2;
	public static TextureRegion hospitalCell3;
	public static TextureRegion hospitalCell4;
	public static TextureRegion hospitalCell5;
	public static TextureRegion hospitalCell6;
	public static Texture hospitalRedactor;
	public static TextureRegion hospitalRedactorRegion;

	public static Texture hospitalSelected;
	public static TextureRegion hospitalSelectedCell1;
	public static TextureRegion hospitalSelectedCell2;
	public static TextureRegion hospitalSelectedCell3;
	public static TextureRegion hospitalSelectedCell4;
	public static TextureRegion hospitalSelectedCell5;
	public static TextureRegion hospitalSelectedCell6;
	
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
	public static Texture powerStationRedactor;
	public static TextureRegion powerStationRedactorRegion;

	public static Texture waterStation;
	public static TextureRegion waterStationRegion;


	public static Texture cityHall;
	public static TextureRegion cityHallCell1;
	public static TextureRegion cityHallCell2;
	public static TextureRegion cityHallCell3;
	public static TextureRegion cityHallCell4;
	public static TextureRegion cityHallCell5;
	public static TextureRegion cityHallCell6;
	public static Texture cityHallRedactor;
	public static TextureRegion cityHallRedactorRegion;
	
	public static Texture fireStationRedactor;
	public static TextureRegion fireStationRedactorRegion;
	
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
		powerStationRedactor = loadTexture("data/powerStation_redactor.png");
		powerStationRedactorRegion = new TextureRegion(powerStationRedactor);


		waterStation = loadTexture("data/waterStation.png");
		waterStationRegion = new TextureRegion(waterStation);

		policeStationRedactor = loadTexture("data/policeStation_redactor.png");
		policeStationRedactorRegion = new TextureRegion(policeStationRedactor);

		policeStation = loadTexture("data/policeStation.png");
		policeStationCell1 = new TextureRegion(policeStation, 0, 0, 64, 96);
		policeStationCell2 = new TextureRegion(policeStation, 64, 0, 64, 96);
		policeStationCell3 = new TextureRegion(policeStation, 0, 96, 64, 96);
		policeStationCell4 = new TextureRegion(policeStation, 64, 96, 64, 96);
		
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
