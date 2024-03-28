package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

// Objects of the game (Abstracted so the GameObject can not be instantiated)
public abstract class GameObject {
	
	// Essentials for objects in the game
	// protected means variables can only be accessed through inheritance (extends)
	protected float x, y; // position of the object
	protected ID id;
	protected float velX, velY; // velocity of object 
	
	// Constructor for GameObject
	public GameObject (float x2, float y2, ID id) {
		this.x = x2;
		this.y = y2;
		this.id = id;
	}
	
	// Ticks and Rendering GameObject
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	
	public ID getId() {
		return id;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public float getVelY() {
		return velY;
	}
}
