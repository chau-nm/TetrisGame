package factory;

import java.util.Random;

import model.Game;
import obj.AShape;
import obj.ShapeI;
import obj.ShapeJ;
import obj.ShapeL;
import obj.ShapeO;
import obj.ShapeS;
import obj.ShapeT;
import obj.ShapeZ;

public class ShapeRandomFactory implements IShapeFactory{

	@Override
	public AShape creatShape(Game game) {
		char result = this.creatShapeRandom();
		switch (result) {
		case 'J':
			return new ShapeJ(game);
		case 'L':
			return new ShapeL(game);
		case 'O':
			return new ShapeO(game);
		case 'S':
			return new ShapeS(game);
		case 'T':
			return new ShapeT(game);
		case 'Z':
			return new ShapeZ(game);
		case 'I':
			return new ShapeI(game);
		default:
			return null;
		}
	}
	
	public char creatShapeRandom() {
		char[] icons = {'J', 'L', 'O', 'S', 'T', 'Z', 'I'};
		Random r = new Random();
		int index = r.nextInt(icons.length);
		return icons[index];
	}
}
