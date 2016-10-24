package com.itproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class Assets {

	// Down below will be all resources, textures & stuff
	
	public static Texture items;
	public static Texture block;
	public static Texture grass;
	public static TextureRegion demoBlock;
	public static TextureRegion demoGrass;
	public static Texture house;
	public static TextureRegion demoHouse1;
	public static TextureRegion demoHouse2;
	public static TextureRegion demoHouse3;
	public static TextureRegion demoHouse4;
	public static TextureRegion demoHouse5;
	public static TextureRegion demoHouse6;
	
	
	public static TiledMap tiledMap;
	public static TiledMapRenderer tiledMapRenderer;
	
	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static void load() {
		// For now just a few examples
		block = loadTexture("data/block.png");
		demoBlock = new TextureRegion(block);
		
		grass = loadTexture("data/grass_demo1.png");
		demoGrass = new TextureRegion(grass);
		house = loadTexture("data/house_demo.png");
		demoHouse1 = new TextureRegion(house, 0, 0, 64, 64);
		demoHouse2 = new TextureRegion(house, 64, 0, 64, 64);
		demoHouse3 = new TextureRegion(house, 0, 64, 64, 64);
		demoHouse4 = new TextureRegion(house, 64, 64, 64, 64);
		demoHouse5 = new TextureRegion(house, 0, 128, 64, 64);
		demoHouse6 = new TextureRegion(house, 64, 128, 64, 64);
		
		tiledMap = new TmxMapLoader().load("data/test_map.tmx");
		tiledMapRenderer = new IsometricTiledMapRenderer(Assets.tiledMap);
		
	}	
}
