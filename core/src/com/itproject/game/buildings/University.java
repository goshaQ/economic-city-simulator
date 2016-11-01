package com.itproject.game.buildings;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Polygon;
import com.itproject.game.Assets;
import com.itproject.game.Citizen;

public class University extends Building {

	public static final int TILE_HEIGHT = 32;
	public static final int TILE_WIDTH = 64;	
	public static final int UNIVERSITY_OK = 0;
	public static final int UNIVERSITY_ON_FIRE = 1;
	public static final int UNIVERSITY_SELECTED = 2;
	public static final int UNIVERSITY_UNSELECTED = 3;
	public static final int UNIVERSITY_DESTROYED = 4;
	
	TiledMapTileLayer.Cell[] cell;
	int state;
	private int col, row;
	private Polygon shape;
	List<Citizen> students;
	List<Citizen> teachers;
	TiledMapTileLayer layer;
	  
	public University(int row, int col) {
		super(10000, 500);
		
		state = 0;
		
		this.col = col;
		this.row = row;
		cell = new TiledMapTileLayer.Cell[6];
		students = new ArrayList<Citizen>(10); // default 10 firefighters at start
		teachers = new ArrayList<Citizen>(10);
		layer = (TiledMapTileLayer)Assets.tiledMap.getLayers().get(0);
	}
	
	public void update() {
		updateSelected();
	}
	
	public void updateSelected() {
		/*if(state == UNIVERSITY_SELECTED) {
			cell[0] = layer.getCell(row, col);
			cell[1] = layer.getCell(row + 1, col);
			cell[2] = layer.getCell(row, col + 1);
			cell[3] = layer.getCell(row + 1, col + 1);
			cell[4] = layer.getCell(row, col + 2);
			cell[5] = layer.getCell(row + 1, col + 2);
			
			cell[0].setTile(new StaticTiledMapTile(Assets.selectedFireStationCell5));
			cell[1].setTile(new StaticTiledMapTile(Assets.selectedFireStationCell6));
			cell[2].setTile(new StaticTiledMapTile(Assets.selectedFireStationCell3));
			cell[3].setTile(new StaticTiledMapTile(Assets.selectedFireStationCell4));
			cell[4].setTile(new StaticTiledMapTile(Assets.selectedFireStationCell1));
			cell[5].setTile(new StaticTiledMapTile(Assets.selectedFireStationCell2));
		} else if(state == UNIVERSITY_UNSELECTED) {
	
			cell[0].setTile(new StaticTiledMapTile(Assets.fireStationCell5));
			cell[1].setTile(new StaticTiledMapTile(Assets.fireStationCell6));
			cell[2].setTile(new StaticTiledMapTile(Assets.fireStationCell3));
			cell[3].setTile(new StaticTiledMapTile(Assets.fireStationCell4));
			cell[4].setTile(new StaticTiledMapTile(Assets.fireStationCell1));
			cell[5].setTile(new StaticTiledMapTile(Assets.fireStationCell2));
			
			state = UNIVERSITY_OK;
		}*/
	}
	
	public void createShape(int row, int col) {
		this.col = col; 
		this.row = row;
		int screenx = (col + row + 1) * TILE_WIDTH / 2 - 32;
	    int screeny = (col - row + 1) * TILE_HEIGHT / 2;
	    float[] vertices = new float[12];
	    vertices[0] = screenx;   vertices[1] = screeny;
	    vertices[2] = screenx + 64; vertices[3] = screeny - 32;
	    vertices[4] = screenx + 128; vertices[5] = screeny;
	    vertices[6] = screenx + 128; vertices[7] = screeny + 32;
	    vertices[8] = screenx + 64; vertices[9] = screeny - 32 + 128;
	    vertices[10] = screenx; vertices[11] = screeny + 64;
		shape = new Polygon(vertices);
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public Polygon getShape() {
		return shape;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public void showInfo() {
		// to implement
		System.out.println("It is a Power Station!!");
	}

	@Override
	public void createCollisionShape(int row, int col) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Polygon getCollisionShape() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}