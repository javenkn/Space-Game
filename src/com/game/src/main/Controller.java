package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
	
	private LinkedList<GameObject> gameObjectList = new LinkedList<GameObject>();
	
	Random r = new Random();
	BufferedImageLoader loader;
	GameObject tempObject;

	private HUD hud;
	
	public Controller(BufferedImageLoader loader, HUD hud) {
		this.hud = hud;
		this.loader = loader;
		
		createEnemies(hud.getEnemyCount());
	}
	
	public void tick() {
		for(int i = 0; i < gameObjectList.size(); i++) {
			tempObject = gameObjectList.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < gameObjectList.size(); i++) {
			tempObject = gameObjectList.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void createEnemies(int enemies) {
		System.out.println(enemies);
		for(int i = 0; i < enemies; i++) {
			addGameObject(new Enemy(r.nextInt((Game.WIDTH * Game.SCALE) - 56), 0, loader, ID.Enemy, this, hud));
		}
	}
	
	public void addGameObject(GameObject object) {
		gameObjectList.add(object);
	}
	
	public void removeGameObject(GameObject object) {
		gameObjectList.remove(object);
	}
	
	public LinkedList<GameObject> getGameObjectList() {
		return gameObjectList;
	}
}