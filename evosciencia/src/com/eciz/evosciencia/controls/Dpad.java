package com.eciz.evosciencia.controls;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.entities.Checkpoint;
import com.eciz.evosciencia.entities.SkillSlot;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.resources.Maps;
import com.eciz.evosciencia.utils.EventUtils;
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
	private Texture actionBtn;
	private Texture healthBar;
	private Texture pauseBtn;
	private Texture slot;
	
	/**
	 * DPAD Rectangle
	 */
	private Rectangle upArrowRectangle;
	private Rectangle downArrowRectangle;
	private Rectangle leftArrowRectangle;
	private Rectangle rightArrowRectangle;
	private Rectangle navHandlerRectangle;
	private Rectangle actionRectangle;
	private Rectangle healthBarRectangle;
	private Rectangle pauseRectangle;
	private List<SkillSlot> skillSlots;
	
	public static final int DPAD_WIDTH = 24;
	public static final int DPAD_HEIGHT = 24;
	
	public static boolean isDpadActive = true;
	public static boolean buttonActive = false;
	
	public Dpad() {
		upArrow = new Texture(Gdx.files.internal("dpad/up.png"));
		downArrow = new Texture(Gdx.files.internal("dpad/down.png"));
		leftArrow = new Texture(Gdx.files.internal("dpad/left.png"));
		rightArrow = new Texture(Gdx.files.internal("dpad/right.png"));
		navHandler = new Texture(Gdx.files.internal("dpad/dpad_handler.png"));
		actionBtn = new Texture(Gdx.files.internal("dpad/action.png"));
		pauseBtn = new Texture(Gdx.files.internal("dpad/pause.png"));
		healthBar = new Texture(Gdx.files.internal("dpad/gauge.png"));
		slot = new Texture(Gdx.files.internal("dpad/slot.png"));
		
		upArrowRectangle = new Rectangle();
		downArrowRectangle = new Rectangle();
		leftArrowRectangle = new Rectangle();
		rightArrowRectangle = new Rectangle();
		navHandlerRectangle = new Rectangle();
		actionRectangle = new Rectangle();
		pauseRectangle = new Rectangle();
		healthBarRectangle = new Rectangle();
		
		skillSlots = new ArrayList<SkillSlot>();
		
		for( int i = 0 ; i < 5 ; i++ ) {
			Rectangle skillSlotRec = new Rectangle();
			skillSlotRec.width = DPAD_WIDTH;
			skillSlotRec.height = DPAD_HEIGHT;
			
			SkillSlot skillSlot = new SkillSlot();
			skillSlot.setRectangle(skillSlotRec);
			skillSlot.setId(i);
			skillSlot.setSkillId(0);
			skillSlots.add(skillSlot);
		}
		
		upArrowRectangle.width = DPAD_WIDTH;
		upArrowRectangle.height = DPAD_HEIGHT;
		downArrowRectangle.width = DPAD_WIDTH;
		downArrowRectangle.height = DPAD_HEIGHT;
		leftArrowRectangle.width = DPAD_WIDTH;
		leftArrowRectangle.height = DPAD_HEIGHT;
		rightArrowRectangle.width = DPAD_WIDTH;
		rightArrowRectangle.height = DPAD_HEIGHT;
		navHandlerRectangle.width = DPAD_WIDTH*2;
		navHandlerRectangle.height = DPAD_HEIGHT*2;
		actionRectangle.width = DPAD_WIDTH*2;
		actionRectangle.height = DPAD_HEIGHT*2;
		pauseRectangle.width = DPAD_WIDTH;
		pauseRectangle.height = DPAD_HEIGHT;
		healthBarRectangle.width = DPAD_WIDTH * 4;
		healthBarRectangle.height = DPAD_HEIGHT/2;
	}

	public static void positionDpad(boolean isX, boolean isY) {
		
		/*
		 * Setting values
		 */
		
		float
			dpadControlXCenter = GameValues.camera.position.x - ( (GameValues.SCREEN_WIDTH/GameValues.CAMERA_ZOOM_MULTIPLE) - ( DPAD_WIDTH*3 ) ),
			dpadControlYCenter = GameValues.camera.position.y - ( (GameValues.SCREEN_HEIGHT/GameValues.CAMERA_ZOOM_MULTIPLE) - ( DPAD_HEIGHT*2 ) ),
			dpadActionXCenter = GameValues.camera.position.x + ( (GameValues.SCREEN_WIDTH/GameValues.CAMERA_ZOOM_MULTIPLE) - ( DPAD_WIDTH*3 ) ),
			dpadActionYCenter = dpadControlYCenter,
			dpadPauseXCenter = dpadActionXCenter + DPAD_WIDTH,
			dpadPauseYCenter = GameValues.camera.position.y + ( (GameValues.SCREEN_HEIGHT/GameValues.CAMERA_ZOOM_MULTIPLE) - ( DPAD_HEIGHT + 10 ) ),
			dpadHealthXCenter = dpadControlXCenter - (DPAD_WIDTH*3),
			dpadHealthYCenter = dpadPauseYCenter + DPAD_HEIGHT - 2,
			dpadSkillSlotXCenter = GameValues.camera.position.x - (2 * (DPAD_WIDTH + (DPAD_WIDTH/2))),
			dpadSkillSlotYCenter = dpadControlYCenter - (DPAD_HEIGHT*2) + 10;
		
		if( isX ) {
			GameValues.dpad.upArrowRectangle.x = dpadControlXCenter;
			GameValues.dpad.downArrowRectangle.x = dpadControlXCenter;
			GameValues.dpad.leftArrowRectangle.x = dpadControlXCenter - DPAD_WIDTH;
			GameValues.dpad.rightArrowRectangle.x = dpadControlXCenter + DPAD_WIDTH;
			GameValues.dpad.navHandlerRectangle.x = dpadControlXCenter - (DPAD_WIDTH/2);
			GameValues.dpad.actionRectangle.x = dpadActionXCenter - (DPAD_WIDTH/2);
			GameValues.dpad.pauseRectangle.x = dpadPauseXCenter;
			GameValues.dpad.healthBarRectangle.x = dpadHealthXCenter;
			int i = 0;
			for( SkillSlot skillSlot : GameValues.dpad.getSkillSlots() ) {
				skillSlot.getRectangle().x = dpadSkillSlotXCenter + (i * (DPAD_WIDTH + (DPAD_WIDTH/2)));
				i++;
			}
		}
		
		if( isY ) {
			GameValues.dpad.upArrowRectangle.y = dpadControlYCenter + DPAD_HEIGHT;
			GameValues.dpad.downArrowRectangle.y = dpadControlYCenter - DPAD_HEIGHT;
			GameValues.dpad.leftArrowRectangle.y = dpadControlYCenter;
			GameValues.dpad.rightArrowRectangle.y = dpadControlYCenter;
			GameValues.dpad.navHandlerRectangle.y = dpadControlYCenter - (DPAD_HEIGHT/2);
			GameValues.dpad.actionRectangle.y = dpadActionYCenter - (DPAD_HEIGHT/2);
			GameValues.dpad.pauseRectangle.y = dpadPauseYCenter;
			GameValues.dpad.healthBarRectangle.y = dpadHealthYCenter;
			for( SkillSlot skillSlot : GameValues.dpad.getSkillSlots() ) {
				skillSlot.getRectangle().y = dpadSkillSlotYCenter;
			}
		}
		
	}
	
	public static void moveAvatar( float valueX, float valueY ) {
		
		Rectangle tempRect = new Rectangle();
		tempRect.set(GameValues.avatar.getX() + valueX, GameValues.avatar.getY() + valueY, GameValues.avatar.getWidth(), GameValues.avatar.getHeight());
		
		for(Checkpoint checkpoint : GameValues.checkpoints ) {
			if( tempRect.overlaps(checkpoint.getRectangle()) ) {
				GameValues.avatar.updateStandBy();
				String prevMap = GameValues.maps.name;
				GameValues.maps.setCurrentMap(checkpoint.getValue());
				GameValues.maps.changeMap();
				
				StanceEnum facing = GameValues.avatar.facingFlag;
				float x = GameValues.avatar.getX(), y = GameValues.avatar.getY();
				
				for(Checkpoint newMapCheckpoint : GameValues.checkpoints ) {
					if( newMapCheckpoint.getValue().equals(prevMap) ) {
						x = newMapCheckpoint.getRectangle().getX();
						y = newMapCheckpoint.getRectangle().getY();
						switch (facing) {
							case FRONT_STAND:
								y = y - newMapCheckpoint.getRectangle().getHeight();
								break;
							case BACK_STAND:
								y = y + newMapCheckpoint.getRectangle().getHeight();
								break;
							case LEFT_STAND:
								x = x - newMapCheckpoint.getRectangle().getWidth();
								break;
							case RIGHT_STAND:
								x = x + newMapCheckpoint.getRectangle().getWidth();
								break;
							default:
								break;
						}
						break;
					}
				}
				
				GameValues.avatar.repositionAvatar(x, y);
				
				if( GameValues.avatar.getX() <= GameValues.SCREEN_WIDTH/GameValues.CAMERA_ZOOM_MULTIPLE ) {
					x = GameValues.SCREEN_WIDTH/GameValues.CAMERA_ZOOM_MULTIPLE;
				}
				
				if( GameValues.avatar.getX()  >= Maps.MAP_WIDTH - (GameValues.SCREEN_WIDTH/GameValues.CAMERA_ZOOM_MULTIPLE) ) {
					x = Maps.MAP_WIDTH - (GameValues.SCREEN_WIDTH/GameValues.CAMERA_ZOOM_MULTIPLE);
				}
				
				if( GameValues.avatar.getY() <= GameValues.SCREEN_HEIGHT/GameValues.CAMERA_ZOOM_MULTIPLE ) {
					y = GameValues.SCREEN_HEIGHT/GameValues.CAMERA_ZOOM_MULTIPLE;
				}
				
				if( GameValues.avatar.getY() >= Maps.MAP_HEIGHT - (GameValues.SCREEN_HEIGHT/GameValues.CAMERA_ZOOM_MULTIPLE) ) {
					y = Maps.MAP_HEIGHT - (GameValues.SCREEN_HEIGHT/GameValues.CAMERA_ZOOM_MULTIPLE);
				}
				
				GameValues.camera.position.set(x, y, 0);
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
		if( Gdx.input.isTouched() && Dpad.isDpadActive ) {
			
			StanceEnum face = GameValues.avatar.facingFlag;
			GameValues.touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			GameValues.camera.unproject(GameValues.touchPos);
			
			if( !Dpad.buttonActive ) {
				
				// Action button is clicked
				if( EventUtils.isTap(GameValues.dpad.getActionRectangle()) ) {
					Dpad.buttonActive = true;
					System.out.println( "ACT" );
				}
				
				// Pause button is clicked
				if( EventUtils.isTap(GameValues.dpad.getPauseRectangle()) ) {
					Dpad.buttonActive = true;
					GameValues.settingUtils.toggleBGM();
					System.out.println( "PAUSE" );
				}
				
				// Skill slots
				for( SkillSlot skillSlot : GameValues.dpad.skillSlots ) {
					if( EventUtils.isTap(skillSlot.getRectangle()) ) {
						Dpad.buttonActive = true;
						System.out.println( "SKILL" );
					}
				}
				
			}
			
			// Up arrow is clicked/touched
			if( EventUtils.isTap(GameValues.dpad.getUpArrowRectangle()) ) {
				valueY = GameValues.CHARACTER_SPEED;
				face = StanceEnum.BACK_STAND;
			}
			
			// Down arrow is clicked/touched
			if( EventUtils.isTap(GameValues.dpad.getDownArrowRectangle()) ) {
				valueY = -GameValues.CHARACTER_SPEED;
				face = StanceEnum.FRONT_STAND;
			}
			
			// Left arrow is clicked/touched
			if( EventUtils.isTap(GameValues.dpad.getLeftArrowRectangle()) ) {
				valueX = -GameValues.CHARACTER_SPEED;
				face = StanceEnum.LEFT_STAND;
			}
			
			// Right arrow is clicked/touched
			if( EventUtils.isTap(GameValues.dpad.getRightArrowRectangle()) ) {
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
			Dpad.buttonActive = false;
		}
		
	}
	
	public void drawDpad() {
		GameValues.currentBatch.draw(GameValues.dpad.getNavHandler(), GameValues.dpad.getNavHandlerRectangle().x, GameValues.dpad.getNavHandlerRectangle().y, GameValues.dpad.getNavHandlerRectangle().width, GameValues.dpad.getNavHandlerRectangle().height);
		GameValues.currentBatch.draw(GameValues.dpad.getUpArrow(), GameValues.dpad.getUpArrowRectangle().x, GameValues.dpad.getUpArrowRectangle().y, GameValues.dpad.getUpArrowRectangle().width, GameValues.dpad.getUpArrowRectangle().height);
		GameValues.currentBatch.draw(GameValues.dpad.getDownArrow(), GameValues.dpad.getDownArrowRectangle().x, GameValues.dpad.getDownArrowRectangle().y, GameValues.dpad.getDownArrowRectangle().width, GameValues.dpad.getDownArrowRectangle().height);
		GameValues.currentBatch.draw(GameValues.dpad.getLeftArrow(), GameValues.dpad.getLeftArrowRectangle().x, GameValues.dpad.getLeftArrowRectangle().y, GameValues.dpad.getLeftArrowRectangle().width, GameValues.dpad.getLeftArrowRectangle().height);
		GameValues.currentBatch.draw(GameValues.dpad.getRightArrow(), GameValues.dpad.getRightArrowRectangle().x, GameValues.dpad.getRightArrowRectangle().y, GameValues.dpad.getRightArrowRectangle().width, GameValues.dpad.getRightArrowRectangle().height);
		GameValues.currentBatch.draw(GameValues.dpad.getActionBtn(), GameValues.dpad.getActionRectangle().x, GameValues.dpad.getActionRectangle().y, GameValues.dpad.getActionRectangle().width, GameValues.dpad.getActionRectangle().height);
		GameValues.currentBatch.draw(GameValues.dpad.getPauseBtn(), GameValues.dpad.getPauseRectangle().x, GameValues.dpad.getPauseRectangle().y, GameValues.dpad.getPauseRectangle().width, GameValues.dpad.getPauseRectangle().height);
		GameValues.currentBatch.draw(GameValues.dpad.getHealthBar(), GameValues.dpad.getHealthBarRectangle().x, GameValues.dpad.getHealthBarRectangle().y, GameValues.dpad.getHealthBarRectangle().width, GameValues.dpad.getHealthBarRectangle().height);
		
		for( SkillSlot skillSlot : GameValues.dpad.getSkillSlots() ) {
			GameValues.currentBatch.draw(GameValues.dpad.getSlot(), skillSlot.getRectangle().x, skillSlot.getRectangle().y, skillSlot.getRectangle().width, skillSlot.getRectangle().height);
		}
	}
	
	public Texture getActionBtn() {
		return actionBtn;
	}

	public void setActionBtn(Texture actionBtn) {
		this.actionBtn = actionBtn;
	}

	public Rectangle getActionRectangle() {
		return actionRectangle;
	}

	public void setActionRectangle(Rectangle actionRectangle) {
		this.actionRectangle = actionRectangle;
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

	public Texture getHealthBar() {
		return healthBar;
	}

	public void setHealthBar(Texture healthBar) {
		this.healthBar = healthBar;
	}

	public Texture getPauseBtn() {
		return pauseBtn;
	}

	public void setPauseBtn(Texture pauseBtn) {
		this.pauseBtn = pauseBtn;
	}

	public Rectangle getHealthBarRectangle() {
		return healthBarRectangle;
	}

	public void setHealthBarRectangle(Rectangle healthBarRectangle) {
		this.healthBarRectangle = healthBarRectangle;
	}

	public Rectangle getPauseRectangle() {
		return pauseRectangle;
	}

	public void setPauseRectangle(Rectangle pauseRectangle) {
		this.pauseRectangle = pauseRectangle;
	}

	public Texture getSlot() {
		return slot;
	}

	public void setSlot(Texture slot) {
		this.slot = slot;
	}

	public List<SkillSlot> getSkillSlots() {
		return skillSlots;
	}

	public void setSkillSlots(List<SkillSlot> skillSlots) {
		this.skillSlots = skillSlots;
	}
	
}
