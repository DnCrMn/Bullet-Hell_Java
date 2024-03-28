package com.tutorial.main;

import java.util.Random;

public class Spawner {
	private Handler handler;
	private HUD hud; // implement HUD
	private int scoreKeep = 0; // Keeps the score
	private Random r = new Random();
	
	public Spawner(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		
		// Every 500 increments of score, the level increases and spawns enemies
		if (scoreKeep >= 100) {
			scoreKeep = 0; // resets scoreKeep variable
			hud.setLvl(hud.getLvl() + 1); // Go to the next lvl
			
			if (hud.getLvl() == 2) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.EnemyBoss, handler));
			}
			else if (hud.getLvl() == 3) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.BasicEnemy, handler));
			}
			else if (hud.getLvl() == 4) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.FastEnemy, handler));
			}
			else if (hud.getLvl() == 5) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.FastEnemy, handler));
			}
			else if (hud.getLvl() == 6) {
				handler.addObject(new SlowEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.SlowEnemy, handler));
			}
			else if (hud.getLvl() == 7) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.SlowEnemy, handler));
			}
			else if (hud.getLvl() == 10) {
				// Clear enemies then spawn the Boss
				handler.clearEnemies();
				handler.addObject(new EnemyBoss(Game.WIDTH/2 - 32, -100, ID.EnemyBoss, handler));
			}
		}
			
	}
}
