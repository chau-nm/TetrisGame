package translation;

import java.util.Observable;

public class Language extends Observable {

	// singleton pattern

	private Language() {
	}

	private static Language instance;

	public static Language getInstance() {
		if (instance == null)
			instance = new Language();
		return instance;
	}

	private ITranslation lan = new TranslateEnglish(); // init default language

	public void setLanguage(ITranslation lan) {
		this.lan = lan;
		changeLanguage();
	}

	public void changeLanguage() {
		try {
			super.setChanged();
			super.notifyObservers();
		} catch (NullPointerException e) { // đôi lúc button bị null do chưa load kịp
			changeLanguage();
		}
	}

	public String getYourName() {
		return lan.performYourName();
	}

	public String getStartName() {
		return lan.performStartName();
	}

	public String getSettingName() {
		return lan.performSettingName();
	}

	public String getHighScoreName() {
		return lan.performHighScoreName();
	}

	public String getTutorialName() {
		return lan.performTutorialName();
	}

	public String getLanguageName() {
		return lan.performLanguageName();
	}

	public String getExitName() {
		return lan.performExitName();
	}

	public String getCancleName() {
		return lan.performCancleName();
	}

	public String getRankName() {
		return lan.performRankName();
	}

	public String getPlayerName() {
		return lan.performPlayerName();
	}

	public String getScoreName() {
		return lan.performScoreName();
	}

	public String getTutorialImageName() {
		return lan.performTutorialImageName();
	}

	public String getRotateName() {
		return lan.performRotateName();
	}

	public String getRightName() {
		return lan.performRightName();
	}

	public String getSpeedName() {
		return lan.performSpeedName();
	}

	public String getLeftName() {
		return lan.performLeftName();
	}

	public String getGameOverName() {
		return lan.performGameOver();
	}

	public String getReadyName() {
		return lan.performReadyName();
	}

	public String getStartIconName() {
		return lan.performStartIconName();
	}

	public String getNextName() {
		return lan.performNextName();
	}

	public String getNewGameName() {
		return lan.performNewGameName();
	}

	public String getPauseName() {
		return lan.performPauseName();
	}

	public String getHomeName() {
		return lan.performHomeName();
	}

	public String getQuitName() {
		return lan.performQuitName();
	}

	public String getRestartName() {
		return lan.performRestartName();
	}

	public String getResumeName() {
		return lan.performResumeName();
	}

	public String getTitleQuestion() {
		return lan.performTitleQuestion();
	}

	public String getQuestion() {
		return lan.performQuestion();
	}

	public String getYesName() {
		return lan.performYesName();
	}

	public String getNoName() {
		return lan.performNoName();
	}

	public String getEnglishNameBtn() {
		return lan.performEnglishNameBtn();
	}

	public String getVietNameseNameBtn() {
		return  lan.performVietNameseNameBtn();
	}

	public String getChineseNameBtn() {
		return lan.performChineseNameBtn();
	}

}