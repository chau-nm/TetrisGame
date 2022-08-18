package model;

import java.awt.Graphics2D;

import obj.AShape;

public interface IGame {
	public static final int WIDTH = 14;
	public static final int HEIGHT = 20;
	public static final int TILE_SIZE = 36;
	
	public void updateInGame();//update game
	public void left();//di chuyen shape sang trai
	public void right();//di chuyen shape sang phai
	public void down();//dua shape xuong 1 bac
	public void setNormalSpeed();//dat lai toc do ban dau
	public void rotate();//xoay
	public void pause();//tam dung game
	public void resume();//tiep tuc
	public void start();//bat dau
	public void setPlayer(String name);//set up ten nguoi choi
	public void setStateSoundtrack();//bat tat nhac nen
	public void setStateEffectMusic();//bat tat effect
	public void changeVolumeSoundtrack(int value);//thay doi am luong nhac nen
	public void changeVolumeEffect(int value);//thay doi am luong effect
	public void setInGame(boolean op);//thay doi trang thai ingame
	public void tranlateEnglish();//
	public void tranlateVietnamese();
	public void tranlateChinese();

}
