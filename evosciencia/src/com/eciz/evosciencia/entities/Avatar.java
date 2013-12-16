package com.eciz.evosciencia.entities;

import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eciz.evosciencia.values.GameValues;

public class Avatar extends Actor {
	
	public int id;
	public static final int width = 64;
	public static final int height = 64;
	public int x;
	public int y;
	public Map<String, Sprite> avatarSprites;
	public String name;
	public Sprite sprite;
	
	// Walking flag, 0 = right 1 = left
	public boolean walkFlag = true;
	// Animation flag
	public long animationFlag = 0;
	public String facingFlag = "front";
	
	public Avatar() {
		setWidth(Avatar.width);
		setHeight(Avatar.height);
	}
	
	public void setSprite(Sprite sprite) {
		GameValues.avatar.sprite = sprite;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
	}
	
}
