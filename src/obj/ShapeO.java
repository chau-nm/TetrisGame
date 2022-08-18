package obj;

import model.Game;

public class ShapeO extends AShape {
	public ShapeO(Game game) {
		super(game);
		this.coords = new int[][] { { 1, 1 }, { 1, 1 } };
		this.colorFlag = 4;
	}
}
