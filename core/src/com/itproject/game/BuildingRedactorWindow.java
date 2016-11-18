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

	public class GroceryShopRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/groceryShopRedactor.png"));
	
		boolean started = false;
		public GroceryShopRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((GroceryShopRedactor)event.getTarget()).started = true;
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
				GameScreen.redactorState = GameScreen.BUILD_GROCERY_SHOP;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	
	public class BarRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/barRedactor.png"));
	
		boolean started = false;
		public BarRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((BarRedactor)event.getTarget()).started = true;
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
				GameScreen.redactorState = GameScreen.BUILD_BAR;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	
	public class PowerStationRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/powerStationRedactor.png"));
	
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
		Texture texture1 = new Texture(Gdx.files.internal("data/policeStationRedactor.png"));
	
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
		Texture texture1 = new Texture(Gdx.files.internal("data/hospitalRedactor.png"));
	
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
		Texture texture1 = new Texture(Gdx.files.internal("data/cityHallRedactor.png"));
	
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
		TextureRegion texture2 = new TextureRegion(new Texture("data/houseRedactor.png"));
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
	
	public class OilPlantRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/oilPlantRedactor.png"));
	
		boolean started = false;
		public OilPlantRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((OilPlantRedactor)event.getTarget()).started = true;
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
				GameScreen.redactorState = GameScreen.BUILD_OIL_PLANT;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	public class IronPlantRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/ironPlantRedactor.png"));
	
		boolean started = false;
		public IronPlantRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((IronPlantRedactor)event.getTarget()).started = true;
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
				GameScreen.redactorState = GameScreen.BUILD_IRON_PLANT;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	public class ParkRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/parkRedactor.png"));
	
		boolean started = false;
		public ParkRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((ParkRedactor)event.getTarget()).started = true;
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
				GameScreen.redactorState = GameScreen.BUILD_PARK;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
	public class WTCRedactor extends Actor {
		Texture texture1 = new Texture(Gdx.files.internal("data/worldTradeCenterRedactor.png"));
	
		boolean started = false;
		public WTCRedactor() {
			setWidth(texture1.getWidth());
			setHeight(texture1.getHeight());
			setBounds(0, 0, getWidth(), getHeight());
			setOrigin(getX(), getY());
			setTouchable(Touchable.enabled);
			addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					((WTCRedactor)event.getTarget()).started = true;
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
				GameScreen.redactorState = GameScreen.BUILD_WTC;
				Hud.redactorActor.remove();
				Hud.redactorStep = 0;
				started = false;
			}
		}
	}
}
