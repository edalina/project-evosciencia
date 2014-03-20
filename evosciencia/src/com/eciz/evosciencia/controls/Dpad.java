package com.eciz.evosciencia.controls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.entities.Checkpoint;
import com.eciz.evosciencia.entities.Coordinate;
import com.eciz.evosciencia.entities.DataHandler;
import com.eciz.evosciencia.entities.Enemy;
import com.eciz.evosciencia.entities.Quest;
import com.eciz.evosciencia.entities.Scientist;
import com.eciz.evosciencia.entities.SkillSlot;
import com.eciz.evosciencia.entities.User;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.resources.Maps;
import com.eciz.evosciencia.utils.DialogUtils;
import com.eciz.evosciencia.utils.EventUtils;
import com.eciz.evosciencia.values.GameSettings;
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
	private Texture healthGauge;
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
	
	public static final int DPAD_WIDTH = 32;
	public static final int DPAD_HEIGHT = 32;
	
	public static boolean isDpadActive = true;
	public static boolean buttonActive = false;
	
	// Quest
	public BitmapFont questInfo;
	
	public Dpad() {
		upArrow = new Texture(Gdx.files.internal("dpad/up.png"));
		downArrow = new Texture(Gdx.files.internal("dpad/down.png"));
		leftArrow = new Texture(Gdx.files.internal("dpad/left.png"));
		rightArrow = new Texture(Gdx.files.internal("dpad/right.png"));
		navHandler = new Texture(Gdx.files.internal("dpad/dpad_handler.png"));
		actionBtn = new Texture(Gdx.files.internal("dpad/action.png"));
		pauseBtn = new Texture(Gdx.files.internal("dpad/pause.png"));
		healthBar = new Texture(Gdx.files.internal("dpad/gauge.png"));
		healthGauge = new Texture(Gdx.files.internal("images/red.png"));
		slot = new Texture(Gdx.files.internal("dpad/slot.png"));
		
		questInfo = new BitmapFont();
		questInfo.setScale(0.7f);
		
		upArrowRectangle = new Rectangle();
		downArrowRectangle = new Rectangle();
		leftArrowRectangle = new Rectangle();
		rightArrowRectangle = new Rectangle();
		navHandlerRectangle = new Rectangle();
		actionRectangle = new Rectangle();
		pauseRectangle = new Rectangle();
		healthBarRectangle = new Rectangle();
		
		skillSlots = new ArrayList<SkillSlot>();
		
//		for( int i = 0 ; i < 5 ; i++ ) {
//			Rectangle skillSlotRec = new Rectangle();
//			skillSlotRec.width = DPAD_WIDTH;
//			skillSlotRec.height = DPAD_HEIGHT;
//			
//			SkillSlot skillSlot = new SkillSlot();
//			skillSlot.setRectangle(skillSlotRec);
//			skillSlot.setId(i);
//			skillSlot.setSkillId(0);
//			skillSlots.add(skillSlot);
//		}
		
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

//	public static void positionDpad(boolean isX, boolean isY) {
	public static void positionDpad() {
		
		/*
		 * Setting values
		 */
		
		float
			dpadControlXCenter = GameValues.camera.position.x - ( (GameValues.SCREEN_WIDTH/GameValues.CAMERA_ZOOM_MULTIPLE) - ( DPAD_WIDTH*3 ) ) - (DPAD_WIDTH + (DPAD_WIDTH/2)),
			dpadControlYCenter = GameValues.camera.position.y - ( (GameValues.SCREEN_HEIGHT/GameValues.CAMERA_ZOOM_MULTIPLE) - ( DPAD_HEIGHT*2 ) ) - DPAD_HEIGHT,
			dpadActionXCenter = GameValues.camera.position.x + ( (GameValues.SCREEN_WIDTH/GameValues.CAMERA_ZOOM_MULTIPLE) - ( DPAD_WIDTH*3 ) ),
			dpadActionYCenter = dpadControlYCenter,
			dpadPauseXCenter = dpadActionXCenter + DPAD_WIDTH,
			dpadPauseYCenter = GameValues.camera.position.y + ( (GameValues.SCREEN_HEIGHT/GameValues.CAMERA_ZOOM_MULTIPLE) - ( DPAD_HEIGHT + 10 ) ),
			dpadHealthXCenter = dpadControlXCenter - (DPAD_WIDTH*3) + (DPAD_WIDTH + (DPAD_WIDTH/2)),
			dpadHealthYCenter = dpadPauseYCenter + DPAD_HEIGHT - 7,
			dpadSkillSlotXCenter = GameValues.camera.position.x - (2 * (DPAD_WIDTH + (DPAD_WIDTH/2))),
			dpadSkillSlotYCenter = dpadControlYCenter - (DPAD_HEIGHT*2) + 10 + DPAD_HEIGHT;
		
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
	
	public static void moveAvatar( float valueX, float valueY ) {
		
		Rectangle tmpRect = new Rectangle();
		tmpRect.set(GameValues.avatar.getX() + valueX, GameValues.avatar.getY() + valueY, GameValues.avatar.getWidth(), GameValues.avatar.getHeight());
		
		if( GameValues.user.getQuestDone()[GameValues.currentMapValue] &&
			GameValues.currentScientist.getRectangle() != null ) {
			if( tmpRect.overlaps(GameValues.portal.getRectangle()) ) {
				GameValues.avatar.updateStandBy();
				GameValues.currentMapValue = GameValues.currentMapValue+1;
				GameValues.maps = new Maps();
				GameValues.avatar.repositionAvatar(384, 384);
				GameValues.user.setCurrentQuestInProgress(false);
				GameValues.user.setCurrentQuestDone(false);
				GameValues.portal = null;
				return;
			}
		}
		
		for(Checkpoint checkpoint : GameValues.checkpoints ) {
			if( tmpRect.overlaps(checkpoint.getRectangle()) ) {
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
				Dpad.positionDpad();
				
				return;
			}
		}
		
		if( tmpRect.getX() < 1 ||
			tmpRect.getX() > Maps.MAP_WIDTH - Avatar.width ||
			tmpRect.getY() < 1 ||
			tmpRect.getY() > Maps.MAP_HEIGHT - Avatar.height) {
			GameValues.avatar.updateStandBy();
			return;
		}
		
		for(Enemy enemy : Maps.enemies) {
			if( enemy.isAlive() ) {
				if( tmpRect.overlaps(enemy.getBoundingRectangle()) ) {
					GameValues.avatar.updateStandBy();
					return;
				}
			}
		}
		
		for(Rectangle collision : GameValues.collisions ) {
			if( tmpRect.overlaps(collision) ) {
				GameValues.avatar.updateStandBy();
				return;
			}
		}
		
		if( GameValues.currentScientist != null && GameValues.currentScientist.getRectangle() != null ) {
			if( tmpRect.overlaps(GameValues.currentScientist.getRectangle()) ) {
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
			
			if( !Dpad.buttonActive ) {
				
				// Action button is clicked
				if( EventUtils.isTap(GameValues.dpad.getActionRectangle()) ) {
					Dpad.buttonActive = true;
					actionButton();
				}
				
				// Pause button is clicked, CURRENTLY SAVING
				if( EventUtils.isTap(GameValues.dpad.getPauseRectangle()) ) {
					Dpad.buttonActive = true;
					Dpad.isDpadActive = false;
					DialogUtils.createItemDialog("Saving Data...");
					
					Scientist tmpScientist = GameValues.currentScientist;
					
					GameValues.user.setCoordinate(Coordinate.getCurrentCoordinate());
					
					for( User user : GameValues.dataHandler.getUsers() ) {
						if( user.getId() == GameValues.user.getId() ) {
							user = GameValues.user;
						}
					}
					
					DataHandler tmpDataHandler = GameValues.dataHandler;
					
					for( Scientist scientist : tmpDataHandler.getScientists() ) {
						scientist.setTexture(null);
						scientist.setRectangle(null);
					}
					
					Json json = new Json();
					json.setIgnoreUnknownFields(true);
					String string = json.prettyPrint(tmpDataHandler);
					FileHandle fileHandle = Gdx.files.local("data/data.json");
					fileHandle.writeString(string, false);
					DialogUtils.closeDialog();
					DialogUtils.createItemDialog("Data saved!");
					Dpad.isDpadActive = true;
//					GameValues.settingUtils.toggleBGM();
					GameValues.currentScientist = tmpScientist;
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
			
			if( GameValues.user.getCurrentLife() > 0 )
				GameValues.avatar.updateStandBy();
			Dpad.buttonActive = false;
			
		}
		
	}
	
	public void drawDpad() {
		if( isDpadActive ) {
			float healthLength = GameValues.dpad.getHealthBarRectangle().width * ( Float.valueOf(GameValues.user.getCurrentLife())/Float.valueOf(GameValues.user.getLife()) );
			
			GameValues.currentBatch.draw(GameValues.dpad.getNavHandler(), GameValues.dpad.getNavHandlerRectangle().x, GameValues.dpad.getNavHandlerRectangle().y, GameValues.dpad.getNavHandlerRectangle().width, GameValues.dpad.getNavHandlerRectangle().height);
			GameValues.currentBatch.draw(GameValues.dpad.getUpArrow(), GameValues.dpad.getUpArrowRectangle().x, GameValues.dpad.getUpArrowRectangle().y, GameValues.dpad.getUpArrowRectangle().width, GameValues.dpad.getUpArrowRectangle().height);
			GameValues.currentBatch.draw(GameValues.dpad.getDownArrow(), GameValues.dpad.getDownArrowRectangle().x, GameValues.dpad.getDownArrowRectangle().y, GameValues.dpad.getDownArrowRectangle().width, GameValues.dpad.getDownArrowRectangle().height);
			GameValues.currentBatch.draw(GameValues.dpad.getLeftArrow(), GameValues.dpad.getLeftArrowRectangle().x, GameValues.dpad.getLeftArrowRectangle().y, GameValues.dpad.getLeftArrowRectangle().width, GameValues.dpad.getLeftArrowRectangle().height);
			GameValues.currentBatch.draw(GameValues.dpad.getRightArrow(), GameValues.dpad.getRightArrowRectangle().x, GameValues.dpad.getRightArrowRectangle().y, GameValues.dpad.getRightArrowRectangle().width, GameValues.dpad.getRightArrowRectangle().height);
			GameValues.currentBatch.draw(GameValues.dpad.getActionBtn(), GameValues.dpad.getActionRectangle().x, GameValues.dpad.getActionRectangle().y, GameValues.dpad.getActionRectangle().width, GameValues.dpad.getActionRectangle().height);
			GameValues.currentBatch.draw(GameValues.dpad.getPauseBtn(), GameValues.dpad.getPauseRectangle().x, GameValues.dpad.getPauseRectangle().y, GameValues.dpad.getPauseRectangle().width, GameValues.dpad.getPauseRectangle().height);
			GameValues.currentBatch.draw(GameValues.dpad.getHealthGauge(), GameValues.dpad.getHealthBarRectangle().x, GameValues.dpad.getHealthBarRectangle().y, healthLength, GameValues.dpad.getHealthBarRectangle().height);
			GameValues.currentBatch.draw(GameValues.dpad.getHealthBar(), GameValues.dpad.getHealthBarRectangle().x, GameValues.dpad.getHealthBarRectangle().y, GameValues.dpad.getHealthBarRectangle().width, GameValues.dpad.getHealthBarRectangle().height);
			
			if( GameValues.user.isCurrentQuestInProgress() && !GameValues.user.getQuestDone()[GameValues.currentMapValue] ) {
				String dialog = "";
				if( GameValues.user.isCurrentQuestDone() ) {
					dialog = "Quest Complete. Talk back to the NPC";
				} else {
					dialog = "Collect: ";
					List<Quest> quests = Quest.getQuests();
					int i = 0;
					for( Quest quest : quests ) {
						dialog = dialog.concat(quest.getName());
						if( i < quests.size()-1 )
							dialog = dialog.concat(", ");
						i++;
					}
				}
				questInfo.draw(GameValues.currentBatch, dialog, getHealthBarRectangle().x + 10, getHealthBarRectangle().y - 30);
			}
			
			for( SkillSlot skillSlot : GameValues.dpad.getSkillSlots() ) {
				GameValues.currentBatch.draw(GameValues.dpad.getSlot(), skillSlot.getRectangle().x, skillSlot.getRectangle().y, skillSlot.getRectangle().width, skillSlot.getRectangle().height);
			}
		}
	}
	
	public void actionButton() {
		boolean isScientist = false;
		Rectangle tmpRectangle = new Rectangle();
		switch(GameValues.avatar.facingFlag) {
			case FRONT_STAND:
				tmpRectangle.set(GameValues.avatar.getX(), GameValues.avatar.getY() - Avatar.height, Avatar.width, Avatar.height);
				break;
			case BACK_STAND:
				tmpRectangle.set(GameValues.avatar.getX(), GameValues.avatar.getY() + Avatar.height, Avatar.width, Avatar.height);
				break;
			case LEFT_STAND:
				tmpRectangle.set(GameValues.avatar.getX() - Avatar.width, GameValues.avatar.getY(), Avatar.width, Avatar.height);
				break;
			case RIGHT_STAND:
				tmpRectangle.set(GameValues.avatar.getX() + Avatar.width, GameValues.avatar.getY(), Avatar.width, Avatar.height);
				break;
			default:
				break;
		}
		
		if( GameValues.currentScientist != null ) {
			
			if( !GameValues.user.isCurrentQuestInProgress() && !GameValues.user.getQuestDone()[GameValues.currentMapValue] ) {
				if( GameValues.currentScientist.getRectangle() != null && tmpRectangle.overlaps(GameValues.currentScientist.getRectangle()) ) {
					String dialog = "";
					List<Quest> quests = Quest.getQuests();
					int i = 0;
					for( Quest quest : quests ) {
						dialog = dialog.concat(quest.getName());
						if( i < quests.size()-1 )
							dialog = dialog.concat(", ");
						i++;
					}
					GameValues.user.setCurrentQuestDone(false);
					DialogUtils.createQuestDialog(dialog + " x 1");
					
					isScientist = true;
					
					return;
				}
			} else if( GameValues.user.isCurrentQuestInProgress() && GameValues.user.isCurrentQuestDone() && !GameValues.user.getQuestDone()[GameValues.currentMapValue] ) {
				if( GameValues.currentScientist.getRectangle() != null && tmpRectangle.overlaps(GameValues.currentScientist.getRectangle()) ) {
					String[] sName = GameValues.currentScientist.getName().split(" ");
					String jumbled = "";
					for( String _sName : sName ) {
						char[] _tmp = _sName.toCharArray();
						ArrayList<Character> tmpList = new ArrayList<Character>();
						for( char c : _tmp ) {
							tmpList.add(c);
						}
						
						Collections.shuffle(tmpList);
						char[] _cTmp = new char[tmpList.size()];
						for( int i = 0 ; i < tmpList.size() ; i++ ) {
							_cTmp[i] = tmpList.get(i);
						}
						
						jumbled += new String(_cTmp) + "  ";
					}
					
					DialogUtils.createCompleteDialog("CLUE: " + jumbled.toUpperCase() + "\nGuess the Scientist: \n" + GameValues.currentScientist.getDescription());
					
					isScientist = true;
				}
			} else if( GameValues.user.getQuestDone()[GameValues.currentMapValue] ) {
				if( GameValues.currentScientist.getRectangle() != null && tmpRectangle.overlaps(GameValues.currentScientist.getRectangle()) ) {
					String name = "";
					for( String s : GameValues.currentScientist.getName().split(" ") ) {
						name = name + s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase() + " ";
					}
					DialogUtils.createDialog( name + ": Thanks for helping me!");
					
					isScientist = true;
					
					return;
				}
			}
			
		}
		
		if( !GameValues.avatar.isAttacking && TimeUtils.nanoTime() - GameValues.avatar.attackAnimCtr > GameValues.ATTACK_SPEED * 2 ) {
			GameValues.avatar.isAttacking = true;
			GameValues.avatar.attackAnimCtr = TimeUtils.nanoTime();
			if( GameSettings.sfx && !isScientist ) {
				Avatar.attackSound.play();
			}
		}
		
		for( Enemy enemy : Maps.enemies ) {
			if( tmpRectangle.overlaps(enemy.getBoundingRectangle()) ) {
				enemy.attack();
				enemy.setLife(enemy.getLife()-GameValues.user.getDamage());
				if( enemy.getLife() <= 0 ) {
					Enemy.dieSound.play();
					enemy.setAlive(false);
					GameValues.user.setExperience(GameValues.user.getExperience()+1);
					if( GameValues.user.getCurrentLife() < GameValues.user.getLife() ) {
						GameValues.user.setCurrentLife(GameValues.user.getCurrentLife()+10);
						if( GameValues.user.getCurrentLife() > GameValues.user.getLife() ) {
							GameValues.user.setCurrentLife(GameValues.user.getLife());
						}
					}
					if( GameValues.user.getExperience() == GameValues.user.getLevel() * 10 ) {
						GameValues.user.setLevel(GameValues.user.getLevel() + 1);
						GameValues.user.setCurrentLife(GameValues.user.getLife());
						Avatar.levelSound.play();
						DialogUtils.createItemDialog("You have level up!");
					}
					if( GameValues.user.isCurrentQuestInProgress() && !GameValues.user.isCurrentQuestDone() && !GameValues.user.getQuestDone()[GameValues.currentMapValue] ) {
						if( MathUtils.random(0, 100) < 100 ) {
							
//							GameValues.user.getQuestDone()[GameValues.currentMapValue] = true;
							GameValues.user.setCurrentQuestDone(true);
							
							DialogUtils.createItemDialog("Quest completed");
							
						}
					}
				}
			}
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
	
	public Texture getHealthGauge() {
		return healthGauge;
	}

	public void setHealthGauge(Texture healthGauge) {
		this.healthGauge = healthGauge;
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
