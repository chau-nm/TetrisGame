package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import obj.Player;
import obj.PlayerList;

public class SaveResultPlayers extends AFileResultPlayer {

	private BufferedWriter bufferedWriter = null;
	private FileWriter fileWriter = null;

	@Override
	public void useThisFile(File scoreFile, Object object) throws IOException {
		// save player to file
		if (!scoreFile.exists()) {
			scoreFile.createNewFile();
		}
		Player player = (Player) object;
		fileWriter = new FileWriter(scoreFile.getAbsoluteFile(), true);
		bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(player.toString() + "\n");
	}

	@Override
	public void closeThisFile() throws IOException {
			if (bufferedWriter != null)
				bufferedWriter.close();
			if (fileWriter != null)
				fileWriter.close();
	}

	@Override
	public PlayerList getPlayerList() {
		return null;
	}
}
