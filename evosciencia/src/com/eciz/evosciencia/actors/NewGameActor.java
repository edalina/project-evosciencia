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
import com.eciz.evosciencia.entities.Avatar;
import com.eciz.evosciencia.entities.CharacterSlot;
import com.eciz.evosciencia.resources.LoadAssets;
import com.eciz.evosciencia.screens.IntroScreen;
import com.eciz.evosciencia.utils.EventUtils;
import com.eciz.evosciencia.values.GameValues;

public class NewGameActor extends Table {
	
	private Texture saveDataBox;
	private Texture characterSlotTexture;
	private Texture characterSlotTextureActive;
	private Texture dataBox;
	private List<CharacterSlot> characterSlots;
	
	private int coor = 50;
	private Rectangle dataBoxRect;
	private BitmapFont dataText;
	private String currentText = "";
	
	private Texture[] charactersTextures;
	
	private static final String[] CHARACTERS = {
		"<Yjae>",
		"<Carlo>",
		"<Ia>",
		"<Zhandy>"};
	
	public NewGameActor() {
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
		
		charactersTextures = new Texture[4];
		
		characterSlots = new ArrayList<CharacterSlot>();
		
		for( int i = 0 ; i < CHARACTERS.length ; i++ ) {
			CharacterSlot characterSlot = new CharacterSlot();
			characterSlot.setId(i);
			characterSlot.setTexture(characterSlotTexture);
			characterSlot.setActive(false);
			characterSlot.setDefinition(CHARACTERS[i]);
			
			charactersTextures[i] = new Texture(Gdx.files.internal("images/selection_" + characterSlot.getDefinition().split(">")[0].replace("<", "").toLowerCase() + ".jpg"));
			
			Rectangle rectangle = new Rectangle();
			rectangle.width = (GameValues.SCREEN_WIDTH - (coor*2) - 20)/4;
			rectangle.height = ((GameValues.SCREEN_HEIGHT - (coor*2))/2) - 20;
			rectangle.x = (i * rectangle.width)+  (coor + 10);
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
		int i = 0;
		for( CharacterSlot characterSlot : characterSlots ) {
			GameValues.currentBatch.draw(charactersTextures[i], characterSlot.getRectangle().getX(), characterSlot.getRectangle().y, characterSlot.getRectangle().width, characterSlot.getRectangle().height);
			GameValues.currentBatch.draw(characterSlot.getTexture(), characterSlot.getRectangle().x, characterSlot.getRectangle().y, characterSlot.getRectangle().width, characterSlot.getRectangle().height);
			i++;
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
			GameValues.avatar = new Avatar();
			GameValues.user = GameValues.dataHandler.getUsers().get(characterSlot.getId());
			GameValues.avatar.name = characterSlot.getDefinition().split(">")[0].replace("<", "").toLowerCase();
			LoadAssets.loadAvatarAssets();
			GameValues.touchDown = false;
			GameValues.currentScreen = new IntroScreen();
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
