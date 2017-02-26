package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	private int enemyCount = 5;
	private int realEnemiesKilled = 0;
	private int enemiesKilled = 0;
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
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
