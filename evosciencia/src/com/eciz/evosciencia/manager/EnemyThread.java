package com.eciz.evosciencia.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.eciz.evosciencia.entities.Enemy;
import com.eciz.evosciencia.values.GameValues;

public class EnemyThread implements Runnable {
	
	private Enemy enemy;
	
	public EnemyThread(Enemy enemy) {
		this.enemy = enemy;
	}

	@Override
	public void run() {
		Runnable actionDone = new Runnable() {
			
			public void run() {
				enemy.isMoving = false;
				System.out.println( "DONE MOVING " + enemy.getId() );
				new EnemyThread(enemy).run();
			}
			
		};
		if( enemy != null ) {
			if( !enemy.isMoving ) {
				float x = enemy.getX(), y = enemy.getY();
				float speed = GameValues.CHARACTER_SPEED * 5;
				if( MathUtils.randomBoolean() )
					x += MathUtils.randomBoolean() ? speed : -speed;
				else
					y += MathUtils.randomBoolean() ? speed : -speed;
				enemy.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(actionDone)));
				enemy.act(Gdx.graphics.getDeltaTime());
				enemy.isMoving = true;
			}
		}
	}

}
