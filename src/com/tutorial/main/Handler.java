package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

// Handler Class (Handles and processes all the game objects; Updates and renders them)
public class Handler {
	// Create a linked list for game objects (Because we don't know how many objects the game will have)
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		// Loop through every object in the linked list
		for(int i = 0; i < object.size(); i ++) {
			GameObject tempObject = object.get(i); // Uses temporary object to get ID inside object
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g); 
		}
	}
	
	// Method to add game Objects
	public void addObject(GameObject object) {
		this.object.add(object); // add the game object to the linked list
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object); // removes the game object to the linked list
	}

	public void clearEnemies() {
		object.removeIf(object -> object.getId() != ID.Player);
		
	}
}
