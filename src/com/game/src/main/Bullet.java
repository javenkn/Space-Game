package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Bullet extends GameObject{

	BufferedImageLoader loader = new BufferedImageLoader();
	BufferedImage bullet;
	private LinkedList<GameObject> gameObjectList;
	
	public Bullet(double x, double y, ID id, LinkedList<GameObject> gameObjectList) {
		super(x, y, id);

		this.gameObjectList = gameObjectList;
		bullet = loader.loadImage("/Sprites/bullet.png");
	}
	
	public void tick() {
		y -= 10;
		
		if(Physics.collision(this, gameObjectList)) {
			System.out.println("Collision detected");
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(bullet, (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 15, 29);
	}
}
