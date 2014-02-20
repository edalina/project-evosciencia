package com.eciz.evosciencia.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	private Texture questIndicator;
	
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
			GameValues.avatar.facingFlag = StanceEnum.BACK_STAND;
			GameValues.avatar.sprite = GameValues.avatar.avatarSprites.get(StanceEnum.BACK_STAND.getValue());
		}

		questIndicator = GameValues.questMarkInactive;
		
		if( GameValues.currentScientist.getRectangle() != null )
			GameValues.collisions.add(GameValues.maps.createNPCObjects());
		
		new DialogUtils();
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if( Avatar.isQuestActive ) {
			questIndicator = GameValues.questMarkActive;
		} else {
			questIndicator = GameValues.questMarkInactive;
		}
		
		if( GameValues.user.getQuestDone()[GameValues.currentMapValue] ) {
			GameValues.currentScientist.setTexture( new Texture(Gdx.files.internal("npc/" + GameValues.currentScientist.getName() + ".png")) );
		} else {
			GameValues.currentScientist.setTexture( new Texture(Gdx.files.internal("npc/npc_unknown.png")) );
		}
		
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
		
		if( GameValues.currentScientist.getRectangle() != null ) {
			DialogUtils.createDialog();
			GameValues.currentBatch.draw(GameValues.currentScientist.getTexture(), GameValues.currentScientist.getRectangle().getX(), GameValues.currentScientist.getRectangle().getY(), GameValues.currentScientist.getRectangle().getWidth(), GameValues.currentScientist.getRectangle().getHeight());
			GameValues.currentBatch.draw(questIndicator, GameValues.currentScientist.getRectangle().getX() + (GameValues.currentScientist.getRectangle().getWidth()/4), GameValues.currentScientist.getRectangle().getY() + (GameValues.currentScientist.getRectangle().getHeight()/4), GameValues.currentScientist.getRectangle().getWidth()/2, GameValues.currentScientist.getRectangle().getHeight()/2);
		}
		GameValues.maps.renderMonsters();
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
		
		if( GameValues.currentScientist.getRectangle() != null ) {
			DialogUtils.createDialog();
		}
		
		GameValues.currentBatch.end();
		
	}
	
}
