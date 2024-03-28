package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SlowEnemy extends GameObject{

	private Handler handler;
	public SlowEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 2;
		velY = 1;
	}

	public void tick() {
		x += velX;
		y += velY;
		handler.addObject(new Trail(x, y, ID.Trail, new Color(153,102, 0), 16, 16, 0.05f, handler));
		bounce();
	}
	
	// Makes player bounce within the screen
	public void bounce() {
		if (this.x >= Game.WIDTH - 33|| this.x <= 0) velX *= -1;
		if (this.y >= Game.HEIGHT - 60|| this.y <= 0) velY *= -1;
	}

	public void render(Graphics g) {
		g.setColor(new Color(153,102, 0));
		g.fillRect((int)x,(int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}


}
