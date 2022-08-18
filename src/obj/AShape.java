package obj;

import java.awt.Color;
import java.awt.Graphics;

import factory.ColorFactory;
import factory.Difficulty;
import factory.DifficultyFactory;
import factory.IColorFactory;
import model.Game;
import model.IGame;

public abstract class AShape {
	protected int colorFlag;
	protected int x;
	protected int y;
	protected int[][] coords;
	protected int size;
	protected int normalSpeed;
	protected int downSpeed;
	protected int currentSpeed;
	protected int detalX = 0;
	protected boolean collision = false;
	protected boolean moveX;
	protected Game game;
	protected long time, lastTime;
	protected IColorFactory colorFactory;
	protected DifficultyFactory difficultyFactory;

	public AShape(Game game) {
		this.game = game;
		difficultyFactory = new Difficulty();
		colorFactory = new ColorFactory();
		normalSpeed = difficultyFactory.creatDifficulty(game.getScore());
		downSpeed = normalSpeed / 10;
		size = IGame.TILE_SIZE;
		currentSpeed = normalSpeed;
		x = IGame.WIDTH / 2 - 1;
		y = 0;
		time = 0;
		lastTime = 0;
	}

	// main
	public void update() {
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		blocksCollisionTogether();
		moveX();
		moveY();
 
		this.detalX = 0;
		moveX = true;
	}

	public void moveX() {
		// check move x
		if (!(x + detalX + coords[0].length > IGame.WIDTH) && !(x + detalX < 0)) {
			for (int row = 0; row < coords.length; row++) {
				for (int col = 0; col < coords[row].length; col++) {
					if (coords[row][col] != 0) {
						if (this.game.getBoard()[y + row][x + detalX + col] != 0)
							this.moveX = false;
					}
				}
			}
			// move x
			if (moveX)
				x += this.detalX;
		}
	}

	public void moveY() {
		// check move y
		if (!(y + 1 + coords.length > IGame.HEIGHT)) {
			for (int row = 0; row < coords.length; row++) {
				for (int col = 0; col < coords[row].length; col++) {
					if (coords[row][col] != 0) {
						if (this.game.getBoard()[y + row + 1][col + x] != 0)
							collision = true;
					}
				}
			}
			// move y
			if (time > currentSpeed) {
				y++;
				time = 0;
			}
		} else
			collision = true;
	}

	public void blocksCollisionTogether() {
		if (collision) {
			for (int row = 0; row < coords.length; row++) {
				for (int col = 0; col < coords[row].length; col++) {
					if (coords[row][col] != 0) {
						this.game.getBoard()[y + row][x + col] = colorFlag;
					}
				}
			}
			game.setCurrentShape();
			game.checkLine();
			game.addScore();
		}
	}

	public void rotate() {
		// insert code
		if (this.collision) {
			return;
		} else {
			int[][] temp = null;
			temp = this.transpose(coords);
			temp = this.reverse(temp);
			if (x + temp[0].length > IGame.WIDTH || y + temp.length > IGame.HEIGHT) {
				return;
			}
			for (int row = 0; row < temp.length; row++) {
				for (int col = 0; col < temp.length; col++) {
					if (this.game.getBoard()[y + col][x + col] != 0) {
						return;
					}
				}
			}
			this.coords = temp;
		}
	}

	public void left() {
		// insert code
		this.detalX = -1;
	}

	public void right() {
		// insert code
		this.detalX = 1;
	}

	// time++
	public void down() {
		// insert code
		currentSpeed = downSpeed;
	}

	public void setNormalSpeed() {
		this.currentSpeed = normalSpeed;
	}

	// reverse matrix
	public int[][] reverse(int[][] input) {
		int mid = input.length / 2;
		for (int i = 0; i < mid; i++) {
			int[] temp = input[i];
			input[i] = input[input.length - i - 1];
			input[input.length - i - 1] = temp;
		}
		return input;
	}

	// transpose matrix
	public int[][] transpose(int[][] input) {
		int[][] output = new int[input[0].length][input.length];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length; j++) {
				output[j][i] = input[i][j];
			}
		}
		return output;

	}

	public int[][] getCoords() {
		return coords;
	}

	public int getDownSpeed() {
		return downSpeed;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public int getNormalSpeed() {
		return normalSpeed;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}

	public int getColorFlag() {
		return colorFlag;
	}

	public int getScore() {
		int score = 0;
		for (int i = 0; i < coords.length; i++) {
			for (int j = 0; j < coords[i].length; j++) {
				if (coords[i][j] > 0)
					score += 10;
			}
		}
		return score;
	}
}
