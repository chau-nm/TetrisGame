package factory;

import java.awt.Color;

public class ColorFactory implements IColorFactory {

	@Override
	public Color createColor(int flagColor) {
		switch (flagColor) {
		case 1:
			return new Color(71, 246, 255);
		case 2:
			return new Color(71, 74, 255);
		case 3:
			return new Color(255, 154, 71);
		case 4:
			return new Color(255, 230, 71);
		case 5:
			return new Color(71, 255, 114);
		case 6:
			return new Color(191, 71, 255);
		case 7:
			return new Color(255, 71, 71);
		default:
			return null;
		}
	}

}
