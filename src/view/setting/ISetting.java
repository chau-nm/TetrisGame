package view.setting;

import java.util.Observable;

import translation.Language;
import view.high_score.IView;

public interface ISetting extends IView{
	public void changeVolumeSoundTrack(int value);
	public void changeVolumeEffectMusic(int value);
	public void setStateSoundTrack();
	public void setStateEffectMusic();
	public void show();
}
