package factory;

public class Difficulty implements DifficultyFactory {

	@Override
	public int creatDifficulty(int score) {
		if (score > 20000)
			return 50;
		else if (score > 15000)
			return 100;
		else if (score > 12000)
			return 300;
		else if (score > 8000)
			return 350;
		else if (score > 5000)
			return 400;
		else if (score > 3000)
			return 500;
		else if (score > 2000)
			return 600;
		else if (score > 1000)
			return 700;
		else
			return 800;
	}
}
