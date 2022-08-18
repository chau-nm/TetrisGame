package translation;

public interface ITranslation{
	// home screen
	public String performYourName();
	public String performStartName();
	public String performSettingName();
	public String performHighScoreName();
	public String performTutorialName();
	public String performLanguageName();
	public String performExitName();
	public String performCancleName();

	// high score screen
	public String performRankName();
	public String performPlayerName();
	public String performScoreName();

	// tutorial screen
	public String performTutorialImageName();
	public String performRotateName();
	public String performRightName();
	public String performSpeedName();
	public String performLeftName();

	// in game screen
	public String performGameOver();
	public String performReadyName();
	public String performStartIconName();
	public String performNextName();
	public String performNewGameName();
	public String performPauseName();
	public String performHomeName();
	public String performQuitName();
	public String performRestartName();
	public String performResumeName();

	// question screen
	public String performTitleQuestion();
	public String performQuestion();
	public String performYesName();
	public String performNoName();
	
	// language screen
	public String performEnglishNameBtn();
	public String performVietNameseNameBtn();
	public String performChineseNameBtn();


}
