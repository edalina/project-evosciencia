package com.eciz.evosciencia.resources;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Maps {
	
	private TiledMap testMap;
	private TiledMapRenderer renderer;
	
	public Maps() {
		testMap = new TmxMapLoader().load("tmx/test.tmx");
		renderer = new OrthogonalTiledMapRenderer(testMap, 2);
	}
	
	public static Maps getMaps() {
		return new Maps();
	}

	public TiledMap getTestMap() {
		return testMap;
	}

	public void setTestMap(TiledMap testMap) {
		this.testMap = testMap;
	}

	public TiledMapRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(TiledMapRenderer renderer) {
		this.renderer = renderer;
	}

}
