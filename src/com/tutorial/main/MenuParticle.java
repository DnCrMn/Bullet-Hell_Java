package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{

	private Handler handler;
	private Random r = new Random();
	private Color color;
	
	
	public MenuParticle(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = r.nextInt(7+7) - 7;
		velY = r.nextInt(7+7) - 7;
		
		if (velX == 0) velX = 1;
		if (velY == 0) velY = 1;
		
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}

	public void tick() {
		x += velX;
		y += velY;
		handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.05f, handler));
		bounce();
	}
	
	// Makes player bounce within the screen
	public void bounce() {
		if (this.x >= Game.WIDTH - 33|| this.x <= 0) velX *= -1;
		if (this.y >= Game.HEIGHT - 60|| this.y <= 0) velY *= -1;
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x,(int)y, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}


}
