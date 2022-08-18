package view.setting;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import translation.Language;
import controller.IController;
import model.Game;

public class Setting extends JDialog implements ISetting, Observer {

	private IController controller;
	private JLabel title, soundtrackLb, effectLb, soundtrackValueLb, effectValueLb;
	private JSlider soundtrackSlider, effectSlider;
	private JPanel titlePn, soundtrackPn, effectSliderPn, btnPn;
	private JButton cancleBt;

	public Setting(IController controller, Observable observableModel, Observable observableLanguage) {
		this.controller = controller;
		observableModel.addObserver(this);
		observableLanguage.addObserver(this);

		setFrame();
		displayTitle();
		displaySoundtrack();
		displayEffectMusic();
		displayCancleButton();
	}

	public void setFrame() {
		this.getContentPane().setBackground(new Color(189, 215, 255));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		setSize(350, 350);
		setResizable(false);
		setIconImage(new ImageIcon("resource/img/104.png").getImage());
		setLocationRelativeTo(null);
	}

	private void displayTitle() {
		add(title = new JLabel());
		title.setPreferredSize(new Dimension(200, 100));
		title.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
		title.setHorizontalAlignment(JLabel.CENTER);
	}

	private void displaySoundtrack() {
		add(soundtrackPn = new JPanel());
		makeSoundtrackLb();
		makeSoundtrackSlider();
		adjustSoundtrackLb();
		adjustSoundtrackSlider();
	}

	private void makeSoundtrackLb() {
		soundtrackPn.setBackground(new Color(189, 215, 255));
		soundtrackPn.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));
		soundtrackPn.add(soundtrackLb = new JLabel());
		soundtrackLb.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	private void makeSoundtrackSlider() {
		soundtrackPn.add(soundtrackSlider = new JSlider(0, 100));
		soundtrackSlider.setBackground(Color.WHITE);
		soundtrackSlider.setCursor(new Cursor(Cursor.HAND_CURSOR));
		soundtrackPn.add(soundtrackValueLb = new JLabel(soundtrackSlider.getValue() + ""));
	}

	private void adjustSoundtrackLb() {
		soundtrackLb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				setStateSoundTrack();
			}
		});
	}

	private void adjustSoundtrackSlider() {
		soundtrackSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				soundtrackValueLb.setText(soundtrackSlider.getValue() + "");
				changeVolumeSoundTrack(soundtrackSlider.getValue());
			}
		});
	}

	private void displayEffectMusic() {
		add(effectSliderPn = new JPanel());
		makeEffectMusicLb();
		makeEffectMusicSlider();
		adjustEffectMusicLb();
		adjustEffectMusicSlider();
	}

	private void makeEffectMusicLb() {
		effectSliderPn.setBackground(new Color(189, 215, 255));
		effectSliderPn.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));
		effectSliderPn.add(effectLb = new JLabel());
		effectLb.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	private void makeEffectMusicSlider() {
		effectSliderPn.add(effectSlider = new JSlider(0, 100));
		effectSlider.setBackground(Color.WHITE);
		effectSlider.setCursor(new Cursor(Cursor.HAND_CURSOR));
		effectSliderPn.add(effectValueLb = new JLabel(effectSlider.getValue() + ""));
	}

	private void adjustEffectMusicLb() {
		effectLb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				setStateEffectMusic();
			}
		});
	}

	private void adjustEffectMusicSlider() {
		effectSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				effectValueLb.setText(effectSlider.getValue() + "");
				changeVolumeEffectMusic(effectSlider.getValue());
			}
		});
	}

	private void displayCancleButton() {
		add(btnPn = new JPanel());
		btnPn.setBackground(new Color(189, 215, 255));
		btnPn.setPreferredSize(new Dimension(200, 50));
		btnPn.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnPn.add(cancleBt = new JButton());
		cancleBt.setPreferredSize(new Dimension(100, 30));
		cancleBt.setBackground(new Color(240, 205, 139));
		cancleBt.setCursor(new Cursor(Cursor.HAND_CURSOR));

		doActionCancleButton();
	}

	private void doActionCancleButton() {
		cancleBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				cancleBt.setBackground(new Color(240, 177, 60));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				cancleBt.setBackground(new Color(240, 205, 139));
			}
		});
	}

	@Override
	public void changeVolumeSoundTrack(int value) {
		controller.changeVolumeSoundtrack(value);
	}

	@Override
	public void changeVolumeEffectMusic(int value) {
		controller.changeVolumeEffect(value);
	}

	@Override
	public void setStateSoundTrack() {
		controller.setStateSoundtrack();
	}

	@Override
	public void setStateEffectMusic() {
		controller.setStateEffectMusic();
	}

	@Override
	public void update(Observable observable, Object arg) {
		updateMusic(observable);
		updateLanguage(observable);
	}

	private void updateMusic(Observable o) {
		if (o instanceof Game) {
			Game game = (Game) o;
			updateIconSoundtrack(game);
			updateIconEffectMs(game);
			updateSliderBar(game);
		}
	}

	
	private void updateIconSoundtrack(Game game) {
		if (!game.isRunningSoundtrack())
			soundtrackLb.setIcon(new ImageIcon("resource/img/102.png"));
		else
			soundtrackLb.setIcon(new ImageIcon("resource/img/103.png"));
	}

	private void updateIconEffectMs(Game game) {
		if (!game.isRunningEffect())
			effectLb.setIcon(new ImageIcon("resource/img/101.png"));
		else
			effectLb.setIcon(new ImageIcon("resource/img/100.png"));
	}

	private void updateSliderBar(Game game) {
		if (soundtrackSlider != null)
			soundtrackSlider.setValue(game.getValueSoundtrack());
		if (effectSlider != null)
			effectSlider.setValue(game.getValueEffect());
	}

	private void updateLanguage(Observable o) {
		if (o instanceof Language) {
			Language lan = (Language) o;
			this.setTitle(lan.getSettingName());
			title.setText(lan.getSettingName());
			cancleBt.setText(lan.getCancleName());
		}
	}

}
