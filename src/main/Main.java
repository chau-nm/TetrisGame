package main;

import controller.Controller;
import controller.IController;
import model.Game;
import model.IGame;

public class Main implements Runnable {
	private Thread thread;
	private IGame game;
	private IController controller;
	private int fps = 30;
	private int tagetTime = 1000 / fps;

	public Main() {
		game = new Game();
		controller = new Controller(game);
		
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run() {
		long startTime;
		int delay;

		while (true) {
			startTime = System.nanoTime();

			game.updateInGame();
			
			delay = tagetTime - (int) ((System.nanoTime() - startTime) / 1000000);
			if (delay < 0)
				delay = 30;
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}
