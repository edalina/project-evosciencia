package com.eciz.evosciencia.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eciz.evosciencia.enums.StanceEnum;

public class Enemy extends Actor {

	public static final int width = 64;
	public static final int height = 64;
	
	public int id;
	public Sprite currentSprite;
	public StanceEnum facing;
	
}
