package com.eciz.evosciencia.actors;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.eciz.evosciencia.entities.User;
import com.eciz.evosciencia.resources.LoadAssets;
import com.eciz.evosciencia.screens.GameScreen;
import com.eciz.evosciencia.screens.NewGameScreen;
import com.eciz.evosciencia.utils.AvatarUtils;
import com.eciz.evosciencia.utils.EventUtils;
import com.eciz.evosciencia.values.GameValues;

public class SaveDataActor extends Table {
	
	private Texture saveDataBox;
	private Texture characterSlotTexture;
	private Texture characterSlotTextureActive;
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
		
		saveDataBox = new Texture(Gdx.files.internal("images/save_data_box.png"));
		characterSlotTexture = new Texture(Gdx.files.internal("images/character_slot.png"));
		characterSlotTextureActive = new Texture(Gdx.files.internal("images/character_slot_active.png"));
		
		dataBoxRect = new Rectangle();
		
		dataBoxRect.set(coor, 10, GameValues.SCREEN_WIDTH - (coor*2), ((GameValues.SCREEN_HEIGHT - (coor*3))/2) - 5);
		
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
			else {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(user.getPlaytime());
				String date = (calendar.get(Calendar.HOUR)) + ":" +
							(calendar.get(Calendar.MINUTE) + 1) + " " +
							(calendar.get(Calendar.MONTH) + 1) + "/" +
							(calendar.get(Calendar.DATE)) + "/" +
							calendar.get(Calendar.YEAR);
				characterSlot.setDefinition("Avatar: " + user.getAvatar() + "\nDate Created: " + date );
			}
			
			Rectangle rectangle = new Rectangle();
			rectangle.width = (GameValues.SCREEN_WIDTH - (coor*4) - 30)/5;
			rectangle.height = ((GameValues.SCREEN_HEIGHT - (coor*2))/2) - 20;
			rectangle.x = (user.getId() * rectangle.width) + coor +  (coor + 12);
			rectangle.y = coor + (GameValues.SCREEN_HEIGHT - (coor*4))/2;
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
		GameValues.currentBatch.draw(saveDataBox, 10, 10, GameValues.SCREEN_WIDTH - 20, GameValues.SCREEN_HEIGHT - 20);
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
				GameValues.avatar = new Avatar();
				GameValues.user = GameValues.dataHandler.getUsers().get(characterSlot.getId());
				GameValues.avatar.name = GameValues.user.getAvatar();
				GameValues.currentMapValue = GameValues.user.getCoordinate().getMap();
				LoadAssets.loadAvatarAssets();
				AvatarUtils.repositionAvatarByCoordinate();
				GameValues.touchDown = false;
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
