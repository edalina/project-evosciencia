package com.eciz.evosciencia.resources;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.eciz.evosciencia.entities.Checkpoint;
import com.eciz.evosciencia.entities.Enemy;
import com.eciz.evosciencia.enums.MonsterEnum;
import com.eciz.evosciencia.enums.StanceEnum;
import com.eciz.evosciencia.values.GameValues;

public class Maps {
	
	private TiledMap currentMap;
	private TiledMapRenderer renderer;
	public static final float MAP_UNIT_SCALE = 1f;
	public static final float MAP_WIDTH = 800 * MAP_UNIT_SCALE;
	public static final float MAP_HEIGHT = 800 * MAP_UNIT_SCALE;
	public static List<Enemy> enemies;
	public String name = "map1_1";
	
	public Maps() {
		GameValues.currentScientist = GameValues.dataHandler.getScientists().get((GameValues.user.getScientists()[GameValues.currentMapValue]));
		GameValues.currentScientist.setTexture( new Texture(Gdx.files.internal("npc/" + GameValues.currentScientist.getName() + ".png")) );
		currentMap = new TmxMapLoader().load("tmx/" + name + ".tmx");
		changeMap();
	}
	
	public static Maps getMaps() {
		return new Maps();
	}

	public TiledMap getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(String name) {
		this.name = name;
		this.currentMap = new TmxMapLoader().load("tmx/" + name + ".tmx");
	}

	public TiledMapRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(TiledMapRenderer renderer) {
		this.renderer = renderer;
	}
	
	public void changeMap() {
		Maps.enemies = new ArrayList<Enemy>();
		createObjects();
		renderCheckpoints();
		createSpawningGrounds();
		renderer = new OrthogonalTiledMapRenderer(currentMap, MAP_UNIT_SCALE);
		GameValues.currentScientist.setRectangle(createNPCObjects());
	}
	
