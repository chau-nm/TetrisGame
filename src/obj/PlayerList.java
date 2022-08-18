package obj;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;

public class PlayerList {
	private ArrayList<Player> players;

	public PlayerList() {
		players = new ArrayList<Player>();
	}

	public String toString() {
		StringBuilder writer = new StringBuilder();
		for (int i = 0; i < players.size(); i++) {
			writer.append((i + 1) + " " + players.get(i) + "\n");
		}
		return writer.toString();
	}

	public void add(Player player) {
		players.add(player);
	}

	public void remove(Player player) {
		players.remove(player);
	}

	public void sort() {

		players.sort(new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				return o2.compareTo(o1);
			}
		});
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
}
