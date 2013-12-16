package com.eciz.evosciencia.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.values.GameValues;

public class Dpad {
	
	/**
	 * DPAD Elements
	 */
	private Texture upArrow;
	private Texture downArrow;
	private Texture leftArrow;
	private Texture rightArrow;
	
	/**
	 * DPAD Rectangle
	 */
	private Rectangle upArrowRectangle;
	private Rectangle downArrowRectangle;
	private Rectangle leftArrowRectangle;
	private Rectangle rightArrowRectangle;
	
	public Dpad() {
		upArrow = new Texture(Gdx.files.internal("dpad/up.png"));
		downArrow = new Texture(Gdx.files.internal("dpad/down.png"));
		leftArrow = new Texture(Gdx.files.internal("dpad/left.png"));
		rightArrow = new Texture(Gdx.files.internal("dpad/right.png"));
		
		upArrowRectangle = new Rectangle();
		downArrowRectangle = new Rectangle();
		leftArrowRectangle = new Rectangle();
		rightArrowRectangle = new Rectangle();
	}

	public static Dpad positionDpad() {
		Dpad dpad = new Dpad();
		
		/*
		 * Setting values
		 */
		
		float dpadControlXCenter = ( GameValues.DPAD_WIDTH * 2 ) + ( GameValues.DPAD_WIDTH / 2 );
		float dpadControlYCenter = ( GameValues.DPAD_HEIGHT * 2 ) + ( GameValues.DPAD_HEIGHT / 2 );
		
		dpad.upArrowRectangle.width = GameValues.DPAD_WIDTH;
		dpad.upArrowRectangle.height = GameValues.DPAD_HEIGHT;
		dpad.upArrowRectangle.x = dpadControlXCenter;
		dpad.upArrowRectangle.y = dpadControlYCenter + GameValues.DPAD_HEIGHT;
		
		dpad.downArrowRectangle.width = GameValues.DPAD_WIDTH;
		dpad.downArrowRectangle.height = GameValues.DPAD_HEIGHT;
		dpad.downArrowRectangle.x = dpadControlXCenter;
		dpad.downArrowRectangle.y = dpadControlYCenter - GameValues.DPAD_HEIGHT;
		
		dpad.leftArrowRectangle.width = GameValues.DPAD_WIDTH;
		dpad.leftArrowRectangle.height = GameValues.DPAD_HEIGHT;
		dpad.leftArrowRectangle.x = dpadControlXCenter - GameValues.DPAD_WIDTH;
		dpad.leftArrowRectangle.y = dpadControlYCenter;
		
		dpad.rightArrowRectangle.width = GameValues.DPAD_WIDTH;
		dpad.rightArrowRectangle.height = GameValues.DPAD_HEIGHT;
		dpad.rightArrowRectangle.x = dpadControlXCenter + GameValues.DPAD_WIDTH;
		dpad.rightArrowRectangle.y = dpadControlYCenter;
		
		return dpad;
	}
	
	public static void moveAvatar( float valueX, float valueY ) {
		boolean isMoved = false;
		if( Avatar.x == GameValues.camera.position.x - (Avatar.width/2) ) {
			GameValues.camera.position.x += valueX;
			if( valueX != 0 )
				isMoved = true;
		}
		if( Avatar.y == GameValues.camera.position.y - (Avatar.height/2) ) {
			GameValues.camera.position.y += valueY;
			if( valueY != 0 )
				isMoved = true;
		}
		if( isMoved ) {
			GameValues.dpad.upArrowRectangle.x += valueX;
			GameValues.dpad.upArrowRectangle.y += valueY;
			GameValues.dpad.downArrowRectangle.x += valueX;
			GameValues.dpad.downArrowRectangle.y += valueY;
			GameValues.dpad.leftArrowRectangle.x += valueX;
			GameValues.dpad.leftArrowRectangle.y += valueY;
			GameValues.dpad.rightArrowRectangle.x += valueX;
			GameValues.dpad.rightArrowRectangle.y += valueY;
		}
		
		Avatar.x += valueX;
		Avatar.y += valueY;
		
		if( TimeUtils.nanoTime() - Avatar.animationFlag > GameValues.ANIMATION_SPEED ) {
			
			Avatar.animationFlag = TimeUtils.nanoTime();
		
			if( valueY == -GameValues.CHARACTER_SPEED ) {
				if( Avatar.walkFlag ) {
					Avatar.setSprite(Avatar.avatarSprites.get(StanceEnum.FRONT_WALK1.getValue()));
					Avatar.walkFlag = false;
				} else {
					Avatar.setSprite(Avatar.avatarSprites.get(StanceEnum.FRONT_WALK2.getValue()));
					Avatar.walkFlag = true;
				}
			}
			
			if( valueY == GameValues.CHARACTER_SPEED ) {
				if( Avatar.walkFlag ) {
					Avatar.setSprite(Avatar.avatarSprites.get(StanceEnum.BACK_WALK1.getValue()));
					Avatar.walkFlag = false;
				} else {
					Avatar.setSprite(Avatar.avatarSprites.get(StanceEnum.BACK_WALK2.getValue()));
					Avatar.walkFlag = true;
				}
			}
			
			if( valueX == -GameValues.CHARACTER_SPEED ) {
				if( Avatar.walkFlag ) {
					Avatar.setSprite(Avatar.avatarSprites.get(StanceEnum.LEFT_WALK1.getValue()));
					Avatar.walkFlag = false;
				} else {
					Avatar.setSprite(Avatar.avatarSprites.get(StanceEnum.LEFT_WALK2.getValue()));
					Avatar.walkFlag = true;
				}
			}
			
			if( valueX == GameValues.CHARACTER_SPEED ) {
				if( Avatar.walkFlag ) {
					Avatar.setSprite(Avatar.avatarSprites.get(StanceEnum.RIGHT_WALK1.getValue()));
					Avatar.walkFlag = false;
				} else {
					Avatar.setSprite(Avatar.avatarSprites.get(StanceEnum.RIGHT_WALK2.getValue()));
					Avatar.walkFlag = true;
				}
			}
			
		}
			
		
	}
	
