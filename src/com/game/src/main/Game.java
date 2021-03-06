package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -7228164263489589306L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH/12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "2D Space Game";
	
	public static boolean paused = false;
	private boolean running = false;
	private Thread thread;
	
	private BufferedImageLoader loader;
	
	private Controller controller;
	private BufferedImage background;
	private HUD hud;
	private Menu menu;
	
	public enum STATE {
		Menu,
		Pause,
		Help,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Game () { // constructor - initializes everything
		
		hud = new HUD();
		loader = new BufferedImageLoader();
		controller = new Controller(loader, hud);
		menu = new Menu(controller, hud, loader);
		
		background = loader.loadImage("/Sprites/starstars.jpg");
		// attaches keyboard listener
		this.addKeyListener(new KeyInput(controller));
		this.addMouseListener(menu);
	}
	
	private synchronized void start() { // utilizes threads to start the game
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() { // stops the game from running using threads
		if(!running)
			return;
		
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(1);
	}
	
	public void run() {
		this.requestFocus(); // makes it so that you don't have to click on screen to move
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(running) { // creates FPS and ticks
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, FPS " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if(gameState == STATE.Game) {
			if(!paused) {
				controller.tick();
				hud.tick();
				
				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;
				}
				
				if(hud.getEnemiesKilled() >= hud.getEnemyCount()) {
					hud.setEnemyCount(hud.getEnemyCount() + 2);
					hud.setEnemiesKilled(0);
					controller.createEnemies(hud.getEnemyCount());
				}
			}
		}
	}
	
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy(); // buffer for faster fps
		if(bs == null) {
			createBufferStrategy(3); // creates 3 buffers
			return;
		}
		
		Graphics g = bs.getDrawGraphics(); // initializes graphics variable
		
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		
		if(gameState == STATE.Game) { // game state
			controller.render(g);
			hud.render(g);
			if(paused) {
				Font font = new Font("arial", 1, 30);
				g.setFont(font);
				g.setColor(Color.WHITE);
				g.drawString("Paused", (WIDTH*SCALE)/2 - 60, (HEIGHT*SCALE)/2 - 30);
			}
		} else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
		}
		
		g.dispose(); // shows the graphics
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max) 
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		
		// sets the size of the game screen
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		// creates JFrame
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); // doesn't allow resizing
		frame.setLocationRelativeTo(null); // sets window to the center of screen 
		frame.setVisible(true); // shows JFrame
		
		game.start();
	}
	
}
