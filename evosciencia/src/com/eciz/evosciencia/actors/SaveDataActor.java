package com.eciz.evosciencia.actors;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eciz.evosciencia.EvoSciencia;
import com.eciz.evosciencia.entities.CharacterSlot;
import com.eciz.evosciencia.entities.User;
import com.eciz.evosciencia.screens.GameScreen;
import com.eciz.evosciencia.screens.NewGameScreen;
import com.eciz.evosciencia.utils.EventUtils;
import com.eciz.evosciencia.values.GameValues;

public class SaveDataActor extends Table {
	
	private Texture saveDataBox;
	private Texture characterSlotTexture;
	private Texture characterSlotTextureActive;
	private Texture dataBox;
	private List<CharacterSlot> characterSlots;
	
	private int coor = 50;
	private Rectangle dataBoxRect;
	private BitmapFont dataText;
	private String currentText = "";
	
	public SaveDataActor() {
		setBounds(0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		setClip(true);
		
		GameValues.currentBatch = new SpriteBatch();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.touchPos = new Vector3();
		
		saveDataBox = new Texture(Gdx.files.internal("images/character_box.png"));
		dataBox = new Texture(Gdx.files.internal("images/data_box.png"));
		characterSlotTexture = new Texture(Gdx.files.internal("images/character_slot.png"));
		characterSlotTextureActive = new Texture(Gdx.files.internal("images/character_slot_active.png"));
		
		dataBoxRect = new Rectangle();
		
		dataBoxRect.set(coor + 10, coor + 10, GameValues.SCREEN_WIDTH - (coor*2) - 20, ((GameValues.SCREEN_HEIGHT - (coor*2))/2) - 20);
		
		dataText = new BitmapFont();
		
		characterSlots = new ArrayList<CharacterSlot>();
		
		for( User user : GameValues.dataHandler.getUsers() ) {
			CharacterSlot characterSlot = new CharacterSlot();
			characterSlot.setId(user.getId());
			characterSlot.setTexture(characterSlotTexture);
			characterSlot.setPlaytime(user.getPlaytime());
			characterSlot.setActive(false);
			if( user.getPlaytime() == 0 )
				characterSlot.setDefinition("NEW GAME");
			else
				characterSlot.setDefinition("Avatar: " + user.getAvatar() + "\nPlaytime: " + user.getPlaytime());
			
			Rectangle rectangle = new Rectangle();
			rectangle.width = (GameValues.SCREEN_WIDTH - (coor*2) - 20)/5;
			rectangle.height = ((GameValues.SCREEN_HEIGHT - (coor*2))/2) - 20;
			rectangle.x = (user.getId() * rectangle.width)+  (coor + 10);
			rectangle.y = coor + (GameValues.SCREEN_HEIGHT - (coor*2))/2;
			characterSlot.setRectangle(rectangle);
			characterSlots.add(characterSlot);
		}
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		GameValues.camera.update();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.currentBatch.begin();
		GameValues.currentBatch.draw(saveDataBox, coor, coor, GameValues.SCREEN_WIDTH - (coor*2), GameValues.SCREEN_HEIGHT - (coor*2));
		GameValues.currentBatch.draw(dataBox, dataBoxRect.getX(), dataBoxRect.getY(), dataBoxRect.getWidth(), dataBoxRect.getHeight());
		dataText.drawMultiLine(GameValues.currentBatch, currentText, dataBoxRect.getX() + 10, (dataBoxRect.getY() + dataBoxRect.getHeight()) - 10);
		for( CharacterSlot characterSlot : characterSlots ) {
			GameValues.currentBatch.draw(characterSlot.getTexture(), characterSlot.getRectangle().x, characterSlot.getRectangle().y, characterSlot.getRectangle().width, characterSlot.getRectangle().height);
		}
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
			if( characterSlot.getPlaytime() == 0 ) {
				GameValues.currentScreen = new NewGameScreen();
			} else {
				GameValues.currentScreen = new GameScreen();
			}
			EvoSciencia.getMainInstance().setScreen(GameValues.currentScreen);
			return;
		}
		for( CharacterSlot otherSlots : characterSlots ) {
			otherSlots.setActive(false);
			otherSlots.setTexture(characterSlotTexture);
		}
		characterSlot.setActive(true);
		characterSlot.setTexture(characterSlotTextureActive);
		currentText = characterSlot.getDefinition();
	}

}
