package factory;

import model.Drawing;

public class DrawingFactory {
	
	public static Drawing createDrawing() {	
		return new Drawing();
	}
}

