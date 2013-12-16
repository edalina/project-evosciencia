package com.eciz.evosciencia.resources;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.values.GameValues;

public class LoadAssets {

	public LoadAssets() {
	}
	
	public static void loadAllAssets() {
		LoadAssets.loadAvatarAssets();
		LoadAssets.loadAllMonsters();
	}
	
	public static void loadAllMonsters() {
		Map<String, Sprite> goblins = new LinkedHashMap<String, Sprite>();
		for( StanceEnum stanceEnum : StanceEnum.values()  ) {
			goblins.put(stanceEnum.getValue(), new Sprite(new Texture(Gdx.files.internal("monsters/goblin/" + stanceEnum.getValue() + ".png"))));
		}
		GameValues.monsters.put("goblin", goblins);
	}
	
	public static void loadAvatarAssets() {
		Avatar.avatarSprites = new HashMap<String, Sprite>();
		
		for( StanceEnum stanceEnum : StanceEnum.values() ) {
			Avatar.avatarSprites.put(stanceEnum.getValue(), new Sprite(new Texture(Gdx.files.internal("avatar/boy_1/" + stanceEnum.getValue() + ".png"))));
		}
		
		Avatar.sprite = Avatar.avatarSprites.get(StanceEnum.FRONT_STAND.getValue());
	}
	
}
