package com.eciz.evosciencia.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.entities.Checkpoint;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.resources.Maps;
import com.eciz.evosciencia.values.GameValues;

public class Dpad {
	
	/**
	 * DPAD Elements
	 */
	private Texture upArrow;
	private Texture downArrow;
	private Texture leftArrow;
	private Texture rightArrow;
	private Texture navHandler;
	
	/**
	 * DPAD Rectangle
	 */
	private Rectangle upArrowRectangle;
	private Rectangle downArrowRectangle;
	private Rectangle leftArrowRectangle;
	private Rectangle rightArrowRectangle;
	private Rectangle navHandlerRectangle;
	
	public Dpad() {
		upArrow = new Texture(Gdx.files.internal("dpad/up.png"));
		downArrow = new Texture(Gdx.files.internal("dpad/down.png"));
		leftArrow = new Texture(Gdx.files.internal("dpad/left.png"));
		rightArrow = new Texture(Gdx.files.internal("dpad/right.png"));
		navHandler = new Texture(Gdx.files.internal("dpad/dpad_handler.png"));
		
		upArrowRectangle = new Rectangle();
		downArrowRectangle = new Rectangle();
		leftArrowRectangle = new Rectangle();
		rightArrowRectangle = new Rectangle();
		navHandlerRectangle = new Rectangle();
		
		upArrowRectangle.width = GameValues.DPAD_WIDTH;
		upArrowRectangle.height = GameValues.DPAD_HEIGHT;
		downArrowRectangle.width = GameValues.DPAD_WIDTH;
		downArrowRectangle.height = GameValues.DPAD_HEIGHT;
		leftArrowRectangle.width = GameValues.DPAD_WIDTH;
		leftArrowRectangle.height = GameValues.DPAD_HEIGHT;
		rightArrowRectangle.width = GameValues.DPAD_WIDTH;
		rightArrowRectangle.height = GameValues.DPAD_HEIGHT;
		
	}

	public static void positionDpad(boolean isX, boolean isY) {
		
		/*
		 * Setting values
		 */
		
		float dpadControlXCenter = GameValues.camera.position.x - ( (GameValues.CAMERA_WIDTH/2) - ( GameValues.DPAD_WIDTH*3 ) );
		float dpadControlYCenter = GameValues.camera.position.y - ( (GameValues.CAMERA_HEIGHT/2) - ( GameValues.DPAD_HEIGHT*3 ) );
		
		if( isX ) {
			GameValues.dpad.upArrowRectangle.x = dpadControlXCenter;
			GameValues.dpad.downArrowRectangle.x = dpadControlXCenter;
			GameValues.dpad.leftArrowRectangle.x = dpadControlXCenter - GameValues.DPAD_WIDTH;
			GameValues.dpad.rightArrowRectangle.x = dpadControlXCenter + GameValues.DPAD_WIDTH;
		}
		
		if( isY ) {
			GameValues.dpad.upArrowRectangle.y = dpadControlYCenter + GameValues.DPAD_HEIGHT;
			GameValues.dpad.downArrowRectangle.y = dpadControlYCenter - GameValues.DPAD_HEIGHT;
			GameValues.dpad.leftArrowRectangle.y = dpadControlYCenter;
			GameValues.dpad.rightArrowRectangle.y = dpadControlYCenter;
		}
		
	}
	
