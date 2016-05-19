package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import util.BoundBox;

public class Group extends AbstractShape {
	
	private List<AbstractShape> children = new LinkedList<>();
	
	public Group( final List<AbstractShape> figures ) {
		super( new BoundBox(), Color.BLACK );
		assert figures != null : "Figure list must be non null";
		assert figures.size() > 1 : "Figure list must have at least 2 items";
		children = figures;
		BoundBox bbox = getBoundBox();
		for ( AbstractShape f : children ) {
			bbox.add( f.getBoundBox().normalize() );
		}
		setSelected( true );
	}

	@Override
	protected void doPaint( final Graphics2D g ) {
		if ( children != null ) {
			for ( AbstractShape f : children ) {
				f.doPaint( g );
			}
		}
	}
	
	@Override
	protected Iterator<AbstractShape> iterator() {	
		return new Iterator<AbstractShape>() {
			@Override
			public boolean hasNext() {
				return children.size() > 0;
			}
			@Override
			public AbstractShape next() {
				return children.remove( 0 );
			}
		};
	}
}
