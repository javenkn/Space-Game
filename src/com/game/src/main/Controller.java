package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
	
	private LinkedList<Bullet> ammoList = new LinkedList<Bullet>();
	
	Bullet tempBullet;
	
	public void tick() {
		for(int i = 0; i < ammoList.size(); i++) {
			tempBullet = ammoList.get(i);
			
			if(tempBullet.getY() < 0) { // once the bullet is off the screen delete it
				removeBullet(tempBullet);
			}
			
			tempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < ammoList.size(); i++) {
			tempBullet = ammoList.get(i);
			
			tempBullet.render(g);
		}
	}
	
	public void addBullet(Bullet newBullet) {
		ammoList.add(newBullet);
	}
	
	public void removeBullet(Bullet bullet) {
		ammoList.remove(bullet);
	}
}
