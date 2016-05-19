package util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;

import model.Shape;

public class BoundBox extends Rectangle implements Shape {

	private static final long serialVersionUID = 1L;
	private Color color = Color.LIGHT_GRAY;
	public static final BasicStroke DASHED = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{ 2.0f, 2.0f }, 0.0f );
	
	public BoundBox() {
		super();
	}
	
	public BoundBox( int x, int y, int width, int height ) {	
		super( x, y, width, height );
	}
	
	public BoundBox( Point pt1, Point pt2 ) {
		super( pt1.x, pt1.y, pt2.x-pt1.x, pt2.y - pt1.y );
	}
	
	public BoundBox( final Rectangle r ) {
		super( r );
	}
	
	public void copy( final Rectangle r ) {
		x = r.x;
		y = r.y;
		width = r.width;
		height = r.height;
	}

	@Override
	public void add( final Rectangle r ) {		
		if ( isEmpty() ) {
			copy( r );
		}
		else {
			super.add( r );
		}
	}
	
	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor( Color color ) {
		this.color = color;
	}

	@Override
	public void paint( Graphics2D g ) {
		Stroke stroke = g.getStroke();
		g.setStroke( DASHED );
		g.setColor( getColor() );
		BoundBox bBox = normalize();
		g.drawRect( bBox.x, bBox.y, bBox.width, bBox.height );
		g.setStroke( stroke );
	}
	
	public Boolean isNormalized() {
		return( width >= 0 && height >= 0 );
	}
	
	public BoundBox normalize(){
		int x_n = this.x;
		int y_n = this.y;
		int w_n = this.width;
		int h_n = this.height;	
		if ( this.height < 0 ){
			y_n = this.y + this.height;
			h_n = Math.abs(this.height);
		}
		if ( this.width < 0){
			x_n = this.x + this.width;
			w_n = Math.abs(this.width);
		}
		BoundBox value = new BoundBox(x_n, y_n, w_n, h_n);
		return value;
	}	
}
