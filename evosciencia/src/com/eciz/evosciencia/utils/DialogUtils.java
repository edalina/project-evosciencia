package com.eciz.evosciencia.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.eciz.evosciencia.controls.Dpad;
import com.eciz.evosciencia.screens.GameScreen;
import com.eciz.evosciencia.values.GameValues;
import com.eciz.evosciencia.values.HelpValues;

public class DialogUtils {
	
	public static boolean isDialogActive = false;
	
	private static BitmapFont dialogText;
	private static Texture nextText;
	private static Texture acceptText;
	private static Texture cancelText;
	private static Texture backText;
	private static Rectangle nextRect;
	private static Rectangle acceptRect;
	private static Rectangle backRect;
	
	private static Texture dialogTexture;
	private static Texture helpAvatar;
	private static Texture charAvatar;
	
	private static String dialog = "";
	private static String itemDialog = "";
	private static String questDialog = "";
	private static String questCompleteDialog = "";
	
	private static float DIALOG_WIDTH = GameValues.SCREEN_WIDTH * GameValues.CAMERA_ZOOM;
	private static float DIALOG_HEIGHT = (GameValues.SCREEN_HEIGHT/3) * GameValues.CAMERA_ZOOM;
	private static float DIALOG_X = GameValues.camera.position.x - (DIALOG_WIDTH/2);
	private static float DIALOG_Y = GameValues.camera.position.y - (DIALOG_HEIGHT + 30);
	
	private static int idx = 0;

	public DialogUtils() {
		dialogTexture = new Texture(Gdx.files.internal("images/dialog.png"));
		helpAvatar = new Texture(Gdx.files.internal("images/einstein.png"));
		
		dialogText = new BitmapFont();
		dialogText.setScale(GameValues.CAMERA_ZOOM);
		dialogText.setColor(1, 1, 1, 1);
		
		nextText = new Texture(Gdx.files.internal("images/next.png"));
		acceptText = new Texture(Gdx.files.internal("images/accept.png"));
		cancelText = new Texture(Gdx.files.internal("images/cancel.png"));
		backText = new Texture(Gdx.files.internal("images/back.png"));
		
		nextRect = new Rectangle();
		acceptRect = new Rectangle();
		backRect = new Rectangle();
		
		repositionButtons();
	}
	
	public static void repositionButtons() {
		nextRect.width = 40;
		nextRect.height = 15;
		nextRect.x = DIALOG_X + DIALOG_WIDTH - (nextRect.width) - 10;
		nextRect.y = DIALOG_Y + 10;
		
		acceptRect.width = 60;
		acceptRect.height = 20;
		acceptRect.x = DIALOG_X + 15;
		acceptRect.y = DIALOG_Y + acceptRect.height + 10;
		
		backRect.width = 60;
		backRect.height = 20;
		backRect.x = DIALOG_X + 15;
		backRect.y = DIALOG_Y + 10;
	}

	public static void introDialogs() {
		if( isDialogActive ) {
			Dpad.isDpadActive = false;
			if( idx == HelpValues.INTRO.length ) {
				isDialogActive = false;
				Dpad.isDpadActive = true;
				GameValues.isNewGame = false;
				return;
			}
//			GameValues.currentBatch.draw(helpAvatar, DIALOG_X - 100, DIALOG_Y - 50, 167, 200);
			GameValues.currentBatch.draw(dialogTexture, DIALOG_X, DIALOG_Y, DIALOG_WIDTH, DIALOG_HEIGHT);
			dialogText.drawWrapped(GameValues.currentBatch, HelpValues.INTRO[idx], DIALOG_X + 10, DIALOG_Y + DIALOG_HEIGHT - 10, DIALOG_WIDTH - 20);
			GameValues.currentBatch.draw(nextText, nextRect.getX(), nextRect.getY(), nextRect.getWidth(), nextRect.getHeight());
			if( Gdx.input.isTouched() ) {
				if( !GameValues.touchDown ) {
					GameValues.touchDown = true;
					if( EventUtils.isTap(nextRect) ) {
						idx++;
					}
				}
			} else {
				GameValues.touchDown = false;
			}
		}
	}
	
