package com.eciz.evosciencia.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.eciz.evosciencia.controls.Dpad;
import com.eciz.evosciencia.enums.MonsterEnum;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.resources.Maps;
import com.eciz.evosciencia.utils.DialogUtils;
import com.eciz.evosciencia.values.GameSettings;
import com.eciz.evosciencia.values.GameValues;

public class Enemy extends Sprite {

	public static final float WIDTH = GameValues.avatar.getWidth();
	public static final float HEIGHT = GameValues.avatar.getWidth();
	public static final float TERRITORY_RANGE = 50;
	public static final Sound attackSound = Gdx.audio.newSound(Gdx.files.internal("sfx/bite.mp3"));
	public static final Sound dieSound = Gdx.audio.newSound(Gdx.files.internal("sfx/monster_die.wav"));
	public static final Texture attackTexture = new Texture(Gdx.files.internal("monsters/goblin/attack.png"));
	
	private int id;
	private Texture texture;
	private StanceEnum facing;
	private int life = 100;
	private int damage = 5;
	private boolean alive = true;
	private Rectangle futurePosition;
	private long animationSpeed = 0;
	private boolean walkingFlag = true;
	private boolean onHunt = false;
	public boolean isMoving = false;
	private long lastAttackTime = 0;
	
	public boolean isOnHunt() {
		return onHunt;
	}
	public void setOnHunt(boolean onHunt) {
		this.onHunt = onHunt;
	}
	public boolean isWalkingFlag() {
		return walkingFlag;
	}
	public void setWalkingFlag(boolean walkingFlag) {
		this.walkingFlag = walkingFlag;
	}
	public long getAnimationSpeed() {
		return animationSpeed;
	}
	public void setAnimationSpeed(long animationSpeed) {
		this.animationSpeed = animationSpeed;
	}
	public Rectangle getFuturePosition() {
		return futurePosition;
	}
	public void setFuturePosition(Rectangle futurePosition) {
		this.futurePosition = futurePosition;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public StanceEnum getFacing() {
		return facing;
	}
	public void setFacing(StanceEnum facing) {
		this.facing = facing;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean isAlive) {
		this.alive = isAlive;
	}
	
	public void moveEnemy() {
		if( !isMoving ) {
			moveUtils();
			isMoving = true;
		} else {
			Rectangle advanceRectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
			if( getX() > futurePosition.getX() ) {
				advanceRectangle.setX(getX() - 0.5f);
			} else if( getX() < futurePosition.getX() ) {
				advanceRectangle.setX(getX() + 0.5f);
			}
			if( getY() > futurePosition.getY() ) {
				advanceRectangle.setY(getY() - 0.5f);
			} else if( getY() < futurePosition.getY() ) {
				advanceRectangle.setY(getY() + 0.5f);
			}
			
			repositionEnemy(advanceRectangle);
			
		}
		if( getX() == futurePosition.getX() && getY() == futurePosition.getY() ) {
			isMoving = false;
		}
		
	}
	
	public void moveUtils() {
		futurePosition = new Rectangle();
		futurePosition.setWidth(WIDTH);
		futurePosition.setHeight(HEIGHT);
		futurePosition.setX(getX());
		futurePosition.setY(getY());
		int rnd = MathUtils.random(1, 4);
		int distance = 30;
		walkingFlag = true;
//		if( onHunt ) {
//			float ax = GameValues.avatar.getX(),
//				ay = GameValues.avatar.getY();
//			
//			if( getX() < ax && getY() < ay ) {
//				futurePosition.setX(GameValues.avatar.getX());
//				futurePosition.setY(GameValues.avatar.getY());
//			}
//			
//		} else {
			switch( rnd ) {
				case 1:
					setFacing(StanceEnum.FRONT_WALK1);
					futurePosition.setY(getY() - distance);
					break;
				case 2:
					setFacing(StanceEnum.BACK_WALK1);
					futurePosition.setY(getY() + distance);
					break;
				case 3:
					setFacing(StanceEnum.LEFT_WALK1);
					futurePosition.setX(getX() - distance);
					break;
				case 4:
					setFacing(StanceEnum.RIGHT_WALK1);
					futurePosition.setX(getX() + distance);
					break;
				default:
			}
			
//		}
		
	}
	
	public void repositionEnemy(Rectangle tmpRect) {
		boolean isCollided = false;
		if( GameValues.avatar.sprite.getBoundingRectangle().overlaps(tmpRect) ) {
			futurePosition.set(getX(), getY(), getWidth(), getHeight());
			isCollided = true;
		}
		for(Checkpoint checkpoint : GameValues.checkpoints ) {
			if( tmpRect.overlaps(checkpoint.getRectangle()) ) {
				futurePosition.set(getX(), getY(), getWidth(), getHeight());
				isCollided = true;
			}
		}
		
		if( tmpRect.getX() < 1 ||
			tmpRect.getX() > Maps.MAP_WIDTH - Enemy.WIDTH ||
			tmpRect.getY() < 1 ||
			tmpRect.getY() > Maps.MAP_HEIGHT - Enemy.HEIGHT) {
			futurePosition.set(getX(), getY(), getWidth(), getHeight());
			isCollided = true;
		}
		
		for(Enemy enemy : Maps.enemies) {
			if( enemy.isAlive() && enemy.getId() != getId() ) {
				if( tmpRect.overlaps(enemy.getBoundingRectangle()) ) {
					futurePosition.set(getX(), getY(), getWidth(), getHeight());
					isCollided = true;
				}
			}
		}
		
		for(Rectangle collision : GameValues.collisions ) {
			if( tmpRect.overlaps(collision) ) {
				futurePosition.set(getX(), getY(), getWidth(), getHeight());
				isCollided = true;
			}
		}
		
		if( !isCollided ) {
			
			setX(tmpRect.getX());
			setY(tmpRect.getY());
			
			if( Math.abs(GameValues.avatar.getX() - getX()) < TERRITORY_RANGE &&
				Math.abs(GameValues.avatar.getY() - getY()) < TERRITORY_RANGE ) {
				onHunt = true;
//				futurePosition.setX(GameValues.avatar.getX());
//				futurePosition.setY(GameValues.avatar.getY());
			} else {
				if( onHunt ) {
					if( Math.abs(GameValues.avatar.getX() - getX()) > TERRITORY_RANGE * 2 &&
						Math.abs(GameValues.avatar.getY() - getY()) > TERRITORY_RANGE * 2 ) {
						onHunt = false;
					}
				}
			}
			
			if( TimeUtils.nanoTime() - animationSpeed > GameValues.ANIMATION_SPEED ) {
				animationSpeed = TimeUtils.nanoTime();
				switch( getFacing() ) {
					case FRONT_WALK1:
					case FRONT_WALK2:
						if( walkingFlag ) {
							walkingFlag = false;
							setFacing(StanceEnum.FRONT_WALK1);
						} else {
							walkingFlag = true;
							setFacing(StanceEnum.FRONT_WALK2);
						}
						break;
					case BACK_WALK1:
					case BACK_WALK2:
						if( walkingFlag ) {
							walkingFlag = false;
							setFacing(StanceEnum.BACK_WALK1);
						} else {
							walkingFlag = true;
							setFacing(StanceEnum.BACK_WALK2);
						}
						break;
					case LEFT_WALK1:
					case LEFT_WALK2:
						if( walkingFlag ) {
							walkingFlag = false;
							setFacing(StanceEnum.LEFT_WALK1);
						} else {
							walkingFlag = true;
							setFacing(StanceEnum.LEFT_WALK2);
						}
						break;
					case RIGHT_WALK1:
					case RIGHT_WALK2:
						if( walkingFlag ) {
							walkingFlag = false;
							setFacing(StanceEnum.RIGHT_WALK1);
						} else {
							walkingFlag = true;
							setFacing(StanceEnum.RIGHT_WALK2);
						}
						break;
					default:
				}
			}
			
			setTexture(GameValues.monsters.get(MonsterEnum.GOBLIN.getValue()).get(getFacing().getValue()));
			
		}
		
	}
	public void attack() {
		this.isMoving = false;
		this.futurePosition.setCenter(this.getX(), this.getY());
		switch( GameValues.avatar.facingFlag ) {
			case FRONT_ATTACK:
			case FRONT_STAND:
			case FRONT_WALK1:
			case FRONT_WALK2:
				this.setFacing(StanceEnum.BACK_STAND);
				break;
			case BACK_ATTACK:
			case BACK_STAND:
			case BACK_WALK1:
			case BACK_WALK2:
				this.setFacing(StanceEnum.FRONT_STAND);
				break;
			case LEFT_ATTACK:
			case LEFT_STAND:
			case LEFT_WALK1:
			case LEFT_WALK2:
				this.setFacing(StanceEnum.RIGHT_STAND);
				break;
			case RIGHT_ATTACK:
			case RIGHT_STAND:
			case RIGHT_WALK1:
			case RIGHT_WALK2:
				this.setFacing(StanceEnum.LEFT_STAND);
				break;
			default:
		}
		
		if( MathUtils.randomBoolean() ) {
			if( GameSettings.sfx )
				Enemy.attackSound.play();
			GameValues.currentBatch.draw(Enemy.attackTexture, GameValues.avatar.getX(), GameValues.avatar.getY(), GameValues.avatar.getWidth(), GameValues.avatar.getHeight());
			GameValues.user.setCurrentLife(GameValues.user.getCurrentLife()-this.getDamage());
			if( GameValues.user.getCurrentLife() <= 0 ) {
				GameValues.avatar.isAttacking = false;
				GameValues.avatar.setSprite(GameValues.avatar.avatarSprites.get(StanceEnum.DEATH.getValue()));
				GameValues.avatar.facingFlag = StanceEnum.DEATH;
				Dpad.isDpadActive = false;
				Avatar.dieSound.play();
				DialogUtils.createItemDialog("YOU DIED");
			}
		}
	}
	
}
