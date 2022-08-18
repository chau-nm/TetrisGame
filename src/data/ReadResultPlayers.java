package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.StringTokenizer;

import obj.Player;
import obj.PlayerList;

public class ReadResultPlayers extends AFileResultPlayer {

	private static PlayerList playerList = new PlayerList();

	private static BufferedReader bufferedReader = null;
	private static FileReader fileReader = null;

	@Override
	public void useThisFile(File scoreFile, Object object) throws IOException {
		// read player from file
		if (scoreFile.exists()) {
			Observable observable = (Observable) object;
			fileReader = new FileReader(scoreFile);
			bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			while (line != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, "\t");
				String name = tokenizer.nextToken();
				int score = Integer.parseInt(tokenizer.nextToken());
				playerList.add(new Player(name, score, observable));
				line = bufferedReader.readLine();
			}
		}
	}

	@Override
	public void closeThisFile() throws IOException {
		if (bufferedReader != null)
			bufferedReader.close();
		if (fileReader != null)
			fileReader.close();
	}

	@Override
	public PlayerList getPlayerList() {
		return playerList;
	}

}
