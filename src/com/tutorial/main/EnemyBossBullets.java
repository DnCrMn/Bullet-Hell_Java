package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullets extends GameObject{

	private Handler handler;
	private Random r = new Random();
	
	public EnemyBossBullets(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = r.nextFloat(5 + 5) - 5;
		velY = 5;
	}

	public void tick() {
		x += velX;
		y += velY;
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));

		//Removes object from the game if it goes out of bounds
		if (y >= Game.HEIGHT) handler.removeObject(this);
	}
	
	// Makes player bounce within the screen
	public void bounce() {
		if (this.x >= Game.WIDTH - 33|| this.x <= 0) velX *= -1;
		if (this.y >= Game.HEIGHT - 60|| this.y <= 0) velY *= -1;
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}


}