	public static void moveAvatar( float valueX, float valueY ) {
		
		Rectangle tempRect = new Rectangle();
		tempRect.set(GameValues.avatar.getX() + valueX, GameValues.avatar.getY() + valueY, GameValues.avatar.getWidth(), GameValues.avatar.getHeight());
		
		for(Checkpoint checkpoint : GameValues.checkpoints ) {
			if( tempRect.overlaps(checkpoint.getRectangle()) ) {
				GameValues.avatar.updateStandBy();
				GameValues.maps.setCurrentMap(new TmxMapLoader().load("tmx/" + checkpoint.getValue() + ".tmx"));
				GameValues.maps.changeMap();
				
				StanceEnum facing = GameValues.avatar.facingFlag;
				float x = GameValues.avatar.getX(), y = GameValues.avatar.getY();
				
				switch (facing) {
					case FRONT_STAND:
						y = Maps.MAP_HEIGHT - (Avatar.height + checkpoint.getRectangle().getHeight());
						break;
					case BACK_STAND:
						y = checkpoint.getRectangle().getHeight();
						break;
					case LEFT_STAND:
						x = Maps.MAP_WIDTH - (Avatar.width + checkpoint.getRectangle().getWidth());
						break;
					case RIGHT_STAND:
						x = checkpoint.getRectangle().getWidth();
						break;
					default:
						break;
				}
				
				GameValues.avatar.repositionAvatar(x, y);
				GameValues.camera.position.set(GameValues.avatar.getX(), GameValues.avatar.getY(), 0);
				
				if( GameValues.avatar.getX() <= GameValues.CAMERA_WIDTH/2 ) {
					GameValues.camera.position.x = GameValues.CAMERA_WIDTH/2;
				}
				
				if( GameValues.avatar.getX()  >= Maps.MAP_WIDTH - (GameValues.CAMERA_WIDTH/2) ) {
					GameValues.camera.position.x = Maps.MAP_WIDTH - (GameValues.CAMERA_WIDTH/2);
				}
				
				if( GameValues.avatar.getY() <= GameValues.CAMERA_HEIGHT/2 ) {
					GameValues.camera.position.y = GameValues.CAMERA_HEIGHT/2;
				}
				
				if( GameValues.avatar.getY()  >= Maps.MAP_HEIGHT - (GameValues.CAMERA_HEIGHT/2) ) {
					GameValues.camera.position.x = Maps.MAP_HEIGHT - (GameValues.CAMERA_HEIGHT/2);
				}
				
				Dpad.positionDpad(true, true);
				
				return;
			}
		}
		
		if( tempRect.getX() < 1 ||
			tempRect.getX() > Maps.MAP_WIDTH - Avatar.width ||
			tempRect.getY() < 1 ||
			tempRect.getY() > Maps.MAP_HEIGHT - Avatar.height) {
			GameValues.avatar.updateStandBy();
			return;
		}
		
		for(Rectangle collision : GameValues.collisions ) {
			if( tempRect.overlaps(collision) ) {
				GameValues.avatar.updateStandBy();
				return;
			}
		}
		
		GameValues.avatar.repositionAvatar(GameValues.avatar.getX() + valueX, GameValues.avatar.getY() + valueY);
		
		if( TimeUtils.nanoTime() - GameValues.avatar.animationFlag > GameValues.ANIMATION_SPEED ) {
			
			GameValues.avatar.animationFlag = TimeUtils.nanoTime();
		
			if( valueY == -GameValues.CHARACTER_SPEED ) {
				if( GameValues.avatar.walkFlag ) {
					GameValues.avatar.setSprite(GameValues.avatar.avatarSprites.get(StanceEnum.FRONT_WALK1.getValue()));
					GameValues.avatar.walkFlag = false;
				} else {
					GameValues.avatar.setSprite(GameValues.avatar.avatarSprites.get(StanceEnum.FRONT_WALK2.getValue()));
					GameValues.avatar.walkFlag = true;
				}
			}
			
			if( valueY == GameValues.CHARACTER_SPEED ) {
				if( GameValues.avatar.walkFlag ) {
					GameValues.avatar.setSprite(GameValues.avatar.avatarSprites.get(StanceEnum.BACK_WALK1.getValue()));
					GameValues.avatar.walkFlag = false;
				} else {
					GameValues.avatar.setSprite(GameValues.avatar.avatarSprites.get(StanceEnum.BACK_WALK2.getValue()));
					GameValues.avatar.walkFlag = true;
				}
			}
			
			if( valueX == -GameValues.CHARACTER_SPEED ) {
				if( GameValues.avatar.walkFlag ) {
					GameValues.avatar.setSprite(GameValues.avatar.avatarSprites.get(StanceEnum.LEFT_WALK1.getValue()));
					GameValues.avatar.walkFlag = false;
				} else {
					GameValues.avatar.setSprite(GameValues.avatar.avatarSprites.get(StanceEnum.LEFT_WALK2.getValue()));
					GameValues.avatar.walkFlag = true;
				}
			}
			
			if( valueX == GameValues.CHARACTER_SPEED ) {
				if( GameValues.avatar.walkFlag ) {
					GameValues.avatar.setSprite(GameValues.avatar.avatarSprites.get(StanceEnum.RIGHT_WALK1.getValue()));
					GameValues.avatar.walkFlag = false;
				} else {
					GameValues.avatar.setSprite(GameValues.avatar.avatarSprites.get(StanceEnum.RIGHT_WALK2.getValue()));
					GameValues.avatar.walkFlag = true;
				}
			}
			
			if( Math.abs(valueX) != GameValues.CHARACTER_SPEED && Math.abs(valueY) != GameValues.CHARACTER_SPEED ) {
				GameValues.avatar.updateStandBy();
			}
			
		}
			
		
	}
	
