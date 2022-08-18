package obj;

import model.Game;

public class ShapeS extends AShape{
	public ShapeS(Game game) {
		super(game);
		this.coords = new int[][] { { 0, 1, 1 }, { 1, 1, 0 } };
		this.colorFlag = 5;
	}
}
