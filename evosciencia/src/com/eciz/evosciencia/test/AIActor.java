package com.eciz.evosciencia.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eciz.evosciencia.values.GameValues;

public class AIActor extends Table {
	
	private Rectangle avatarRect;
	private Rectangle monsterRect;
	private Texture avatarTexture;
	private Texture monsterTexture;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Vector3 touchPos;

	public AIActor() {
		
		avatarTexture = new Texture(Gdx.files.internal("avatar/yjae/front_standby.png"));
		monsterTexture = new Texture(Gdx.files.internal("monsters/goblin/front_standby.png"));
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameValues.SCREEN_WIDTH, GameValues.SCREEN_HEIGHT);
		
		Rectangle rectangle = new Rectangle();
		rectangle.set(150, 10, 50, 300);
		
		avatarRect = new Rectangle();
		monsterRect = new Rectangle();
		
		avatarRect.set(50, 100, 20, 20);
		monsterRect.set(250, 100, 20, 20);
		
		touchPos = new Vector3();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		batch.draw(avatarTexture, avatarRect.x, avatarRect.y, avatarRect.width, avatarRect.height);
		batch.draw(monsterTexture, monsterRect.x, monsterRect.y, monsterRect.width, monsterRect.height);
		batch.end();
		
		if( Gdx.input.isTouched() ) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			
			avatarRect.x = touchPos.x;
			avatarRect.y = touchPos.y;
			
			checkEnemyAction(avatarRect, monsterRect);
		}
		
	}
	
	public void checkEnemyAction(Rectangle avatar, Rectangle monster ) {
		Rectangle monsterZone = new Rectangle();
		monsterZone.setWidth(monsterRect.width*19);
		monsterZone.setHeight(monsterRect.height*19);
		monsterZone.setX(monsterRect.getX() - ((monsterZone.getWidth()-monsterRect.width)/2));
		monsterZone.setY(monsterRect.getY() - ((monsterZone.getHeight()-monsterRect.height)/2));
		
		if( avatar.overlaps(monsterZone) ) {
			if( avatar.x < monster.x ) {
				while( avatar.x < monster.x ) {
					monsterRect.x--;
				}
			} else { 
				while( avatar.x > monster.x ) {
					monsterRect.x++;
				}
			}
			
			if( avatar.y < monster.y ) {
				while( avatar.y < monster.y ) {
					monsterRect.y--;
				}
			} else { 
				while( avatar.y > monster.y ) {
					monsterRect.y++;
				}
			}
			
		}
	}
	
}