package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {
	
	private BufferedImage enemy;
	Random r = new Random();
	private double speed = r.nextDouble() * (0.4) + 0.1;
	private Controller controller;
	private HUD hud;
	
	public Enemy(double x, double y, BufferedImageLoader loader, ID id, Controller controller, HUD hud) {
		super(x, y, id);
		
		this.controller = controller;
		this.hud = hud;
		
		enemy = loader.loadImage("/Sprites/roundysh.png");
	}
	
	public void tick() {
		if(y > (Game.HEIGHT * Game.SCALE)) {
			y = 0;
			x = r.nextInt((Game.WIDTH * Game.SCALE) - 56);
		}
		y += speed;
		this.setY(y);
		
		if(Physics.collision(this, controller.getGameObjectList())) {
			controller.removeGameObject(this); // removes enemy
			hud.setEnemiesKilled(hud.getEnemiesKilled() + 1); // increments enemy count by 1
			hud.setRealEnemiesKilled(hud.getRealEnemiesKilled() + 1);
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(enemy, (int) x, (int) y, null);
	}
	
	public Rectangle getBounds() { // collision box
		return new Rectangle((int) x, (int) y, 56, 60);
	}
}
