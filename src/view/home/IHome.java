package view.home;

import view.high_score.IView;

public interface IHome extends IView{
	public void start();
	public void showSetting();
	public void showHighScore();
	public void showTutorial();
	public void showLanguage();
	public void exit();
	public void dispose();
	public boolean isDisplay();
	public String getNamePlayer();
}
