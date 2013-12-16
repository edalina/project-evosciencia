package com.eciz.evosciencia.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eciz.evosciencia.controls.Dpad;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.resources.Maps;
import com.eciz.evosciencia.values.GameValues;

public class GameMapActor extends Table {
	
	private Batch batch;
	
	public GameMapActor() {
		setBounds(0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		setClip(true);
		
		GameValues.dpad = Dpad.positionDpad();
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.touchPos = new Vector3();
		
		GameValues.maps = Maps.getMaps();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		// Updating camera/frame
		GameValues.camera.update();
		
		// Check if event is triggered
		GameValues.dpad.checkEvents();
		
		// Set tiled map
		GameValues.maps.getRenderer().setView(GameValues.camera);
		GameValues.maps.getRenderer().render();
		
		// Initialize batch
		batch.setProjectionMatrix(GameValues.camera.combined);
		batch.begin();
		
		batch.draw(Avatar.sprite, Avatar.x, Avatar.y, Avatar.width, Avatar.height);
		batch.draw(GameValues.dpad.getUpArrow(), GameValues.dpad.getUpArrowRectangle().x, GameValues.dpad.getUpArrowRectangle().y, GameValues.dpad.getUpArrowRectangle().width, GameValues.dpad.getUpArrowRectangle().height);
		batch.draw(GameValues.dpad.getDownArrow(), GameValues.dpad.getDownArrowRectangle().x, GameValues.dpad.getDownArrowRectangle().y, GameValues.dpad.getDownArrowRectangle().width, GameValues.dpad.getDownArrowRectangle().height);
		batch.draw(GameValues.dpad.getLeftArrow(), GameValues.dpad.getLeftArrowRectangle().x, GameValues.dpad.getLeftArrowRectangle().y, GameValues.dpad.getLeftArrowRectangle().width, GameValues.dpad.getLeftArrowRectangle().height);
		batch.draw(GameValues.dpad.getRightArrow(), GameValues.dpad.getRightArrowRectangle().x, GameValues.dpad.getRightArrowRectangle().y, GameValues.dpad.getRightArrowRectangle().width, GameValues.dpad.getRightArrowRectangle().height);
		batch.end();
	}
	
}
