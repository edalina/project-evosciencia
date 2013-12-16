package com.eciz.evosciencia.manager.pool;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;
import com.eciz.evosciencia.entities.Enemy;
import com.eciz.evosciencia.enums.MonsterEnum;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.values.GameValues;

public class EnemyPool {
	
	public static List<Enemy> enemies;
	
	public EnemyPool() {
		EnemyPool.enemies = new ArrayList<Enemy>();
		for( int i = 0 ; i < GameValues.ENEMY_COUNT_PER_MAP ; i++ ) {
			Enemy enemy = new Enemy();
			enemy.setId(i);
			enemy.setCurrentSprite(GameValues.monsters.get(MonsterEnum.GOBLIN.getValue()).get(StanceEnum.FRONT_STAND.getValue()));
			enemy.setFacing(StanceEnum.FRONT_STAND);
			enemy.setVisible(false);
			enemy.setWidth(GameValues.ENEMY_WIDTH); 
			enemy.setHeight(GameValues.ENEMY_HEIGHT);
			
			float x = MathUtils.random(0, GameValues.SCREEN_WIDTH - GameValues.ENEMY_WIDTH),
				y = MathUtils.random(0, GameValues.SCREEN_HEIGHT - GameValues.ENEMY_HEIGHT);
			
			enemy.setX(x);
			enemy.setY(y);
			enemy.getCurrentSprite().getBoundingRectangle().set(
					x, 
					y, 
					GameValues.ENEMY_WIDTH, 
					GameValues.ENEMY_HEIGHT);
			
			enemies.add(enemy);
		}
	}

	public static int getInstance() {
		return 0;
	}
	
}
