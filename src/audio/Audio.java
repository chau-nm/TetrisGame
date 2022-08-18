package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
	private Clip clip;
	private boolean mute;
	private int valueSound;

	public Audio(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audio = AudioSystem.getAudioInputStream(new File(fileName));
		clip = AudioSystem.getClip();
		clip.open(audio);

		this.mute = false;
		this.valueSound = 80;
	}

	public void playSound(boolean isPause, boolean isLoop) {
		if (!mute && !isPause) {
			start();
			if (isLoop)
				loop();
		} else
			stop();
	}

	public void start() {
		if (!clip.isRunning()) {
			clip.setMicrosecondPosition(0);
			clip.start();
		}
	}

	public void stop() {
		if (clip.isRunning()) {
			clip.stop();
		}
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public boolean getMute() {
		return this.mute;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}

	public int getValueSound() {
		return this.valueSound;
	}

	public void setValueSound(int valueSound) {
		this.valueSound = valueSound;
		if (valueSound == 0)
			mute = true;
		else
			mute = false;
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float) ((valueSound / 100.0f * 80) - 80));
	}

}
