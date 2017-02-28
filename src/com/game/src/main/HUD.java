package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	private int enemyCount = 3;
	private int realEnemiesKilled = 0;
	private int enemiesKilled = 0;
	
	public static float HEALTH = 100;
	private float greenValue = 255f;
	public int bounds = 0;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100+(bounds/2));
		greenValue = HEALTH * 2;
		greenValue = Game.clamp(greenValue, 0, 255);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect(15, 15, (int) (HEALTH * 2), 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + bounds, 32);
		
		g.setColor(Color.WHITE);
		g.drawString("Enemies Killed: " + realEnemiesKilled, 15, 64);
	}

	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public int getEnemiesKilled() {
		return enemiesKilled;
	}

	public void setEnemiesKilled(int enemiesKilled) {
		this.enemiesKilled = enemiesKilled;
	}
	
	public int getRealEnemiesKilled() {
		return realEnemiesKilled;
	}

	public void setRealEnemiesKilled(int enemies) {
		this.realEnemiesKilled = enemies;
	}
}
