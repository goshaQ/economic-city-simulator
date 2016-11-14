package com.itproject.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class BuildingRedactorWindow extends Group {

	
	
	public class FireStationRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/fireStation_redactor.png"));
	
		boolean started = false;
		public FireStationRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((FireStationRedactor)event.getTarget()).started = true;
					return true;
				}
			
			});
		}
		
		@Override 
		public void draw(Batch batch, float parentAlpha) {
			 batch.setColor(1, 1, 1, parentAlpha);
			batch.draw(texture1, getX(), getY());
		}
		
		@Override
		public void act(float delta) {
			if(started) {
				GameScreen.state = GameScreen.GAME_REDACTOR_MODE;
				GameScreen.redactorState = GameScreen.BUILD_FIRESTATION;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	
	public class WaterStationRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/waterStation.png"));
	
		boolean started = false;
		public WaterStationRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((WaterStationRedactor)event.getTarget()).started = true;
					return true;
				}
			});
		}
		
		@Override 
		public void draw(Batch batch, float parentAlpha) {
			 batch.setColor(1, 1, 1, parentAlpha);
			batch.draw(texture1, getX(), getY());
		}
		
		@Override
		public void act(float delta) {
			if(started) {
				GameScreen.state = GameScreen.GAME_REDACTOR_MODE;
				GameScreen.redactorState = GameScreen.BUILD_WATERSTATION;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	
	public class PowerStationRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/powerStation_redactor.png"));
	
		boolean started = false;
		public PowerStationRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((PowerStationRedactor)event.getTarget()).started = true;
					return true;
				}
			});
		}
		
		@Override 
		public void draw(Batch batch, float parentAlpha) {
			 batch.setColor(1, 1, 1, parentAlpha);
			batch.draw(texture1, getX(), getY());
		}
		
		@Override
		public void act(float delta) {
			if(started) {
				GameScreen.state = GameScreen.GAME_REDACTOR_MODE;
				GameScreen.redactorState = GameScreen.BUILD_POWERSTATION;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	
	public class PoliceStationRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/policeStation_redactor.png"));
	
		boolean started = false;
		public PoliceStationRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((PoliceStationRedactor)event.getTarget()).started = true;
					return true;
				}
			});
		}
		
		@Override 
		public void draw(Batch batch, float parentAlpha) {
			 batch.setColor(1, 1, 1, parentAlpha);
			batch.draw(texture1, getX(), getY());
		}
		
		@Override
		public void act(float delta) {
			if(started) {
				GameScreen.state = GameScreen.GAME_REDACTOR_MODE;
				GameScreen.redactorState = GameScreen.BUILD_POLICESTATION;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	
	public class BankRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/bank_redactor.png"));
	
		boolean started = false;
		public BankRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((BankRedactor)event.getTarget()).started = true;
					
					return true;
				}
			});
		}
		
		@Override 
		public void draw(Batch batch, float parentAlpha) {
			 batch.setColor(1, 1, 1, parentAlpha);
			batch.draw(texture1, getX(), getY());
		}
		
		@Override
		public void act(float delta) {
			if(started) {
				GameScreen.state = GameScreen.GAME_REDACTOR_MODE;
				GameScreen.redactorState = GameScreen.BUILD_BANK;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	
	public class HospitalRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/hospital_redactor.png"));
	
		boolean started = false;
		public HospitalRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((HospitalRedactor)event.getTarget()).started = true;
					return true;
				}
			});
		}
		
		@Override 
		public void draw(Batch batch, float parentAlpha) {
			 batch.setColor(1, 1, 1, parentAlpha);
			batch.draw(texture1, getX(), getY());
		}
		
		@Override
		public void act(float delta) {
			if(started) {
				GameScreen.state = GameScreen.GAME_REDACTOR_MODE;
				GameScreen.redactorState = GameScreen.BUILD_HOSPITAL;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	
	public class CityHallRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/cityHall_redactor.png"));
	
		boolean started = false;
		public CityHallRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((CityHallRedactor)event.getTarget()).started = true;
					return true;
				}
			});
		}
		
		@Override 
		public void draw(Batch batch, float parentAlpha) {
			 batch.setColor(1, 1, 1, parentAlpha);
			batch.draw(texture1, getX(), getY());
		}
		
		@Override
		public void act(float delta) {
			if(started) {
				GameScreen.state = GameScreen.GAME_REDACTOR_MODE;
				GameScreen.redactorState = GameScreen.BUILD_CITYHALL;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	
	
	public class HouseRedactor extends Actor {
		TextureRegion texture2 = new TextureRegion(new Texture("data/block.png"));
		boolean started = false;
		public HouseRedactor() {
			setBounds(getX(), getY(), texture2.getRegionWidth(), texture2.getRegionHeight());
			setPosition(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					
					((HouseRedactor)event.getTarget()).started = true;
					return true;
				}
			});
		}
		
		@Override 
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(texture2, getX(), getY());
		}
		
		@Override
		public void act(float delta) {
			if(started) {
				GameScreen.state = GameScreen.GAME_REDACTOR_MODE;
				GameScreen.redactorState = GameScreen.BUILD_HOUSE;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	

}
