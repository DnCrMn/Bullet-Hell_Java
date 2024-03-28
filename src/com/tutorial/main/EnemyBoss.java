package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{

	private Handler handler;
	private int downTimer = 80; // Timer for when boss goes down the screen
	private int moveTimer = 50; // Timer for when boss moves sideways
	private Random r = new Random();
	
	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if (downTimer <= 0) { velY = 0; moveTimer--; } // Sets the boss in place and starts moveTimer
		else downTimer--; // Timer decrement
		
		// Boss moves sideways and spawns bullets
		if (moveTimer <= 0) {
			if(velX == 0) velX = 2;
			
			// Increases x velocity of Boss
			velX = (velX > 0) ? velX + 0.005f : velX - 0.005f;
			
			velX = Game.clamp(velX, 10, -10); // Make sure that the boss' speed doesn't go beyond 10
			int spawn = r.nextInt(10);
			if (spawn == 0) handler.addObject(new EnemyBossBullets((int)x + 32, (int)y + 32, id, handler));
		}
		
		
		bounce();
	}
	
	// Makes player bounce within the screen
	public void bounce() {
		if (this.x >= Game.WIDTH - 66|| this.x <= 0) velX *= -1;
		//if (this.y >= Game.HEIGHT - 60|| this.y <= 0) velY *= -1;
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 64, 64);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,64,64);
	}


}
