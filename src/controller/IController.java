package controller;

public interface IController {
	public void left();//su kien di chuyen sang trai
	public void right();//di chuyen sang phai
	public void down();//tang toc do di chuyen
	public void setNormalSpeed();//tro lai toc do ban dau
	public void rotate();//xoay hinh
	public void pause();//tam dung tro choi
	public void resume();//tiep tuc tro choi
	public boolean startNewGame();//bat dau game moi
	public void showHighScore();//hien thi bang xep hang
	public void showTutorial();//hien thi huong dan
	public void showSetting();//hien thi cai dat am thanh
	public void showLanguage();//hien thi cai dat am thanh
	public boolean backToHome();//dong cua so in game, mo cua so home
	public void exit();//thoat game
	public void startGameAtHome();//bat dau game tai cua so home
	public void setStateSoundtrack();// bat tat nhac nen
	public void setStateEffectMusic();// bat tat effect
	public void changeVolumeSoundtrack(int value);// thay doi am luong nhac nen
	public void changeVolumeEffect(int value);// thay doi am luong effect
	public void tranlateEnglish();
	public void tranlateVietnamese();
	public void tranlateChinese();
}