	public static void createDialog(String value) {
		dialogTexture = new Texture(Gdx.files.internal("images/dialog.png"));
		DIALOG_WIDTH = GameValues.SCREEN_WIDTH * GameValues.CAMERA_ZOOM;
		DIALOG_HEIGHT = (GameValues.SCREEN_HEIGHT/3) * GameValues.CAMERA_ZOOM;
		DIALOG_X = GameValues.camera.position.x - (DIALOG_WIDTH/2);
		DIALOG_Y = GameValues.camera.position.y - (DIALOG_HEIGHT + 30);
		
		repositionButtons();
		dialog = value;
		createDialog();
	}
	
	public static void createItemDialog(String value) {
		dialogTexture = new Texture(Gdx.files.internal("images/dialog.png"));
		DIALOG_WIDTH = (GameValues.SCREEN_WIDTH) * GameValues.CAMERA_ZOOM;
		DIALOG_HEIGHT = (GameValues.SCREEN_HEIGHT/8) * GameValues.CAMERA_ZOOM;
		DIALOG_X = GameValues.camera.position.x - (DIALOG_WIDTH/2);
		DIALOG_Y = GameValues.camera.position.y + (DIALOG_HEIGHT*2-10);
		
		itemDialog = value;
		createItemDialog();
	}
	
	public static void createDialog() {
		if( !dialog.equals("") ) {
			Dpad.isDpadActive = false;
			GameValues.currentBatch.draw(dialogTexture, DIALOG_X, DIALOG_Y, DIALOG_WIDTH, DIALOG_HEIGHT);
			dialogText.drawWrapped(GameValues.currentBatch, dialog, DIALOG_X + 10, DIALOG_Y + DIALOG_HEIGHT - 10, DIALOG_WIDTH - 20);
			GameValues.currentBatch.draw(backText, nextRect.getX(), nextRect.getY(), nextRect.getWidth(), nextRect.getHeight());
			if( Gdx.input.isTouched() ) {
				if( !GameValues.touchDown ) {
					GameValues.touchDown = true;
					if( EventUtils.isTap(nextRect) ) {
						closeDialog();
						GameValues.user.setCurrentQuestInProgress(true);
					}
					if( EventUtils.isTap(backRect) ) {
						closeDialog();
					}
				}
			} else {
				GameValues.touchDown = false;
			}
		}
	}
	
	public static void createItemDialog() {
		if( !itemDialog.equals("") ) {
			GameValues.currentBatch.draw(dialogTexture, DIALOG_X, DIALOG_Y, DIALOG_WIDTH, DIALOG_HEIGHT);
			dialogText.drawWrapped(GameValues.currentBatch, itemDialog, DIALOG_X + 10, DIALOG_Y + DIALOG_HEIGHT - 10, DIALOG_WIDTH - 20);
			if( Gdx.input.isTouched() ) {
				if( !GameValues.touchDown ) {
					GameValues.touchDown = true;
					Rectangle rectangle = new Rectangle();
					rectangle.set(DIALOG_X, DIALOG_Y, DIALOG_WIDTH, DIALOG_HEIGHT);
					if( Gdx.input.isTouched() ) {
						itemDialog = "";
					}
				}
			} else {
				GameValues.touchDown = false;
			}
		}
	}
	
	public static void createQuestDialog(String value) {
		dialogTexture = new Texture(Gdx.files.internal("images/quest_dialog.png"));
		DIALOG_WIDTH = GameValues.SCREEN_WIDTH * GameValues.CAMERA_ZOOM;
		DIALOG_HEIGHT = GameValues.SCREEN_HEIGHT * GameValues.CAMERA_ZOOM;
		DIALOG_X = GameValues.camera.position.x - (DIALOG_WIDTH/2);
		DIALOG_Y = GameValues.camera.position.y - (DIALOG_HEIGHT/2);
		
		repositionButtons();
		questDialog = value;
		createQuestDialog();
	}
	
