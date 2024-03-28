package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{

	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		// Get player object 
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player)	player = handler.object.get(i);
		}
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
		float deltaX = x - player.getX() - 8; // X Diff of enemy and player
		float deltaY = y - player.getY() - 8; // Y Diff of enemy and player
		// Get Distance between Smart Enemy and Player
		float distance = (float) Math.sqrt(Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2));
		
		// Formula for following player (Change numerator to higher value for higher speeds)
		velX = (float) ((-1.0/distance) * deltaX); 
		velY = (float) ((-1.0/distance) * deltaY);
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.05f, handler));
		bounce();
	}
	
	// Makes player bounce within the screen
	public void bounce() {
		if (this.x >= Game.WIDTH - 33|| this.x <= 0) velX *= -1;
		if (this.y >= Game.HEIGHT - 60|| this.y <= 0) velY *= -1;
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x,(int)y, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}


}
