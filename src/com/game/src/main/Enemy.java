package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {
	private double x, y;
	
	private BufferedImage enemy;
	Random r = new Random();
	private double speed = r.nextDouble() * (0.4) + 0.1;
	
	public Enemy(double x, double y, BufferedImageLoader loader, ID id) {
		super(x, y, id);
		
		this.x = x;
		this.y = y;
		this.id = id;
		
		enemy = loader.loadImage("/Sprites/roundysh.png");
	}
	
	public void tick() {
		if(y > (Game.HEIGHT * Game.SCALE)) {
			y = 0;
			x = r.nextInt((Game.WIDTH * Game.SCALE) - 56);
		}
		y += speed;
		this.setY(y);
	}
	
	public void render(Graphics g) {
		g.drawImage(enemy, (int) x, (int) y, null);
	}
}