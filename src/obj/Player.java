package obj;

import java.util.Observable;
import java.util.Observer;

import model.Game;

public class Player implements Comparable<Player>, Observer{
	private String name;
	private int score;
	private boolean isPlaying;

	public Player(String name, int score, Observable observable) {
		observable.addObserver(this);
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return name + "\t" + score;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	@Override
	public int compareTo(Player o) {
		return this.score - o.score;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Game && isPlaying) {
			Game game = (Game) o;
			score = game.getScore();
		}
	}
}
