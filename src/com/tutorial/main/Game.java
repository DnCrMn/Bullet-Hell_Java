package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1550691097823471818L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	private Thread thread; // Make a thread
	private boolean running = false; // Game is not running by default

	private Random random;
	private Menu menu;
	private Handler handler; // create handler object for game class
	private HUD hud; // create hud object from hud class
	private Spawner spawner; // Create spawner object

	public STATE gameState = STATE.Menu; // Set Game State to show menu first

	// Game constructor (Add handler and game objects here)
	public Game() {

		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler)); // Add a key listener using KeyInput class
		this.addMouseListener(menu); // Add a mouse listener using Menu class
		new Window(WIDTH, HEIGHT, "MyFirstGame from RealTutsGML Tutorial", this);

		spawner = new Spawner(handler, hud);

		random = new Random();

		if (gameState == STATE.Game) {
			// Spawn player at center
			handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
			// Spawn enemies
			handler.addObject(
					new BasicEnemy(random.nextInt(HEIGHT - 64), random.nextInt(HEIGHT - 64), ID.BasicEnemy, handler));

		} else {
			for (int i = 0; i < 10; i++) {
				handler.addObject(new MenuParticle(random.nextInt(HEIGHT - 64), random.nextInt(HEIGHT - 64),
						ID.MenuParticle, handler));
			}
		}

	}

	public synchronized void start() { // Starting the Game (by starting the thread)
		thread = new Thread(this);
		thread.start();
		running = true; // Running is set to true
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace(); // Error message
		}
	}

	// The game loop (Even notch uses this type of game loop)
	public void run() {
		/*
		 * TLDR;
		 * The game loop basically gets the current time, and waits for the game to
		 * "tick".
		 * The delta gets the difference between the now and lastTime variable. Of
		 * course, time
		 * always changes, so the lastTime would be the now var before it changed. BTW
		 * delta means
		 * the change in two variables of similar units. Example: 100 feet and 50 feet.
		 * the delta
		 * is 50 feet
		 */
		this.requestFocus(); // So you don't have to click the game to play the game
		long lastTime = System.nanoTime();
		double amountofTicks = 60.0; // Amount of ticks per Second
		double ns = 1000000000 / amountofTicks; // Amount of nanoseconds per tick
		double delta = 0; // Change in time
		long timer = System.currentTimeMillis();
		int frames = 0; // Game's fps

		// While the game is running (Boolean running = true)
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) { // When one tick has passed
				tick();
				delta--; // Reset delta to 0
				frames++;
			}
			// If the game is running, render graphics
			if (running) {
				render();
			}

			// If the difference between the current time and the timer is greater than 1
			// second
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000; // Add 1 second to timer
				// System.out.println("FPS: " + frames); //Check the current FPS
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();
		if (gameState == STATE.Game) {
			hud.tick();
			spawner.tick();

			if (hud.HEALTH <= 0) {
				handler.object.clear(); // clear all objects first
				gameState = STATE.GameOver;
				hud.HEALTH = 100;
				for (int i = 0; i < 10; i++) {
					handler.addObject(new MenuParticle(random.nextInt(HEIGHT - 64), random.nextInt(HEIGHT - 64),
							ID.MenuParticle, handler));
				}
			}
		} else if (gameState == STATE.Menu || gameState == STATE.GameOver)
			menu.tick();
	}

	// Rendering the graphics
	private void render() {
		BufferStrategy bs = this.getBufferStrategy(); // Make a buffer strategy

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// Set window to solid color (Otherwise it swill flicker black and white)
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);
		// Add Handler and HUD Rendering
		if (gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver) {
			menu.render(g);
		}

		g.dispose();
		bs.show();
	}

	public static float clamp(float var, float max, float min) {
		/*
		 * Making sure that the var doesn't exceed max or min value
		 * by clamping var to min and max
		 */
		if (var >= max)
			return var = max;
		if (var <= min)
			return var = min;
		else
			return var;

	}

	public static void main(String[] args) {
		new Game();
	}

}
