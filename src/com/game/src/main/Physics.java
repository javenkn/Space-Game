package com.game.src.main;

public class Physics {
	
	public static boolean collision(GameObject entity, GameObject comparedEntity) {
		switch(entity.getID()) {
		case Player:
			if(entity.getBounds().intersects(comparedEntity.getBounds())) {
				if(comparedEntity.getID() == ID.Enemy) { // if the player hits the enemy
					return true;
				} else {
					return false;
				}
			}
		case Enemy:
			if(comparedEntity.getID() == ID.Bullet) { // if the enemy gets hit by a bullet
				if(entity.getBounds().intersects(comparedEntity.getBounds())) {
					return true;
				}
			}
		default:
			break;
		}
		return false;
	}
}
