package view.ingame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import factory.ColorFactory;
import factory.IColorFactory;
import model.Game;
import model.IGame;
import obj.AShape;
import translation.Language;

public class BoardGamePanel extends JPanel implements Observer {
	private int[][] board;
	private AShape currentShape;
	private IInGame view;
	private int tileSize;
	private JLabel signalLb;
	private int prepareTime;
	private IColorFactory colorFactory;
	private String gameOverName = "GAME OVER", 
					readyName = "READY!!!", 
					startName = "START!!!";

	public BoardGamePanel(IInGame view, Observable observableModel, Observable observableLanguage) {

		observableModel.addObserver(this);
		observableLanguage.addObserver(this);

		this.view = view;
		tileSize = IGame.TILE_SIZE;
		colorFactory = new ColorFactory();

		setFrame();
		displaySignalLb();
	}

	private void setFrame() {
		setPreferredSize(new Dimension(IGame.WIDTH * IGame.TILE_SIZE, IGame.HEIGHT * IGame.TILE_SIZE));
		setBackground(new Color(232, 228, 240));
		setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2)));
		setLayout(new BorderLayout());
	}

	private void displaySignalLb() {
		add(signalLb = new JLabel(""), BorderLayout.CENTER);
		signalLb.setHorizontalAlignment(JLabel.CENTER);
		signalLb.setFont(new Font(Font.DIALOG, Font.BOLD, 60));
	}

	public void update(Observable o, Object arg) {
		updateBoardGame(o);
		updateLanguage(o);
	}

	private void updateBoardGame(Observable o) {
		if (o instanceof Game) {
			Game game = (Game) o;
			board = game.getBoard();
			currentShape = game.getCurrentShape();
			prepareTime = game.getPrepareTime();
			changeSignalLbText(game);
		}
	}

	private void updateLanguage(Observable o) {
		if (o instanceof Language) {
			Language lan = (Language) o;
			gameOverName = lan.getGameOverName();
			readyName = lan.getReadyName();
			startName = lan.getStartIconName();
		}
	}

	private void changeSignalLbText(Game game) {
		if (signalLb != null) {
			if (game.isGameOver())
				signalLb.setText(gameOverName);
			else
				switch (prepareTime) {
				case 2:
					signalLb.setText(readyName);
					break;
				case 1:
					signalLb.setText(startName);
					break;
				default:
					signalLb.setText("");
					break;
				}
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawShape(g);
		if (board != null) {
			drawBoard(g);
			// ve cac khoi da roi xuong
			drawBlockFixedly(g);
		}
	}

	public void drawShape(Graphics g) {
		if (currentShape != null) {
			int[][] coords = currentShape.getCoords();
			for (int i = 0; i < coords.length; i++) {
				for (int j = 0; j < coords[0].length; j++) {
					if (coords[i][j] != 0) {
						g.setColor(colorFactory.createColor(currentShape.getColorFlag()));
						g.fillRect(currentShape.getSize() * (currentShape.getX() + j),
								currentShape.getSize() * (currentShape.getY() + i), currentShape.getSize(),
								currentShape.getSize());
						g.setColor(Color.BLACK);
						g.drawRect(currentShape.getSize() * (currentShape.getX() + j),
								currentShape.getSize() * (currentShape.getY() + i), currentShape.getSize(),
								currentShape.getSize());
					}
				}
			}
		}
	}

	public void drawBoard(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		for (int i = 0; i < board.length; i++) {
			g.drawLine(0, i * tileSize, tileSize * board[i].length, i * tileSize);
		}
		for (int j = 0; j < board[0].length; j++) {
			g.drawLine(j * tileSize, 0, j * tileSize, tileSize * board.length);
		}
	}

	public void drawBlockFixedly(Graphics g) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != 0) {
					g.setColor(colorFactory.createColor(board[i][j]));
					g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
					g.setColor(Color.BLACK);
					g.drawRect(j * tileSize, i * tileSize, tileSize, tileSize);
				}
			}
		}
	}
}
