package com.eciz.evosciencia.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eciz.evosciencia.controls.Dpad;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.enums.BGMEnum;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.resources.Maps;
import com.eciz.evosciencia.utils.DialogUtils;
import com.eciz.evosciencia.values.GameValues;

public class GameScreenActor extends Table {
	
	private Texture npcTexture;
	private Rectangle npcRectangle;
	
	public GameScreenActor() {
		
		GameValues.settingUtils.stopBGM();
		GameValues.settingUtils.startBGM(BGMEnum.BGM_5.getValue());
		setBounds(0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		setClip(true);
		
		GameValues.currentBatch = new SpriteBatch();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.touchPos = new Vector3();
		
		GameValues.maps = Maps.getMaps();
		
		if( GameValues.isNewGame ) {
			GameValues.avatar.facingFlag = StanceEnum.BACK_STAND_1;
			GameValues.avatar.sprite = GameValues.avatar.avatarSprites.get(StanceEnum.BACK_STAND_1.getValue());
		}

		GameValues.currentScientist = GameValues.dataHandler.getScientists().get((GameValues.user.getScientists()[Integer.parseInt(GameValues.maps.name.replace("town_", ""))-1]));
		
		npcTexture = new Texture(Gdx.files.internal("npc/" + GameValues.currentScientist.getName() + ".png"));
		npcRectangle = new Rectangle();
		
		float x = ( GameValues.SCREEN_WIDTH - GameValues.avatar.getWidth() ) / 2,
			y = ( GameValues.SCREEN_HEIGHT - GameValues.avatar.getHeight() ) / 2;
		
		npcRectangle.set(x, y + (Avatar.height * 5), Avatar.width, Avatar.height);
		
		GameValues.currentScientist.setRectangle(npcRectangle);
		
		GameValues.collisions.add(npcRectangle);
		
		new DialogUtils();
		
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
		if( DialogUtils.isDialogActive && GameValues.isNewGame ) {
			GameValues.currentBatch.draw(new Texture(Gdx.files.internal("npc/albert einstein.png")), GameValues.avatar.getX(), GameValues.avatar.getY() + (GameValues.avatar.getHeight()*2), Avatar.width, Avatar.height);
		}
		
		DialogUtils.createDialog();
		GameValues.currentBatch.draw(npcTexture, npcRectangle.getX(), npcRectangle.getY(), npcRectangle.getWidth(), npcRectangle.getHeight());
		GameValues.currentBatch.draw(GameValues.questMarkActive, npcRectangle.getX(), npcRectangle.getY(), npcRectangle.getWidth(), npcRectangle.getHeight());
		GameValues.currentBatch.draw(GameValues.avatar.sprite, GameValues.avatar.getX(), GameValues.avatar.getY(), GameValues.avatar.getWidth(), GameValues.avatar.getHeight());
		GameValues.currentBatch.end();
		
		GameValues.maps.renderObstacleObj();
		
		GameValues.currentBatch.begin();
		GameValues.dpad.drawDpad();
		
		// Check if event is triggered
		GameValues.dpad.checkEvents();
		
		if( GameValues.isNewGame ) {
			Dpad.isDpadActive = false;
			DialogUtils.isDialogActive = true;
			DialogUtils.introDialogs();
		}
		
		GameValues.currentBatch.end();
		
	}
	
}
