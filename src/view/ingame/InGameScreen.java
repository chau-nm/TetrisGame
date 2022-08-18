package view.ingame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import audio.Audio;
import controller.IController;
import model.Game;
import model.IGame;

public class InGameScreen extends JFrame implements IInGame, KeyListener {
	private IController controller;
	private BoardGamePanel boardGamePn;
	private NextShapePanel nextShapePn;
	private ScorePanel scorePn;
	private ButtonPanel optionPn;
	private JPanel rightPn;

	public InGameScreen(IController controller, Observable observableModel, Observable observableLanguage) {
		this.controller = controller;
		if (observableModel != null) {
			boardGamePn = new BoardGamePanel(this, observableModel, observableLanguage);
			nextShapePn = new NextShapePanel(this, observableModel, observableLanguage);
			scorePn = new ScorePanel(this, observableModel, observableLanguage);
			optionPn = new ButtonPanel(this, observableLanguage);
		}
		
		add(boardGamePn);
		displayRightPn();
		setFrame();
	}

	public void displayRightPn() {
		add(rightPn = new JPanel());
		rightPn.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		rightPn.setBackground(new Color(161, 114, 224));
		rightPn.setPreferredSize(new Dimension(260, IGame.HEIGHT * IGame.TILE_SIZE));
		rightPn.add(nextShapePn);
		rightPn.add(scorePn);
		rightPn.add(optionPn);
	}
	
	public void setFrame() {
		setFocusable(true);
		addKeyListener(this);
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		getContentPane().setBackground(new Color(161, 114, 224));
		pack();
		setTitle("TETRIS");
		setIconImage(new ImageIcon("resource/img/iconGame.png").getImage());
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void requestFocus() {
		super.requestFocus();
	}

	@Override
	public void pause() {
		controller.pause();
	}

	@Override
	public void resume() {
		controller.resume();
	}

	@Override
	public void startNewGame() {
		controller.startNewGame();
	}

	@Override
	public void left() {
		controller.left();
	}

	@Override
	public void right() {
		controller.right();
	}

	@Override
	public void down() {
		controller.down();
	}

	@Override
	public void setNormalSpeed() {
		controller.setNormalSpeed();
	}

	@Override
	public void rotate() {
		controller.rotate();
	}

	@Override
	public void exit() {
		controller.exit();
	}

	@Override
	public void backToHome() {
		if (controller.backToHome())
			dispose();
	}
	
	@Override
	public void disposeInGame() {
		dispose();
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT)
			left();
		if (key == KeyEvent.VK_RIGHT)
			right();
		if (key == KeyEvent.VK_DOWN)
			down();
		if (key == KeyEvent.VK_UP)
			rotate();
		if (key == KeyEvent.VK_ESCAPE)
			pause();
		if (key == KeyEvent.VK_N)
			startNewGame();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_DOWN)
			setNormalSpeed();
	}
	
}
