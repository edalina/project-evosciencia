package com.eciz.evosciencia.resources;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.enums.MonsterEnum;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.manager.pool.EnemyPool;
import com.eciz.evosciencia.values.GameValues;

public class LoadAssets {

	public LoadAssets() {
	}
	
	public static void loadAllAssets() {
		LoadAssets.loadAvatarAssets();
		LoadAssets.loadAllMonsters();
		LoadAssets.loadPools();
	}
	
	public static void loadAllMonsters() {
		for(MonsterEnum monsterEnum : MonsterEnum.values()) {
			Map<String, Sprite> monsters = new LinkedHashMap<String, Sprite>();
			for( StanceEnum stanceEnum : StanceEnum.values()  ) {
				monsters.put(stanceEnum.getValue(), new Sprite(new Texture(Gdx.files.internal("monsters/" + monsterEnum.getValue() + "/" + stanceEnum.getValue() + ".png"))));
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
			GameValues.avatar.avatarSprites.put(stanceEnum.getValue(), new Sprite(new Texture(Gdx.files.internal("avatar/boy_1/" + stanceEnum.getValue() + ".png"))));
		}
		
		GameValues.avatar.sprite = GameValues.avatar.avatarSprites.get(StanceEnum.FRONT_STAND.getValue());
	}
	
}
