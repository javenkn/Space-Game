package com.game.src.main;

import java.util.LinkedList;

public class Physics {
	
	public static boolean collision(GameObject entity, LinkedList<GameObject> gameObjectList) {
		
		for(int i = 0; i < gameObjectList.size(); i++) {
			
			GameObject tempObject = gameObjectList.get(i);
			
			switch(entity.getID()) {
			case Player:
				if(tempObject.getID() == ID.Enemy) {
					if(entity.getBounds().intersects(tempObject.getBounds())) { //tempObject = basic enemy
						return true;
					}
				}
			case Enemy:
				if(tempObject.getID() == ID.Bullet) {
					if(entity.getBounds().intersects(tempObject.getBounds())) { //tempObject = basic enemy
						return true;
					}
				}
			default:
				break;
			}
		}
		
		return false;
	}
}
