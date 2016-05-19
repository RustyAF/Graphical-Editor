package view;

import java.awt.Color;
import java.awt.Point;

import util.BoundBox;
import model.AbstractShape;
import model.Line;

public class LineCreationTool extends CreationTool {

	@Override
	public AbstractShape createFigure() {
		
		Point ptPressed = getPtPressed();
		Point ptReleased = getPtRelased();
		return new Line(new BoundBox(ptPressed, ptReleased), Color.BLACK);
	}
}
