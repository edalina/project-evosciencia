package com.eciz.evosciencia.manager;

import com.eciz.evosciencia.entities.Avatar;
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
				
//				if( (
//						GameValues.avatar.getX() >= monster.getX() && GameValues.avatar.getX() <= monster.getX() + monster.getWidth() ||
//						GameValues.avatar.getX() + GameValues.avatar.getWidth() >= monster.getX() && GameValues.avatar.getX() + GameValues.avatar.getWidth() <= monster.getX() + monster.getWidth()
//					) &&
//					(
//						GameValues.avatar.getY() >= monster.getY() && GameValues.avatar.getY() <= monster.getY() + monster.getHeight() ||
//						GameValues.avatar.getY() + GameValues.avatar.getHeight() >= monster.getY() && GameValues.avatar.getY() + GameValues.avatar.getHeight() <= monster.getY() + monster.getHeight()
//					)) {
//					System.out.println( "hit monster #" + monster.getId() );
//				}
				GameValues.currentBatch.draw(monster.getCurrentSprite(), monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());
			}
		}
	}

}
