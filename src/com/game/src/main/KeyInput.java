package com.game.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Player player;
	private Controller controller;
	private boolean[] keyDown = new boolean[4];

	public KeyInput(Player player, Controller controller) {
		this.player = player;
		this.controller = controller;
		
		for(int i = 0; i < keyDown.length; i++) {
			keyDown[i] = false;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); // key pressed
		
		if(key == KeyEvent.VK_UP) {
			player.setVelY(-5); // when up key is pressed
			keyDown[0] = true;
		}
		else if(key == KeyEvent.VK_DOWN) {
			player.setVelY(5); // when down key is pressed
			keyDown[1] = true;
		}
		else if(key == KeyEvent.VK_LEFT) {
			player.setVelX(-5); // when left key is pressed
			keyDown[2] = true;
		}
		else if(key == KeyEvent.VK_RIGHT) {
			player.setVelX(5); // when right key is pressed
			keyDown[3] = true;
		}
		
		if(key == KeyEvent.VK_SPACE) { // shoots bullets
			controller.addGameObject(new Bullet(player.getX() + 17, player.getY() - 8, ID.Bullet));
		}
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode(); // key pressed
		
		// when key is released keyDown value is set to false
		if(key == KeyEvent.VK_UP) keyDown[0] = false;
		else if(key == KeyEvent.VK_DOWN) keyDown[1] = false;
		else if(key == KeyEvent.VK_LEFT) keyDown[2] = false;
		else if(key == KeyEvent.VK_RIGHT) keyDown[3] = false;
		
		// this prevents sticky keys
		// vertical movement
		// if both up and down are released then make the player stop
		if(!keyDown[0] && !keyDown[1]) player.setVelY(0);
		// horizontal movement
		//if both left and right are released then make the player stop
		if(!keyDown[2] && !keyDown[3]) player.setVelX(0);
	}
}
