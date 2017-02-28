package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyBullet extends GameObject{
	
	BufferedImageLoader loader = new BufferedImageLoader();
	BufferedImage enemyBullet;
	Random r = new Random();
	private GameObject player;
	private Controller controller;
	private double diffX, diffY, distance;
	
	public EnemyBullet(double x, double y, ID id, Controller controller) {
		super(x, y, id);
		
		this.controller = controller;
		
		enemyBullet = loader.loadImage("/Sprites/bullet3.png");
		
		for(int i = 0; i < controller.getGameObjectList().size(); i++) {
			if(controller.getGameObjectList().get(i).getID() == ID.Player) player = controller.getGameObjectList().get(i);
		}
		
		diffX = x - player.getX() - 8;
		diffY = y - player.getY() - 8; 
		distance = (double) Math.sqrt(((x - player.getX()) *  (x - player.getX())) + ((y - player.getY()) *  (y - player.getY())));
		
		velX = (double) ((-5.0 / distance) * diffX);
		velY = (double) ((-5.0 / distance) * diffY);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		 
		if(y >= Game.HEIGHT * Game.SCALE) controller.removeGameObject(this);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(enemyBullet, (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 15, 30);
	}
}
