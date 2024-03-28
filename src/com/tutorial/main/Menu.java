package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter{
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		// Cursor Position
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState == STATE.Menu) {
			// Play Button
			if (mouseOver(mx, my, Game.WIDTH/2 - 100, 100, 200, 64)) {
				handler.clearEnemies();
				game.gameState = STATE.Game;
				// Spawn player at center
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));		
				// Spawn enemies
				handler.addObject(new BasicEnemy(r.nextInt(Game.HEIGHT - 64), r.nextInt(Game.HEIGHT - 64), ID.BasicEnemy, handler));	
			}
			
			// Help button
			if (mouseOver(mx, my, Game.WIDTH/2 - 100, 200, 200, 64)) {
				game.gameState = STATE.Help;
			}
			
			// Quit button
			if (mouseOver(mx, my, Game.WIDTH/2 - 100, 300, 200, 64)) {
				System.exit(1);
			}
			
		}
		
		if (game.gameState == STATE.Help) {
			// Help button's back button
			if (mouseOver(mx, my,  Game.WIDTH/2 - 100, 300, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		if (game.gameState == STATE.GameOver) {
			// Game Over button's back button
			if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 300, 200, 64)) {
				game.gameState = STATE.Menu;
				hud.setLvl(1);
				hud.setScore(0);
				return;
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
	}
	
	public void tick() {
		
	}
	
	// Method to check if mouse is over an object
	private boolean mouseOver(int mx, int my ,int x, int y, int width, int height) {
		// If Cursor x position is within the range of the left and rightmost corners of the object
		if (mx > x && mx < x + width) {
			// If the cursor y position is within the range of the upper and lower areas of the object
			if (my > y && my < y + height) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			// Font for text
			Font font = new Font("arial", 1, 50);
			Font fontTwo = new Font("arial", 1, 30);
			
			// Menu Text
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Dodger", Game.WIDTH/2 - 90, 75);
			
			// Draws rectangle menu options
			g.setColor(Color.green);
			g.drawRect(Game.WIDTH/2 - 100, 100, 200, 64); // Rectangle #1
			// Text for Rectangle #1
			g.setFont(fontTwo);
			g.setColor(Color.white);
			g.drawString("Start", Game.WIDTH/2 - 35, 143);
			
			g.setColor(Color.green);
			g.drawRect(Game.WIDTH/2 - 100, 200, 200, 64); // Rectangle #2
			// Text for Rectangle #2
			g.setFont(fontTwo);
			g.setColor(Color.white);
			g.drawString("Help", Game.WIDTH/2 - 30, 243);
			
			g.setColor(Color.green);
			g.drawRect(Game.WIDTH/2 - 100, 300, 200, 64); // Rectangle #3
			// Text for Rectangle #3
			g.setFont(fontTwo);
			g.setColor(Color.white);
			g.drawString("Quit", Game.WIDTH/2 - 30, 343);
		}
		else if (game.gameState == STATE.Help) {
			Font font = new Font("arial", 1, 50);
			Font fontTwo = new Font("arial", 1, 30);
			Font fontThree = new Font("arial", 1, 20);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Help", 265, 70);
			
			g.setFont(fontThree);
			g.setColor(Color.white);
			g.drawString("Use the WASD keys to doge around enemy blocks.", 75, 200);
			
			g.setColor(Color.green);
			g.drawRect(Game.WIDTH/2 - 100, 300, 200, 64); // Back Rectangle
			// Text for Back Rectangle
			g.setFont(fontTwo);
			g.setColor(Color.white);
			g.drawString("Back", Game.WIDTH/2 - 30, 343);
		}
		// GAME OVER SCREEN
		else if (game.gameState == STATE.GameOver) {
			Font font = new Font("arial", 1, 50);
			Font fontTwo = new Font("arial", 1, 30);
			Font fontThree = new Font("arial", 1, 20);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Game Over", 185, 70);
			
			g.setFont(fontThree);
			g.setColor(Color.white);
			g.drawString("You lost with a score of " + hud.getScore(), 185, 200);
			
			g.setColor(Color.green);
			g.drawRect(Game.WIDTH/2 - 100, 300, 200, 64); // Back Rectangle
			// Text for Back Rectangle
			g.setFont(fontTwo);
			g.setColor(Color.white);
			g.drawString("Main Menu", Game.WIDTH/2 - 80, 343);
		}
		
	}
	
}
