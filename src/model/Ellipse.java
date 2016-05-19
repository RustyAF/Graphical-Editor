package model;

import java.awt.Color;
import java.awt.Graphics2D;

import util.BoundBox;

public class Ellipse extends AbstractShape{

	public Ellipse(BoundBox bBox, Color color) {
		super(bBox, color);
	}
	
	@Override
	protected void doPaint(Graphics2D g) {
		g.setColor( getColor() );
		BoundBox bbox = getBoundBox().normalize();
		g.drawOval( bbox.x, bbox.y, bbox.width, bbox.height);	
	}
}
