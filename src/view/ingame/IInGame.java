package view.ingame;

import view.high_score.IView;

public interface IInGame extends IView{
	public void pause();
	public void resume();
	public void startNewGame();
	public void requestFocus();
	public void left();
	public void right();
	public void down();
	public void backToHome();
	public void disposeInGame();
	public void setNormalSpeed();
	public void rotate();
	public void exit();
}
