package com.eciz.evosciencia.manager.pool;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;
import com.eciz.evosciencia.entities.Enemy;
import com.eciz.evosciencia.enums.MonsterEnum;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.resources.Maps;
import com.eciz.evosciencia.values.GameValues;

public class EnemyPool {
	
	public static List<Enemy> enemies;
	
	public EnemyPool() {
		EnemyPool.enemies = new ArrayList<Enemy>();
		for( int i = 0 ; i < GameValues.ENEMY_COUNT_PER_MAP ; i++ ) {
			Enemy enemy = new Enemy();
			enemy.setId(i);
			enemy.setCurrentSprite(GameValues.monsters.get(MonsterEnum.GOBLIN.getValue()).get(StanceEnum.FRONT_STAND_1.getValue()));
			enemy.setFacing(StanceEnum.FRONT_STAND_1);
			enemy.setVisible(false);
			enemy.setWidth(GameValues.ENEMY_WIDTH); 
			enemy.setHeight(GameValues.ENEMY_HEIGHT);
			
			float x = MathUtils.random(0, Maps.MAP_WIDTH - GameValues.ENEMY_WIDTH),
				y = MathUtils.random(0, Maps.MAP_HEIGHT - GameValues.ENEMY_HEIGHT);
			
			enemy.setPosition(x, y);
			
			enemies.add(enemy);
		}
	}
	
	public static void manageEnemies() {
		for( Enemy monster : EnemyPool.enemies ) {
			if( monster.isVisible() ) {
				if( GameValues.avatar.sprite.getBoundingRectangle().overlaps( monster.getCurrentSprite().getBoundingRectangle() ) ) {
					// TODO if hit
				}
				GameValues.currentBatch.draw(monster.getCurrentSprite(), monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());
			}
		}
	}

	public static Enemy getInstance() {
		for( Enemy enemy : EnemyPool.enemies ) {
			if( !enemy.isVisible() ) {
				enemy.setVisible(true);
				return enemy;
			}
		}
		return null;
	}
	
}
