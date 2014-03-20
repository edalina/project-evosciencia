package com.eciz.evosciencia.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eciz.evosciencia.controls.MenuControls;
import com.eciz.evosciencia.enums.BGMEnum;
import com.eciz.evosciencia.resources.LoadAssets;
import com.eciz.evosciencia.values.GameValues;

public class MenuScreenActor extends Table {
	
	public MenuScreenActor() {
		setBounds(0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		setClip(true);
		
		GameValues.currentBatch = new SpriteBatch();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.touchPos = new Vector3();
		
		MenuControls.getInstance();
		
		GameValues.settingUtils.startBGM(BGMEnum.BGM_2.getValue());
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		GameValues.camera.update();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		
		GameValues.currentBatch.begin();
		GameValues.currentBatch.draw(LoadAssets.splashTexture, 0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		MenuControls.getInstance().draw();
		GameValues.currentBatch.end();
		MenuControls.getInstance().checkEvents();
	}
	
}
