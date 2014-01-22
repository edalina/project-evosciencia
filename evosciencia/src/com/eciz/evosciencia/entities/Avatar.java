package com.eciz.evosciencia.entities;

import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.eciz.evosciencia.controls.Dpad;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.resources.Maps;
import com.eciz.evosciencia.values.GameValues;

public class Avatar extends Rectangle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4502025065283224473L;
	
	public int id;
	public static final int width = (int) ( 16 * Maps.MAP_UNIT_SCALE );
	public static final int height = (int) ( 16 * Maps.MAP_UNIT_SCALE );
	public int x;
	public int y;
	public Map<String, Sprite> avatarSprites;
	public String name = "yjae";
	public Sprite sprite;
	
	// Walking flag, 0 = right 1 = left
	public boolean walkFlag = true;
	// Animation flag
	public long animationFlag = 0;
	public StanceEnum facingFlag = StanceEnum.FRONT_STAND;
	
	public Avatar() {
		setWidth(Avatar.width);
		setHeight(Avatar.height);
	}
	
	public void setSprite(Sprite sprite) {
		GameValues.avatar.sprite = sprite;
	}
	
	public void repositionAvatar(float x, float y) {
		boolean isX = false, isY = false;
		GameValues.avatar.setX(x);
		GameValues.avatar.setY(y);
		GameValues.avatar.sprite.getBoundingRectangle().set(GameValues.avatar.getX(),
				GameValues.avatar.getY(), 
				GameValues.avatar.getWidth(), 
				GameValues.avatar.getHeight());
		
		if( !(GameValues.avatar.getX() <= GameValues.CAMERA_WIDTH/2 ||
			GameValues.avatar.getX()  >= Maps.MAP_WIDTH - (GameValues.CAMERA_WIDTH/2)) ) {
			angleCameraOnAvatarOnX();
			isX = true;
		}
	
		if( !(GameValues.avatar.getY()  <= GameValues.CAMERA_HEIGHT/2 ||
			GameValues.avatar.getY() >= Maps.MAP_HEIGHT - (GameValues.CAMERA_HEIGHT/2)) ) {
			angleCameraOnAvatarOnY();
			isY = true;
		}
		
		Dpad.positionDpad(isX, isY);
		
	}
	
	public void updateStandBy() {
		GameValues.avatar.setSprite(GameValues.avatar.avatarSprites.get(GameValues.avatar.facingFlag.getValue()));
	}
	
	public void angleCameraOnAvatar() {
		GameValues.camera.position.x = GameValues.avatar.getX();
		GameValues.camera.position.y = GameValues.avatar.getY();
	}
	
	public void angleCameraOnAvatarOnX() {
		GameValues.camera.position.x = GameValues.avatar.getX();
	}
	
	public void angleCameraOnAvatarOnY() {
		GameValues.camera.position.y = GameValues.avatar.getY();
	}
	
}
