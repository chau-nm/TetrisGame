package view.ingame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import translation.Language;
import model.Game;
import model.IGame;

public class ButtonPanel extends JPanel implements MouseListener, Observer {

	private IInGame view;
	private JButton newGame, pause, home, exit;
	private JLabel title;

	public ButtonPanel(IInGame view, Observable observableLanguage) {
		observableLanguage.addObserver(this);
		this.view = view;

		setFrame();
		displayTitle();
		displayButton();
	}

	private void setFrame() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 2));
		setPreferredSize(new Dimension(240, 500));
		setBackground(new Color(252, 252, 252));
		setBorder(BorderFactory.createBevelBorder(1));
		setFocusable(true);
		requestFocus();
	}

	private void displayTitle() {
		add(title = new JLabel(new ImageIcon("resource/img/200_150.png")));
		title.setPreferredSize(new Dimension(200, 150));
		title.setHorizontalAlignment(JLabel.CENTER);
	}

	private void displayButton() {
		add(newGame = setUpBtn("NEW GAME", null, 125, 40));
		add(pause = setUpBtn("PAUSE", null, 125, 40));
		add(home = setUpBtn("HOME", null, 125, 40));
		add(exit = setUpBtn("EXIT", null, 125, 40));
	}

	private JButton setUpBtn(String value, ImageIcon icon, int w, int h) {
		JButton button = new JButton(value, icon);
		button.setPreferredSize(new Dimension(w, h));
		button.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		button.setBackground(new Color(240, 205, 139));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.addMouseListener(this);
		return button;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (newGame == e.getSource())
			view.startNewGame();
		if (pause == e.getSource())
			view.pause();
		if (exit == e.getSource())
			view.exit();
		if (home == e.getSource())
			view.backToHome();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (newGame == e.getSource())
			newGame.setBackground(new Color(240, 177, 60));
		if (pause == e.getSource())
			pause.setBackground(new Color(240, 177, 60));
		if (home == e.getSource())
			home.setBackground(new Color(240, 177, 60));
		if (exit == e.getSource())
			exit.setBackground(new Color(240, 177, 60));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (newGame == e.getSource())
			newGame.setBackground(new Color(240, 205, 139));
		if (pause == e.getSource())
			pause.setBackground(new Color(240, 205, 139));
		if (home == e.getSource())
			home.setBackground(new Color(240, 205, 139));
		if (exit == e.getSource())
			exit.setBackground(new Color(240, 205, 139));
	}

	@Override
	public void update(Observable o, Object arg) {
		updateLanguage(o);
	}

	private void updateLanguage(Observable o) {
		if (o instanceof Language) {
			Language lan = (Language) o;
			newGame.setText(lan.getNewGameName());
			pause.setText(lan.getPauseName());
			home.setText(lan.getHomeName());
			exit.setText(lan.getExitName());		}
	}
	

}
