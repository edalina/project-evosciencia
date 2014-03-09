package com.eciz.evosciencia.actors;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.eciz.evosciencia.EvoSciencia;
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.entities.CharacterSlot;
import com.eciz.evosciencia.resources.LoadAssets;
import com.eciz.evosciencia.screens.IntroScreen;
import com.eciz.evosciencia.utils.EventUtils;
import com.eciz.evosciencia.values.GameValues;

public class NewGameActor extends Table {
	
	private Texture saveDataBox;
	private List<CharacterSlot> characterSlots;
	
	private static final String[] CHARACTERS = {
		"yjae",
		"carlo",
		"ia",
		"zhandy"};
	
	public NewGameActor() {
		setBounds(0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		setClip(true);
		
		GameValues.currentBatch = new SpriteBatch();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.touchPos = new Vector3();
		
		saveDataBox = new Texture(Gdx.files.internal("images/character_box.png"));
		
		characterSlots = new ArrayList<CharacterSlot>();
		
		int slotWidth = 105, slotHeight = 140, slotY = 145, slotX = 105, deviationX = 162;
		
		// Adding character slots
		for( int i = 0 ; i < CHARACTERS.length ; i++ ) {
			
			CharacterSlot slot = new CharacterSlot();
			slot.setId(i);
			slot.setActive(false);
			slot.setDefinition("");
			slot.setTexture(new Texture(Gdx.files.internal("images/selection_" + CHARACTERS[i] + ".png")));
			slot.getExtras().put("avatarName", CHARACTERS[i]);
		
			Rectangle rect = new Rectangle();
			rect.set(slotX, slotY, slotWidth, slotHeight);
			slot.setRectangle(rect);
			
			characterSlots.add(slot);
			
			slotX += deviationX;
			
		}
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		GameValues.camera.update();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.currentBatch.begin();
		GameValues.currentBatch.draw(saveDataBox, 10, 10, GameValues.SCREEN_WIDTH - 20, GameValues.SCREEN_HEIGHT - 20);
		GameValues.currentBatch.end();
		if( Gdx.input.isTouched() ) {
			if( !GameValues.touchDown ) {
				GameValues.touchDown = true;
				for( CharacterSlot characterSlot : characterSlots ) {
					if( EventUtils.isTap(characterSlot.getRectangle()) ) {
						activateSlot(characterSlot);
					}
				}
			}
		} else {
			GameValues.touchDown = false;
		}
	}
	
	public void activateSlot(CharacterSlot characterSlot) {
		if( characterSlot.isActive() ) {
			GameValues.avatar = new Avatar();
			GameValues.user = GameValues.dataHandler.getUsers().get(characterSlot.getId());
			GameValues.avatar.name = characterSlot.getExtras().get("avatarName").toString();
			LoadAssets.loadAvatarAssets();
			GameValues.touchDown = false;
			LoadAssets.loadIntroAssets();
			GameValues.user.setPlaytime(TimeUtils.millis());
			GameValues.currentScreen = new IntroScreen();
			EvoSciencia.getMainInstance().setScreen(GameValues.currentScreen);
			return;
		}
		for( CharacterSlot otherSlots : characterSlots ) {
			otherSlots.setActive(false);
		}
		characterSlot.setActive(true);
		saveDataBox = characterSlot.getTexture();
	}

}
