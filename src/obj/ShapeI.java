package obj;

import model.Game;

public class ShapeI extends AShape {

	public ShapeI(Game game) {
		super(game);
		this.coords = new int[][] { { 1, 1, 1, 1 } };
		this.colorFlag = 1;
	}

}
