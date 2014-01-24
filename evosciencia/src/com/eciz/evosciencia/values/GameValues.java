package com.eciz.evosciencia.values;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.eciz.evosciencia.controls.Dpad;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.entities.Checkpoint;
import com.eciz.evosciencia.entities.Enemy;
import com.eciz.evosciencia.resources.Maps;

public class GameValues {
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 400;

	public static final int CHARACTER_WIDTH = Avatar.width;
	public static final int CHARACTER_HEIGHT = Avatar.height;
	public static final int ENEMY_WIDTH = Enemy.width;
	public static final int ENEMY_HEIGHT = Enemy.height;
	public static final int DPAD_WIDTH = 16;
	public static final int DPAD_HEIGHT = 16;
	
	public static Screen currentScreen;
	
	public static Vector3 touchPos;
	
	public static OrthographicCamera camera;
	
	public static Avatar avatar;
	
	public static List<Rectangle> collisions;
	
	public static List<Checkpoint> checkpoints;
	
	public static Dpad dpad;
	
	public static Maps maps;
	
	public static final float CAMERA_ZOOM = 0.5f;
	public static final float CAMERA_ZOOM_MULTIPLE = 8*CAMERA_ZOOM;
	
	public static final int ENEMY_COUNT_PER_MAP = 30;
	
	public static final float CHARACTER_SPEED = 1;
	
	public static final long ANIMATION_SPEED = 200000000;
	
	public static Map<String, Map<String, Sprite>> monsters = new LinkedHashMap<String, Map<String,Sprite>>();
	
	public static Batch currentBatch;
	
	public static final String COLLISION_PROPERTY = "collision";
	
	public static final String OBSTACLE_PROPERTY = "obstacles_";
	
	public static final String CHECK_POINT = "cpoint";
}
