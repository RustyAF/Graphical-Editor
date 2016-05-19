package model;

import java.awt.Color;
import java.awt.Graphics2D;

import util.BoundBox;

public class Line extends AbstractShape {
	
	public Line( BoundBox bbox, Color color ) {
		super( bbox, color );
	}

	@Override
	public void doPaint( Graphics2D g ) {
		g.setColor( getColor() );
		BoundBox bbox = getBoundBox();
		g.drawLine( bbox.x, bbox.y, bbox.x + bbox.width, bbox.y + bbox.height );
	}
}
