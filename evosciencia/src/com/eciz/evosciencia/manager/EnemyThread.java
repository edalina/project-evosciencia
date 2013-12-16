package com.eciz.evosciencia.manager;

import com.eciz.evosciencia.entities.Enemy;
import com.eciz.evosciencia.manager.pool.EnemyPool;
import com.eciz.evosciencia.values.GameValues;

public class EnemyThread implements Runnable {

	@Override
	public void run() {
		
		for( Enemy enemy : EnemyPool.enemies ) {
			if( !enemy.isVisible() ) {
				enemy.setVisible(true);
			}
		}
		
		for( Enemy monster : EnemyPool.enemies ) {
			if( monster.isVisible() ) {
				if( GameValues.avatar.sprite.getBoundingRectangle().overlaps( monster.getCurrentSprite().getBoundingRectangle() ) ) {
//					System.out.println( "HIT" );
				}
				GameValues.currentBatch.draw(monster.getCurrentSprite(), monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());
			}
		}
	}

}
