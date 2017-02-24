package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;;

public class Player {
	private double x;
	private double y;
	
	private double velX = 0;
	private double velY = 0;
	
	private BufferedImage player;
	BufferedImageLoader loader = new BufferedImageLoader();
	
	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		
		player = loader.loadImage("/Sprites/bgbattleship.png");
	}
	
	public void tick() {
		x += velX;
		y += velY;
	}
	
	public void render(Graphics g) {
		g.drawImage(player, (int) x, (int) y, null);
	}
	
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
