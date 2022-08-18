package view.ingame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import translation.Language;
import model.Game;
import model.IGame;
import obj.Player;

public class ScorePanel extends JPanel implements Observer {

	private IInGame view;
	private Player player;
	private JLabel nameLb, scoreLb;
	private String playerName, scoreName;

	public ScorePanel(IInGame view, Observable observableModel, Observable observableLanguage) {

		this.view = view;
		observableModel.addObserver(this);
		observableLanguage.addObserver(this);

		displayNameLb();
		displayScoreLb();
		setFrame();
	}

	private void displayNameLb() {
		add(nameLb = new JLabel("PLAYER"));
		nameLb.setHorizontalAlignment(JLabel.CENTER);
		nameLb.setFont(new Font(Font.DIALOG, Font.BOLD, 30));

	}

	private void displayScoreLb() {
		add(scoreLb = new JLabel("SCORE"));
		scoreLb.setHorizontalAlignment(JLabel.CENTER);
		scoreLb.setFont(new Font(Font.DIALOG, Font.ITALIC, 20));

	}

	private void setFrame() {
		setPreferredSize(new Dimension(240, 120));
		setBackground(new Color(252, 252, 252));
		setLayout(new GridLayout(2, 1));
		setBorder(BorderFactory.createBevelBorder(1));

	}

	public void update(Observable o, Object arg) {
		updateNameAndScore(o);
		updateLanguage(o);

	}

	public void updateNameAndScore(Observable o) {
		if (o instanceof Game) {
			Game game = (Game) o;
			player = game.getPlayer();
			if (nameLb != null)
				nameLb.setText(playerName + ": " + player.getName());
			if (scoreLb != null)
				scoreLb.setText(scoreName + ": " + player.getScore());
		}
	}

	public void updateLanguage(Observable o) {
		if (o instanceof Language) {
			Language lan = (Language) o;
			playerName = lan.getPlayerName();
			scoreName = lan.getScoreName();
		}
	}
}
