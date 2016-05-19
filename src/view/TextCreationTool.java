package view;

import java.awt.Color;
import java.awt.Point;

import util.BoundBox;
import model.AbstractShape;
import model.Text;

public class TextCreationTool extends CreationTool {

	@Override
	public AbstractShape createFigure() {
		 Point ptPressed = getPtPressed();
		 Point ptReleased = getPtRelased();		 
		 Text myString = new Text(new BoundBox(ptPressed, ptReleased), Color.BLACK);
		 myString.setText("Start writing here");
		 return myString;
	}
}
