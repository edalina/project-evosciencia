package com.eciz.evosciencia.resources;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Json;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.entities.DataHandler;
import com.eciz.evosciencia.entities.Scientist;
import com.eciz.evosciencia.enums.MonsterEnum;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.utils.SettingUtils;
import com.eciz.evosciencia.values.GameValues;

public class LoadAssets {
	
	public static Texture splashTexture;
	
	public static AssetManager assetManager;

	public LoadAssets() {
	}
	
	public static void loadAllAssets() {
		assetManager = new AssetManager();
		LoadAssets.loadAllMonsters();
		LoadAssets.loadEtc();
		LoadAssets.loadScientists();
//		LoadAssets.loadPools();
//		LoadAssets.loadIntroAssets();
		GameValues.settingUtils = new SettingUtils();
	}
	
	private static void loadScientists() {
		for( Scientist scientist : GameValues.dataHandler.getScientists() ) {
			assetManager.load("npc/" + scientist.getName() + ".png", Texture.class);
		}
		assetManager.load("npc/npc_unknown.png", Texture.class);
	}

	private static void loadEtc() {
		
		Json json = new Json();
		GameValues.dataHandler = json.fromJson(DataHandler.class, Gdx.files.internal("data/data.json"));
		
		assetManager.load("images/quest.png", Texture.class);
		
		GameValues.questMarkActive = new Texture(Gdx.files.internal("images/quest.png"));
		GameValues.questMarkInactive = new Texture(Gdx.files.internal("images/quest_inactive.png"));
		
		assetManager.load("images/toggle_on.png", Texture.class);
		assetManager.load("images/toggle_off.png", Texture.class);
		
		assetManager.load("images/data_box.png", Texture.class);
		assetManager.load("images/character_box.png", Texture.class);
		assetManager.load("images/character_slot.png", Texture.class);
		assetManager.load("images/character_slot_active.png", Texture.class);
		
		assetManager.load("images/einstein.png", Texture.class);
		assetManager.load("images/dialog.png", Texture.class);
		
		assetManager.load("images/selection_carlo.png", Texture.class);
		assetManager.load("images/selection_ia.png", Texture.class);
		assetManager.load("images/selection_yjae.png", Texture.class);
		assetManager.load("images/selection_zhandy.png", Texture.class);
		
	}
	
	public static void loadIntroAssets() {
		GameValues.introAnimations = new Texture[72];
		GameValues.introIndexPointer = 1;
		
		for( int i = 1 ; i <= GameValues.introAnimations.length ; i++ ) {
			String pathName = "intro/00" + ( i < 10 ? "0" : "" ) + ( i < 100 ? "0" : "" ) + i + ".jpg";
			assetManager.load(pathName, Texture.class);
			GameValues.introAnimations[i-1] = new Texture(Gdx.files.internal(pathName));
		}
	}

	public static void loadAllMonsters() {
		for(MonsterEnum monsterEnum : MonsterEnum.values()) {
			Map<String, Texture> monsters = new LinkedHashMap<String, Texture>();
			for( StanceEnum stanceEnum : StanceEnum.values()  ) {
				String path = "monsters/" + monsterEnum.getValue() + "/" + stanceEnum.getValue() + ".png";
				assetManager.load(path, Texture.class);
				monsters.put(stanceEnum.getValue(), new Texture(Gdx.files.internal(path)));
			}
			GameValues.monsters.put(monsterEnum.getValue(), monsters);
		}
	}
	
	public static void loadPools() {
//		new EnemyPool();
	}
	
//	public static void 
	
	public static void loadAvatarAssets() {
		
		GameValues.avatar.avatarSprites = new HashMap<String, Sprite>();
		
		for( StanceEnum stanceEnum : StanceEnum.values() ) {
			String path = "avatar/" + GameValues.avatar.name + "/" + stanceEnum.getValue() + ".png";
			assetManager.load(path, Texture.class);
			GameValues.avatar.avatarSprites.put(stanceEnum.getValue(), new Sprite(new Texture(Gdx.files.internal(path))));
		}
		
		GameValues.avatar.sprite = GameValues.avatar.avatarSprites.get(StanceEnum.FRONT_STAND.getValue());
		
		GameValues.avatar.sprite.setBounds(GameValues.avatar.getX(), GameValues.avatar.getY(), Avatar.width, Avatar.height);
	}
	
}
