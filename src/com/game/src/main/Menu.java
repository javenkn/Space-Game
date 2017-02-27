package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.game.src.main.Game.STATE;

public class Menu extends MouseAdapter{

	private HUD hud;
	private Controller controller;
	private BufferedImageLoader loader;

	public Menu(Controller controller, HUD hud, BufferedImageLoader loader) {
		this.controller = controller;
		this.hud = hud;
		this.loader = loader;
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu) {
			// play button
			if(mouseOver(mx, my, 220, 150, 200, 64)) {
				Game.gameState = STATE.Game;
				controller.addGameObject(new Player(((Game.WIDTH*Game.SCALE)/2) - 30, 350, loader, ID.Player, controller, hud));
				controller.createEnemies(hud.getEnemyCount());
//				AudioPlayer.getSound("sound").play();
				return;
			}
			
			// help button
			if(mouseOver(mx, my, 220, 250, 200, 64)) {
				Game.gameState = STATE.Help;
//				AudioPlayer.getSound("sound").play();
			}
			
			// quit button
			if(mouseOver(mx, my, 220, 350, 200, 64)) {
				System.exit(1);
			}
		}
		
		// back button for help screen
		if(Game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 220, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
//				AudioPlayer.getSound("sound").play();
				return;
			}
		}
		
		// try again button
		if(Game.gameState == STATE.End) {
			if(mouseOver(mx, my, 220, 250, 200, 64)) { // pressed try again button
				Game.gameState = STATE.Game;
				hud.setEnemiesKilled(0);
				hud.setRealEnemiesKilled(0);
				hud.setEnemyCount(5);
				controller.clearAll();
				controller.addGameObject(new Player(((Game.WIDTH*Game.SCALE)/2) - 30, 350, loader, ID.Player, controller, hud));
				controller.createEnemies(hud.getEnemyCount());
			} else if(mouseOver(mx, my, 220, 350, 200, 64)) { // pressed back to main menu
				Game.gameState = STATE.Menu;
				hud.setEnemiesKilled(0);
				hud.setRealEnemiesKilled(0);
				hud.setEnemyCount(5);
				controller.clearAll();
			}
		}
	}
	
	public void render(Graphics g) {
		if(Game.gameState == STATE.Menu) {
			Font font = new Font("arial", 1, 55);
			Font font2 = new Font("arial", 1, 30);
			int x = 120, y = 90;
			
			for (int i = 0; i < 5; i++) {
				g.setFont(font);
				g.setColor(Color.GRAY);
				g.drawString("Space Invasion", ShiftEast(x, i), ShiftNorth(ShiftSouth(y, i), 1));
				g.setColor(Color.GRAY);
				g.drawString("Space Invasion", ShiftWest(ShiftEast(x, i), 1), ShiftSouth(y, i));
			}
			
			g.setColor(Color.WHITE);
			g.drawString("Space Invasion", ShiftEast(x, 5), ShiftSouth(y, 5));
			
//			g.setFont(font);
			g.setColor(Color.white);
//			g.drawString("Space Invasion", 130, 70);
			
			g.setFont(font2);
			g.drawRect(220, 150, 200, 64);
			g.drawString("Play", 288, 190);
			
			g.drawRect(220, 250, 200, 64);
			g.drawString("Help", 288, 290);
			
			g.drawRect(220, 350, 200, 64);
			g.drawString("Quit", 288, 390);
		} else if(Game.gameState == STATE.Help) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			Font font3 = new Font("arial", 1, 20);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("HELP", 248, 70);
			
			g.setFont(font3);
			g.drawString("Use arrow keys to control player and dodge enemies.", 65, 200);
			g.drawString("Press the space bar to shoot the enemies.", 65, 240);
			
			g.setFont(font2);
			g.setColor(Color.white);
			g.drawRect(220, 350, 200, 64);
			g.drawString("Back", 284, 390);
		} else if(Game.gameState == STATE.End) {
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			Font font3 = new Font("arial", 1, 20);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(font3);
			if(hud.getRealEnemiesKilled() != 1) {
				g.drawString("You killed " + hud.getRealEnemiesKilled() + " enemies.", 210, 200);
			} else {
				g.drawString("You killed " + hud.getRealEnemiesKilled() + " enemy.", 230, 200);
			}
			
			g.setFont(font2);
			g.drawRect(220, 250, 200, 64);
			g.drawString("Try Again", 250, 290);
			
			g.drawRect(220, 350, 200, 64);
			g.drawString("Main Menu", 243, 390);
		}
	}
	
	public int ShiftNorth(int p, int distance) {
		return (p - distance);
	}
	public int ShiftSouth(int p, int distance) {
		return (p + distance);
	}
	public int ShiftEast(int p, int distance) {
		return (p + distance);
	}
	public int ShiftWest(int p, int distance) {
		return (p - distance);
	}
}