	public void checkEvents() {
		
		float valueX = 0, valueY = 0;
		
		// Check if user touched or clicked
		if( Gdx.input.isTouched() ) {
			
			StanceEnum face = GameValues.avatar.facingFlag;
			
			GameValues.touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			GameValues.camera.unproject(GameValues.touchPos);
			
			// Up arrow is clicked/touched
			if( GameValues.touchPos.x >= GameValues.dpad.getUpArrowRectangle().x &&
				GameValues.touchPos.x <= GameValues.dpad.getUpArrowRectangle().x + GameValues.dpad.getUpArrowRectangle().width &&
				GameValues.touchPos.y >= GameValues.dpad.getUpArrowRectangle().y &&
				GameValues.touchPos.y <= GameValues.dpad.getUpArrowRectangle().y + GameValues.dpad.getUpArrowRectangle().height) {
				valueY = GameValues.CHARACTER_SPEED;
				face = StanceEnum.BACK_STAND;
			}
			
			// Down arrow is clicked/touched
			if( GameValues.touchPos.x >= GameValues.dpad.getDownArrowRectangle().x &&
				GameValues.touchPos.x <= GameValues.dpad.getDownArrowRectangle().x + GameValues.dpad.getDownArrowRectangle().width &&
				GameValues.touchPos.y >= GameValues.dpad.getDownArrowRectangle().y &&
				GameValues.touchPos.y <= GameValues.dpad.getDownArrowRectangle().y + GameValues.dpad.getDownArrowRectangle().height) {
				valueY = -GameValues.CHARACTER_SPEED;
				face = StanceEnum.FRONT_STAND;
			}
			
			// Left arrow is clicked/touched
			if( GameValues.touchPos.x >= GameValues.dpad.getLeftArrowRectangle().x &&
				GameValues.touchPos.x <= GameValues.dpad.getLeftArrowRectangle().x + GameValues.dpad.getLeftArrowRectangle().width &&
				GameValues.touchPos.y >= GameValues.dpad.getLeftArrowRectangle().y &&
				GameValues.touchPos.y <= GameValues.dpad.getLeftArrowRectangle().y + GameValues.dpad.getLeftArrowRectangle().height) {
				valueX = -GameValues.CHARACTER_SPEED;
				face = StanceEnum.LEFT_STAND;
			}
			
			// Right arrow is clicked/touched
			if( GameValues.touchPos.x >= GameValues.dpad.getRightArrowRectangle().x &&
				GameValues.touchPos.x <= GameValues.dpad.getRightArrowRectangle().x + GameValues.dpad.getRightArrowRectangle().width &&
				GameValues.touchPos.y >= GameValues.dpad.getRightArrowRectangle().y &&
				GameValues.touchPos.y <= GameValues.dpad.getRightArrowRectangle().y + GameValues.dpad.getRightArrowRectangle().height) {
				valueX = GameValues.CHARACTER_SPEED;
				face = StanceEnum.RIGHT_STAND;
			}
			
			if( !face.equals(GameValues.avatar.facingFlag) ) {
				GameValues.avatar.animationFlag = 0;
			}
			
			GameValues.avatar.facingFlag = face;
			
			Dpad.moveAvatar(valueX, valueY);
			
		} else {
			GameValues.avatar.updateStandBy();
		}
		
	}
	
	public void drawDpad() {
		GameValues.currentBatch.draw(GameValues.dpad.getUpArrow(), GameValues.dpad.getUpArrowRectangle().x, GameValues.dpad.getUpArrowRectangle().y, GameValues.dpad.getUpArrowRectangle().width, GameValues.dpad.getUpArrowRectangle().height);
		GameValues.currentBatch.draw(GameValues.dpad.getDownArrow(), GameValues.dpad.getDownArrowRectangle().x, GameValues.dpad.getDownArrowRectangle().y, GameValues.dpad.getDownArrowRectangle().width, GameValues.dpad.getDownArrowRectangle().height);
		GameValues.currentBatch.draw(GameValues.dpad.getLeftArrow(), GameValues.dpad.getLeftArrowRectangle().x, GameValues.dpad.getLeftArrowRectangle().y, GameValues.dpad.getLeftArrowRectangle().width, GameValues.dpad.getLeftArrowRectangle().height);
		GameValues.currentBatch.draw(GameValues.dpad.getRightArrow(), GameValues.dpad.getRightArrowRectangle().x, GameValues.dpad.getRightArrowRectangle().y, GameValues.dpad.getRightArrowRectangle().width, GameValues.dpad.getRightArrowRectangle().height);
		GameValues.currentBatch.draw(GameValues.dpad.getNavHandler(), GameValues.dpad.getNavHandlerRectangle().x, GameValues.dpad.getNavHandlerRectangle().y, GameValues.dpad.getNavHandlerRectangle().width, GameValues.dpad.getNavHandlerRectangle().height);
	}
	
	public Texture getUpArrow() {
		return upArrow;
	}

	public Texture getDownArrow() {
		return downArrow;
	}

	public Texture getLeftArrow() {
		return leftArrow;
	}

	public Texture getRightArrow() {
		return rightArrow;
	}
	
	public Texture getNavHandler() {
		return navHandler;
	}

	public void setNavHandler(Texture navHandler) {
		this.navHandler = navHandler;
	}

	public Rectangle getUpArrowRectangle() {
		return upArrowRectangle;
	}

	public Rectangle getDownArrowRectangle() {
		return downArrowRectangle;
	}

	public Rectangle getLeftArrowRectangle() {
		return leftArrowRectangle;
	}

	public Rectangle getRightArrowRectangle() {
		return rightArrowRectangle;
	}
	
	public Rectangle getNavHandlerRectangle() {
		return navHandlerRectangle;
	}

	public void setNavHandlerRectangle(Rectangle navHandlerRectangle) {
		this.navHandlerRectangle = navHandlerRectangle;
	}
	
}
