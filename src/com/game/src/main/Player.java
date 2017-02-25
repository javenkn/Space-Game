package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;;

public class Player {
	private double x;
	private double y;
	
	private double velX = 0;
	private double velY = 0;
	
	private BufferedImage player;
	
	public Player(double x, double y, BufferedImageLoader loader) {
		this.x = x;
		this.y = y;
		
		player = loader.loadImage("/Sprites/bgbattleship.png");
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0) x = 0;
		else if(x >= Game.WIDTH * Game.SCALE - 50) x = (Game.WIDTH * Game.SCALE) - 50;
		
		if(y <= 0) y = 0;
		else if(y >= Game.HEIGHT * Game.SCALE - 65) y = (Game.HEIGHT * Game.SCALE) - 65;
	}
	
	public void render(Graphics g) {
		g.drawImage(player, (int) x, (int) y, null);
	}
	
	// Getters and Setters
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
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
}