	public void renderMonsters() {
		GameValues.maps.getRenderer().setView(GameValues.camera);
		for( Enemy enemy : enemies ) {
			if( enemy.isAlive() ) {
				GameValues.currentBatch.draw(enemy.getTexture(), enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
				enemy.moveEnemy();
			}
		}
	}
	
	public void renderNonObstacleObj() {
		GameValues.maps.getRenderer().setView(GameValues.camera);
		for( int i = 0 ; i < GameValues.maps.getCurrentMap().getLayers().getCount() ; i++ ) {
			if( !GameValues.maps.getCurrentMap().getLayers().get(i).getName().contains(GameValues.OBSTACLE_PROPERTY) &&
				!GameValues.maps.getCurrentMap().getLayers().get(i).getName().contains(GameValues.SPAWN_PROPERTY) ) {
				GameValues.maps.getRenderer().render(new int[]{i});
			}
		}
	}
	
	public void renderObstacleObj() {
		GameValues.maps.getRenderer().setView(GameValues.camera);
		for( int i = 0 ; i < GameValues.maps.getCurrentMap().getLayers().getCount() ; i++ ) {
			if( GameValues.maps.getCurrentMap().getLayers().get(i).getName().contains(GameValues.OBSTACLE_PROPERTY) ) {
				GameValues.maps.getRenderer().render(new int[]{i});
			}
		}
	}
	
	public Rectangle createNPCObjects() {
		Rectangle npcRect = null;
		for( int i = 0 ; i < currentMap.getLayers().getCount() ; i++ ) {
			if( currentMap.getLayers().get(i).getName().contains(GameValues.NPC_PROPERTY) ) {
				TiledMapTileLayer currentLayer = (TiledMapTileLayer) currentMap.getLayers().get(i);
				int tileWidth = (int) (currentLayer.getTileWidth() * Maps.MAP_UNIT_SCALE),
					tileHeight = (int) (currentLayer.getTileHeight() * Maps.MAP_UNIT_SCALE);
				for( int x = 0 ; x < 50 ; x++ ) {
					for( int y = 0 ; y < 50 ; y++ ) {
						if( currentLayer.getCell(x, y) != null ) {
							npcRect = new Rectangle();
							npcRect.set(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
						}
					}
				}
			}
		}
		return npcRect;
	}
	
	public void renderCheckpoints() {
		GameValues.checkpoints = new ArrayList<Checkpoint>();
		for( int i = 0 ; i < currentMap.getLayers().getCount() ; i++ ) {
			TiledMapTileLayer currentLayer = ((TiledMapTileLayer) currentMap.getLayers().get(i));
			int tileWidth = (int) (currentLayer.getTileWidth() * Maps.MAP_UNIT_SCALE),
				tileHeight = (int) (currentLayer.getTileHeight() * Maps.MAP_UNIT_SCALE);
			for( int x = 0 ; x < 50 ; x++ ) {
				for( int y = 0 ; y < 50 ; y++ ) {
					if( currentLayer.getCell(x, y) != null ) {
						TiledMapTile tile = currentLayer.getCell(x, y).getTile();
						if( tile.getProperties().containsKey(GameValues.CHECK_POINT) ) {
							Rectangle checkpoint = new Rectangle();
							checkpoint.set(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
							GameValues.checkpoints.add(new Checkpoint(tile.getProperties().get(GameValues.CHECK_POINT).toString(), checkpoint));
						}
					}
				}
			}
		}
	}
	
	public void createObjects() {
		GameValues.collisions = new ArrayList<Rectangle>();
		for( int i = 0 ; i < currentMap.getLayers().getCount() ; i++ ) {
			TiledMapTileLayer currentLayer = ((TiledMapTileLayer) currentMap.getLayers().get(i));
			int tileWidth = (int) (currentLayer.getTileWidth() * Maps.MAP_UNIT_SCALE),
				tileHeight = (int) (currentLayer.getTileHeight() * Maps.MAP_UNIT_SCALE);
			for( int x = 0 ; x < 50 ; x++ ) {
				for( int y = 0 ; y < 50 ; y++ ) {
					if( currentLayer.getCell(x, y) != null ) {
						TiledMapTile tile = currentLayer.getCell(x, y).getTile();
						if( tile.getProperties().containsKey(GameValues.COLLISION_PROPERTY) ) {
							Rectangle collision = new Rectangle();
							collision.set(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
							GameValues.collisions.add(collision);
						}
					}
				}
			}
		}
	}
	
	public void createSpawningGrounds() {
		for( int i = 0 ; i < currentMap.getLayers().getCount() ; i++ ) {
			if( currentMap.getLayers().get(i).getName().contains(GameValues.SPAWN_PROPERTY) ) {
				TiledMapTileLayer currentLayer = (TiledMapTileLayer) currentMap.getLayers().get(i);
				int tileWidth = (int) (currentLayer.getTileWidth() * Maps.MAP_UNIT_SCALE),
					tileHeight = (int) (currentLayer.getTileHeight() * Maps.MAP_UNIT_SCALE);
				int id = 0;
				for( int x = 0 ; x < 50 ; x++ ) {
					for( int y = 0 ; y < 50 ; y++ ) {
						if( currentLayer.getCell(x, y) != null ) {
							Enemy enemy = new Enemy();
							enemy.setId(id);
							enemy.setAlive(true);
							enemy.setLife(100+(GameValues.user.getLevel()*10));
							enemy.setDamage(5 + (GameValues.user.getLevel() * 3));
							int rnd = (int) (Math.random() * 4);
							switch( rnd ) {
								case 0: enemy.setFacing(StanceEnum.FRONT_STAND);
									break;
								case 1: enemy.setFacing(StanceEnum.BACK_STAND);
									break;
								case 2: enemy.setFacing(StanceEnum.LEFT_STAND);
									break;
								case 3: enemy.setFacing(StanceEnum.RIGHT_STAND);
									break;
							}
							enemy.setBounds(x*tileWidth, y*tileHeight, Enemy.WIDTH, Enemy.HEIGHT);
							
							enemy.setTexture(GameValues.monsters.get(MonsterEnum.GOBLIN.getValue()).get(enemy.getFacing().getValue()));
							
							enemies.add(enemy);
							
//							new Thread(enemy).start();
							
							id++;
						}
					}
				}
			}
		}
	}
	
}
