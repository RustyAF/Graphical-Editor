package view;

import java.awt.Color;
import java.awt.Point;

import util.BoundBox;
import model.AbstractShape;
import model.Ellipse;

public class ElliCreationTool extends CreationTool {

	@Override
	public AbstractShape createFigure() {
		
		Point ptPressed = getPtPressed();
		Point ptReleased = getPtRelased();
		return new Ellipse(
				new BoundBox(ptPressed, ptReleased), Color.BLACK);
	}
}
