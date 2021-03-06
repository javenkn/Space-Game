package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

public class Enemy extends GameObject {
	
	private BufferedImage enemy;
	Random r = new Random();
	private double speed = r.nextDouble() * (0.7) + 0.2;
	private Controller controller;
	private HUD hud;
	private LinkedList<GameObject> gameObjectList;
	private int count = 0;
	
	public Enemy(double x, double y, BufferedImageLoader loader, ID id, Controller controller, HUD hud) {
		super(x, y, id);
		
		this.controller = controller;
		this.hud = hud;
		
		enemy = loader.loadImage("/Sprites/roundysh.png");
		gameObjectList = controller.getGameObjectList();
	}
	
	public void tick() {
		count++;
		if(y > (Game.HEIGHT * Game.SCALE)) {
			y = -50;
			x = r.nextInt((Game.WIDTH * Game.SCALE) - 56);
		}
		y += speed;
		this.setY(y);
		
		if(count == 80) {
			controller.addGameObject(new EnemyBullet(x + 22, y + 55, ID.EnemyBullet, controller));
			count = 0;
		}
		
		for(int i = 0; i < gameObjectList.size(); i++) {
			
			GameObject tempObject = gameObjectList.get(i);
			
			if(Physics.collision(this, tempObject)) {
				controller.removeGameObject(tempObject); // removes bullet when collision happens
				controller.removeGameObject(this); // removes enemy
				hud.setEnemiesKilled(hud.getEnemiesKilled() + 1); // increments enemy count by 1
				hud.setRealEnemiesKilled(hud.getRealEnemiesKilled() + 1);
			}
			
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(enemy, (int) x, (int) y, null);
	}
	
	public Rectangle getBounds() { // collision box
		return new Rectangle((int) x, (int) y, 56, 60);
	}
}
