package controller;

import java.util.Observable;

import javax.swing.JOptionPane;

import model.IGame;
import translation.Language;
import view.high_score.HighScore;
import view.high_score.IHighScore;
import view.home.HomeScreen;
import view.home.IHome;
import view.ingame.IInGame;
import view.ingame.InGameScreen;
import view.language.ILanguageScreen;
import view.language.LanguageScreen;
import view.pause.IPause;
import view.pause.PauseScreen;
import view.setting.ISetting;
import view.setting.Setting;
import view.tutorial.ITutorial;
import view.tutorial.Tutorial;

public class Controller implements IController {
	private IGame model;
	private IInGame inGame;
	private IPause pause;
	private IHome home;
	private IHighScore highScore;
	private ISetting setting;
	private ILanguageScreen translation;
	private ITutorial tutorial;
	private Language lan ; // set up language

	public Controller(IGame model) {
		this.model = model;
		lan = Language.getInstance();
		home = new HomeScreen(this, (Observable) lan);
		pause = new PauseScreen(this, (Observable) lan);
		highScore = new HighScore(this, (Observable) model, (Observable) lan);
		setting = new Setting(this, (Observable) model, (Observable) lan);
		tutorial = new Tutorial((Observable) lan);
		translation = new LanguageScreen(this, (Observable) lan);
		lan.changeLanguage(); // lấy default language cho observer
	}
	
	@Override
	public void left() {
		model.left();
	}

	@Override
	public void right() {
		model.right();
	}

	@Override
	public void down() {
		model.down();
	}

	@Override
	public void setNormalSpeed() {
		model.setNormalSpeed();
	}

	@Override
	public void rotate() {
		model.rotate();
	}

	@Override
	public void pause() {
		model.pause();
		pause.show();
	}

	@Override
	public void resume() {
		model.resume();
		inGame.requestFocus();
		pause.dispose();
	}

	@Override
	public boolean startNewGame() {
		model.pause();
		Object[] options = { lan.getYesName(), lan.getNoName() };
		int op = JOptionPane.showOptionDialog(null, lan.getTitleQuestion(), lan.getRestartName(),
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		if (op == JOptionPane.YES_OPTION) {
			model.start();
			inGame.requestFocus();
			return true;
		} else {
			if (!pause.isShow()) {
				model.resume();
				inGame.requestFocus();
			}
			return false;
		}
	}

	@Override
	public void showHighScore() {
		model.pause();
		highScore.show();
	}

	@Override
	public void showTutorial() {
		model.pause();
		tutorial.show();
	}

	@Override
	public void showLanguage() {
		model.pause();
		translation.show();

	}

	@Override
	public boolean backToHome() {
		model.pause();
		Object[] options = { lan.getYesName(), lan.getNoName() };
		int op = JOptionPane.showOptionDialog(null, lan.getTitleQuestion(), lan.getQuestion(),
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		if (op == JOptionPane.YES_OPTION) {
			model.setInGame(false);
			inGame.disposeInGame();
			home = new HomeScreen(this, (Observable) lan);
			lan.changeLanguage(); // user chỉ chọn language trong ingame nên lan gọi lại update cho home
			pause.dispose();
			return true;
		} else {
			if (!pause.isShow()) {
				model.resume();
				inGame.requestFocus();
			}
			return false;
		}

	}

	@Override
	public void exit() {
		model.pause();
		Object[] options = { lan.getYesName(), lan.getNoName() };
		int op = JOptionPane.showOptionDialog(null, lan.getQuestion(), lan.getExitName(),
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		if (op == JOptionPane.YES_OPTION)
			System.exit(0);
		
		else if (!pause.isShow() && !home.isDisplay()) {
			model.resume();
			if (inGame != null)
				inGame.requestFocus();
		}		
	}

	@Override
	public void startGameAtHome() {
		home.dispose();
		model.setPlayer(home.getNamePlayer());
		model.start();

		inGame = new InGameScreen(this, (Observable) model, (Observable) lan);
		lan.changeLanguage();// user chỉ chọn language trong home nên lan gọi lại update cho ingame
	}

	@Override
	public void setStateSoundtrack() {
		model.setStateSoundtrack();
	}

	@Override
	public void setStateEffectMusic() {
		model.setStateEffectMusic();
	}

	@Override
	public void changeVolumeSoundtrack(int value) {
		model.changeVolumeSoundtrack(value);
	}

	@Override
	public void changeVolumeEffect(int value) {
		model.changeVolumeEffect(value);
	}

	@Override
	public void showSetting() {
		setting.show();
	}

	@Override
	public void tranlateEnglish() {
		model.tranlateEnglish();
		translation.dispose();
	}

	@Override
	public void tranlateVietnamese() {
		model.tranlateVietnamese();
		translation.dispose();
	}

	@Override
	public void tranlateChinese() {
		model.tranlateChinese();
		translation.dispose();
	}
}
