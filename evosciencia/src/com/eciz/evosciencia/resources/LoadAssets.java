package com.eciz.evosciencia.resources;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.enums.MonsterEnum;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.manager.pool.EnemyPool;
import com.eciz.evosciencia.values.GameValues;

public class LoadAssets {
	
	public static Texture splashTexture;
	
	public static AssetManager assetManager;

	public LoadAssets() {
	}
	
	public static void loadAllAssets() {
		assetManager = new AssetManager();
		assetManager.finishLoading();
		LoadAssets.loadAvatarAssets();
		LoadAssets.loadAllMonsters();
		LoadAssets.loadEtc();
		LoadAssets.loadPools();
	}
	
	private static void loadEtc() {
		assetManager.load("images/data_box.png", Texture.class);
		assetManager.load("images/character_box.png", Texture.class);
		assetManager.load("images/character_slot.png", Texture.class);
		assetManager.load("images/character_slot_active.png", Texture.class);
	}

	public static void loadAllMonsters() {
		for(MonsterEnum monsterEnum : MonsterEnum.values()) {
			Map<String, Sprite> monsters = new LinkedHashMap<String, Sprite>();
			for( StanceEnum stanceEnum : StanceEnum.values()  ) {
				String path = "monsters/" + monsterEnum.getValue() + "/" + stanceEnum.getValue() + ".png";
				assetManager.load(path, Texture.class);
				monsters.put(stanceEnum.getValue(), new Sprite(new Texture(Gdx.files.internal(path))));
			}
			GameValues.monsters.put(monsterEnum.getValue(), monsters);
		}
	}
	
	public static void loadPools() {
		new EnemyPool();
	}
	
	public static void loadAvatarAssets() {
		
		GameValues.avatar = new Avatar();
		
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
