package view.home;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import translation.Language;
import controller.IController;

public class HomeScreen extends JFrame implements MouseListener, IHome, Observer {

	private IController controller;
	private JLabel title, nameLb, backgoundLb;
	private JTextField nameTf;
	private JButton start, highScore, setting, tutorial, language, exit;
	private JPanel namePn, btnPn, contentPn;
	private Graphics2D graphic;

	public HomeScreen(IController controller, Observable observableLanguage) {
		this.controller = controller;
		observableLanguage.addObserver(this);
		
		// swing
		add(backgoundLb = new JLabel(new ImageIcon("resource/img/background_tetris.png")));
		backgoundLb.setLayout(null);

		pack();

		setFrame();
		displayNameLb();
		displayNameTf();
		displayButtons();
	}

	public void setFrame() {
		setTitle("TETRIS");
		setIconImage(new ImageIcon("resource/img/iconGame.png").getImage());
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void displayNameLb() {
		backgoundLb.add(nameLb = new JLabel());
		nameLb.setBounds(171, 275, 125, 30);
		nameLb.setForeground(Color.WHITE);
		nameLb.setHorizontalAlignment(JLabel.CENTER);
		nameLb.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
	}

	private void displayNameTf() {
		backgoundLb.add(nameTf = new JTextField());
		nameTf.setBounds(171, 310, 125, 28);
		nameTf.setHorizontalAlignment(JTextField.CENTER);
		nameTf.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 20));
		nameTf.setBorder(null);
		nameTf.setBackground(new Color(245, 245, 245));
		nameTf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
	}

	private void displayButtons() {
		backgoundLb.add(start = setUpBtn());
		start.setBounds(177, 385, 112, 38);
		backgoundLb.add(setting = setUpBtn());
		setting.setBounds(177, 438, 112, 38);
		backgoundLb.add(highScore = setUpBtn());
		highScore.setBounds(177, 491, 112, 38);
		backgoundLb.add(tutorial = setUpBtn());
		tutorial.setBounds(177, 544, 112, 38);
		backgoundLb.add(language = setUpBtn());
		language.setBounds(177, 597, 112, 38);
		backgoundLb.add(exit = setUpBtn());
		exit.setBounds(177, 650, 112, 38);
	}

	private JButton setUpBtn() {
		JButton button = new JButton();
		button.setFont(new Font(Font.DIALOG, Font.BOLD, 17));
		button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		button.setHorizontalAlignment(JLabel.CENTER);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBackground(new Color(240, 205, 139));
		button.addMouseListener(this);
		return button;
	}

	@Override
	public void start() {
		if (!nameTf.getText().equals("")) {
			controller.startGameAtHome();
		} else {
			nameTf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			nameTf.setBackground(new Color(250, 207, 207));
		}
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
	public void showLanguage() {
		controller.showLanguage();

	}

	@Override
	public void exit() {
		controller.exit();
	}

	@Override
	public String getNamePlayer() {
		return nameTf.getText();
	}

	@Override
	public void showSetting() {
		controller.showSetting();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (start == e.getSource())
			start();
		if (highScore == e.getSource())
			showHighScore();
		if (exit == e.getSource())
			exit();
		if (tutorial == e.getSource())
			showTutorial();
		if (setting == e.getSource())
			showSetting();
		if (language == e.getSource())
			showLanguage();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (start == e.getSource())
			start.setBackground(new Color(240, 177, 60));
		if (setting == e.getSource())
			setting.setBackground(new Color(240, 177, 60));
		if (highScore == e.getSource())
			highScore.setBackground(new Color(240, 177, 60));
		if (tutorial == e.getSource())
			tutorial.setBackground(new Color(240, 177, 60));
		if (language == e.getSource())
			language.setBackground(new Color(240, 177, 60));
		if (exit == e.getSource())
			exit.setBackground(new Color(240, 177, 60));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (start == e.getSource())
			start.setBackground(new Color(240, 205, 139));
		if (setting == e.getSource())
			setting.setBackground(new Color(240, 205, 139));
		if (highScore == e.getSource())
			highScore.setBackground(new Color(240, 205, 139));
		if (tutorial == e.getSource())
			tutorial.setBackground(new Color(240, 205, 139));
		if (language == e.getSource())
			language.setBackground(new Color(240, 205, 139));
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
			nameLb.setText(lan.getYourName());
			start.setText(lan.getStartName());
			highScore.setText(lan.getHighScoreName());
			setting.setText(lan.getSettingName());
			tutorial.setText(lan.getTutorialName());
			language.setText(lan.getLanguageName());
			exit.setText(lan.getExitName());
		}
	}

	@Override
	public boolean isDisplay() {
		return super.isDisplayable();
	}
}
