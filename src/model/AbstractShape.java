package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Iterator;

import util.BoundBox;
import util.ControlPoint;
import util.ControlPoint.Cardinal;

public abstract class AbstractShape implements Shape {

	private BoundBox bbox;
	private Color color;
	private boolean selected;
	protected abstract void doPaint( Graphics2D g );

	public AbstractShape( final BoundBox boundBox, final Color color ) {
		this.bbox = boundBox;
		this.color = color;
	}

	protected BoundBox getBoundBox() {
		return bbox;
	}

	public void move( final int dx, final int dy ) {// TODO
		bbox.translate( dx, dy );
	}

	public void grow( final int cx, final int cy ) { //TODO
		bbox.grow( cx, cy );
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor( final Color color ) {
		this.color = color;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected( boolean selected ) {
		this.selected = selected;
	}
	
	protected Iterator<AbstractShape> iterator() {
		return new Iterator<AbstractShape>() {

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public AbstractShape next() {
				return null;
			}
		};
	}
	
	public boolean contains( Point pt ) {
		assert pt != null;
		BoundBox bBox = bbox.normalize();
		return bBox.contains( pt );
	}
	
	public boolean contained( BoundBox bBox ) {
		assert bBox != null;
		return bBox.contains( bbox.normalize() );
	}
	
	public ControlPoint ctrlPointBoundBox( BoundBox bBox) {
		assert bBox != null;
		assert bBox.isNormalized();
		ControlPoint cp = null;
		for ( int i = 0; i <= NUM_CTRLPOINTS; i++ ) {
			Point pt = ctrlPoints[ i ].getPosition( bBox.normalize() );
			if ( bBox.contains( pt ) ) {
				cp = ctrlPoints[ i ];
				break;
			}
		}
		return cp;
	}
	
	@Override
	public final void paint( final Graphics2D g ) {
		g.setColor( color );
		doPaint( g );
		if ( isSelected() ) {
			bbox.paint( g );
		}
	}
	// TODO move to BoundBox class 
		protected ControlPoint[] ctrlPoints;
	
	private static final int NUM_CTRLPOINTS = Cardinal.values().length;
}
