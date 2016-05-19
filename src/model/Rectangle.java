package model;

import java.awt.Color;
import java.awt.Graphics2D;

import util.BoundBox;

public class Rectangle extends AbstractShape {
	
	public Rectangle( BoundBox bbox, Color color ) {
		super( bbox, color );
	}

	@Override
	protected void doPaint(Graphics2D g) {
		g.setColor( getColor() );
		BoundBox bbox = getBoundBox().normalize();
		g.drawRect( bbox.x, bbox.y, bbox.width, bbox.height );
	}
}
