package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	private int enemyCount = 5;
	private int enemiesKilled = 0;
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Enemies Killed: " + enemiesKilled, 15, 64);
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
}
