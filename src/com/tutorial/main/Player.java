package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{

	Handler handler;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		// Player moves depending on their velocity
		x += velX; 
		y += velY;
		
		// Use clamp function to make sure the player is always inside the game window
		x = Game.clamp(x,Game.WIDTH - 46,0); 
		y = Game.clamp(y,Game.HEIGHT - 64,0);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.1f, handler)); // Trail for player
		collision(); // Add collision detection
	}
	
	public void collision() {
		// Enemy Collision (If player gets hit)
				for (int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					
					// If the object is an enemy
					if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy 
						|| tempObject.getId() == ID.SlowEnemy || tempObject.getId() == ID.SmartEnemy
						|| tempObject.getId() == ID.EnemyBoss) {
						if (tempObject.getBounds().intersects(this.getBounds()))  HUD.HEALTH--; // Decrease heath
					}
				}
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; // Casting graphics 2d over graphics var
		
		g.setColor(Color.white);
		g.fillRect((int)x,(int) y, 32, 32);

		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}

}
