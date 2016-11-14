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
	public static TextureRegion fireStationCell5;
	public static TextureRegion fireStationCell6;

	public static Texture fireStationSelected;
	public static TextureRegion selectedFireStationCell1;
	public static TextureRegion selectedFireStationCell2;
	public static TextureRegion selectedFireStationCell3;
	public static TextureRegion selectedFireStationCell4;
	public static TextureRegion selectedFireStationCell5;
	public static TextureRegion selectedFireStationCell6;
	
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
	
	public static Texture houseRedactor;
	public static TextureRegion demoHouseRedactor;
	
	public static Texture fireStationRedactor;
	public static TextureRegion fireStationRedactorRegion;
	
	public static Texture redactorMenu;
	public static TextureRegion demoRedactorMenu;
	
	public static TiledMap tiledMap;
	public static TiledMapRenderer tiledMapRenderer;
	
	public static Texture selectedBlock;
	public static TextureRegion selectedDemoBlock;
	
	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static void load() {
		// For now just a few examples
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
		
		houseRedactor = loadTexture("data/house_redactor.png");
		demoHouseRedactor = new TextureRegion(houseRedactor);
		
		/*house = loadTexture("data/house_demo.png");
		demoHouse1 = new TextureRegion(house, 0, 0, 64, 64);
		demoHouse2 = new TextureRegion(house, 64, 0, 64, 64);
		demoHouse3 = new TextureRegion(house, 0, 64, 64, 64);
		demoHouse4 = new TextureRegion(house, 64, 64, 64, 64);
		demoHouse5 = new TextureRegion(house, 0, 128, 64, 64);
		demoHouse6 = new TextureRegion(house, 64, 128, 64, 64);
		*/
		
		fireStationRedactor = loadTexture("data/fireStation_redactor.png");
		fireStationRedactorRegion = new TextureRegion(fireStationRedactor);
		
		fireStation = loadTexture("data/fireStation.png");
		fireStationCell1 = new TextureRegion(fireStation, 0, 0, 64, 64);
		fireStationCell2 = new TextureRegion(fireStation, 64, 0, 64, 64);
		fireStationCell3 = new TextureRegion(fireStation, 0, 64, 64, 64);
		fireStationCell4 = new TextureRegion(fireStation, 64, 64, 64, 64);
		fireStationCell5 = new TextureRegion(fireStation, 0, 128, 64, 64);
		fireStationCell6 = new TextureRegion(fireStation, 64, 128, 64, 64);

		fireStationSelected = loadTexture("data/fireStationSelected.png");
		selectedFireStationCell1 = new TextureRegion(fireStationSelected, 0, 0, 64, 64);
		selectedFireStationCell2 = new TextureRegion(fireStationSelected, 64, 0, 64, 64);
		selectedFireStationCell3 = new TextureRegion(fireStationSelected, 0, 64, 64, 64);
		selectedFireStationCell4 = new TextureRegion(fireStationSelected, 64, 64, 64, 64);
		selectedFireStationCell5 = new TextureRegion(fireStationSelected, 0, 128, 64, 64);
		selectedFireStationCell6 = new TextureRegion(fireStationSelected, 64, 128, 64, 64);
		
		hospital= loadTexture("data/hospital.png");
		hospitalCell1 = new TextureRegion(hospital, 0, 0, 64, 96);
		hospitalCell2 = new TextureRegion(hospital, 64, 0, 64, 96);
		hospitalCell3 = new TextureRegion(hospital, 0, 96, 64, 96);
		hospitalCell4 = new TextureRegion(hospital, 64, 96, 64, 96);
		hospitalCell5 = new TextureRegion(hospital, 0, 192, 64, 96);
		hospitalCell6 = new TextureRegion(hospital, 64, 192, 64, 96);
		
		hospitalSelected = loadTexture("data/hospitalSelected.png");
		hospitalSelectedCell1 = new TextureRegion(hospital, 0, 0, 64, 96);
		hospitalSelectedCell2 = new TextureRegion(hospital, 64, 0, 64, 96);
		hospitalSelectedCell3 = new TextureRegion(hospital, 0, 96, 64, 96);
		hospitalSelectedCell4 = new TextureRegion(hospital, 64, 96, 64, 96);
		hospitalSelectedCell5 = new TextureRegion(hospital, 0, 192, 64, 96);
		hospitalSelectedCell6 = new TextureRegion(hospital, 64, 192, 64, 96);
		
		
		
		tiledMap = new TmxMapLoader().load("data/test_map.tmx");
		tiledMapRenderer = new IsometricTiledMapRenderer(Assets.tiledMap);
		
		
		
	}
}