	public static void createQuestDialog() {
		if( !questDialog.equals("") ) {
			Dpad.isDpadActive = false;
			GameValues.currentBatch.draw(dialogTexture, DIALOG_X, DIALOG_Y, DIALOG_WIDTH, DIALOG_HEIGHT);
			dialogText.setScale(0.6f);
			dialogText.drawWrapped(GameValues.currentBatch, questDialog, DIALOG_X + 120, DIALOG_Y + DIALOG_HEIGHT - 80, DIALOG_WIDTH - 240);
			GameValues.currentBatch.draw(acceptText, acceptRect.getX(), acceptRect.getY(), acceptRect.getWidth(), acceptRect.getHeight());
			GameValues.currentBatch.draw(cancelText, backRect.getX(), backRect.getY(), backRect.getWidth(), backRect.getHeight());
			if( Gdx.input.isTouched() ) {
				if( !GameValues.touchDown ) {
					GameValues.touchDown = true;
					if( EventUtils.isTap(acceptRect) ) {
						closeDialog();
						GameValues.user.setCurrentQuestInProgress(true);
					}
					if( EventUtils.isTap(backRect) ) {
						closeDialog();
					}
				}
			} else {
				GameValues.touchDown = false;
			}
		}
	}
	
	public static void createCompleteDialog(String description) {
		dialogTexture = new Texture(Gdx.files.internal("images/quest_dialog_completed.png"));
		
		DIALOG_WIDTH = GameValues.SCREEN_WIDTH * GameValues.CAMERA_ZOOM;
		DIALOG_HEIGHT = GameValues.SCREEN_HEIGHT * GameValues.CAMERA_ZOOM;
		DIALOG_X = GameValues.camera.position.x - (DIALOG_WIDTH/2);
		DIALOG_Y = GameValues.camera.position.y - (DIALOG_HEIGHT/2);

		GameScreen.field.setScale(2);
		GameScreen.field.setWidth(GameValues.SCREEN_WIDTH * 2);
		GameScreen.field.setHeight(GameValues.SCREEN_HEIGHT/5);
		GameScreen.field.setX(150);
		GameScreen.field.setY(700);
		GameScreen.field.getStyle().font.setScale(2);
		GameScreen.field.setVisible(true);
		questCompleteDialog = description;
		repositionButtons();
		createCompleteDialog();
	}
	
	public static void createCompleteDialog() {
		if( !questCompleteDialog.equals("") ) {
			String thisDialog = questCompleteDialog;
			
			Dpad.isDpadActive = false;
			GameValues.currentBatch.draw(dialogTexture, DIALOG_X, DIALOG_Y, DIALOG_WIDTH, DIALOG_HEIGHT);
			dialogText.drawWrapped(GameValues.currentBatch, thisDialog, DIALOG_X + 50, DIALOG_Y + nextRect.getY() + 50, DIALOG_WIDTH - 100);
			GameValues.currentBatch.draw(nextText, acceptRect.getX(), acceptRect.getY(), acceptRect.getWidth(), acceptRect.getHeight());
			GameValues.currentBatch.draw(cancelText, backRect.getX(), backRect.getY(), backRect.getWidth(), backRect.getHeight());
			if( Gdx.input.isTouched() ) {
				if( !GameValues.touchDown ) {
					GameValues.touchDown = true;
					if( EventUtils.isTap(acceptRect) ) {
						if( GameScreen.field.getText().toLowerCase().equals(GameValues.currentScientist.getName().toLowerCase()) ) {
							GameValues.user.setCurrentQuestInProgress(false);
							GameValues.user.getQuestDone()[GameValues.currentMapValue] = true;
							closeDialog();
							String name = "";
							for( String s : GameValues.currentScientist.getName().split(" ") ) {
								name = name + s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase() + " ";
							}
							createDialog(name + ": Thank you for helping me, my name is " + name);
							
						}
					}
					if( EventUtils.isTap(backRect) ) {
						closeDialog();
					}
				}
			} else {
				GameValues.touchDown = false;
			}
		}
	}
	
	public static void createAvatarDialog() {
		
	}
	
	public static void closeDialog() {
		dialog = "";
		questDialog = "";
		itemDialog = "";
		questCompleteDialog = "";
		GameScreen.field.setVisible(false);
		Dpad.isDpadActive = true;
	}

}
