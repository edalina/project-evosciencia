package com.eciz.evosciencia.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eciz.evosciencia.resources.Maps;
import com.eciz.evosciencia.values.GameValues;

public class GameScreenActor extends Table {
	
	public GameScreenActor() {
		
		GameValues.settingUtils.stopBGM();
		setBounds(0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		setClip(true);
		
		GameValues.currentBatch = new SpriteBatch();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.touchPos = new Vector3();
		
		GameValues.maps = Maps.getMaps();
		GameValues.avatar.repositionAvatar(( GameValues.SCREEN_WIDTH - GameValues.avatar.getWidth() ) / 2, ( GameValues.SCREEN_HEIGHT - GameValues.avatar.getHeight() )/2);
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		// Updating camera/frame
		GameValues.camera.update();
		
		// Set tiled map
		GameValues.maps.renderNonObstacleObj();
		
		// Initialize batch
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.currentBatch.begin();
		GameValues.currentBatch.draw(GameValues.avatar.sprite, GameValues.avatar.getX(), GameValues.avatar.getY(), GameValues.avatar.getWidth(), GameValues.avatar.getHeight());
		GameValues.currentBatch.end();
		
		GameValues.maps.renderObstacleObj();
		
		GameValues.currentBatch.begin();
		GameValues.dpad.drawDpad();
		GameValues.currentBatch.end();
		
		// Check if event is triggered
		GameValues.dpad.checkEvents();
	}
	
}
