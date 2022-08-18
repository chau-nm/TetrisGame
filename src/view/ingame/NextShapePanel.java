package view.ingame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import translation.Language;
import factory.ColorFactory;
import factory.IColorFactory;
import model.Game;
import obj.AShape;

public class NextShapePanel extends JPanel implements Observer {

	private AShape nextShape;
	private IInGame view;
	private IColorFactory colorFactory;
	private JLabel title;

	public NextShapePanel(IInGame view, Observable observableModel, Observable observableLanguage) {
		this.view = view;
		colorFactory = new ColorFactory();
		observableModel.addObserver(this);
		observableLanguage.addObserver(this);
		
		displayTitle();
		setFrame();
	}
	
	private void displayTitle() {
		setLayout(new BorderLayout());
		add(title = new JLabel("NEXT"), BorderLayout.SOUTH);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
	}
	
	private void setFrame() {
		setPreferredSize(new Dimension(240, 240));
		setBackground(new Color(252, 252, 252));
		setBorder(BorderFactory.createBevelBorder(1));
	}

	public void update(Observable o, Object arg) {
		updateGame(o);
		updateLanguage(o);
		
	}
	
	private void updateGame(Observable o) {
		if (o instanceof Game) {
			Game game = (Game) o;
			nextShape = game.getNextShape();
			repaint();
		}
	}
	
	private void updateLanguage(Observable o) {
		if (o instanceof Language) {
			Language tran = (Language) o;
			title.setText(tran.getNextName());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (nextShape != null) {
			int[][] coords = nextShape.getCoords();
			int tileSize = nextShape.getSize();
			for (int i = 0; i < coords.length; i++) {
				for (int j = 0; j < coords[i].length; j++) {
					if (coords[i][j] != 0) {
						g.setColor(colorFactory.createColor(nextShape.getColorFlag()));
						g.fillRect((getWidth() - coords[0].length * nextShape.getSize()) / 2 + j * tileSize,
								(getHeight() - coords.length * nextShape.getSize()) / 2 + i * tileSize, tileSize,
								tileSize);
						g.setColor(Color.BLACK);
						g.drawRect((getWidth() - coords[0].length * nextShape.getSize()) / 2 + j * tileSize,
								(getHeight() - coords.length * nextShape.getSize()) / 2 + i * tileSize, tileSize,
								tileSize);
					}
				}
			}
		}
	}
}
