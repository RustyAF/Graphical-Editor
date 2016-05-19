package view;

import java.awt.Color;
import java.awt.Point;

import util.BoundBox;
import model.AbstractShape;
import model.Rectangle;

public class RectCreationTool extends CreationTool {

	@Override
	public AbstractShape createFigure() {
		Point ptPressed = getPtPressed();
		Point ptReleased = getPtRelased();
		return new Rectangle(new BoundBox(ptPressed, ptReleased), Color.BLACK);
	}
}
