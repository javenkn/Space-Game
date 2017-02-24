package com.game.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Player player;

	public KeyInput(Player player) {
		this.player = player;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); // key pressed
		
		if(key == KeyEvent.VK_UP) player.setY(player.getY() - 5); // when up key is pressed
		else if(key == KeyEvent.VK_DOWN) player.setY(player.getY() + 5); // when down key is pressed
		else if(key == KeyEvent.VK_LEFT) player.setX(player.getX() - 5); // when left key is pressed
		else if(key == KeyEvent.VK_RIGHT) player.setX(player.getX() + 5); // when right key is pressed
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
}
