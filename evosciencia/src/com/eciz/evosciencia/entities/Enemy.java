package com.eciz.evosciencia.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eciz.evosciencia.enums.StanceEnum;

public class Enemy extends Actor {

	public static final int width = 64;
	public static final int height = 64;
	
	private int id;
	private Sprite currentSprite;
	private StanceEnum facing;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Sprite getCurrentSprite() {
		return currentSprite;
	}
	public void setCurrentSprite(Sprite currentSprite) {
		this.currentSprite = currentSprite;
	}
	public StanceEnum getFacing() {
		return facing;
	}
	public void setFacing(StanceEnum facing) {
		this.facing = facing;
	}
	
	
}
