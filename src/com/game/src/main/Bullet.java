package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject{
	private double x;
	private double y;
	
	BufferedImageLoader loader = new BufferedImageLoader();
	BufferedImage bullet;
	
	public Bullet(double x, double y, ID id) {
		super(x, y, id);
		
		this.x = x;
		this.y = y;
		this.id = id;
		
		bullet = loader.loadImage("/Sprites/bullet.png");
	}
	
	public void tick() {
		y -= 10;
	}
	
	public void render(Graphics g) {
		g.drawImage(bullet, (int) x, (int) y, null);
	}
}
