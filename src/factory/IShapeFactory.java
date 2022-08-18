package factory;

import model.Game;
import obj.AShape;

public interface IShapeFactory {
	public AShape creatShape(Game game);
}
