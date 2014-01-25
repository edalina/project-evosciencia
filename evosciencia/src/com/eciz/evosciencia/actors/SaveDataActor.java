package com.eciz.evosciencia.actors;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eciz.evosciencia.entities.CharacterSlot;
import com.eciz.evosciencia.utils.EventUtils;
import com.eciz.evosciencia.values.GameValues;

public class SaveDataActor extends Table {
	
	private Texture saveDataBox;
	private Texture characterSlotTexture;
	private Texture dataBox;
	private List<CharacterSlot> characterSlots;
	
	private int coor = 50;
	
	public SaveDataActor() {
		setBounds(0, 0, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		setClip(true);
		
		GameValues.currentBatch = new SpriteBatch();
		GameValues.currentBatch.setProjectionMatrix(GameValues.camera.combined);
		GameValues.touchPos = new Vector3();
		
		saveDataBox = new Texture(Gdx.files.internal("images/character_box.png"));
		dataBox = new Texture(Gdx.files.internal("images/data_box.png"));
		characterSlotTexture = new Texture(Gdx.files.internal("images/character_slot.png"));
		
		characterSlots = new ArrayList<CharacterSlot>();
		
		for( int i = 0 ; i < 5 ; i++ ) {
			CharacterSlot characterSlot = new CharacterSlot();
			characterSlot.setId(i);
			characterSlot.setTexture(characterSlotTexture);
			characterSlot.setSaveDataId(0);
			
			Rectangle rectangle = new Rectangle();
			rectangle.width = (GameValues.SCREEN_WIDTH - (coor*2) - 20)/5;
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
		GameValues.currentBatch.draw(dataBox, coor + 10, coor + 10, GameValues.SCREEN_WIDTH - (coor*2) - 20, ((GameValues.SCREEN_HEIGHT - (coor*2))/2) - 20);
		for( CharacterSlot characterSlot : characterSlots ) {
			GameValues.currentBatch.draw(characterSlotTexture, characterSlot.getRectangle().x, characterSlot.getRectangle().y, characterSlot.getRectangle().width, characterSlot.getRectangle().height);
		}
		GameValues.currentBatch.end();
		if( Gdx.input.isTouched() ) {
			GameValues.touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			GameValues.camera.unproject(GameValues.touchPos);
			for( CharacterSlot characterSlot : characterSlots ) {
				if( EventUtils.isTap(characterSlot.getRectangle()) ) {
					System.out.println( characterSlot.getId() );
				}
			}
		}
	}

}
