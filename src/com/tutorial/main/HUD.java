package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	public static float HEALTH = 100;
	
	private float greenValue = 255; // RGB value for green
	
	// Variables for score and level
	private int score = 0;
	private int lvl = 1;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 100, 0);
		greenValue = Game.clamp(greenValue, 255, 0); //Makes it so that the health can rgb for green can only go from 255 to 0
		
		greenValue = 255 * HEALTH / 100; // Based on the percentage of your health, the color of your health will change
		score++;
		
	}
	
	public void render(Graphics g) {
		// Makes the Health Bar
		g.setColor(new Color(75, 0, 0));
		g.fillRect(15, 15, 200, 27);
		g.setColor(new Color(150, (int)greenValue, 0));
		g.fillRect((int)15, (int)15, (int)HEALTH * 2, (int)27);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 27);
		
		// Makes the Score and level
		g.drawString("Level " + lvl, 15, 65);
		g.drawString("Score: " + score, 15, 85);
		
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	public int getLvl() {
		return lvl;
	}
}
