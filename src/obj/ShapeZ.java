package obj;

import model.Game;

public class ShapeZ extends AShape {
	public ShapeZ(Game game) {
		super(game);
		this.coords = new int[][] { { 1, 1, 0 }, { 0, 1, 1 } };
		this.colorFlag = 7;
	}
}
