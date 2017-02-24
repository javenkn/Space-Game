package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;;

public class Player {
	private double x;
	private double y;
	
	private BufferedImage player;
	BufferedImageLoader loader = new BufferedImageLoader();
	
	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		
		player = loader.loadImage("/Sprites/bgbattleship.png");
	}
	
	public void tick() {
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
}
