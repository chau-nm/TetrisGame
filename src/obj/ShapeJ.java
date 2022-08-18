package obj;

import model.Game;

public class ShapeJ extends AShape{
	public ShapeJ(Game game) {
		super(game);
		this.coords = new int[][] { { 0, 1 }, { 0, 1 }, { 1, 1 } };
		this.colorFlag = 2;
	}
}
