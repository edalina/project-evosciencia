package com.eciz.evosciencia.entities;

import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Avatar extends Actor {
	
	public static int id;
	public static final int width = 64;
	public static final int height = 64;
	public static int x;
	public static int y;
	public static Map<String, Sprite> avatarSprites;
	public static String name;
	public static Sprite sprite;
	
	// Walking flag, 0 = right 1 = left
	public static boolean walkFlag = true;
	// Animation flag
	public static long animationFlag = 0;
	public static String facingFlag = "front";
	
	public Avatar() {
		
		
	}
	
	public static void setSprite(Sprite sprite) {
		Avatar.sprite = sprite;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
	}
	
}
