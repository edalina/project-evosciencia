package com.eciz.evosciencia.resources;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.eciz.evosciencia.entities.Checkpoint;
import com.eciz.evosciencia.values.GameValues;

public class Maps {
	
	private TiledMap currentMap;
	private TiledMapRenderer renderer;
	public static final float MAP_UNIT_SCALE = 1f;
	public static final float MAP_WIDTH = 800 * MAP_UNIT_SCALE;
	public static final float MAP_HEIGHT = 800 * MAP_UNIT_SCALE;
	public String name = "town_1";
	
	public Maps() {
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
		createObjects();
		renderCheckpoints();
		renderer = new OrthogonalTiledMapRenderer(currentMap, MAP_UNIT_SCALE);
	}
	
	public void renderNonObstacleObj() {
		GameValues.maps.getRenderer().setView(GameValues.camera);
		for( int i = 0 ; i < GameValues.maps.getCurrentMap().getLayers().getCount() ; i++ ) {
			if( !GameValues.maps.getCurrentMap().getLayers().get(i).getName().contains(GameValues.OBSTACLE_PROPERTY) ) {
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

}
