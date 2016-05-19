package util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;

public class ControlPoint {
	
	private Cardinal cd;
	private Point pt;
	public static final Color COLOR = Color.LIGHT_GRAY;
	public static final int HSIZE = 3;
	
	public static enum Cardinal {
		N, NE, E, SE, S, SW, W, NW
	}
	
	public ControlPoint( final Cardinal cd) {
		super();
		this.cd = cd;
		this.pt = new Point();
	}
	
	public Point getPosition( final BoundBox bBox ) {
		assert bBox != null;
		assert bBox.isNormalized();
		switch ( cd ) {
		case N:
			pt.x = bBox.x + bBox.width / 2;
			pt.y = bBox.y;
			break;
		case NE:
			pt.x = bBox.x + bBox.width;
			pt.y = bBox.y;
			break;
		case E:
			pt.x = bBox.x + bBox.width;
			pt.y = bBox.y + bBox.height / 2;
			break;
		case SE:
			pt.x = bBox.x + bBox.width;
			pt.y = bBox.y + bBox.height;
			break;
		case S:
			pt.x = bBox.x + bBox.width / 2;
			pt.y = bBox.y + bBox.height;
			break;
		case SW:
			pt.x = bBox.x;
			pt.y = bBox.y + bBox.height;
			break;
		case W:
			pt.x = bBox.x;
			pt.y = bBox.y + bBox.height / 2;
			break;
		case NW:
			pt.x = bBox.x;
			pt.y = bBox.y;
			break;
		}
		return pt;
	}
	
	public void paint( final Graphics2D g, final BoundBox bBox )  {
		assert g != null;
		assert bBox != null;
		Point pt = getPosition( bBox );
		g.setColor( COLOR );
		g.fillRect( pt.x - HSIZE,  pt.y - HSIZE, 2 * HSIZE, 2 * HSIZE );
	}
	
	public Cursor getCursor() {
		Cursor cursor = null;
		switch ( cd ) {
			case N:
				cursor = Cursor.getPredefinedCursor( Cursor.N_RESIZE_CURSOR );
				break;
			case NE:
				cursor = Cursor.getPredefinedCursor( Cursor.NE_RESIZE_CURSOR );
				break;
			case E:
				cursor = Cursor.getPredefinedCursor( Cursor.E_RESIZE_CURSOR );
				break;
			case SE:
				cursor = Cursor.getPredefinedCursor( Cursor.SE_RESIZE_CURSOR );
				break;
			case S:
				cursor = Cursor.getPredefinedCursor( Cursor.S_RESIZE_CURSOR );
				break;
			case SW:
				cursor = Cursor.getPredefinedCursor( Cursor.SW_RESIZE_CURSOR );
				break;
			case W:
				cursor = Cursor.getPredefinedCursor( Cursor.W_RESIZE_CURSOR );
				break;
			case NW:
				cursor = Cursor.getPredefinedCursor( Cursor.NW_RESIZE_CURSOR );
				break;
		}
		return cursor;
	}
}