	public void checkEvents() {
		
		float valueX = 0, valueY = 0;
		
		// Check if user touched or clicked
		if( Gdx.input.isTouched() ) {
			
			String face = "";
			
			GameValues.touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			GameValues.camera.unproject(GameValues.touchPos);
			
			// Up arrow is clicked/touched
			if( GameValues.touchPos.x >= GameValues.dpad.getUpArrowRectangle().x &&
				GameValues.touchPos.x <= GameValues.dpad.getUpArrowRectangle().x + GameValues.dpad.getUpArrowRectangle().width &&
				GameValues.touchPos.y >= GameValues.dpad.getUpArrowRectangle().y &&
				GameValues.touchPos.y <= GameValues.dpad.getUpArrowRectangle().y + GameValues.dpad.getUpArrowRectangle().height) {
				valueY = GameValues.CHARACTER_SPEED;
				face = "back";
			}
			
			// Down arrow is clicked/touched
			if( GameValues.touchPos.x >= GameValues.dpad.getDownArrowRectangle().x &&
				GameValues.touchPos.x <= GameValues.dpad.getDownArrowRectangle().x + GameValues.dpad.getDownArrowRectangle().width &&
				GameValues.touchPos.y >= GameValues.dpad.getDownArrowRectangle().y &&
				GameValues.touchPos.y <= GameValues.dpad.getDownArrowRectangle().y + GameValues.dpad.getDownArrowRectangle().height) {
				valueY = -GameValues.CHARACTER_SPEED;
				face = "front";
			}
			
			// Left arrow is clicked/touched
			if( GameValues.touchPos.x >= GameValues.dpad.getLeftArrowRectangle().x &&
				GameValues.touchPos.x <= GameValues.dpad.getLeftArrowRectangle().x + GameValues.dpad.getLeftArrowRectangle().width &&
				GameValues.touchPos.y >= GameValues.dpad.getLeftArrowRectangle().y &&
				GameValues.touchPos.y <= GameValues.dpad.getLeftArrowRectangle().y + GameValues.dpad.getLeftArrowRectangle().height) {
				valueX = -GameValues.CHARACTER_SPEED;
				face = "left";
			}
			
			// Right arrow is clicked/touched
			if( GameValues.touchPos.x >= GameValues.dpad.getRightArrowRectangle().x &&
				GameValues.touchPos.x <= GameValues.dpad.getRightArrowRectangle().x + GameValues.dpad.getRightArrowRectangle().width &&
				GameValues.touchPos.y >= GameValues.dpad.getRightArrowRectangle().y &&
				GameValues.touchPos.y <= GameValues.dpad.getRightArrowRectangle().y + GameValues.dpad.getRightArrowRectangle().height) {
				valueX = GameValues.CHARACTER_SPEED;
				face = "right";
			}
			
			if( !face.equals(Avatar.facingFlag) ) {
				Avatar.animationFlag = 0;
			}
			
			Avatar.facingFlag = face;
			
			Dpad.moveAvatar(valueX, valueY);
			
		} else {
			if( Avatar.facingFlag.equals("front") ) {
				Avatar.setSprite(Avatar.avatarSprites.get(StanceEnum.FRONT_STAND.getValue()));
			} else if( Avatar.facingFlag.equals("back") ) {
				Avatar.setSprite(Avatar.avatarSprites.get(StanceEnum.BACK_STAND.getValue()));
			} else if( Avatar.facingFlag.equals("left") ) {
				Avatar.setSprite(Avatar.avatarSprites.get(StanceEnum.LEFT_STAND.getValue()));
			} else if( Avatar.facingFlag.equals("right") ) {
				Avatar.setSprite(Avatar.avatarSprites.get(StanceEnum.RIGHT_STAND.getValue()));
			}
		}
		
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

}
