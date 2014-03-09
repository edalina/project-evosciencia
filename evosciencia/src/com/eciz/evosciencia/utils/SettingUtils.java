package com.eciz.evosciencia.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.eciz.evosciencia.values.GameSettings;

public class SettingUtils {

	private String currentBGM;
	private Music bgm;
	
	public void startBGM(String path) {
//		currentBGM = path;
//		bgm = Gdx.audio.newMusic(Gdx.files.internal(currentBGM));
//		bgm.setVolume(0.5f);
//		bgm.setLooping(true);
//		if( GameSettings.bgm ) {
//			bgm.play();
//		}
	}
	
	public void stopBGM() {
//		if( bgm != null ) {
//			bgm.stop();
//		}
	}
	
	public void toggleBGM() {
//		if( GameSettings.bgm ) {
//			GameSettings.bgm = false;
//			stopBGM();
//		} else {
//			GameSettings.bgm = true;
//			startBGM(currentBGM);
//		}
	}

	public void toggleSFX() {
//		if( GameSettings.sfx ) {
//			GameSettings.sfx = false;
//		} else {
//			GameSettings.sfx = true;
//		}
	}
	
}
