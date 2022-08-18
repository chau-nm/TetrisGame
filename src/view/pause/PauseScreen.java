	package view.pause;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import translation.Language;
import controller.IController;
import model.Game;

public class PauseScreen extends JDialog implements IPause, MouseListener, Observer {

	private IController controller;
	private JLabel title;
	private JButton resume, restart, highScore, tutorial, setting, home, language, exit;
	private boolean show;

	public PauseScreen(IController controller, Observable observableLanguage) {
		this.controller = controller;
		observableLanguage.addObserver(this);

		displayTitle();
		displayButtons();
		setFrame();
	}

	private void displayTitle() {
		add(title = new JLabel());
		title.setPreferredSize(new Dimension(180, 50));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
	}

	private void displayButtons() {
		add(resume = setUpBtn());
		add(restart = setUpBtn());
		add(setting = setUpBtn());
		add(highScore = setUpBtn());
		add(tutorial = setUpBtn());
		add(language = setUpBtn());
		add(home = setUpBtn());
		add(exit = setUpBtn());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				resume();
			}
		});
	}

	private void setFrame() {
		this.getContentPane().setBackground(new Color(189, 215, 255));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		setSize(280, 500);
		setTitle("PAUSE");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	private JButton setUpBtn() {
		JButton btn = new JButton();
		btn.setPreferredSize(new Dimension(150, 35));
		btn.setBackground(new Color(240, 205, 139));
		btn.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.addMouseListener(this);
		return btn;
	}

	@Override
	public void dispose() {
		super.dispose();
		show = false;
	}

	@Override
	public void resume() {
		controller.resume();
	}

	@Override
	public void show() {
		super.show();
		show = true;
	}

	@Override
	public void restart() {
		if (controller.startNewGame())
			dispose();
	}

	@Override
	public void showHighScore() {
		controller.showHighScore();
	}

	@Override
	public void showTutorial() {
		controller.showTutorial();
	}

	@Override
	public void translate() {
		controller.showLanguage();
	}

	@Override
	public void backToHome() {
		if (controller.backToHome())
		dispose();
	}

	@Override
	public void exit() {
		controller.exit();
	}

	@Override
	public boolean isShow() {
		return show;
	}

	@Override
	public void showSetting() {
		controller.showSetting();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (resume == e.getSource())
			resume();
		if (restart == e.getSource())
			restart();
		if (highScore == e.getSource())
			showHighScore();
		if (tutorial == e.getSource())
			showTutorial();
		if (setting == e.getSource())
			showSetting();
		if (home == e.getSource())
			backToHome();
		if (language == e.getSource())
			translate();
		if (exit == e.getSource())
			exit();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (resume == e.getSource())
			resume.setBackground(new Color(240, 177, 60));
		if (restart == e.getSource())
			restart.setBackground(new Color(240, 177, 60));
		if (highScore == e.getSource())
			highScore.setBackground(new Color(240, 177, 60));
		if (tutorial == e.getSource())
			tutorial.setBackground(new Color(240, 177, 60));
		if (setting == e.getSource())
			setting.setBackground(new Color(240, 177, 60));
		if (home == e.getSource())
			home.setBackground(new Color(240, 177, 60));
		if (exit == e.getSource())
			exit.setBackground(new Color(240, 177, 60));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (resume == e.getSource())
			resume.setBackground(new Color(240, 205, 139));
		if (restart == e.getSource())
			restart.setBackground(new Color(240, 205, 139));
		if (highScore == e.getSource())
			highScore.setBackground(new Color(240, 205, 139));
		if (tutorial == e.getSource())
			tutorial.setBackground(new Color(240, 205, 139));
		if (setting == e.getSource())
			setting.setBackground(new Color(240, 205, 139));
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
			this.setTitle(lan.getPauseName());
			title.setText(lan.getPauseName());
			resume.setText(lan.getResumeName());
			highScore.setText(lan.getHighScoreName());
			restart.setText(lan.getRestartName());
			tutorial.setText(lan.getTutorialName());
			setting.setText(lan.getSettingName());
			language.setText(lan.getLanguageName());
			home.setText(lan.getHomeName());
			exit.setText(lan.getExitName());
		}
	}

}
