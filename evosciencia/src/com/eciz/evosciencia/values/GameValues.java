package com.eciz.evosciencia.values;

import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.eciz.evosciencia.controls.Dpad;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.entities.Enemy;
import com.eciz.evosciencia.resources.Maps;

public class GameValues {
	
	public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
	public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();

	public static final int CHARACTER_WIDTH = Avatar.width;
	public static final int CHARACTER_HEIGHT = Avatar.height;
	public static final int ENEMY_WIDTH = Enemy.width;
	public static final int ENEMY_HEIGHT = Enemy.height;
	public static final int DPAD_WIDTH = 32;
	public static final int DPAD_HEIGHT = 32;
	
	public static Screen currentScreen;
	
	public static Vector3 touchPos;
	
	public static OrthographicCamera camera;

	public static Avatar avatar;
	
	public static Dpad dpad;
	
	public static Maps maps;
	
	public static final int ENEMY_COUNT_PER_MAP = 30;
	
	public static final int CHARACTER_SPEED = 1;
	
	public static final long ANIMATION_SPEED = 300000000;
	
	public static Map<String, Map<String, Sprite>> monsters = new LinkedHashMap<String, Map<String,Sprite>>();
	
	public static Batch currentBatch;
}
