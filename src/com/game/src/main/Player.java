package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;;

public class Player extends GameObject{
	
	private double velX = 0;
	private double velY = 0;
	
	private BufferedImage player;
	private LinkedList<GameObject> gameObjectList;
	
	public Player(double x, double y, BufferedImageLoader loader, ID id, LinkedList<GameObject> gameObjectList) {
		super(x, y, id);
		this.gameObjectList = gameObjectList;
		
		player = loader.loadImage("/Sprites/bgbattleship.png");
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0) x = 0;
		else if(x >= Game.WIDTH * Game.SCALE - 50) x = (Game.WIDTH * Game.SCALE) - 50;
		
		if(y <= 0) y = 0;
		else if(y >= Game.HEIGHT * Game.SCALE - 65) y = (Game.HEIGHT * Game.SCALE) - 65;
		
		if(Physics.collision(this, gameObjectList)) {
			// health bar stuff
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(player, (int) x, (int) y, null);
	}
	
	// Getters and Setters
	
	public double getVelX(double velX) {
		return velX;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public double getVelY(double velY) {
		return velY;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public Rectangle getBounds() { // collision box
		return new Rectangle((int) x, (int) y, 50, 65);
	}
}
