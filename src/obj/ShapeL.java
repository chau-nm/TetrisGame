package obj;

import model.Game;

public class ShapeL extends AShape{
	public ShapeL(Game game) {
		super(game);
		this.coords = new int[][] { { 1, 0 }, { 1, 0 }, { 1, 1 } };
		this.colorFlag = 3;
	}
}
