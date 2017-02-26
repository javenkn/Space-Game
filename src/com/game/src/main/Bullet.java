package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//import java.util.LinkedList;

public class Bullet extends GameObject{

	BufferedImageLoader loader = new BufferedImageLoader();
	BufferedImage bullet;
	
	public Bullet(double x, double y, ID id) {
		super(x, y, id);
		
		bullet = loader.loadImage("/Sprites/bullet.png");
	}
	
	public void tick() {
		y -= 10;
	}
	
	public void render(Graphics g) {
		g.drawImage(bullet, (int) x, (int) y, null);
	}

	public Rectangle getBounds() { // collision box
		return new Rectangle((int) x, (int) y, 15, 29);
	}
}
