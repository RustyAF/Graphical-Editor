package view;

import java.awt.Point;
import java.awt.event.MouseEvent;

import controller.App;
import util.BoundBox;
import model.tools.Tool;

public class SelectionTool extends Tool {
	
	private Point ptPressed;
	private Point ptReleased;
	private BoundBox selectionBox = new BoundBox();
	 
	@Override
	public void mousePressed( MouseEvent e ) {
		super.mousePressed( e );
		App.getInstance().deselectAll();
		selectionBox.x = e.getX();
		selectionBox.y = e.getY();
		selectionBox.setSize( 0, 0 );
	}
	
	@Override
	public void mouseDragged( MouseEvent e ) {
		super.mouseDragged( e );
		App.getInstance().drawSelectionBox( selectionBox );
		selectionBox.setSize(e.getX() - selectionBox.x, e.getY() - selectionBox.y);
		App.getInstance().drawSelectionBox( selectionBox );
	}
	
	@Override
	public void mouseReleased( MouseEvent e ) {
		super.mouseReleased( e );
		ptPressed = getPtPressed();
		ptReleased = getPtRelased();
		if ( ptPressed.equals( ptReleased ) ) {
			select( ptPressed );
		}
		else {
			select( new BoundBox( ptPressed, ptReleased ));
		}
	}
	
	private void select( Point pt ) {
		App.getInstance().select( pt );
	}
	
	private void select( BoundBox bBox ) {
		App.getInstance().select( bBox );
	}
}
