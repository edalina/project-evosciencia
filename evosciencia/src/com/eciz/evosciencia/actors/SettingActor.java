package com.eciz.evosciencia.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eciz.evosciencia.EvoSciencia;
import com.eciz.evosciencia.screens.MenuScreen;
import com.eciz.evosciencia.utils.EventUtils;
import com.eciz.evosciencia.values.GameSettings;
import com.eciz.evosciencia.values.GameValues;

public class SettingActor extends Table {
	
	private BitmapFont title;
	private BitmapFont sfx;
	private BitmapFont bgm;
	private BitmapFont save;
	
	private Texture sfxTexture, bgmTexture;
	private Rectangle sfxRect, bgmRect, saveRect;
	
	private Texture saveDataBox;
	
	private int coor = 30;
	
	public SettingActor() {
		setBounds(0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		setClip(true);
		
		GameValues.currentBatch = new SpriteBatch();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.touchPos = new Vector3();
		
		saveDataBox = new Texture(Gdx.files.internal("images/dialog.png"));
		
		sfxTexture = new Texture(Gdx.files.internal("images/toggle_on.png"));
		bgmTexture = new Texture(Gdx.files.internal("images/toggle_on.png"));
		
		title = new BitmapFont();
		bgm = new BitmapFont();
		sfx = new BitmapFont();
		save = new BitmapFont();
		
		title.setScale(2);
		
		sfxRect = new Rectangle();
		bgmRect = new Rectangle();
		saveRect = new Rectangle();
		
		sfxRect.set( sfx.getBounds("sfx").width + (coor*3) + coor, GameValues.SCREEN_HEIGHT - (coor*3), 20, 20);
		bgmRect.set( bgm.getBounds("bgm").width + (coor*3) + coor, GameValues.SCREEN_HEIGHT - (coor*4), 20, 20);
		saveRect.set(20, 10, save.getBounds("save").width, 15);
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		GameValues.camera.update();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.currentBatch.begin();
		GameValues.currentBatch.draw(saveDataBox, coor, coor, GameValues.SCREEN_WIDTH - (coor*2), GameValues.SCREEN_HEIGHT - (coor*2));
		title.draw(GameValues.currentBatch, "Options", coor, GameValues.SCREEN_HEIGHT);
		sfx.draw(GameValues.currentBatch, "sfx", coor * 3, sfxRect.y + 17);
		bgm.draw(GameValues.currentBatch, "bgm", coor * 3, bgmRect.y + 17);
		GameValues.currentBatch.draw(sfxTexture, sfxRect.x, sfxRect.y, sfxRect.width, sfxRect.height);
		GameValues.currentBatch.draw(bgmTexture, bgmRect.x, bgmRect.y, bgmRect.width, bgmRect.height);
		save.draw(GameValues.currentBatch, "save", saveRect.x, saveRect.y + 15);
		GameValues.currentBatch.end();
		if( Gdx.input.isTouched() ) {
			if( !GameValues.touchDown ) {
				GameValues.touchDown = true;
				if( EventUtils.isTap(saveRect) ) {
					GameValues.currentScreen = new MenuScreen();
					EvoSciencia.getMainInstance().setScreen(GameValues.currentScreen);
				}
				if( EventUtils.isTap(sfxRect) ) {
					GameValues.settingUtils.toggleSFX();
					if( GameSettings.sfx )
						sfxTexture = new Texture(Gdx.files.internal("images/toggle_on.png"));
					else
						sfxTexture = new Texture(Gdx.files.internal("images/toggle_off.png"));
				}
				if( EventUtils.isTap(bgmRect) ) {
					GameValues.settingUtils.toggleBGM();
					if( GameSettings.bgm )
						bgmTexture = new Texture(Gdx.files.internal("images/toggle_on.png"));
					else
						bgmTexture = new Texture(Gdx.files.internal("images/toggle_off.png"));
				}
			}
		} else {
			GameValues.touchDown = false;
		}
	}
	
}
