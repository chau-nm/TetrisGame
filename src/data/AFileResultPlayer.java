package data;

import java.io.File;
import java.io.IOException;

import obj.PlayerList;

public abstract class AFileResultPlayer {

	public abstract PlayerList getPlayerList();

	// template method pattern
	public final void executeAbilityThisFile(String scoreFileLink, Object object) {
		try {
			File scoreFile = openThisFile(scoreFileLink);

			useThisFile(scoreFile, object);

			closeThisFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File openThisFile(String scoreFileLink) {
		return new File(scoreFileLink);
	}

	public abstract void useThisFile(File scoreFileName, Object object) throws IOException;

	public abstract void closeThisFile() throws IOException;

}
